package ucv.codelab.service;

import java.sql.SQLException;
import java.util.Optional;

import ucv.codelab.model.Medico;
import ucv.codelab.model.Persona;
import ucv.codelab.repository.MedicoRepository;
import ucv.codelab.repository.PersonaRepository;
import ucv.codelab.util.Mensajes;

public class MedicoService {
    
    // Busca un medico por su DNI, si no se encuentra retorna null
    public static Medico buscarMedico(String dni) {
        try {
            PersonaRepository personaRepo = new PersonaRepository();
            Optional<Persona> p = personaRepo.buscarPorDni(dni);
            if (p.isEmpty()) {
                return null;
            }

            MedicoRepository medicoRepo = new MedicoRepository();
            Optional<Medico> m = medicoRepo.buscarPorPersona(p.get().getId());
            if (m.isEmpty()) {
                return null;
            }
            Medico medico = m.get();
            medico.setPersona(p.get());
            return medico;
        } catch (SQLException e) {
            Mensajes.errorConexion();
            return null;
        }
    }

    // Requiere que la Persona dentro de medico sea válida
    public static boolean upsertMedico(Medico medico) {
        // Si no se creó el objeto de la persona retorna
        if (medico.getPersona() == null) {
            return false;
        }
        try {
            PersonaRepository personaRepo = new PersonaRepository();
            MedicoRepository medicoRepo = new MedicoRepository();

            Optional<Persona> personaExiste = personaRepo.buscarPorDni(medico.getPersona().getDni());
            // Si ya existe el dni
            if (personaExiste.isPresent()) {
                // Actualiza el id de la persona con el registrado en la bdd
                Persona persona = personaExiste.get();
                medico.getPersona().setId(persona.getId());
                medico.setIdPersona(persona.getId());

                // Si hubo algun cambio actualiza la bdd
                if (!medico.getPersona().equals(persona)) {
                    personaRepo.actualizar(medico.getPersona());
                }
            } else {
                // Hace insert de la persona
                personaRepo.crear(medico.getPersona());
                medico.setIdPersona(medico.getPersona().getId());
            }

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
            return true;
        } catch (SQLException e) {
            Mensajes.errorConexion();
            return false;
        }
    }
}
