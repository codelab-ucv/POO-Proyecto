package ucv.codelab.service;

import java.sql.Connection;
import java.util.Optional;

import ucv.codelab.enumerados.TipoDiagnostico;
import ucv.codelab.model.Diagnostico;
import ucv.codelab.model.ExamenFisico;
import ucv.codelab.model.HistoriaClinica;
import ucv.codelab.model.Medico;
import ucv.codelab.model.Paciente;
import ucv.codelab.model.Tratamiento;
import ucv.codelab.repository.DiagnosticoRepository;
import ucv.codelab.repository.ExamenFisicoRepository;
import ucv.codelab.repository.HistoriaClinicaRepository;
import ucv.codelab.repository.MedicoRepository;
import ucv.codelab.repository.MySQLConexion;
import ucv.codelab.repository.PacienteRepository;
import ucv.codelab.repository.TratamientoRepository;
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

        try (Connection conn = new MySQLConexion().getConexion()) {
            HistoriaClinicaRepository repo = new HistoriaClinicaRepository(conn);
            repo.crear(historiaClinica);

            ExamenFisico examenFisico = loadExamenFisico(view);
            if (examenFisico != null) {
                // Establece el ID de la historia en el obtenido al insertar
                examenFisico.setIdHistoria(historiaClinica.getIdHistoria());
                ExamenFisicoRepository examenFisicoRepository = new ExamenFisicoRepository(conn);
                examenFisicoRepository.crear(examenFisico);
            }

            Diagnostico diagnostico = loadDiagnostico(view);
            if (diagnostico != null) {
                // Establece el ID de la historia en el obtenido al insertar
                diagnostico.setIdHistoria(historiaClinica.getIdHistoria());
                DiagnosticoRepository diagnosticoRepository = new DiagnosticoRepository(conn);
                diagnosticoRepository.crear(diagnostico);
            }

            Tratamiento tratamiento = loadTratamiento(view);
            if (tratamiento != null) {
                // Establece el ID de la historia en el obtenido al insertar
                tratamiento.setIdHistoria(historiaClinica.getIdHistoria());
                TratamientoRepository tratamientoRepository = new TratamientoRepository(conn);
                tratamientoRepository.crear(tratamiento);
            }

            return true;
        } catch (Exception e) {
            Mensajes.error("Error al guardar", "Verifique los datos ingresados");
            return false;
        }
    }

    private static ExamenFisico loadExamenFisico(FrmRegistroHistoriaC view) {
        Double peso = ComprobarDatos.validarDecimal(view.txtPesoPaciente.getText());
        Double talla = ComprobarDatos.validarDecimal(view.txtTallaPaciente.getText());
        String presionArterial = ComprobarDatos.limpiarString(view.txtPresionArterial.getText());
        Double temperatura = ComprobarDatos.validarDecimal(view.txtTemperaturaCorporal.getText());
        Integer frecuenciaCardiaca = ComprobarDatos.validarEntero(view.txtFrecuenciaCardiaca.getText());
        Integer frecuenciaRespiratoria = ComprobarDatos.validarEntero(view.txtFrecuenciaRespiratoria.getText());

        // Si por lo menos uno de los datos existe retorna el objeto
        if (peso != null || talla != null || presionArterial != null || temperatura != null
                || frecuenciaCardiaca != null || frecuenciaRespiratoria != null) {
            ExamenFisico examenFisico = new ExamenFisico();
            examenFisico.setPeso(peso);
            examenFisico.setTalla(talla);
            examenFisico.setPresionArterial(presionArterial);
            examenFisico.setTemperatura(temperatura);
            examenFisico.setFrecuenciaCardiaca(frecuenciaCardiaca);
            examenFisico.setFrecuenciaRespiratoria(frecuenciaRespiratoria);
            return examenFisico;
        }
        return null;
    }

    private static Diagnostico loadDiagnostico(FrmRegistroHistoriaC view) {
        // Verifica primero los ComboBox
        if (view.cmbTipoDiagnostico.getSelectedItem() == null) {
            return null;
        }

        String tipoDiagnostico = ComprobarDatos.limpiarString(view.cmbTipoDiagnostico.getSelectedItem().toString());
        String descripcion = ComprobarDatos.limpiarString(view.txtAreaDescripcionDiagnostico.getText());
        String codigoCie = ComprobarDatos.limpiarString(view.txtCodigoCie10.getText());

        // Si el tipo de diagnostico no es nulo ni el codigo CIE10 retorna el objeto
        if (tipoDiagnostico != null && codigoCie != null) {
            Diagnostico diagnostico = new Diagnostico();
            diagnostico.setTipo(TipoDiagnostico.fromString(tipoDiagnostico));
            diagnostico.setCodigoCIE10(codigoCie);
            diagnostico.setDescripcion(descripcion);
            return diagnostico;
        }
        return null;
    }

    private static Tratamiento loadTratamiento(FrmRegistroHistoriaC view) {
        String descripcion = ComprobarDatos.limpiarString(view.txtAreaDescripcionTratamiento.getText());
        String indicaciones = ComprobarDatos.limpiarString(view.txtAreaIndicaciones.getText());

        // Si por lo menos uno de los datos existe retorna el objeto
        if (descripcion != null || indicaciones != null) {
            Tratamiento tratamiento = new Tratamiento();
            tratamiento.setDescripcion(descripcion);
            tratamiento.setIndicaciones(indicaciones);
            return tratamiento;
        }
        return null;
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
