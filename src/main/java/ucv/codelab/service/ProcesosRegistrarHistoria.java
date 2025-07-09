package ucv.codelab.service;

import java.sql.Connection;
import java.util.Optional;

import ucv.codelab.model.HistoriaClinica;
import ucv.codelab.model.Medico;
import ucv.codelab.model.Paciente;
import ucv.codelab.repository.HistoriaClinicaRepository;
import ucv.codelab.repository.MedicoRepository;
import ucv.codelab.repository.MySQLConexion;
import ucv.codelab.repository.PacienteRepository;
import ucv.codelab.util.ComprobarDatos;
import ucv.codelab.util.Mensajes;
import ucv.codelab.view.FrmRegistroHistoriaC;

public class ProcesosRegistrarHistoria {

    public static Optional<Medico> buscarMedico(FrmRegistroHistoriaC view) {
        try (Connection conn = new MySQLConexion().getConexion()) {
            MedicoRepository repo = new MedicoRepository(conn);
            return repo.buscarPorDni(view.txtDniMedico.getText());
        } catch (Exception e) {
        }
        return Optional.empty();
    }

    public static Optional<Paciente> buscarPaciente(FrmRegistroHistoriaC view) {
        try (Connection conn = new MySQLConexion().getConexion()) {
            PacienteRepository repo = new PacienteRepository(conn);
            return repo.buscarPorDni(view.txtDniPaciente.getText());
        } catch (Exception e) {
        }
        return Optional.empty();
    }

    public static Optional<HistoriaClinica> validarDatos(FrmRegistroHistoriaC view, Paciente paciente, Medico medico) {
        // Verifica primero los ComboBox
        if (view.cmbTipoDiagnostico.getSelectedItem() == null) {
            return Optional.empty();
        }

        // Parametros obligatorios para historia clinica
        int idPaciente = paciente.getIdPaciente();
        int idMedico = medico.getIdMedico();
        String motivoConsulta = ComprobarDatos.limpiarString(view.txtMotivoConsulta.getText());

        // Si algun campo obligatorio no esta lleno
        if (motivoConsulta == null) {
            return Optional.empty();
        }
        return Optional.of(new HistoriaClinica(idPaciente, idMedico, motivoConsulta, true));
    }

    public static boolean guardarHistoria(FrmRegistroHistoriaC view, HistoriaClinica historiaClinica) {
        String antecedentes = ComprobarDatos.limpiarString(view.txtAntecedentesPaciente.getText());
        String tiempoEnfermedad = ComprobarDatos.limpiarString(view.txtTiempoEnfermedad.getText());
        String observaciones = ComprobarDatos.limpiarString(view.txtAreaObservaciones.getText());

        historiaClinica.setAntecedentes(antecedentes);
        historiaClinica.setTiempoEnfermedad(tiempoEnfermedad);
        historiaClinica.setObservaciones(observaciones);

        // TODO pendiente cargar los datos de examen fisico, diagnostico y tratamiento

        try (Connection conn = new MySQLConexion().getConexion()) {
            HistoriaClinicaRepository repo = new HistoriaClinicaRepository(conn);
            repo.crear(historiaClinica);
            return true;
        } catch (Exception e) {
            Mensajes.error("Error al guardar", "Verifique los datos ingresados");
            return false;
        }
    }

    public static void limpiarEntradas(FrmRegistroHistoriaC view) {
        view.txtDniMedico.setText("");
        view.txtNombresMedico.setText("");
        view.txtApellidosMedico.setText("");

        view.txtDniPaciente.setText("");
        view.txtNombresPaciente.setText("");
        view.txtApellidosPaciente.setText("");

        view.txtTallaPaciente.setText("");
        view.txtPesoPaciente.setText("");
        view.txtPresionArterial.setText("");
        view.txtTemperaturaCorporal.setText("");
        view.txtFrecuenciaCardiaca.setText("");
        view.txtFrecuenciaRespiratoria.setText("");

        view.txtMotivoConsulta.setText("");
        view.txtAntecedentesPaciente.setText("");
        view.txtTiempoEnfermedad.setText("");
        view.txtAreaObservaciones.setText("");

        view.cmbTipoDiagnostico.setSelectedItem(null);
        view.txtCodigoCie10.setText("");
        view.txtAreaDescripcionDiagnostico.setText("");

        view.txtAreaDescripcionTratamiento.setText("");
        view.txtAreaIndicaciones.setText("");

        view.txtDniMedico.requestFocus();
    }
}
