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

import ucv.codelab.model.Medico;
import ucv.codelab.repository.EspecialidadRepository;
import ucv.codelab.repository.MedicoRepository;
import ucv.codelab.util.Mensajes;
import ucv.codelab.util.MySQLConexion;
import ucv.codelab.view.FrmMantenimientoDoc;
import ucv.codelab.view.PanelBase;

public class ProcesosEditarMedico {

    private static String[] titulo = { "CODGIO", "ESPECIALIDAD", "NOMBRE", "APELLIDO", "DNI", "FECHA DE NACIMIENTO",
            "SEXO", "TELEFONO", "EMAIL", "COLEGIATURA", "GRADO ACADEMICO" };

    public static void presentacion(FrmMantenimientoDoc view, List<Medico> medicos) {
        cargarDatos(view, medicos);
        personalizarTabla(view);
    }

    private static void cargarDatos(FrmMantenimientoDoc view, List<Medico> medicos) {
        // Crea el modelo con la edicion deshabilitada
        DefaultTableModel dtm = new DefaultTableModel(null, titulo) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.tblResultados.setModel(dtm);

        for (Medico medico : medicos) {
            dtm.addRow(medico.registro());
        }
    }

    private static void personalizarTabla(FrmMantenimientoDoc view) {
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
        columnModel.getColumn(1).setPreferredWidth(150); // ESPECIALIDAD
        columnModel.getColumn(2).setPreferredWidth(120); // NOMBRE
        columnModel.getColumn(3).setPreferredWidth(120); // APELLIDO
        columnModel.getColumn(4).setPreferredWidth(100); // DNI
        columnModel.getColumn(5).setPreferredWidth(140); // FECHA DE NACIMIENTO
        columnModel.getColumn(6).setPreferredWidth(80); // SEXO
        columnModel.getColumn(7).setPreferredWidth(110); // TELEFONO
        columnModel.getColumn(8).setPreferredWidth(200); // EMAIL
        columnModel.getColumn(9).setPreferredWidth(120); // COLEGIATURA
        columnModel.getColumn(10).setPreferredWidth(150); // GRADO ACADEMICO

        // Personalizar altura de filas
        view.tblResultados.setRowHeight(25);

        // Hacer que las columnas no se puedan reordenar
        header.setReorderingAllowed(false);
    }

    public static List<Medico> medicosActivos() {
        // Descarga los datos
        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            MedicoRepository medicoRepository = new MedicoRepository(conn);
            List<Medico> medicos = medicoRepository.buscarActivos();
            try {
                EspecialidadRepository especialidadRepository = new EspecialidadRepository(conn);
                for (Medico medico : medicos) {
                    medico.setEspecialidad(especialidadRepository.buscarPorId(medico.getIdEspecialidad()).get());
                }
            } catch (Exception e) {
                /*
                 * En caso de que ocurra un error al obtener los nombres de las especialidades
                 * cancela la actualizacion
                 */
            }
            return medicos;
        } catch (Exception e) {
            Mensajes.errorConexion();
            return new ArrayList<>();
        }
    }

    public static List<Medico> medicosFiltrados(FrmMantenimientoDoc view) {
        String dni = limpiarString(view.txtDni.getText());
        String nombre = limpiarString(view.txtNombre.getText());
        String apellido = limpiarString(view.txtApellido.getText());

        if (dni == null && nombre == null && apellido == null) {
            return medicosActivos();
        }

        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            MedicoRepository medicoRepository = new MedicoRepository(conn);
            // Actualiza el nombre de especialidades
            List<Medico> medicos = medicoRepository.buscarFiltrado(dni, nombre, apellido);
            try {
                EspecialidadRepository especialidadRepository = new EspecialidadRepository(conn);
                for (Medico medico : medicos) {
                    medico.setEspecialidad(especialidadRepository.buscarPorId(medico.getIdEspecialidad()).get());
                }
            } catch (Exception e) {
                /*
                 * En caso de que ocurra un error al obtener los nombres de las especialidades
                 * cancela la actualizacion
                 */
            }
            return medicos;
        } catch (Exception e) {
            Mensajes.errorConexion();
            return new ArrayList<>();
        }
    }

    public static void borrarMedico(FrmMantenimientoDoc view) {
        String input = JOptionPane.showInputDialog(view, "Ingrese el ID del medico a eliminar");
        input = limpiarString(input);

        // Si se cancela la eliminacion o esta vacio
        if (input == null) {
            return;
        }

        try {
            int idMedico = Integer.valueOf(input);
            try (Connection conn = MySQLConexion.getInstance().getConexion()) {
                MedicoRepository medicoRepository = new MedicoRepository(conn);
                medicoRepository.desactivar(idMedico);
            }
        } catch (NumberFormatException e) {
            Mensajes.error("Valor inválido", "Ingrese un número válido");
        } catch (SQLException e) {
            Mensajes.errorConexion();
        }
    }

    public static Optional<Medico> seleccionarMedico(FrmMantenimientoDoc view) {
        String input = JOptionPane.showInputDialog(view, "Ingrese el ID del medico a editar");
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
