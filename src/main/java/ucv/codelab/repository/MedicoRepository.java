package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import ucv.codelab.model.Medico;

public class MedicoRepository extends BaseRepository<Medico> {

    public MedicoRepository(Connection conexion) throws SQLException {
        super(conexion);
    }

    @Override
    protected String getTableName() {
        return "medico";
    }

    @Override
    protected Medico convertirResultSetEnEntidad(ResultSet rs) throws SQLException {
        return new Medico(
                rs.getInt("id"),
                rs.getInt("id_persona"),
                rs.getString("area"),
                rs.getString("email"),
                rs.getInt("experiencia"),
                rs.getString("colegiatura"),
                rs.getString("universidad"),
                rs.getString("grado"));
    }

    @Override
    protected String buildInsertSQL() {
        return "INSERT INTO medico(id_persona, area, email, experiencia, colegiatura, universidad, grado) VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void establecerParametrosInsertar(PreparedStatement stmt, Medico medico) throws SQLException {
        stmt.setInt(1, medico.getIdPersona());
        stmt.setString(2, medico.getArea());
        stmt.setString(3, medico.getEmail());
        stmt.setInt(4, medico.getExperiencia());
        stmt.setString(5, medico.getColegiatura());
        stmt.setString(6, medico.getUniversidad());
        stmt.setString(7, medico.getGrado());
    }

    @Override
    protected String buildUpdateSQL() {
        return "UPDATE medico SET id_persona = ?, area = ?, email = ?, experiencia = ?, colegiatura = ?, universidad = ?, grado = ? WHERE id = ?";
    }

    @Override
    protected void establecerParametrosActualizar(PreparedStatement stmt, Medico medico) throws SQLException {
        stmt.setInt(1, medico.getIdPersona());
        stmt.setString(2, medico.getArea());
        stmt.setString(3, medico.getEmail());
        stmt.setInt(4, medico.getExperiencia());
        stmt.setString(5, medico.getColegiatura());
        stmt.setString(6, medico.getUniversidad());
        stmt.setString(7, medico.getGrado());
        // El ID va al final para el WHERE
        stmt.setInt(8, medico.getId());
    }

    @Override
    protected void actualizarEntidadConIdGenerado(Medico medico, ResultSet generatedKeys) throws SQLException {
        medico.setId(generatedKeys.getInt(1));
    }

    public Optional<Medico> buscarPorPersona(int idPersona) {
        String sql = "SELECT * FROM medico WHERE id_persona = ?";
        return ejecutarConsultaSoloUnResultado(sql, idPersona);
    }
}