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

import ucv.codelab.model.Especialidad;
import ucv.codelab.repository.EspecialidadRepository;
import ucv.codelab.repository.MySQLConexion;
import ucv.codelab.util.ComprobarDatos;
import ucv.codelab.util.Mensajes;
import ucv.codelab.view.FrmMantenimientoEspecialidad;
import ucv.codelab.view.PanelBase;

public class ProcesosEditarEspecialidad {

    private static String[] titulo = { "CODIGO", "ESPECIALIDAD", "COSTO CONSULTA", "DESCRIPCION",
            "CONSULTORIOS ASIGNADOS", "REQUISITOS ESPECIALES" };

    public static void presentacion(FrmMantenimientoEspecialidad view, List<Especialidad> especialidades) {
        cargarDatos(view, especialidades);
        personalizarTabla(view);
        deshabilitarEdicion(view);
    }

    private static void cargarDatos(FrmMantenimientoEspecialidad view, List<Especialidad> especialidades) {
        // Crea el modelo con la edicion deshabilitada
        DefaultTableModel dtm = new DefaultTableModel(null, titulo) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.tblResultados.setModel(dtm);

        for (Especialidad especialidad : especialidades) {
            dtm.addRow(especialidad.registro());
        }
    }

    private static void personalizarTabla(FrmMantenimientoEspecialidad view) {
        // Personalizar cabecera
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
        columnModel.getColumn(1).setPreferredWidth(80); // ESPECIALIDAD
        columnModel.getColumn(2).setPreferredWidth(50); // COSTO CONSULTA
        columnModel.getColumn(3).setPreferredWidth(200); // DESCRIPCION
        columnModel.getColumn(4).setPreferredWidth(80); // CONSULTORIOS ASIGNADOS
        columnModel.getColumn(5).setPreferredWidth(100); // REQUISITOS ESPECIALES

        // Personalizar altura de filas
        view.tblResultados.setRowHeight(25);

        // Hacer que las columnas no se puedan reordenar
        header.setReorderingAllowed(false);
    }

    public static void deshabilitarEdicion(FrmMantenimientoEspecialidad view) {
        view.txtEditarCodigo.setEnabled(false);
        view.txtEditarEspecialidad.setEnabled(false);
        view.txtEditarCostoConsulta.setEnabled(false);
        view.txtEditarDescripcion.setEnabled(false);
        view.txtEditarConsultoriosAginados.setEnabled(false);
        view.txtEditarRequisitosEspeciales.setEnabled(false);
        view.btnActualizar.setEnabled(false);
    }

    public static List<Especialidad> especialidadesActivas() {
        // Descarga los datos
        try (Connection conn = new MySQLConexion().getConexion()) {
            EspecialidadRepository especialidadRepository = new EspecialidadRepository(conn);
            return especialidadRepository.buscarTodos();
        } catch (Exception e) {
            Mensajes.errorConexion();
            return new ArrayList<>();
        }
    }

    public static List<Especialidad> especialidadesFiltradas(FrmMantenimientoEspecialidad view) {
        String nombreEspecialidad = ComprobarDatos.limpiarString(view.txtEspecialidad.getText());

        if (nombreEspecialidad == null) {
            return especialidadesActivas();
        }

        try (Connection conn = new MySQLConexion().getConexion()) {
            EspecialidadRepository especialidadRepository = new EspecialidadRepository(conn);
            return especialidadRepository.buscarFiltrado(nombreEspecialidad);
        } catch (Exception e) {
            Mensajes.errorConexion();
            return new ArrayList<>();
        }
    }

    public static void borrarEspecialidad(FrmMantenimientoEspecialidad view) {
        String input = JOptionPane.showInputDialog(view, "Ingrese el ID de la especialidad a eliminar");
        input = ComprobarDatos.limpiarString(input);

        // Si se cancela la eliminacion o esta vacio
        if (input == null) {
            return;
        }

        try {
            int idEspecialidad = Integer.valueOf(input);
            try (Connection conn = new MySQLConexion().getConexion()) {
                EspecialidadRepository especialidadRepository = new EspecialidadRepository(conn);
                especialidadRepository.desactivar(idEspecialidad);
            }
        } catch (NumberFormatException e) {
            Mensajes.error("Valor inválido", "Ingrese un número válido");
        } catch (SQLException e) {
            Mensajes.errorConexion();
        }
    }

