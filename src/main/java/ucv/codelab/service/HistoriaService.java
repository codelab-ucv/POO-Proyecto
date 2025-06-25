package ucv.codelab.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import ucv.codelab.model.Diagnostico;
import ucv.codelab.model.ExamenFisico;
import ucv.codelab.model.HistoriaClinica;
import ucv.codelab.model.Tratamiento;
import ucv.codelab.repository.DiagnosticoRepository;
import ucv.codelab.repository.ExamenFisicoRepository;
import ucv.codelab.repository.HistoriaClinicaRepository;
import ucv.codelab.repository.TratamientoRepository;
import ucv.codelab.util.Datos;
import ucv.codelab.util.Mensajes;
import ucv.codelab.util.MySQLConexion;

public class HistoriaService {

    public static List<HistoriaClinica> buscarPorDni(String dniPaciente, String dniMedico) {
        // Limpieza de parámetros
        dniPaciente = Datos.limpiarString(dniPaciente);
        dniMedico = Datos.limpiarString(dniMedico);

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
        nombrePaciente = Datos.limpiarString(nombrePaciente);
        apellidoPaciente = Datos.limpiarString(apellidoPaciente);
        nombreMedico = Datos.limpiarString(nombreMedico);
        apellidoMedico = Datos.limpiarString(apellidoMedico);

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

    public static void descargarHistoria(HistoriaClinica historia) {
        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            DiagnosticoRepository diagnosticoRepository = new DiagnosticoRepository(conn);
            List<Diagnostico> diagnostico = diagnosticoRepository.buscarPorHistoria(historia.getIdHistoria());
            if (!diagnostico.isEmpty()) {
                historia.setDiagnostico(diagnostico);
            }

            ExamenFisicoRepository examenFisicoRepository = new ExamenFisicoRepository(conn);
            Optional<ExamenFisico> examenFisico = examenFisicoRepository.buscarPorHistoria(historia.getIdHistoria());
            if (examenFisico.isPresent()) {
                historia.setExamenFisico(examenFisico.get());
            }

            TratamientoRepository tratamientoRepository = new TratamientoRepository(conn);
            List<Tratamiento> tratamiento = tratamientoRepository.buscarPorHistoria(historia.getIdHistoria());
            if (!tratamiento.isEmpty()) {
                historia.setTratamiento(tratamiento);
            }
        } catch (SQLException e) {
            Mensajes.errorConexion();
        }
    }
}