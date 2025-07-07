package ucv.codelab.service;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import ucv.codelab.model.Especialidad;
import ucv.codelab.repository.EspecialidadRepository;
import ucv.codelab.repository.HistoriaClinicaRepository;
import ucv.codelab.repository.MySQLConexion;
import ucv.codelab.util.ComprobarDatos;
import ucv.codelab.util.Mensajes;
import ucv.codelab.view.FrmAtencionesEspecialidad;
import ucv.codelab.view.PanelBase;

public class ProcesosAtencionesEspecialidad {

    private static String[] titulo = { "ESPECIALIDAD", "FECHA DE ATENCION", "CANTIDAD DE ATENCIONES" };

    public static void presentacion(FrmAtencionesEspecialidad view) {
        cargarDatos(view, new ArrayList<>());
        personalizarTabla(view);

        // Configura combobox
        try (Connection conn = new MySQLConexion().getConexion()) {
            EspecialidadRepository especialidadRepository = new EspecialidadRepository(conn);
            List<Especialidad> especialidades = especialidadRepository.buscarTodos();
            for (Especialidad especialidad : especialidades) {
                view.cbxEspecialidad.addItem(especialidad.getEspecialidad());
            }
            view.cbxEspecialidad.setSelectedItem(null);
        } catch (Exception e) {
            Mensajes.errorConexion();
        }
    }

    private static void personalizarTabla(FrmAtencionesEspecialidad view) {
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
        columnModel.getColumn(0).setPreferredWidth(450); // ESPECIALIDAD
        columnModel.getColumn(1).setPreferredWidth(200); // FECHA DE ATENCION
        columnModel.getColumn(2).setPreferredWidth(100); // CANTIDAD DE ATENCIONES

        // Personalizar altura de filas
        view.tblResultados.setRowHeight(25);

        // Hacer que las columnas no se puedan reordenar
        header.setReorderingAllowed(false);
    }

    private static void cargarDatos(FrmAtencionesEspecialidad view, List<Object[]> datos) {
        // Crea el modelo con la edicion deshabilitada
        DefaultTableModel dtm = new DefaultTableModel(null, titulo) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.tblResultados.setModel(dtm);

        for (Object[] dato : datos) {
            dtm.addRow(dato);
        }
    }

    public static void actualizarTabla(FrmAtencionesEspecialidad view, LocalDate fechaInicio, LocalDate fechaFin) {

        String especialidad = null;
        if (view.cbxEspecialidad.getSelectedItem() != null) {
            especialidad = ComprobarDatos.limpiarString(view.cbxEspecialidad.getSelectedItem().toString());
        }

        List<Object[]> resultados = new ArrayList<>();

        try (Connection conn = new MySQLConexion().getConexion()) {
            HistoriaClinicaRepository repo = new HistoriaClinicaRepository(conn);
            resultados = repo.atencionesPorFecha(fechaInicio, fechaFin, especialidad);
        } catch (Exception e) {
            Mensajes.errorConexion();
        }

        // Actualiza los resultados
        cargarDatos(view, resultados);
        personalizarTabla(view);
    }
}
