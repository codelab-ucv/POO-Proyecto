package ucv.codelab.service;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import ucv.codelab.enumerados.Sexo;
import ucv.codelab.enumerados.TipoSangre;
import ucv.codelab.model.Paciente;
import ucv.codelab.repository.PacienteRepository;
import ucv.codelab.util.Mensajes;
import ucv.codelab.util.MySQLConexion;
import ucv.codelab.view.FrmMantenimientoPaciente;
import ucv.codelab.view.PanelBase;

public class ProcesosEditarPaciente {

    private static String[] titulo = { "CODIGO", "NOMBRE", "APELLIDO", "DNI", "FECHA DE NACIMIENTO", "SEXO",
            "DIRECCION", "TELEFONO", "SANGRE" };

    public static void presentacion(FrmMantenimientoPaciente view, List<Paciente> pacientes) {
        cargarDatos(view, pacientes);
        personalizarTabla(view);
        deshabilitarEdicion(view);
    }

    public static void deshabilitarEdicion(FrmMantenimientoPaciente view) {
        view.txtEditarCodigo.setEnabled(false);
        view.txtEditarNombres.setEnabled(false);
        view.txtEditarApellidos.setEnabled(false);
        view.txtEditarDni.setEnabled(false);
        view.txtEditarFechaNacimiento.setEnabled(false);
        view.cmbEditarSexo.setEnabled(false);
        view.txtEditarDireccion.setEnabled(false);
        view.txtEditarTelefono.setEnabled(false);
        view.cmbEditarTipoSangre.setEnabled(false);
        view.btnActualizar.setEnabled(false);
    }

    private static void cargarDatos(FrmMantenimientoPaciente view, List<Paciente> pacientes) {
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

        // Configura los ComboBox con enumerados
        view.cmbEditarSexo.removeAllItems();
        for (Sexo sexo : Sexo.values()) {
            view.cmbEditarSexo.addItem(sexo.getValor().toUpperCase());
        }
        view.cmbEditarSexo.setSelectedItem(null);
        view.cmbEditarTipoSangre.removeAllItems();
        for (TipoSangre tipoSangre : TipoSangre.values()) {
            view.cmbEditarTipoSangre.addItem(tipoSangre.getValor().toUpperCase());
        }
        view.cmbEditarTipoSangre.setSelectedItem(null);
    }

