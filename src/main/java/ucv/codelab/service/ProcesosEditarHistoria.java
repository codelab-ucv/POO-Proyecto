package ucv.codelab.service;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import ucv.codelab.enumerados.TipoDiagnostico;
import ucv.codelab.model.Diagnostico;
import ucv.codelab.model.ExamenFisico;
import ucv.codelab.model.HistoriaClinica;
import ucv.codelab.model.Tratamiento;
import ucv.codelab.repository.DiagnosticoRepository;
import ucv.codelab.repository.ExamenFisicoRepository;
import ucv.codelab.repository.HistoriaClinicaRepository;
import ucv.codelab.repository.MySQLConexion;
import ucv.codelab.repository.TratamientoRepository;
import ucv.codelab.util.ComprobarDatos;
import ucv.codelab.util.Mensajes;
import ucv.codelab.view.FrmMantenimientoHistoria;
import ucv.codelab.view.PanelBase;

public class ProcesosEditarHistoria {

    private static String[] titulo = { "ID HISTORIA", "FECHA Y HORA DE REGISTRO", "MOTIVO DE CONSULTA" };

    public static void presentacion(FrmMantenimientoHistoria view, List<HistoriaClinica> historias) {
        cargarDatos(view, historias);
        personalizarTabla(view);
        deshabilitarEdicion(view);
    }

    private static void cargarDatos(FrmMantenimientoHistoria view, List<HistoriaClinica> historias) {
        // Crea el modelo con la edicion deshabilitada
        DefaultTableModel dtm = new DefaultTableModel(null, titulo) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.tblResultados.setModel(dtm);

        for (HistoriaClinica historia : historias) {
            dtm.addRow(historia.registro());
        }
    }

    private static void personalizarTabla(FrmMantenimientoHistoria view) { // Personalizar cabecera
        JTableHeader header = view.tblResultados.getTableHeader();
        header.setBackground(Color.BLUE);
        header.setForeground(PanelBase.TEXT_COLOR);
        header.setFont(new Font("DialogInput", Font.BOLD, 12));

        // Personalizar contenido de la tabla
        view.tblResultados.setBackground(Color.WHITE);
        view.tblResultados.setForeground(PanelBase.TEXT_COLOR);
        view.tblResultados.setFont(new Font("Dialog", Font.PLAIN, 11));

        // Personalizar tamaños de columnas
        TableColumnModel columnModel = view.tblResultados.getColumnModel();

        // Ajustar ancho de columnas específicas
        columnModel.getColumn(0).setPreferredWidth(40); // CODIGO
        columnModel.getColumn(1).setPreferredWidth(80); // FECHA Y HORA
        columnModel.getColumn(2).setPreferredWidth(260); // MOTIVO DE CONSULTA

        // Personalizar altura de filas
        view.tblResultados.setRowHeight(25);

        // Hacer que las columnas no se puedan reordenar
        header.setReorderingAllowed(false);
    }

    public static void deshabilitarEdicion(FrmMantenimientoHistoria view) {
        // Examen Fisico
        view.txtTallaPaciente.setEnabled(false);
        view.txtPesoPaciente.setEnabled(false);
        view.txtPresionArterial.setEnabled(false);
        view.txtTemperaturaCorporal.setEnabled(false);
        view.txtFrecuenciaCardiaca.setEnabled(false);
        view.txtFrecuenciaRespiratoria.setEnabled(false);

        // Historia clinica
        view.txtMotivoConsulta.setEnabled(false);
        view.txtAntecedentesPaciente.setEnabled(false);
        view.txtTiempoEnfermedad.setEnabled(false);
        view.txtAreaObservaciones.setEnabled(false);

        // Diagnostico
        view.cmbTipoDiagnostico.setEnabled(false);
        view.txtCodigoCie10.setEnabled(false);
        view.txtAreaDescripcionDiagnostico.setEnabled(false);

        // Tratamiento
        view.txtAreaDescripcionTratamiento.setEnabled(false);
        view.txtAreaIndicaciones.setEnabled(false);

        // Guardar Cambios
        view.btnActualizar.setEnabled(false);
    }

