package ucv.codelab.service;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import ucv.codelab.model.HistoriaClinica;
import ucv.codelab.repository.HistoriaClinicaRepository;
import ucv.codelab.repository.MySQLConexion;
import ucv.codelab.util.ComprobarDatos;
import ucv.codelab.util.Mensajes;
import ucv.codelab.view.FrmExportarHistoria;
import ucv.codelab.view.PanelBase;

public class ProcesosExportarHistoria {

    private static String[] titulo = { "ID HISTORIA", "FECHA Y HORA DE REGISTRO", "MOTIVO DE CONSULTA" };

    public static void presentacion(FrmExportarHistoria view, List<HistoriaClinica> historias) {
        cargarDatos(view, historias);
        personalizarTabla(view);
    }

    private static void cargarDatos(FrmExportarHistoria view, List<HistoriaClinica> historias) {
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

    private static void personalizarTabla(FrmExportarHistoria view) { // Personalizar cabecera
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
        columnModel.getColumn(2).setPreferredWidth(560); // MOTIVO DE CONSULTA

        // Personalizar altura de filas
        view.tblResultados.setRowHeight(25);

        // Hacer que las columnas no se puedan reordenar
        header.setReorderingAllowed(false);
    }

    public static List<HistoriaClinica> historiasFiltradas(FrmExportarHistoria view) {
        // Si el DNI no está vacio primero intenta buscarlo por DNI
        String dniFiltrado = ComprobarDatos.limpiarString(view.txtDni.getText());
        if (dniFiltrado != null) {
            return HistoriaService.buscarPorDni(dniFiltrado, null);
        }

        // De lo contrario intenta buscar por lo menos por nombre o apellido
        String nombreFiltrado = ComprobarDatos.limpiarString(view.txtNombre.getText());
        String apellidoFiltrado = ComprobarDatos.limpiarString(view.txtApellido.getText());
        if (nombreFiltrado != null || apellidoFiltrado != null) {
            return HistoriaService.buscarPorNombre(nombreFiltrado, apellidoFiltrado, null, null);
        }

        // De lo contrario devuelve una lista vacia
        Mensajes.error("Error de busqueda", "Debe especificarse por lo menos un filtro para buscar");
        return new ArrayList<>();
    }

    public static HistoriaClinica descargarHistoria(FrmExportarHistoria view) {
        String input = JOptionPane.showInputDialog(view, "Ingrese el ID de la historia a exportar");
        input = ComprobarDatos.limpiarString(input);

        // Si se cancela la eliminacion o esta vacio
        if (input == null) {
            return null;
        }

        try {
            int idHistoria = Integer.valueOf(input);
            try (Connection conn = new MySQLConexion().getConexion()) {
                HistoriaClinicaRepository historiaClinicaRepository = new HistoriaClinicaRepository(conn);
                Optional<HistoriaClinica> historiaBuscada = historiaClinicaRepository.buscarPorId(idHistoria);
                if (historiaBuscada.isPresent()) {
                    HistoriaClinica historia = historiaBuscada.get();
                    HistoriaService.descargarHistoria(historia);
                    return historia;
                }
                Mensajes.error("Error al exportar",
                        "ID de historia clinica no encontrado, verifique que el ID ingresado exista");
            }
        } catch (NumberFormatException e) {
            Mensajes.error("Valor inválido", "Ingrese un número válido");
        } catch (SQLException e) {
            Mensajes.errorConexion();
        }
        return null;

    }

    public static void exportar(HistoriaClinica historiaExportar) {
        try {
            String userHome = System.getProperty("user.home");
            File initialDirectory = new File(userHome, "Documents");

            String fileName = "historia_" + historiaExportar.getIdHistoria() + ".pdf";
            File ubicacionArchivo = new File(initialDirectory, fileName);
            MakePdf.make(ubicacionArchivo, historiaExportar);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
