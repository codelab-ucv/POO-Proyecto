package ucv.codelab.service;

import java.sql.SQLException;
import java.util.Optional;

import ucv.codelab.model.Paciente;
import ucv.codelab.model.Persona;
import ucv.codelab.repository.PacienteRepository;
import ucv.codelab.repository.PersonaRepository;
import ucv.codelab.util.Mensajes;

public class PacienteService {

    // Busca un Paciente por su DNI, si no se encuentra retorna null
    public static Paciente buscarPaciente(String dni) {
        try {
            PersonaRepository personaRepo = new PersonaRepository();
            Optional<Persona> prsn = personaRepo.buscarPorDni(dni);
            if (prsn.isEmpty()) {
                return null;
            }

            PacienteRepository pacienteRepo = new PacienteRepository();
            Optional<Paciente> pcnt = pacienteRepo.buscarPorPersona(prsn.get().getId());
            if (pcnt.isEmpty()) {
                return null;
            }
            Paciente paciente = pcnt.get();
            paciente.setPersona(prsn.get());
            return paciente;
        } catch (SQLException e) {
            Mensajes.errorConexion();
            return null;
        }
    }

    // Requiere que la Persona dentro de paciente sea válida
    public static boolean upsertPaciente(Paciente paciente) {
        // Si no se creó el objeto de la persona retorna
        if (paciente.getPersona() == null) {
            return false;
        }
        try {
            PersonaRepository personaRepo = new PersonaRepository();
            PacienteRepository pacienteRepo = new PacienteRepository();

            Optional<Persona> personaExiste = personaRepo.buscarPorDni(paciente.getPersona().getDni());
            // Si ya existe el dni
            if (personaExiste.isPresent()) {
                // Actualiza el id de la persona con el registrado en la bdd
                Persona persona = personaExiste.get();
                paciente.getPersona().setId(persona.getId());
                paciente.setIdPersona(persona.getId());

                // Si hubo algun cambio actualiza la bdd
                if (!paciente.getPersona().equals(persona)) {
                    personaRepo.actualizar(paciente.getPersona());
                }
            } else {
                // Hace insert de la persona
                personaRepo.crear(paciente.getPersona());
                paciente.setIdPersona(paciente.getPersona().getId());
            }

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
            return true;
        } catch (SQLException e) {
            Mensajes.errorConexion();
            return false;
        }
    }
}
