package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

import ucv.codelab.model.Especialidad;

public class EspecialidadRepository extends BaseRepository<Especialidad> {

    public EspecialidadRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "especialidad";
    }

    @Override
    protected String getIdColumnName() {
        return "id_especialidad";
    }

    @Override
    protected Especialidad convertirResultSetEnEntidad(ResultSet rs) throws SQLException {
        Especialidad especialidad = new Especialidad();
        especialidad.setIdEspecialidad(rs.getInt("id_especialidad"));
        especialidad.setEspecialidad(rs.getString("especialidad"));

        // El campo descripcion puede ser NULL
        if (rs.getString("descripcion") != null) {
            especialidad.setDescripcion(rs.getString("descripcion"));
        }

        return especialidad;
    }

    @Override
    protected String buildInsertSQL() {
        return "INSERT INTO especialidad (especialidad, descripcion) VALUES (?, ?)";
    }

    @Override
    protected void establecerParametrosInsertar(PreparedStatement stmt, Especialidad entity) throws SQLException {
        stmt.setString(1, entity.getEspecialidad());

        if (entity.getDescripcion() != null) {
            stmt.setString(2, entity.getDescripcion());
        } else {
            stmt.setNull(2, Types.VARCHAR);
        }
    }

    @Override
    protected String buildUpdateSQL() {
        return "UPDATE especialidad SET especialidad = ?, descripcion = ? WHERE id_especialidad = ?";
    }

    @Override
    protected void establecerParametrosActualizar(PreparedStatement stmt, Especialidad entity) throws SQLException {
        stmt.setString(1, entity.getEspecialidad());

        if (entity.getDescripcion() != null) {
            stmt.setString(2, entity.getDescripcion());
        } else {
            stmt.setNull(2, Types.VARCHAR);
        }

        stmt.setInt(3, entity.getIdEspecialidad());
    }

    @Override
    protected void actualizarEntidadConIdGenerado(Especialidad entity, ResultSet generatedKeys) throws SQLException {
        entity.setIdEspecialidad(generatedKeys.getInt(1));
    }

    // Métodos adicionales específicos para Especialidad

    /**
     * Busca una especialidad por su nombre exacto
     * 
     * @param nombreEspecialidad el nombre de la especialidad a buscar
     * @return Optional con la especialidad encontrada o vacío si no existe
     */
    public Optional<Especialidad> buscarPorNombre(String nombreEspecialidad) {
        String sql = "SELECT * FROM especialidad WHERE especialidad = ?";
        return ejecutarConsultaSoloUnResultado(sql, nombreEspecialidad);
    }

    /**
     * Busca especialidades que contengan el texto especificado en su nombre
     * 
     * @param texto el texto a buscar en el nombre de la especialidad
     * @return Lista de especialidades que contienen el texto
     */
    public List<Especialidad> buscarPorNombreParcial(String texto) {
        String sql = "SELECT * FROM especialidad WHERE especialidad LIKE ? ORDER BY especialidad";
        return ejecutarConsulta(sql, "%" + texto + "%");
    }

    /**
     * Obtiene todas las especialidades ordenadas alfabéticamente
     * 
     * @return Lista de todas las especialidades ordenadas por nombre
     */
    public List<Especialidad> buscarTodosOrdenados() {
        String sql = "SELECT * FROM especialidad ORDER BY especialidad";
        return ejecutarConsulta(sql);
    }
}