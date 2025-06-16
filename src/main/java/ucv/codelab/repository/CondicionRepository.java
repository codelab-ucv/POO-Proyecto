package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Condicion> buscarPorPaciente(int idPaciente) {
        String sql = "SELECT c.id, c.tipo, c.condicion, c.gravedad " +
                "FROM condicion c " +
                "JOIN paciente_condicion pc ON c.id = pc.id_condicion " +
                "WHERE pc.id_paciente = ?";
        return ejecutarConsulta(sql, idPaciente);
    }

    public Optional<Condicion> buscarPorCondicion(String tipo, String condicion, String gravedad) {
        String sql = "SELECT * FROM condicion WHERE tipo = ? AND condicion = ? AND gravedad = ? ";
        return ejecutarConsultaSoloUnResultado(sql, tipo, condicion, gravedad);
    }

    /**
     * Asocia una condición con un paciente en la tabla paciente_condicion
     * Ignora si la asociación ya existe (gracias a UNIQUE KEY en la tabla)
     */
    public void asociarCondicionConPaciente(int idPaciente, int idCondicion) throws SQLException {
        String sql = "INSERT IGNORE INTO paciente_condicion (id_paciente, id_condicion) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPaciente);
            stmt.setInt(2, idCondicion);
            stmt.executeUpdate();
        }
    }

    /**
     * Desasocia una condición de un paciente
     */
    public void desasociarCondicionDePaciente(int idPaciente, int idCondicion) throws SQLException {
        String sql = "DELETE FROM paciente_condicion WHERE id_paciente = ? AND id_condicion = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPaciente);
            stmt.setInt(2, idCondicion);
            stmt.executeUpdate();
        }
    }

    /**
     * Obtiene los IDs de las condiciones actualmente asociadas a un paciente
     */
    public List<Integer> obtenerIdsCondicionesPorPaciente(int idPaciente) {
        String sql = "SELECT id_condicion FROM paciente_condicion WHERE id_paciente = ?";
        List<Integer> ids = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPaciente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ids.add(rs.getInt("id_condicion"));
            }
            return ids;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener IDs de condiciones por paciente", e);
        }
    }
}