package ucv.codelab.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import ucv.codelab.model.Usuario;
import ucv.codelab.repository.UsuarioRepository;
import ucv.codelab.util.Mensajes;
import ucv.codelab.util.MySQLConexion;

public class MedicoService {

    public static Usuario validarDatos(String user, String password) {
        // Limpieza de parámetros
        user = limpiarString(user);
        password = limpiarString(password);

        if (user == null || password == null) {
            return null;
        }

        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            UsuarioRepository usuarioRepository = new UsuarioRepository(conn);

            Optional<Usuario> usuario = usuarioRepository.validarUsuario(user, password);

            if (usuario.isPresent()) {
                return usuario.get();
            }
            return null;
        } catch (SQLException e) {
            Mensajes.errorConexion();
            return null;
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
}