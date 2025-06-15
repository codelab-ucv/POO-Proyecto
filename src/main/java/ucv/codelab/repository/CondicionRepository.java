package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ucv.codelab.model.Condicion;

public class CondicionRepository extends BaseRepository<Condicion> {

    public CondicionRepository(Connection conexion) throws SQLException {
        super(conexion);
    }

    @Override
    protected String getTableName() {
        return "condicion";
    }

    @Override
    protected Condicion convertirResultSetEnEntidad(ResultSet rs) throws SQLException {
        return new Condicion(
                rs.getInt("id"),
                rs.getString("tipo"),
                rs.getString("condicion"),
                rs.getString("gravedad"));
    }

    @Override
    protected String buildInsertSQL() {
        return "INSERT INTO condicion(tipo, condicion, gravedad) VALUES (?, ?, ?)";
    }

    @Override
    protected void establecerParametrosInsertar(PreparedStatement stmt, Condicion condicion) throws SQLException {
        stmt.setString(1, condicion.getTipo());
        stmt.setString(2, condicion.getCondicion());
        stmt.setString(3, condicion.getGravedad());
    }

    @Override
    protected String buildUpdateSQL() {
        return "UPDATE condicion SET tipo = ?, condicion = ?, gravedad = ? WHERE id = ?";
    }

    @Override
    protected void establecerParametrosActualizar(PreparedStatement stmt, Condicion condicion) throws SQLException {
        stmt.setString(1, condicion.getTipo());
        stmt.setString(2, condicion.getCondicion());
        stmt.setString(3, condicion.getGravedad());
        // El ID va al final para el WHERE
        stmt.setInt(4, condicion.getId());
    }

    @Override
    protected void actualizarEntidadConIdGenerado(Condicion condicion, ResultSet generatedKeys) throws SQLException {
        condicion.setId(generatedKeys.getInt(1));
    }
}