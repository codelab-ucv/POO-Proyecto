package ucv.codelab.service;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Optional;

import ucv.codelab.enumerados.Sexo;
import ucv.codelab.enumerados.TipoSangre;
import ucv.codelab.model.Paciente;
import ucv.codelab.repository.PacienteRepository;
import ucv.codelab.util.Mensajes;
import ucv.codelab.util.MySQLConexion;
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

        String nombre = limpiarString(view.txtNombres.getText());
        String apellido = limpiarString(view.txtApellidos.getText());
        String dni = limpiarString(view.txtDni.getText());
        String fechaNacimiento = limpiarString(view.txtFechaNacimiento.getText());
        String sexo = limpiarString(view.cmbSexo.getSelectedItem().toString());
        String tipoSangre = limpiarString(view.cmbTipoSangre.getSelectedItem().toString());

        // Verifica que se pueda parsear la fecha
        LocalDate fecha = obtenerFecha(fechaNacimiento);

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
        String telefono = limpiarString(view.txtTelefono.getText());
        String direccion = limpiarString(view.txtDireccion.getText());

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

    private static String limpiarString(String parametro) {
        if (parametro != null) {
            parametro = parametro.trim();
            if (parametro.isEmpty()) {
                return null;
            }
        }
        return parametro;
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
