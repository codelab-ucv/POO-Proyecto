package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import ucv.codelab.model.Usuario;

public class UsuarioRepository extends BaseRepository<Usuario> {

    public UsuarioRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "usuario";
    }

    @Override
    protected Usuario convertirResultSetEnEntidad(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getInt("id"),
                rs.getInt("id_medico"),
                rs.getString("username"),
                rs.getString("password"));
    }

    @Override
    protected String buildInsertSQL() {
        return "INSERT INTO usuario(id_medico, username, password) VALUES (?, ?, ?)";
    }

    @Override
    protected void establecerParametrosInsertar(PreparedStatement stmt, Usuario usuario) throws SQLException {
        stmt.setInt(1, usuario.getIdMedico());
        stmt.setString(2, usuario.getUsername());
        stmt.setString(3, usuario.getPassword());
    }

    @Override
    protected String buildUpdateSQL() {
        return "UPDATE usuario SET id_medico = ?, username = ?, password = ? WHERE id = ?";
    }

    @Override
    protected void establecerParametrosActualizar(PreparedStatement stmt, Usuario usuario) throws SQLException {
        stmt.setInt(1, usuario.getIdMedico());
        stmt.setString(2, usuario.getUsername());
        stmt.setString(3, usuario.getPassword());
        // El ID va al final para el WHERE
        stmt.setInt(4, usuario.getId());
    }

    @Override
    protected void actualizarEntidadConIdGenerado(Usuario usuario, ResultSet generatedKeys) throws SQLException {
        usuario.setId(generatedKeys.getInt(1));
    }

    public Optional<Usuario> validarUsuario(String username, String password) {
        String sql = "SELECT * FROM usuario WHERE username = ? AND password = ?";
        return ejecutarConsultaSoloUnResultado(sql, username, password);
    }
}