    public static List<HistoriaClinica> historiasFiltradas(FrmMantenimientoHistoria view) {
        // Si el DNI no está vacio primero intenta buscarlo por DNI
        String dniFiltrado = ComprobarDatos.limpiarString(view.txtDniBusqueda.getText());
        if (dniFiltrado != null) {
            return HistoriaService.buscarPorDni(dniFiltrado, null);
        }

        // De lo contrario intenta buscar por lo menos por nombre o apellido
        String nombreFiltrado = ComprobarDatos.limpiarString(view.txtNombreBusqueda.getText());
        String apellidoFiltrado = ComprobarDatos.limpiarString(view.txtApellidoBusqueda.getText());
        if (nombreFiltrado != null || apellidoFiltrado != null) {
            return HistoriaService.buscarPorNombre(nombreFiltrado, apellidoFiltrado, null, null);
        }

        // De lo contrario devuelve una lista vacia
        Mensajes.error("Error de busqueda", "Debe especificarse por lo menos un filtro para buscar");
        return new ArrayList<>();
    }

    public static void borrarHistoria(FrmMantenimientoHistoria view) {
        String input = JOptionPane.showInputDialog(view, "Ingrese el ID de la historia clínica a eliminar");
        input = ComprobarDatos.limpiarString(input);

        // Si se cancela la eliminacion o esta vacio
        if (input == null) {
            return;
        }

        try {
            int idHistoria = Integer.valueOf(input);
            try (Connection conn = new MySQLConexion().getConexion()) {
                HistoriaClinicaRepository repo = new HistoriaClinicaRepository(conn);
                repo.desactivar(idHistoria);
            }
        } catch (NumberFormatException e) {
            Mensajes.error("Valor inválido", "Ingrese un número válido");
        } catch (SQLException e) {
            Mensajes.errorConexion();
        }
    }

    public static Optional<HistoriaClinica> seleccionarHistoria(FrmMantenimientoHistoria view) {
        String input = JOptionPane.showInputDialog(view, "Ingrese el ID de la historia clínica a editar");
        input = ComprobarDatos.limpiarString(input);

        // Si se cancela la eliminacion o esta vacio
        if (input == null) {
            return Optional.empty();
        }

        try (Connection conn = new MySQLConexion().getConexion()) {
            int idBuscado = Integer.parseInt(input);

            HistoriaClinicaRepository historiaClinicaRepository = new HistoriaClinicaRepository(conn);
            return historiaClinicaRepository.buscarPorId(idBuscado);
        } catch (NumberFormatException e) {
            Mensajes.error("Valor inválido", "Ingrese un número válido");
        } catch (SQLException e) {
            Mensajes.errorConexion();
        }

        return Optional.empty();
    }

    public static void cargarDatosEdicion(FrmMantenimientoHistoria view, HistoriaClinica historiaEnEdicion) {
        // Descarga toda la informacion de la historia
        HistoriaService.descargarHistoria(historiaEnEdicion);

        // Carga los datos en el formulario
        view.txtIdMedico.setText(historiaEnEdicion.getIdMedico() + "");
        view.txtIdPaciente.setText(historiaEnEdicion.getIdPaciente() + "");

        // Historia clinica
        view.txtMotivoConsulta.setText(historiaEnEdicion.getMotivoConsulta());
        view.txtAntecedentesPaciente.setText(historiaEnEdicion.getAntecedentes());
        view.txtTiempoEnfermedad.setText(historiaEnEdicion.getTiempoEnfermedad());
        view.txtAreaObservaciones.setText(historiaEnEdicion.getObservaciones());

        // Examen Fisico
        ExamenFisico examenFisico = historiaEnEdicion.getExamenFisico();
        if (examenFisico != null) {
            view.txtTallaPaciente.setText(examenFisico.getTalla() + "");
            view.txtPesoPaciente.setText(examenFisico.getPeso() + "");
            view.txtPresionArterial.setText(examenFisico.getPresionArterial() + "");
            view.txtTemperaturaCorporal.setText(examenFisico.getTemperatura() + "");
            view.txtFrecuenciaCardiaca.setText(examenFisico.getFrecuenciaCardiaca() + "");
            view.txtFrecuenciaRespiratoria.setText(examenFisico.getFrecuenciaRespiratoria() + "");
        }

        // Diagnostico
        List<Diagnostico> diagnosticos = historiaEnEdicion.getDiagnostico();
        if (diagnosticos != null && !diagnosticos.isEmpty()) {
            // Usa el ultimo diagnostico registrado (ordenado descendente)
            String tipoDiagnostico = diagnosticos.get(0).getTipo().toString();

            view.cmbTipoDiagnostico.setSelectedItem(tipoDiagnostico);
            view.txtCodigoCie10.setText(diagnosticos.get(0).getCodigoCIE10());
            view.txtAreaDescripcionDiagnostico.setText(diagnosticos.get(0).getDescripcion());
        }

        // Tratamiento
        List<Tratamiento> tratamientos = historiaEnEdicion.getTratamiento();
        if (tratamientos != null && !tratamientos.isEmpty()) {
            // Usa el ultimo tratamiento registrado (ordenado descendente)
            view.txtAreaDescripcionTratamiento.setText(tratamientos.get(0).getDescripcion());
            view.txtAreaIndicaciones.setText(tratamientos.get(0).getIndicaciones());
        }
    }