    private static void personalizarTabla(FrmMantenimientoPaciente view) {
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

    public static List<Paciente> pacientesFiltrados(FrmMantenimientoPaciente view) {
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

    public static void borrarPaciente(FrmMantenimientoPaciente view) {
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

    public static Optional<Paciente> seleccionarPaciente(FrmMantenimientoPaciente view) {
        String input = JOptionPane.showInputDialog(view, "Ingrese el ID del paciente a editar");
        input = limpiarString(input);

        // Si se cancela la eliminacion o esta vacio
        if (input == null) {
            return Optional.empty();
        }

        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            int idBuscado = Integer.parseInt(input);

            PacienteRepository pacienteRepository = new PacienteRepository(conn);
            return pacienteRepository.buscarPorId(idBuscado);
        } catch (NumberFormatException e) {
            Mensajes.error("Valor inválido", "Ingrese un número válido");
        } catch (SQLException e) {
            Mensajes.errorConexion();
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

    // Se verifico previamente que contiene datos, no hace falta revalidar
    public static void cargarDatosEdicion(FrmMantenimientoPaciente view, Paciente pacienteEnEdicion) {
        // Carga los datos en el formulario
        view.txtEditarCodigo.setText(pacienteEnEdicion.getIdPaciente() + "");
        view.txtEditarNombres.setText(pacienteEnEdicion.getNombre());
        view.txtEditarApellidos.setText(pacienteEnEdicion.getApellido());
        view.txtEditarDni.setText(pacienteEnEdicion.getDni());
        view.txtEditarDireccion.setText(pacienteEnEdicion.getDireccion());
        view.txtEditarTelefono.setText(pacienteEnEdicion.getTelefono());

        // Inserta la fecha en el formato indicado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        view.txtEditarFechaNacimiento.setText(pacienteEnEdicion.getFechaNacimiento().format(formatter));

        // Coloca el valor de los ComboBox
        for (int i = 0; i < view.cmbEditarSexo.getItemCount(); i++) {
            if (view.cmbEditarSexo.getItemAt(i).equalsIgnoreCase(pacienteEnEdicion.getSexo().getValor())) {
                view.cmbEditarSexo.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < view.cmbEditarTipoSangre.getItemCount(); i++) {
            if (view.cmbEditarTipoSangre.getItemAt(i).equalsIgnoreCase(pacienteEnEdicion.getTipoSangre().getValor())) {
                view.cmbEditarTipoSangre.setSelectedIndex(i);
                break;
            }
        }
    }

    // Habilita campos editables
    public static void habilitarCamposEditables(FrmMantenimientoPaciente view) {
        view.txtEditarNombres.setEnabled(true);
        view.txtEditarApellidos.setEnabled(true);
        view.txtEditarDni.setEnabled(true);
        view.txtEditarFechaNacimiento.setEnabled(true);
        view.cmbEditarSexo.setEnabled(true);
        view.txtEditarDireccion.setEnabled(true);
        view.txtEditarTelefono.setEnabled(true);
        view.cmbEditarTipoSangre.setEnabled(true);
        view.btnActualizar.setEnabled(true);
    }

    public static boolean actualizarPaciente(FrmMantenimientoPaciente view, Paciente pacienteEnEdicion) {
        // Primero valida los campos obligatorios
        String nombre = limpiarString(view.txtEditarNombres.getText());
        String apellido = limpiarString(view.txtEditarApellidos.getText());
        String dni = limpiarString(view.txtEditarDni.getText());
        String strFechaNacimiento = limpiarString(view.txtEditarFechaNacimiento.getText());
        String sexo = limpiarString(view.cmbEditarSexo.getSelectedItem().toString());
        String tipoSangre = limpiarString(view.cmbEditarTipoSangre.getSelectedItem().toString());

        if (nombre == null || apellido == null || dni == null || strFechaNacimiento == null || sexo == null
                || tipoSangre == null) {
            return false;
        }

        // Verifica que se pueda parsear la fecha
        LocalDate fechaNacimiento = obtenerFecha(strFechaNacimiento);
        if (fechaNacimiento == null) {
            return false;
        }

        // Si no hay problemas limpia los demas campos editables
        String direccion = limpiarString(view.txtEditarDireccion.getText());
        String telefono = limpiarString(view.txtEditarTelefono.getText());

        // Actualiza los datos de la cache
        pacienteEnEdicion.setNombre(nombre);
        pacienteEnEdicion.setApellido(apellido);
        pacienteEnEdicion.setDni(dni);
        pacienteEnEdicion.setFechaNacimiento(fechaNacimiento);
        pacienteEnEdicion.setSexo(Sexo.fromString(sexo));
        pacienteEnEdicion.setDireccion(direccion);
        pacienteEnEdicion.setTelefono(telefono);
        pacienteEnEdicion.setTipoSangre(TipoSangre.fromString(tipoSangre));

        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            PacienteRepository repo = new PacienteRepository(conn);
            repo.actualizar(pacienteEnEdicion);
            return true;
        } catch (SQLException e) {
            Mensajes.errorConexion();
            return false;
        }
    }

    private static LocalDate obtenerFecha(String fechaString) {
        // Obtiene la fecha de un formato dd/mm/yyyy
        if (fechaString != null) {
            String[] fechaSeparada = fechaString.split("/");
            try {
                return LocalDate.of(Integer.valueOf(fechaSeparada[2]), Integer.valueOf(fechaSeparada[1]),
                        Integer.valueOf(fechaSeparada[0]));
            } catch (Exception e) {
            }
        }
        return null;
    }
}
