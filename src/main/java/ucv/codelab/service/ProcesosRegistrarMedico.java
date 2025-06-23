package ucv.codelab.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import ucv.codelab.enumerados.GradoAcademico;
import ucv.codelab.enumerados.Sexo;
import ucv.codelab.model.Especialidad;
import ucv.codelab.model.Medico;
import ucv.codelab.repository.EspecialidadRepository;
import ucv.codelab.repository.MedicoRepository;
import ucv.codelab.util.Mensajes;
import ucv.codelab.util.MySQLConexion;
import ucv.codelab.view.FrmRegistrarMedico;

public class ProcesosRegistrarMedico {

    public static void presentacion(FrmRegistrarMedico view) {
        // Configura los ComboBox con enumerados
        view.cmbSexo.removeAllItems();
        for (Sexo sexo : Sexo.values()) {
            view.cmbSexo.addItem(sexo.getValor().toUpperCase());
        }
        view.cmbSexo.setSelectedItem(null);
        view.cmbGradoAcademico.removeAllItems();
        for (GradoAcademico gradoAcademico : GradoAcademico.values()) {
            view.cmbGradoAcademico.addItem(gradoAcademico.getValor().toUpperCase());
        }
        view.cmbGradoAcademico.setSelectedItem(null);

        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            EspecialidadRepository especialidadRepository = new EspecialidadRepository(conn);
            List<Especialidad> especialidades = especialidadRepository.buscarTodos();
            for (Especialidad especialidad : especialidades) {
                view.cmbEspecialidad.addItem(especialidad.getEspecialidad());
            }
            view.cmbEspecialidad.setSelectedItem(null);
        } catch (Exception e) {
            Mensajes.errorConexion();
        }

    }

    // Valida solo los campos obligatorios que acetan cualquier dato
    public static Optional<Medico> validarDatos(FrmRegistrarMedico view) {
        // Verifica primero los ComboBox
        if (view.cmbEspecialidad.getSelectedItem() == null || view.cmbSexo.getSelectedItem() == null
                || view.cmbGradoAcademico.getSelectedItem() == null) {
            return Optional.empty();
        }

        String nombre = limpiarString(view.txtNombres.getText());
        String apellido = limpiarString(view.txtApellidos.getText());
        String dni = limpiarString(view.txtDni.getText());
        String colegiatura = limpiarString(view.txtColegiatura.getText());
        String fechaNacimiento = limpiarString(view.txtFechaNacimiento.getText());
        String sexo = limpiarString(view.cmbSexo.getSelectedItem().toString());
        String gradoAcademico = limpiarString(view.cmbGradoAcademico.getSelectedItem().toString());
        String especialidadString = limpiarString(view.cmbEspecialidad.getSelectedItem().toString());

        // Verifica que se pueda parsear la fecha
        LocalDate fecha = obtenerFecha(fechaNacimiento);

        // Si algun campo obligatorio no esta lleno
        if (nombre == null || apellido == null || dni == null || colegiatura == null || fecha == null || sexo == null
                || gradoAcademico == null || especialidadString == null) {
            return Optional.empty();
        }

        // Validacion adicional de datos
        if (dni.length() < 8) {
            return Optional.empty();
        }

        // Obtiene el ID de la especialidad de la base de datos
        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            EspecialidadRepository especialidadRepository = new EspecialidadRepository(conn);
            Optional<Especialidad> especialidad = especialidadRepository.buscarPorNombre(especialidadString);
            if (especialidad.isPresent()) {
                // Retorna un Optional con el Medico creado
                return Optional.of(new Medico(especialidad.get().getIdEspecialidad(), nombre, apellido, dni, fecha,
                        Sexo.fromString(sexo), colegiatura, GradoAcademico.fromString(gradoAcademico), true));
            }
        } catch (SQLException e) {
            Mensajes.errorConexion();
        }
        return Optional.empty();
    }

    public static boolean guardarMedico(FrmRegistrarMedico view, Medico medico) {
        String telefono = limpiarString(view.txtTelefono.getText());
        String email = limpiarString(view.txtCorreo.getText());

        medico.setTelefono(telefono);
        medico.setEmail(email);

        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            MedicoRepository medicoRepository = new MedicoRepository(conn);
            medicoRepository.crear(medico);
            return true;
        } catch (Exception e) {
            Mensajes.error("Error al guardar", "Verifique que el medico no se encuentre previamente registrado");
            return false;
        }
    }

    public static void limpiarEntradas(FrmRegistrarMedico view) {
        view.txtNombres.setText("");
        view.txtApellidos.setText("");
        view.txtDni.setText("");
        view.txtColegiatura.setText("");
        view.txtTelefono.setText("");
        view.txtCorreo.setText("");
        view.txtFechaNacimiento.setText("");
        view.cmbEspecialidad.setSelectedItem(null);
        view.cmbSexo.setSelectedItem(null);
        view.cmbGradoAcademico.setSelectedItem(null);
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
