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
import ucv.codelab.util.Mensajes;
import ucv.codelab.util.MySQLConexion;
import ucv.codelab.view.FrmMantenimientoEspecialidad;
import ucv.codelab.view.PanelBase;

public class ProcesosEditarEspecialidad {

    private static String[] titulo = { "CODIGO", "ESPECIALIDAD", "DESCRIPCION" };

    public static void presentacion(FrmMantenimientoEspecialidad view, List<Especialidad> especialidades) {
        cargarDatos(view, especialidades);
        personalizarTabla(view);
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
        columnModel.getColumn(0).setPreferredWidth(80); // CODIGO
        columnModel.getColumn(1).setPreferredWidth(200); // ESPECIALIDAD
        columnModel.getColumn(2).setPreferredWidth(600); // DESCRIPCION

        // Personalizar altura de filas
        view.tblResultados.setRowHeight(25);

        // Hacer que las columnas no se puedan reordenar
        header.setReorderingAllowed(false);
    }

    public static List<Especialidad> especialidadesActivas() {
        // Descarga los datos
        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            EspecialidadRepository especialidadRepository = new EspecialidadRepository(conn);
            return especialidadRepository.buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            Mensajes.errorConexion();
            return new ArrayList<>();
        }
    }

    public static List<Especialidad> especialidadesFiltradas(FrmMantenimientoEspecialidad view) {
        String nombreEspecialidad = limpiarString(view.txtEspecialidad.getText());

        if (nombreEspecialidad == null) {
            return especialidadesActivas();
        }

        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            EspecialidadRepository especialidadRepository = new EspecialidadRepository(conn);
            return especialidadRepository.buscarFiltrado(nombreEspecialidad);
        } catch (Exception e) {
            Mensajes.errorConexion();
            return new ArrayList<>();
        }
    }

    public static void borrarEspecialidad(FrmMantenimientoEspecialidad view) {
        String input = JOptionPane.showInputDialog(view, "Ingrese el ID de la especialidad a eliminar");
        input = limpiarString(input);

        // Si se cancela la eliminacion o esta vacio
        if (input == null) {
            return;
        }

        try {
            int idEspecialidad = Integer.valueOf(input);
            try (Connection conn = MySQLConexion.getInstance().getConexion()) {
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
        input = limpiarString(input);

        // Si se cancela la eliminacion o esta vacio
        if (input == null) {
            return Optional.empty();
        }

        return Optional.empty();
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