package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;

import ucv.codelab.model.ExamenFisico;

public class ExamenFisicoRepository extends BaseRepository<ExamenFisico> {

    public ExamenFisicoRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "examen_fisico";
    }

    @Override
    protected String getIdColumnName() {
        return "id_examen";
    }

    @Override
    protected ExamenFisico convertirResultSetEnEntidad(ResultSet rs) throws SQLException {
        ExamenFisico examen = new ExamenFisico();
        examen.setIdExamen(rs.getInt("id_examen"));
        examen.setIdHistoria(rs.getInt("id_historia"));

        if (rs.getObject("peso") != null) {
            examen.setPeso(rs.getDouble("peso"));
        }

        if (rs.getObject("talla") != null) {
            examen.setTalla(rs.getDouble("talla"));
        }

        examen.setPresionArterial(rs.getString("presion_arterial"));

        if (rs.getObject("temperatura") != null) {
            examen.setTemperatura(rs.getDouble("temperatura"));
        }

        if (rs.getObject("frecuencia_cardiaca") != null) {
            examen.setFrecuenciaCardiaca(rs.getInt("frecuencia_cardiaca"));
        }

        if (rs.getObject("frecuencia_respiratoria") != null) {
            examen.setFrecuenciaRespiratoria(rs.getInt("frecuencia_respiratoria"));
        }

        return examen;
    }

    @Override
    protected String buildInsertSQL() {
        return "INSERT INTO examen_fisico (id_historia, peso, talla, presion_arterial, temperatura, frecuencia_cardiaca, frecuencia_respiratoria) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void establecerParametrosInsertar(PreparedStatement stmt, ExamenFisico entity) throws SQLException {
        stmt.setInt(1, entity.getIdHistoria());

        if (entity.getPeso() != null) {
            stmt.setDouble(2, entity.getPeso());
        } else {
            stmt.setNull(2, Types.DECIMAL);
        }

        if (entity.getTalla() != null) {
            stmt.setDouble(3, entity.getTalla());
        } else {
            stmt.setNull(3, Types.DECIMAL);
        }

        if (entity.getPresionArterial() != null) {
            stmt.setString(4, entity.getPresionArterial());
        } else {
            stmt.setNull(4, Types.VARCHAR);
        }

        if (entity.getTemperatura() != null) {
            stmt.setDouble(5, entity.getTemperatura());
        } else {
            stmt.setNull(5, Types.DECIMAL);
        }

        if (entity.getFrecuenciaCardiaca() != null) {
            stmt.setInt(6, entity.getFrecuenciaCardiaca());
        } else {
            stmt.setNull(6, Types.INTEGER);
        }

        if (entity.getFrecuenciaRespiratoria() != null) {
            stmt.setInt(7, entity.getFrecuenciaRespiratoria());
        } else {
            stmt.setNull(7, Types.INTEGER);
        }
    }

    @Override
    protected String buildUpdateSQL() {
        return "UPDATE examen_fisico SET id_historia = ?, peso = ?, talla = ?, " +
                "presion_arterial = ?, temperatura = ?, frecuencia_cardiaca = ?, frecuencia_respiratoria = ? " +
                "WHERE id_examen = ?";
    }

    @Override
    protected void establecerParametrosActualizar(PreparedStatement stmt, ExamenFisico entity) throws SQLException {
        stmt.setInt(1, entity.getIdHistoria());

        if (entity.getPeso() != null) {
            stmt.setDouble(2, entity.getPeso());
        } else {
            stmt.setNull(2, Types.DECIMAL);
        }

        if (entity.getTalla() != null) {
            stmt.setDouble(3, entity.getTalla());
        } else {
            stmt.setNull(3, Types.DECIMAL);
        }

        if (entity.getPresionArterial() != null) {
            stmt.setString(4, entity.getPresionArterial());
        } else {
            stmt.setNull(4, Types.VARCHAR);
        }

        if (entity.getTemperatura() != null) {
            stmt.setDouble(5, entity.getTemperatura());
        } else {
            stmt.setNull(5, Types.DECIMAL);
        }

        if (entity.getFrecuenciaCardiaca() != null) {
            stmt.setInt(6, entity.getFrecuenciaCardiaca());
        } else {
            stmt.setNull(6, Types.INTEGER);
        }

        if (entity.getFrecuenciaRespiratoria() != null) {
            stmt.setInt(7, entity.getFrecuenciaRespiratoria());
        } else {
            stmt.setNull(7, Types.INTEGER);
        }

        stmt.setInt(8, entity.getIdExamen());
    }

    @Override
    protected void actualizarEntidadConIdGenerado(ExamenFisico entity, ResultSet generatedKeys) throws SQLException {
        entity.setIdExamen(generatedKeys.getInt(1));
    }

    public Optional<ExamenFisico> buscarPorHistoria(int idHistoria) {
        String sql = "SELECT * FROM examen_fisico WHERE id_historia = ?";
        return ejecutarConsultaSoloUnResultado(sql, idHistoria);
    }
}