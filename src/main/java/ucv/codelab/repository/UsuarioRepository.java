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
    protected String getIdColumnName() {
        return "id_usuario";
    }

    @Override
    protected Usuario convertirResultSetEnEntidad(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("id_usuario"));
        usuario.setIdMedico(rs.getInt("id_medico"));
        usuario.setUsername(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        usuario.setEstado(rs.getBoolean("estado"));
        return usuario;
    }

    @Override
    protected String buildInsertSQL() {
        return "INSERT INTO usuario (id_medico, username, password, estado) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected void establecerParametrosInsertar(PreparedStatement stmt, Usuario entity) throws SQLException {
        stmt.setInt(1, entity.getIdMedico());
        stmt.setString(2, entity.getUsername());
        stmt.setString(3, entity.getPassword());
        stmt.setBoolean(4, entity.isEstado());
    }

    @Override
    protected String buildUpdateSQL() {
        return "UPDATE usuario SET id_medico = ?, username = ?, password = ?, estado = ? WHERE id_usuario = ?";
    }

    @Override
    protected void establecerParametrosActualizar(PreparedStatement stmt, Usuario entity) throws SQLException {
        stmt.setInt(1, entity.getIdMedico());
        stmt.setString(2, entity.getUsername());
        stmt.setString(3, entity.getPassword());
        stmt.setBoolean(4, entity.isEstado());
        stmt.setInt(4, entity.getIdUsuario());
    }

    @Override
    protected void actualizarEntidadConIdGenerado(Usuario entity, ResultSet generatedKeys) throws SQLException {
        entity.setIdUsuario(generatedKeys.getInt(1));
    }

    public Optional<Usuario> validarUsuario(String username, String password) {
        String sql = "SELECT * FROM usuario WHERE username = ? AND password = ?";
        return ejecutarConsultaSoloUnResultado(sql, username, password);
    }
}