package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

import ucv.codelab.model.Medico;
import ucv.codelab.util.Mensajes;

public class MedicoRepository extends BaseRepository<Medico> {

    public MedicoRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "medico";
    }

    @Override
    protected String getIdColumnName() {
        return "id_medico";
    }

    @Override
    protected Medico convertirResultSetEnEntidad(ResultSet rs) throws SQLException {
        Medico medico = new Medico();
        medico.setIdMedico(rs.getInt("id_medico"));
        medico.setIdEspecialidad(rs.getInt("id_especialidad"));
        medico.setNombre(rs.getString("nombre"));
        medico.setApellido(rs.getString("apellido"));
        medico.setColegiatura(rs.getString("colegiatura"));

        // El campo telefono puede ser NULL
        if (rs.getString("telefono") != null) {
            medico.setTelefono(rs.getString("telefono"));
        }

        medico.setEstado(rs.getBoolean("estado"));
        return medico;
    }

    @Override
    protected String buildInsertSQL() {
        return "INSERT INTO medico (id_especialidad, nombre, apellido, colegiatura, telefono, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void establecerParametrosInsertar(PreparedStatement stmt, Medico entity) throws SQLException {
        stmt.setInt(1, entity.getIdEspecialidad());
        stmt.setString(2, entity.getNombre());
        stmt.setString(3, entity.getApellido());
        stmt.setString(4, entity.getColegiatura());

        if (entity.getTelefono() != null) {
            stmt.setString(5, entity.getTelefono());
        } else {
            stmt.setNull(5, Types.VARCHAR);
        }

        stmt.setBoolean(6, entity.isEstado());
    }

    @Override
    protected String buildUpdateSQL() {
        return "UPDATE medico SET id_especialidad = ?, nombre = ?, apellido = ?, " +
                "colegiatura = ?, telefono = ?, estado = ? WHERE id_medico = ?";
    }

    @Override
    protected void establecerParametrosActualizar(PreparedStatement stmt, Medico entity) throws SQLException {
        stmt.setInt(1, entity.getIdEspecialidad());
        stmt.setString(2, entity.getNombre());
        stmt.setString(3, entity.getApellido());
        stmt.setString(4, entity.getColegiatura());

        if (entity.getTelefono() != null) {
            stmt.setString(5, entity.getTelefono());
        } else {
            stmt.setNull(5, Types.VARCHAR);
        }

        stmt.setBoolean(6, entity.isEstado());
        stmt.setInt(7, entity.getIdMedico());
    }

    @Override
    protected void actualizarEntidadConIdGenerado(Medico entity, ResultSet generatedKeys) throws SQLException {
        entity.setIdMedico(generatedKeys.getInt(1));
    }

    // Métodos adicionales específicos para Medico
    public Optional<Medico> buscarPorColegiatura(String colegiatura) {
        String sql = "SELECT * FROM medico WHERE colegiatura = ?";
        return ejecutarConsultaSoloUnResultado(sql, colegiatura);
    }

    public List<Medico> buscarPorEspecialidad(int idEspecialidad) {
        String sql = "SELECT * FROM medico WHERE id_especialidad = ?";
        return ejecutarConsulta(sql, idEspecialidad);
    }

    public List<Medico> buscarActivos() {
        String sql = "SELECT * FROM medico WHERE estado = true";
        return ejecutarConsulta(sql);
    }

    public List<Medico> buscarInactivos() {
        String sql = "SELECT * FROM medico WHERE estado = false";
        return ejecutarConsulta(sql);
    }

    public void desactivar(int id) {
        String sql = "UPDATE medico SET estado = ? WHERE id_medico = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Desativa el medico
            stmt.setBoolean(1, false);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            Mensajes.errorConexion("Error al eliminar el medico con ID: " + id + " de " + getTableName());
        }
    }
}