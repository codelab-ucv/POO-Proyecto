package ucv.codelab.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ucv.codelab.model.HistoriaClinica;
import ucv.codelab.repository.HistoriaClinicaRepository;
import ucv.codelab.util.Mensajes;
import ucv.codelab.util.MySQLConexion;

public class HistorialService {

    public static List<HistoriaClinica> buscarPorDni(String dniPaciente, String dniMedico) {
        // Limpieza de parámetros
        dniPaciente = limpiarString(dniPaciente);
        dniMedico = limpiarString(dniMedico);

        if (dniPaciente == null && dniMedico == null) {
            return null;
        }

        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            HistoriaClinicaRepository repo = new HistoriaClinicaRepository(conn);
            return repo.buscarPorDni(dniPaciente, dniMedico);
        } catch (SQLException e) {
            Mensajes.errorConexion();
            return null;
        }
    }

    public static List<HistoriaClinica> buscarPorNombre(String nombrePaciente, String apellidoPaciente,
            String nombreMedico, String apellidoMedico) {
        // Limpieza de parámetros
        nombrePaciente = limpiarString(nombrePaciente);
        apellidoPaciente = limpiarString(apellidoPaciente);
        nombreMedico = limpiarString(nombreMedico);
        apellidoMedico = limpiarString(apellidoMedico);

        if (nombrePaciente == null && apellidoPaciente == null &&
                nombreMedico == null && apellidoMedico == null) {
            return null;
        }

        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            HistoriaClinicaRepository repo = new HistoriaClinicaRepository(conn);
            return repo.buscarPorNombre(nombrePaciente, apellidoPaciente, nombreMedico, apellidoMedico);
        } catch (SQLException e) {
            Mensajes.errorConexion();
            return null;
        }
    }

    private static String limpiarString(String parametro) {
        if (parametro != null) {
            parametro = parametro.trim();
            if (parametro.isEmpty()) {
                return null;
            }
        }
        return parametro;
    }
}