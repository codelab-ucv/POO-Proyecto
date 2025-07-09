package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import ucv.codelab.enumerados.TipoDiagnostico;
import ucv.codelab.model.Diagnostico;

public class DiagnosticoRepository extends BaseRepository<Diagnostico> {

    public DiagnosticoRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "diagnostico";
    }

    @Override
    protected String getIdColumnName() {
        return "id_diagnostico";
    }

    @Override
    protected Diagnostico convertirResultSetEnEntidad(ResultSet rs) throws SQLException {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setIdDiagnostico(rs.getInt("id_diagnostico"));
        diagnostico.setIdHistoria(rs.getInt("id_historia"));
        diagnostico.setTipo(TipoDiagnostico.fromString(rs.getString("tipo")));
        diagnostico.setDescripcion(rs.getString("descripcion"));
        diagnostico.setCodigoCIE10(rs.getString("codigo_cie10"));
        return diagnostico;
    }

    @Override
    protected String buildInsertSQL() {
        return "INSERT INTO diagnostico (id_historia, tipo, descripcion, codigo_cie10) " +
                "VALUES (?, ?, ?, ?)";
    }

    @Override
    protected void establecerParametrosInsertar(PreparedStatement stmt, Diagnostico entity) throws SQLException {
        stmt.setInt(1, entity.getIdHistoria());
        stmt.setString(2, entity.getTipo().getValor());

        if (entity.getDescripcion() != null) {
            stmt.setString(3, entity.getDescripcion());
        } else {
            stmt.setNull(3, Types.VARCHAR);
        }

        if (entity.getCodigoCIE10() != null) {
            stmt.setString(4, entity.getCodigoCIE10());
        } else {
            stmt.setNull(4, Types.VARCHAR);
        }
    }

    @Override
    protected String buildUpdateSQL() {
        return "UPDATE diagnostico SET id_historia = ?, tipo = ?, descripcion = ?, codigo_cie10 = ? " +
                "WHERE id_diagnostico = ?";
    }

    @Override
    protected void establecerParametrosActualizar(PreparedStatement stmt, Diagnostico entity) throws SQLException {
        stmt.setInt(1, entity.getIdHistoria());
        stmt.setString(2, entity.getTipo().getValor());

        if (entity.getDescripcion() != null) {
            stmt.setString(3, entity.getDescripcion());
        } else {
            stmt.setNull(3, Types.VARCHAR);
        }

        if (entity.getCodigoCIE10() != null) {
            stmt.setString(4, entity.getCodigoCIE10());
        } else {
            stmt.setNull(4, Types.VARCHAR);
        }

        stmt.setInt(5, entity.getIdDiagnostico());
    }

    @Override
    protected void actualizarEntidadConIdGenerado(Diagnostico entity, ResultSet generatedKeys) throws SQLException {
        entity.setIdDiagnostico(generatedKeys.getInt(1));
    }

    public List<Diagnostico> buscarPorHistoria(int idHistoria) {
        String sql = "SELECT * FROM diagnostico WHERE id_historia = ? ORDER BY id_diagnostico DESC";
        return ejecutarConsulta(sql, idHistoria);
    }

    public List<Diagnostico> buscarPorTipo(String tipo) {
        String sql = "SELECT * FROM diagnostico WHERE tipo = ?";
        return ejecutarConsulta(sql, tipo);
    }
}