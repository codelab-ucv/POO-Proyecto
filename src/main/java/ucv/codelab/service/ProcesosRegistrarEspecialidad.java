package ucv.codelab.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import ucv.codelab.model.Especialidad;
import ucv.codelab.repository.EspecialidadRepository;
import ucv.codelab.util.Mensajes;
import ucv.codelab.util.MySQLConexion;
import ucv.codelab.view.FrmRegistrarEspecialidad;

public class ProcesosRegistrarEspecialidad {

    public static Optional<Especialidad> validarDatos(FrmRegistrarEspecialidad view) {
        String nombreEspecialidad = limpiarString(view.txtNombreEspecialidad.getText());

        if (nombreEspecialidad == null) {
            return Optional.empty();
        }

        return Optional.of(new Especialidad(nombreEspecialidad));
    }

    public static boolean guardarEspecialidad(FrmRegistrarEspecialidad view, Especialidad especialidad) {
        String descripcion = limpiarString(view.txtDescripcion.getText());

        especialidad.setDescripcion(descripcion);

        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            EspecialidadRepository especialidadRepository = new EspecialidadRepository(conn);
            especialidadRepository.crear(especialidad);
            return true;
        } catch (SQLException e) {
            Mensajes.error("Error al guardar", "Verifique que la especialidad no se encuentre previamente registrado");
            return false;
        }
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

    public static void limpiarEntradas(FrmRegistrarEspecialidad view) {
        view.txtNombreEspecialidad.setText("");
        view.txtDescripcion.setText("");
        view.txtNombreEspecialidad.requestFocus();
    }
}
