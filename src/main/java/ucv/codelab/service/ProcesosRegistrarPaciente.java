package ucv.codelab.service;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Optional;

import ucv.codelab.enumerados.Sexo;
import ucv.codelab.enumerados.TipoSangre;
import ucv.codelab.model.Paciente;
import ucv.codelab.repository.MySQLConexion;
import ucv.codelab.repository.PacienteRepository;
import ucv.codelab.util.ComprobarDatos;
import ucv.codelab.util.Mensajes;
import ucv.codelab.view.FrmRegistrarPaciente;

public class ProcesosRegistrarPaciente {

    public static void presentacion(FrmRegistrarPaciente view) {
        // Configura los ComboBox
        view.cmbSexo.removeAllItems();
        for (Sexo sexo : Sexo.values()) {
            view.cmbSexo.addItem(sexo.getValor().toUpperCase());
        }
        view.cmbSexo.setSelectedItem(null);
        view.cmbTipoSangre.removeAllItems();
        for (TipoSangre tipoSangre : TipoSangre.values()) {
            view.cmbTipoSangre.addItem(tipoSangre.getValor().toUpperCase());
        }
        view.cmbTipoSangre.setSelectedItem(null);
    }

    public static Optional<Paciente> validarDatos(FrmRegistrarPaciente view) {
        // Verifica primero los ComboBox
        if (view.cmbSexo.getSelectedItem() == null || view.cmbTipoSangre.getSelectedItem() == null) {
            return Optional.empty();
        }

        String nombre = ComprobarDatos.limpiarString(view.txtNombres.getText());
        String apellido = ComprobarDatos.limpiarString(view.txtApellidos.getText());
        String dni = ComprobarDatos.limpiarString(view.txtDni.getText());
        String fechaNacimiento = ComprobarDatos.limpiarString(view.txtFechaNacimiento.getText());
        String sexo = ComprobarDatos.limpiarString(view.cmbSexo.getSelectedItem().toString());
        String tipoSangre = ComprobarDatos.limpiarString(view.cmbTipoSangre.getSelectedItem().toString());

        // Verifica que se pueda parsear la fecha
        LocalDate fecha = ComprobarDatos.obtenerFecha(fechaNacimiento);

        // No hay mas datos que requieran validacion ya que los demas campos opcionales
        // son Strings que acetan cualquier dato

        // Si algun campo obligatorio no esta lleno
        if (nombre == null || apellido == null || dni == null || fecha == null || sexo == null
                || tipoSangre == null) {
            return Optional.empty();
        }
        // Validacion adicional de datos
        if (dni.length() < 8) {
            return Optional.empty();
        }
        // Si es que encuentra los valores devuelve un Paciente creado
        return Optional.of(new Paciente(nombre, apellido, dni, fecha, Sexo.fromString(sexo),
                TipoSangre.fromString(tipoSangre), true));
    }

    public static boolean guardarPaciente(FrmRegistrarPaciente view, Paciente paciente) {
        String telefono = ComprobarDatos.limpiarString(view.txtTelefono.getText());
        String direccion = ComprobarDatos.limpiarString(view.txtDireccion.getText());

        paciente.setTelefono(telefono);
        paciente.setDireccion(direccion);

        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            PacienteRepository pacienteRepository = new PacienteRepository(conn);
            pacienteRepository.crear(paciente);
            return true;
        } catch (Exception e) {
            Mensajes.error("Error al guardar", "Verifique que el paciente no se encuentre previamente registrado");
            return false;
        }

    }

    public static void limpiarEntradas(FrmRegistrarPaciente view) {
        view.txtNombres.setText("");
        view.txtApellidos.setText("");
        view.txtDni.setText("");
        view.txtFechaNacimiento.setText("");
        view.cmbSexo.setSelectedItem(null);
        view.txtTelefono.setText("");
        view.txtDireccion.setText("");
        view.cmbTipoSangre.setSelectedItem(null);
        view.txtNombres.requestFocus();
    }
}
