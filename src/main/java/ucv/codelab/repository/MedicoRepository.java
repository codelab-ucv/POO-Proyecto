package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import ucv.codelab.model.Medico;

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
        medico.setNombre(rs.getString("nombre"));
        medico.setApellido(rs.getString("apellido"));
        medico.setColegiatura(rs.getString("colegiatura"));
        medico.setEspecialidad(rs.getString("especialidad"));
        medico.setTelefono(rs.getString("telefono"));
        return medico;
    }

    @Override
    protected String buildInsertSQL() {
        return "INSERT INTO medico (nombre, apellido, colegiatura, especialidad, telefono) " +
                "VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    protected void establecerParametrosInsertar(PreparedStatement stmt, Medico entity) throws SQLException {
        stmt.setString(1, entity.getNombre());
        stmt.setString(2, entity.getApellido());
        stmt.setString(3, entity.getColegiatura());

        if (entity.getEspecialidad() != null) {
            stmt.setString(4, entity.getEspecialidad());
        } else {
            stmt.setNull(4, Types.VARCHAR);
        }

        if (entity.getTelefono() != null) {
            stmt.setString(5, entity.getTelefono());
        } else {
            stmt.setNull(5, Types.VARCHAR);
        }
    }

    @Override
    protected String buildUpdateSQL() {
        return "UPDATE medico SET nombre = ?, apellido = ?, colegiatura = ?, " +
                "especialidad = ?, telefono = ? WHERE id_medico = ?";
    }

    @Override
    protected void establecerParametrosActualizar(PreparedStatement stmt, Medico entity) throws SQLException {
        stmt.setString(1, entity.getNombre());
        stmt.setString(2, entity.getApellido());
        stmt.setString(3, entity.getColegiatura());

        if (entity.getEspecialidad() != null) {
            stmt.setString(4, entity.getEspecialidad());
        } else {
            stmt.setNull(4, Types.VARCHAR);
        }

        if (entity.getTelefono() != null) {
            stmt.setString(5, entity.getTelefono());
        } else {
            stmt.setNull(5, Types.VARCHAR);
        }

        stmt.setInt(6, entity.getIdMedico());
    }

    @Override
    protected void actualizarEntidadConIdGenerado(Medico entity, ResultSet generatedKeys) throws SQLException {
        entity.setIdMedico(generatedKeys.getInt(1));
    }
}