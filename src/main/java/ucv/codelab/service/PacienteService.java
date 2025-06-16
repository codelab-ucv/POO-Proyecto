package ucv.codelab.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ucv.codelab.model.Condicion;
import ucv.codelab.model.Paciente;
import ucv.codelab.model.Persona;
import ucv.codelab.repository.CondicionRepository;
import ucv.codelab.repository.PacienteRepository;
import ucv.codelab.repository.PersonaRepository;
import ucv.codelab.util.Mensajes;
import ucv.codelab.util.MySQLConexion;

public class PacienteService {

    // Busca un paciente por su DNI, si no se encuentra retorna null
    public static Paciente buscar(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            return null;
        }
        Connection conn;
        try {
            conn = MySQLConexion.getInstance().getConexion();

            PersonaRepository personaRepo = new PersonaRepository(conn);
            Optional<Persona> personaExistente = personaRepo.buscarPorDni(dni);
            if (personaExistente.isEmpty()) {
                return null;
            }

            PacienteRepository pacienteRepo = new PacienteRepository(conn);
            Optional<Paciente> pacienteExistente = pacienteRepo.buscarPorPersona(personaExistente.get().getIdPersona());
            if (pacienteExistente.isEmpty()) {
                return null;
            }
            // Actualiza el paciente y los datos de la persona
            Paciente paciente = pacienteExistente.get();
            paciente.modificarDatosPersona(personaExistente.get());

            CondicionRepository condicionRepo = new CondicionRepository(conn);
            List<Condicion> condiciones = condicionRepo.buscarPorPaciente(paciente.getId());

            // Actualiza las condiciones del paciente
            paciente.setCondiciones(condiciones);
            return paciente;
        } catch (SQLException e) {
            Mensajes.errorConexion();
            return null;
        }
    }

    // Requiere que los datos de la Persona dentro de paciente sean válidos
    public static boolean upsert(Paciente paciente) {
        // Si no se cuenta con los datos de Persona
        if (!paciente.datosValidos()) {
            return false;
        }
        Connection conn = null;
        try {
            // Desactiva los cambios automaticos en caso ocurra algun error
            conn = MySQLConexion.getInstance().getConexion();
            conn.setAutoCommit(false);

            // Inicializa los repositorios
            PersonaRepository personaRepo = new PersonaRepository(conn);
            PacienteRepository pacienteRepo = new PacienteRepository(conn);
            CondicionRepository condicionRepo = new CondicionRepository(conn);

            // Hace upsert con los datos de la persona
            upsertPersona(paciente, personaRepo);

            // Hace upsert con los datos del paciente
            Optional<Paciente> pacienteExiste = pacienteRepo.buscarPorPersona(paciente.getIdPersona());
            // Si ya esta registrado como paciente
            if (pacienteExiste.isPresent()) {
                // Actualiza el id del paciente con el registrado en la bdd
                paciente.setId(pacienteExiste.get().getId());
                // Se hace Update en la tabla paciente
                pacienteRepo.actualizar(paciente);
            } else {
                // Hace Insert
                pacienteRepo.crear(paciente);
            }

            // Actualiza todos los datos de las condiciones
            validarCondiciones(paciente, condicionRepo);

            // Aplica los cambios y rehabilita el autoCommit
            conn.commit();
            conn.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    // Restaurar estado
                    conn.rollback();
                    conn.setAutoCommit(true);
                } catch (SQLException rollbackEx) {
                }
            }
            Mensajes.errorConexion();
            return false;
        }
    }

    private static void upsertPersona(Paciente paciente, PersonaRepository personaRepo) {
        Optional<Persona> personaExiste = personaRepo.buscarPorDni(paciente.getDni());
        // Si ya existe el dni
        if (personaExiste.isPresent()) {
            // Actualiza el idPersona con el registrado en la bdd
            Persona persona = personaExiste.get();
            paciente.setIdPersona(persona.getIdPersona());

            // Si hubo algun cambio actualiza la bdd
            if (!paciente.getPersona().equals(persona)) {
                personaRepo.actualizar(paciente.getPersona());
            }
        } else {
            // Hace insert de la persona
            personaRepo.crear(paciente.getPersona());
        }
    }

    private static void validarCondiciones(Paciente paciente, CondicionRepository condicionRepo) throws SQLException {
        // Obtener las condiciones actuales del paciente en la base de datos
        List<Integer> condicionesActualesIds = condicionRepo.obtenerIdsCondicionesPorPaciente(paciente.getId());

        // Lista para almacenar los IDs de las condiciones que deben estar asociadas
        List<Integer> condicionesDeseadasIds = new ArrayList<>();

        // Procesar cada condición del paciente (solo inserts, no updates)
        List<Condicion> condicionesLocal = paciente.getCondiciones();
        for (Condicion condicion : condicionesLocal) {
            int idCondicion;

            if (condicion.getId() == -1 || condicionRepo.buscarPorId(condicion.getId()).isEmpty()) {
                // La condición no tiene ID, verificar si ya existe en la base de datos
                Optional<Condicion> condicionExistente = condicionRepo.buscarPorCondicion(
                        condicion.getTipo(),
                        condicion.getCondicion(),
                        condicion.getGravedad());

                if (condicionExistente.isPresent()) {
                    // La condición ya existe, usar su ID
                    idCondicion = condicionExistente.get().getId();
                    // Actualiza el objeto local
                    condicion.setId(idCondicion);
                } else {
                    // La condición no existe, crearla
                    condicionRepo.crear(condicion);
                    // Usa el ID asignado automáticamente en crear()
                    idCondicion = condicion.getId();
                }
            } else {
                // La condición ya tiene ID, simplemente usarlo
                idCondicion = condicion.getId();
            }

            condicionesDeseadasIds.add(idCondicion);
        }

        // Sincronizar las asociaciones en paciente_condicion
        sincronizarAsociacionesCondiciones(paciente.getId(), condicionesActualesIds, condicionesDeseadasIds,
                condicionRepo);
    }

    /**
     * Sincroniza las asociaciones entre paciente y condiciones
     */
    private static void sincronizarAsociacionesCondiciones(int idPaciente,
            List<Integer> condicionesActuales,
            List<Integer> condicionesDeseadas,
            CondicionRepository condicionRepo) throws SQLException {

        // Condiciones a agregar (están en deseadas pero no en actuales)
        List<Integer> condicionesParaAgregar = new ArrayList<>(condicionesDeseadas);
        condicionesParaAgregar.removeAll(condicionesActuales);

        // Condiciones a eliminar (están en actuales pero no en deseadas)
        List<Integer> condicionesParaEliminar = new ArrayList<>(condicionesActuales);
        condicionesParaEliminar.removeAll(condicionesDeseadas);

        // Agregar nuevas asociaciones
        for (Integer idCondicion : condicionesParaAgregar) {
            condicionRepo.asociarCondicionConPaciente(idPaciente, idCondicion);
        }

        // Eliminar asociaciones que ya no deben existir
        for (Integer idCondicion : condicionesParaEliminar) {
            condicionRepo.desasociarCondicionDePaciente(idPaciente, idCondicion);
        }
    }
}