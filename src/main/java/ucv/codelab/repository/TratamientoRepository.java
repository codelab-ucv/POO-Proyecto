package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import ucv.codelab.model.Tratamiento;

public class TratamientoRepository extends BaseRepository<Tratamiento> {

    public TratamientoRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "tratamiento";
    }

    @Override
    protected String getIdColumnName() {
        return "id_tratamiento";
    }

    @Override
    protected Tratamiento convertirResultSetEnEntidad(ResultSet rs) throws SQLException {
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setIdTratamiento(rs.getInt("id_tratamiento"));
        tratamiento.setIdHistoria(rs.getInt("id_historia"));
        tratamiento.setDescripcion(rs.getString("descripcion"));
        tratamiento.setIndicaciones(rs.getString("indicaciones"));
        return tratamiento;
    }

    @Override
    protected String buildInsertSQL() {
        return "INSERT INTO tratamiento (id_historia, descripcion, indicaciones) " +
                "VALUES (?, ?, ?)";
    }

    @Override
    protected void establecerParametrosInsertar(PreparedStatement stmt, Tratamiento entity) throws SQLException {
        stmt.setInt(1, entity.getIdHistoria());
        stmt.setString(2, entity.getDescripcion());

        if (entity.getIndicaciones() != null) {
            stmt.setString(3, entity.getIndicaciones());
        } else {
            stmt.setNull(3, Types.VARCHAR);
        }
    }

    @Override
    protected String buildUpdateSQL() {
        return "UPDATE tratamiento SET id_historia = ?, descripcion = ?, indicaciones = ? " +
                "WHERE id_tratamiento = ?";
    }

    @Override
    protected void establecerParametrosActualizar(PreparedStatement stmt, Tratamiento entity) throws SQLException {
        stmt.setInt(1, entity.getIdHistoria());
        stmt.setString(2, entity.getDescripcion());

        if (entity.getIndicaciones() != null) {
            stmt.setString(3, entity.getIndicaciones());
        } else {
            stmt.setNull(3, Types.VARCHAR);
        }

        stmt.setInt(4, entity.getIdTratamiento());
    }

    @Override
    protected void actualizarEntidadConIdGenerado(Tratamiento entity, ResultSet generatedKeys) throws SQLException {
        entity.setIdTratamiento(generatedKeys.getInt(1));
    }

    public List<Tratamiento> buscarPorHistoria(int idHistoria) {
        String sql = "SELECT * FROM tratamiento WHERE id_historia = ? ORDER BY id_tratamiento DESC";
        return ejecutarConsulta(sql, idHistoria);
    }
}