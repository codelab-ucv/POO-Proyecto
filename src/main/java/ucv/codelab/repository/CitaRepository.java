package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ucv.codelab.model.Cita;

public class CitaRepository extends BaseRepository<Cita> {

    public CitaRepository(Connection conexion) throws SQLException {
        super(conexion);
    }

    @Override
    protected String getTableName() {
        return "cita";
    }

    @Override
    protected Cita convertirResultSetEnEntidad(ResultSet rs) throws SQLException {
        return new Cita(
                rs.getInt("id"),
                rs.getInt("id_paciente"),
                rs.getInt("id_medico"),
                rs.getDouble("talla"),
                rs.getDouble("peso"),
                rs.getDate("fecha_atencion").toLocalDate(),
                rs.getString("estado_paciente"),
                rs.getString("sintomas"),
                rs.getString("diagnostico"),
                rs.getString("tratamiento"),
                rs.getString("observacion"));
    }

    @Override
    protected String buildInsertSQL() {
        return "INSERT INTO cita(id_paciente, id_medico, talla, peso, fecha_atencion, estado_paciente, sintomas, diagnostico, tratamiento, observacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void establecerParametrosInsertar(PreparedStatement stmt, Cita cita) throws SQLException {
        stmt.setInt(1, cita.getIdPaciente());
        stmt.setInt(2, cita.getIdMedico());
        stmt.setDouble(3, cita.getTalla());
        stmt.setDouble(4, cita.getPeso());
        stmt.setDate(5, Date.valueOf(cita.getFechaAtencion()));
        stmt.setString(6, cita.getEstadoPaciente());
        stmt.setString(7, cita.getSintomas());
        stmt.setString(8, cita.getDiagnostico());
        stmt.setString(9, cita.getTratamiento());
        stmt.setString(10, cita.getObservacion());
    }

    @Override
    protected String buildUpdateSQL() {
        return "UPDATE cita SET id_paciente = ?, id_medico = ?, talla = ?, peso = ?, fecha_atencion = ?, estado_paciente = ?, sintomas = ?, diagnostico = ?, tratamiento = ?, observacion = ? WHERE id = ?";
    }

    @Override
    protected void establecerParametrosActualizar(PreparedStatement stmt, Cita cita) throws SQLException {
        stmt.setInt(1, cita.getIdPaciente());
        stmt.setInt(2, cita.getIdMedico());
        stmt.setDouble(3, cita.getTalla());
        stmt.setDouble(4, cita.getPeso());
        stmt.setDate(5, Date.valueOf(cita.getFechaAtencion()));
        stmt.setString(6, cita.getEstadoPaciente());
        stmt.setString(7, cita.getSintomas());
        stmt.setString(8, cita.getDiagnostico());
        stmt.setString(9, cita.getTratamiento());
        stmt.setString(10, cita.getObservacion());
        // El ID va al final para el WHERE
        stmt.setInt(11, cita.getId());
    }

    @Override
    protected void actualizarEntidadConIdGenerado(Cita cita, ResultSet generatedKeys) throws SQLException {
        cita.setId(generatedKeys.getInt(1));
    }
}