    public static void habilitarCamposEditables(FrmMantenimientoHistoria view) {
        // Examen Fisico
        view.txtTallaPaciente.setEnabled(true);
        view.txtPesoPaciente.setEnabled(true);
        view.txtPresionArterial.setEnabled(true);
        view.txtTemperaturaCorporal.setEnabled(true);
        view.txtFrecuenciaCardiaca.setEnabled(true);
        view.txtFrecuenciaRespiratoria.setEnabled(true);

        // Historia clinica
        view.txtMotivoConsulta.setEnabled(true);
        view.txtAntecedentesPaciente.setEnabled(true);
        view.txtTiempoEnfermedad.setEnabled(true);
        view.txtAreaObservaciones.setEnabled(true);

        // Diagnostico
        view.cmbTipoDiagnostico.setEnabled(true);
        view.txtCodigoCie10.setEnabled(true);
        view.txtAreaDescripcionDiagnostico.setEnabled(true);

        // Tratamiento
        view.txtAreaDescripcionTratamiento.setEnabled(true);
        view.txtAreaIndicaciones.setEnabled(true);

        // Guardar Cambios
        view.btnActualizar.setEnabled(true);
    }

    public static boolean actualizarHistoria(FrmMantenimientoHistoria view, HistoriaClinica historiaEnEdicion) {
        Connection conn = null;
        try {
            conn = new MySQLConexion().getConexion();
            conn.setAutoCommit(false);
            // Retorna true al actualizar todo correctamente
            if (upsertHistoriaClinica(view, historiaEnEdicion, conn)
                    && upsertExamenFisico(view, historiaEnEdicion, conn)
                    && upsertDiagnostico(view, historiaEnEdicion, conn)
                    && upsertTratamiento(view, historiaEnEdicion, conn)) {
                // Si todo esta bien confirma los cambios
                conn.commit();
                return true;
            }
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
        return false;
    }

    private static boolean upsertHistoriaClinica(FrmMantenimientoHistoria view, HistoriaClinica historiaEnEdicion,
            Connection conn) {
        HistoriaClinicaRepository historiaClinicaRepository = new HistoriaClinicaRepository(conn);
        // ========== Actualiza la Historia Clinica ========== //

        // Parametros obligatorios para historia clinica
        String motivoConsulta = ComprobarDatos.limpiarString(view.txtMotivoConsulta.getText());

        // Si algun campo obligatorio no esta lleno
        if (motivoConsulta == null) {
            return false;
        }

        String antecedentes = ComprobarDatos.limpiarString(view.txtAntecedentesPaciente.getText());
        String tiempoEnfermedad = ComprobarDatos.limpiarString(view.txtTiempoEnfermedad.getText());
        String observaciones = ComprobarDatos.limpiarString(view.txtAreaObservaciones.getText());

        historiaEnEdicion.setMotivoConsulta(motivoConsulta);
        historiaEnEdicion.setAntecedentes(antecedentes);
        historiaEnEdicion.setTiempoEnfermedad(tiempoEnfermedad);
        historiaEnEdicion.setObservaciones(observaciones);

        historiaClinicaRepository.actualizar(historiaEnEdicion);
        return true;
    }

    private static boolean upsertExamenFisico(FrmMantenimientoHistoria view, HistoriaClinica historiaEnEdicion,
            Connection conn) {
        ExamenFisicoRepository examenFisicoRepository = new ExamenFisicoRepository(conn);

        // Carga los nuevos datos
        ExamenFisico examenFisico = loadExamenFisico(view);
        examenFisico.setIdHistoria(historiaEnEdicion.getIdHistoria());

        // Si existe un examen fisico actualiza el objeto creado
        if (historiaEnEdicion.getExamenFisico() != null) {
            examenFisico.setIdExamen(historiaEnEdicion.getExamenFisico().getIdExamen());
        }
        // Actualiza el examen fisico
        historiaEnEdicion.setExamenFisico(examenFisico);
        // Si el id es menor que 1 es un caso nuevo, corresponde insert
        if (historiaEnEdicion.getExamenFisico().getIdExamen() < 1) {
            examenFisicoRepository.crear(examenFisico);
        }
        // De lo contrario actualiza
        else {
            examenFisicoRepository.actualizar(examenFisico);
        }
        return true;
    }

    private static ExamenFisico loadExamenFisico(FrmMantenimientoHistoria view) {
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

    private static boolean upsertDiagnostico(FrmMantenimientoHistoria view, HistoriaClinica historiaEnEdicion,
            Connection conn) {
        DiagnosticoRepository diagnosticoRepository = new DiagnosticoRepository(conn);

        // Carga el nuevo diagnostivo
        Diagnostico diagnosticoNuevo = loadDiagnostico(view);
        diagnosticoNuevo.setIdHistoria(historiaEnEdicion.getIdHistoria());

        // Si no hay diagnosticos previos inserta directamente
        if (historiaEnEdicion.getDiagnostico().size() < 1) {
            diagnosticoRepository.crear(diagnosticoNuevo);
            return true;
        }

        // En caso se tengan registros previos compara
        Diagnostico diagnosticoActual = historiaEnEdicion.getDiagnostico().get(0);

        // Si hay un cambio en el tipo de diagnostico o codigo inserta uno nuevo
        if (diagnosticoActual.getTipo() != diagnosticoNuevo.getTipo()
                || !diagnosticoActual.getCodigoCIE10().equals(diagnosticoNuevo.getCodigoCIE10())) {
            diagnosticoRepository.crear(diagnosticoNuevo);
        }
        // De lo contrario se trata del mismo, cambia el ID y actualiza
        else {
            diagnosticoNuevo.setIdDiagnostico(diagnosticoActual.getIdDiagnostico());
            diagnosticoRepository.actualizar(diagnosticoNuevo);
        }
        return true;
    }

    private static Diagnostico loadDiagnostico(FrmMantenimientoHistoria view) {
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

    private static boolean upsertTratamiento(FrmMantenimientoHistoria view, HistoriaClinica historiaEnEdicion,
            Connection conn) {
        TratamientoRepository tratamientoRepository = new TratamientoRepository(conn);

        // Carga el nuevo diagnostivo
        Tratamiento tratamientoNuevo = loadTratamiento(view);
        tratamientoNuevo.setIdHistoria(historiaEnEdicion.getIdHistoria());

        // Si no hay tratamientos previos inserta directamente
        if (historiaEnEdicion.getTratamiento().size() < 1) {
            tratamientoRepository.crear(tratamientoNuevo);
            return true;
        }

        // En caso no se tengan registros previos compara
        Tratamiento tratamientoActual = historiaEnEdicion.getTratamiento().get(0);

        // Si hay un cambio en el tipo de diagnostico o codigo inserta uno nuevo
        if (!tratamientoActual.getDescripcion().equals(tratamientoNuevo.getDescripcion())
                || !tratamientoActual.getIndicaciones().equals(tratamientoNuevo.getIndicaciones())) {
            tratamientoRepository.crear(tratamientoNuevo);
        }
        // De lo contrario se trata del mismo, cambia el ID y actualiza
        else {
            tratamientoNuevo.setIdTratamiento(tratamientoActual.getIdTratamiento());
            tratamientoRepository.actualizar(tratamientoNuevo);
        }

        return true;
    }

    private static Tratamiento loadTratamiento(FrmMantenimientoHistoria view) {
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
}