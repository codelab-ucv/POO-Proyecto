package ucv.codelab.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import ucv.codelab.model.Medico;
import ucv.codelab.model.Persona;
import ucv.codelab.repository.MedicoRepository;
import ucv.codelab.repository.PersonaRepository;
import ucv.codelab.util.Mensajes;
import ucv.codelab.util.MySQLConexion;

public class MedicoService {

    // Busca un medico por su DNI, si no se encuentra retorna null
    public static Medico buscar(String dni) {
        // Quita los espacios y si se vuelve vacio lo cambia a null
        if (dni != null) {
            dni = dni.trim();
            if (dni.isEmpty())
                dni = null;
        }
        if (dni == null) {
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

            MedicoRepository medicoRepo = new MedicoRepository(conn);
            Optional<Medico> m = medicoRepo.buscarPorPersona(p.get().getIdPersona());
            if (m.isEmpty()) {
                return null;
            }
            Medico medico = m.get();
            medico.modificarDatosPersona(p.get());
            return medico;
        } catch (SQLException e) {
            Mensajes.errorConexion();
            return null;
        }
    }

    // Requiere que los datos de la Persona dentro de medico sean válidos
    public static boolean upsert(Medico medico) {
        // Si no se cuenta con los datos de Persona
        if (!medico.datosValidos()) {
            return false;
        }
        Connection conn = null;
        try {
            // Desactiva los cambios automaticos en caso ocurra algun error
            conn = MySQLConexion.getInstance().getConexion();
            conn.setAutoCommit(false);

            // Inicializa los repositorios
            PersonaRepository personaRepo = new PersonaRepository(conn);
            MedicoRepository medicoRepo = new MedicoRepository(conn);

            // Hace upsert con los datos de la persona
            upsertPersona(medico, personaRepo);

            // Hace upsert con los datos del medico
            Optional<Medico> medicoExiste = medicoRepo.buscarPorPersona(medico.getIdPersona());
            // Si ya esta registrado como medico
            if (medicoExiste.isPresent()) {
                // Actualiza el id del medico con el registrado en la bdd
                medico.setId(medicoExiste.get().getId());
                // Se hace Update
                medicoRepo.actualizar(medico);
            } else {
                // Hace Insert
                medicoRepo.crear(medico);
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

    private static void upsertPersona(Medico medico, PersonaRepository personaRepo) {
        Optional<Persona> personaExiste = personaRepo.buscarPorDni(medico.getDni());
        // Si ya existe el dni
        if (personaExiste.isPresent()) {
            // Actualiza el idPersona con el registrado en la bdd
            Persona persona = personaExiste.get();
            medico.setIdPersona(persona.getIdPersona());

            // Si hubo algun cambio actualiza la bdd
            if (!medico.getPersona().equals(persona)) {
                personaRepo.actualizar(medico.getPersona());
            }
        } else {
            // Hace insert de la persona
            personaRepo.crear(medico.getPersona());
        }
    }
}
