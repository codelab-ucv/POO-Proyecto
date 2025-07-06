package ucv.codelab.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import ucv.codelab.model.Usuario;
import ucv.codelab.repository.MySQLConexion;
import ucv.codelab.repository.UsuarioRepository;
import ucv.codelab.util.ComprobarDatos;
import ucv.codelab.util.Mensajes;

public class MedicoService {

    public static Usuario validarDatos(String user, String password) {
        // Limpieza de parámetros
        user = ComprobarDatos.limpiarString(user);
        password = ComprobarDatos.limpiarString(password);

        if (user == null || password == null) {
            return null;
        }

        try (Connection conn = new MySQLConexion().getConexion()) {
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
}