    public static Optional<Especialidad> seleccionarEspecialidad(FrmMantenimientoEspecialidad view) {
        String input = JOptionPane.showInputDialog(view, "Ingrese el ID de la especialidad a editar");
        input = ComprobarDatos.limpiarString(input);

        // Si se cancela la eliminacion o esta vacio
        if (input == null) {
            return Optional.empty();
        }

        try (Connection conn = new MySQLConexion().getConexion()) {
            int idBuscado = Integer.parseInt(input);

            EspecialidadRepository especialidadRepository = new EspecialidadRepository(conn);
            return especialidadRepository.buscarPorId(idBuscado);
        } catch (NumberFormatException e) {
            Mensajes.error("Valor inválido", "Ingrese un número válido");
        } catch (SQLException e) {
            Mensajes.errorConexion();
        }
        return Optional.empty();
    }

    // Se verifico previamente que contiene datos, no hace falta revalidar
    public static void cargarDatosEdicion(FrmMantenimientoEspecialidad view, Especialidad especialidadEnEdicion) {
        // Carga los datos en el formulario
        view.txtEditarCodigo.setText(especialidadEnEdicion.getIdEspecialidad() + "");
        view.txtEditarEspecialidad.setText(especialidadEnEdicion.getEspecialidad());
        view.txtEditarCostoConsulta.setText(especialidadEnEdicion.getCostoConsulta() + "");
        view.txtEditarDescripcion.setText(especialidadEnEdicion.getDescripcion());
        view.txtEditarConsultoriosAginados.setText(especialidadEnEdicion.getConsultoriosAsignados() + "");
        view.txtEditarRequisitosEspeciales.setText(especialidadEnEdicion.getRequisitosEspeciales());
    }

    // Habilita campos editables
    public static void habilitarCamposEditables(FrmMantenimientoEspecialidad view) {
        view.txtEditarEspecialidad.setEnabled(true);
        view.txtEditarCostoConsulta.setEnabled(true);
        view.txtEditarDescripcion.setEnabled(true);
        view.txtEditarConsultoriosAginados.setEnabled(true);
        view.txtEditarRequisitosEspeciales.setEnabled(true);
        view.btnActualizar.setEnabled(true);
    }

    public static boolean actualizarEspecialidad(FrmMantenimientoEspecialidad view,
            Especialidad especialidadEnEdicion) {
        // Primero valida los campos obligatorios
        String nombreEspecialidad = ComprobarDatos.limpiarString(view.txtEditarEspecialidad.getText());
        String strCostoConsulta = ComprobarDatos.limpiarString(view.txtEditarCostoConsulta.getText());
        String strConsultoriosAsignados = ComprobarDatos.limpiarString(view.txtEditarConsultoriosAginados.getText());

        // Verifica los numeros
        Double costoConsulta = ComprobarDatos.validarDecimal(strCostoConsulta);
        Integer consultoriosAsignados = ComprobarDatos.validarEntero(strConsultoriosAsignados);

        // Si algun campo obligatorio no esta lleno
        if (nombreEspecialidad == null || costoConsulta == null || consultoriosAsignados == null) {
            return false;
        }

        // Si no hay problemas limpia los demas campos editables
        String descripcion = ComprobarDatos.limpiarString(view.txtEditarDescripcion.getText());
        String requisitosEspeciales = ComprobarDatos.limpiarString(view.txtEditarRequisitosEspeciales.getText());

        // Actualiza los datos de la cache
        especialidadEnEdicion.setEspecialidad(nombreEspecialidad);
        especialidadEnEdicion.setCostoConsulta(costoConsulta);
        especialidadEnEdicion.setConsultoriosAsignados(consultoriosAsignados);
        especialidadEnEdicion.setDescripcion(descripcion);
        especialidadEnEdicion.setRequisitosEspeciales(requisitosEspeciales);

        try (Connection conn = new MySQLConexion().getConexion()) {
            EspecialidadRepository especialidadRepository = new EspecialidadRepository(conn);
            especialidadRepository.actualizar(especialidadEnEdicion);
            return true;
        } catch (SQLException e) {
            Mensajes.errorConexion();
            return false;
        }
    }
}