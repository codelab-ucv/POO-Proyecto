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

import ucv.codelab.model.Paciente;
import ucv.codelab.repository.PacienteRepository;
import ucv.codelab.util.Mensajes;
import ucv.codelab.util.MySQLConexion;
import ucv.codelab.view.FrmMantenimientoPa;
import ucv.codelab.view.PanelBase;

public class ProcesosEditarPaciente {

    private static String[] titulo = { "CODIGO", "NOMBRE", "APELLIDO", "DNI", "FECHA DE NACIMIENTO", "SEXO",
            "DIRECCION", "TELEFONO", "SANGRE" };

    public static void presentacion(FrmMantenimientoPa view, List<Paciente> pacientes) {
        cargarDatos(view, pacientes);
        personalizarTabla(view);
    }

    private static void personalizarTabla(FrmMantenimientoPa view) {
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
        columnModel.getColumn(1).setPreferredWidth(120); // NOMBRE
        columnModel.getColumn(2).setPreferredWidth(120); // APELLIDO
        columnModel.getColumn(3).setPreferredWidth(100); // DNI
        columnModel.getColumn(4).setPreferredWidth(140); // FECHA DE NACIMIENTO
        columnModel.getColumn(5).setPreferredWidth(100); // SEXO
        columnModel.getColumn(6).setPreferredWidth(200); // DIRECCION
        columnModel.getColumn(7).setPreferredWidth(100); // TELEFONO
        columnModel.getColumn(8).setPreferredWidth(40); // TIPO DE SANGRE

        // Personalizar altura de filas
        view.tblResultados.setRowHeight(25);

        // Hacer que las columnas no se puedan reordenar
        header.setReorderingAllowed(false);
    }

    private static void cargarDatos(FrmMantenimientoPa view, List<Paciente> pacientes) {
        // Crea el modelo con la edicion deshabilitada
        DefaultTableModel dtm = new DefaultTableModel(null, titulo) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.tblResultados.setModel(dtm);

        for (Paciente paciente : pacientes) {
            dtm.addRow(paciente.registro());
        }
    }

    public static List<Paciente> pacientesActivos() {
        // Descarga los datos
        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            PacienteRepository pacienteRepository = new PacienteRepository(conn);
            return pacienteRepository.buscarTodos();
        } catch (Exception e) {
            Mensajes.errorConexion();
            return new ArrayList<>();
        }
    }

    public static List<Paciente> pacientesFiltrados(FrmMantenimientoPa view) {
        String dni = limpiarString(view.txtDni.getText());
        String nombre = limpiarString(view.txtNombre.getText());
        String apellido = limpiarString(view.txtApellido.getText());

        if (dni == null && nombre == null && apellido == null) {
            return pacientesActivos();
        }
        
        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            PacienteRepository pacienteRepository = new PacienteRepository(conn);
            return pacienteRepository.buscarFiltrado(dni, nombre, apellido);
        } catch (Exception e) {
            Mensajes.errorConexion();
            return new ArrayList<>();
        }
    }

    public static void borrarPaciente(FrmMantenimientoPa view) {
        String input = JOptionPane.showInputDialog(view, "Ingrese el ID del paciente a eliminar");
        input = limpiarString(input);

        // Si se cancela la eliminacion o esta vacio
        if (input == null) {
            return;
        }

        try {
            int idPaciente = Integer.valueOf(input);
            try (Connection conn = MySQLConexion.getInstance().getConexion()) {
                PacienteRepository pacienteRepository = new PacienteRepository(conn);
                pacienteRepository.desactivar(idPaciente);
            }
        } catch (NumberFormatException e) {
            Mensajes.error("Valor inválido", "Ingrese un número válido");
        } catch (SQLException e) {
            Mensajes.errorConexion();
        }
    }

    public static Optional<Paciente> seleccionarPaciente(FrmMantenimientoPa view) {
        String input = JOptionPane.showInputDialog(view, "Ingrese el ID del paciente a editar");
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
