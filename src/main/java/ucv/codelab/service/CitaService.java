package ucv.codelab.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ucv.codelab.model.Cita;
import ucv.codelab.repository.CitaRepository;
import ucv.codelab.util.Mensajes;
import ucv.codelab.util.MySQLConexion;

public class CitaService {

    public static List<Cita> buscar(String dniPaciente, String dniMedico) {
        // Quita los espacios y si se vuelve vacio lo cambia a null
        if (dniPaciente != null) {
            dniPaciente = dniPaciente.trim();
            if (dniPaciente.isEmpty())
                dniPaciente = null;
        }
        if (dniMedico != null) {
            dniMedico = dniMedico.trim();
            if (dniMedico.isEmpty())
                dniMedico = null;
        }
        // Si no está ninguno retorna null
        if (dniPaciente == null && dniMedico == null) {
            return null;
        }
        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            CitaRepository citaRepo = new CitaRepository(conn);
            return citaRepo.buscarCitaPorPacienteMedico(dniPaciente, dniMedico);
        } catch (SQLException e) {

            Mensajes.errorConexion();
            return null;
        }
    }
}
