package ucv.codelab.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import ucv.codelab.model.Paciente;
import ucv.codelab.model.Persona;
import ucv.codelab.repository.PacienteRepository;
import ucv.codelab.repository.PersonaRepository;
import ucv.codelab.util.Mensajes;
import ucv.codelab.util.MySQLConexion;

public class PacienteService {

    // Busca un paciente por su DNI, si no se encuentra retorna null
    public static Paciente buscarPaciente(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            return null;
        }
        Connection conn;
        try {
            conn = MySQLConexion.getInstance().getConexion();

            PersonaRepository personaRepo = new PersonaRepository(conn);
            Optional<Persona> p = personaRepo.buscarPorDni(dni);
            if (p.isEmpty()) {
                return null;
            }

            PacienteRepository pacienteRepo = new PacienteRepository(conn);
            Optional<Paciente> m = pacienteRepo.buscarPorPersona(p.get().getIdPersona());
            if (m.isEmpty()) {
                return null;
            }
            Paciente paciente = m.get();
            paciente.modificarDatosPersona(p.get());
            return paciente;
        } catch (SQLException e) {
            Mensajes.errorConexion();
            return null;
        }
    }

    // Requiere que los datos de la Persona dentro de paciente sean válidos
    public static boolean upsertPaciente(Paciente paciente) {
        // Si no se cuenta con los datos de Persona
        if (!paciente.datosPersonaValidos()) {
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

            // Hace upsert con los datos de la persona
            upsertPersona(paciente, personaRepo);

            // Hace upsert con los datos del paciente
            Optional<Paciente> pacienteExiste = pacienteRepo.buscarPorPersona(paciente.getIdPersona());
            // Si ya esta registrado como paciente
            if (pacienteExiste.isPresent()) {
                // Actualiza el id del paciente con el registrado en la bdd
                paciente.setId(pacienteExiste.get().getId());
                // Se hace Update
                pacienteRepo.actualizar(paciente);
            } else {
                // Hace Insert
                pacienteRepo.crear(paciente);
            }

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
}
