package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import ucv.codelab.model.Paciente;

public class PacienteRepository extends BaseRepository<Paciente> {

    public PacienteRepository(Connection conexion) throws SQLException {
        super(conexion);
    }

    @Override
    protected String getTableName() {
        return "paciente";
    }

    @Override
    protected Paciente convertirResultSetEnEntidad(ResultSet rs) throws SQLException {
        return new Paciente(
                rs.getInt("id"),
                rs.getInt("id_persona"),
                rs.getString("tipo_sangre"),
                rs.getString("antecedentes"));
    }

    @Override
    protected String buildInsertSQL() {
        return "INSERT INTO paciente(id_persona, tipo_sangre, antecedentes) VALUES (?, ?, ?)";
    }

    @Override
    protected void establecerParametrosInsertar(PreparedStatement stmt, Paciente paciente) throws SQLException {
        stmt.setInt(1, paciente.getIdPersona());
        stmt.setString(2, paciente.getTipoSangre());
        stmt.setString(3, paciente.getAntecedentes());
    }

    @Override
    protected String buildUpdateSQL() {
        return "UPDATE paciente SET id_persona = ?, tipo_sangre = ?, antecedentes = ? WHERE id = ?";
    }

    @Override
    protected void establecerParametrosActualizar(PreparedStatement stmt, Paciente paciente) throws SQLException {
        stmt.setInt(1, paciente.getIdPersona());
        stmt.setString(2, paciente.getTipoSangre());
        stmt.setString(3, paciente.getAntecedentes());
        // El ID va al final para el WHERE
        stmt.setInt(4, paciente.getId());
    }

    @Override
    protected void actualizarEntidadConIdGenerado(Paciente paciente, ResultSet generatedKeys) throws SQLException {
        paciente.setId(generatedKeys.getInt(1));
    }

    public Optional<Paciente> buscarPorPersona(int idPersona) {
        String sql = "SELECT * FROM paciente WHERE id_persona = ?";
        return ejecutarConsultaSoloUnResultado(sql, idPersona);
    }
}