package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

import ucv.codelab.model.Especialidad;
import ucv.codelab.util.Mensajes;

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
        especialidad.setCostoConsulta(rs.getDouble("costo_consulta"));
        especialidad.setConsultoriosAsignados(rs.getInt("consultorios_asignados"));

        // Los campos descripcion y requisitosEspeciales pueden ser NULL
        if (rs.getString("descripcion") != null) {
            especialidad.setDescripcion(rs.getString("descripcion"));
        }
        
        if (rs.getString("requisitosEspeciales") != null) {
            especialidad.setRequisitosEspeciales(rs.getString("requisitosEspeciales"));
        }

        especialidad.setEstado(rs.getBoolean("estado"));

        return especialidad;
    }

    @Override
    protected String buildInsertSQL() {
        return "INSERT INTO especialidad (especialidad, costo_consulta, descripcion, consultorios_asignados, requisitosEspeciales, estado) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void establecerParametrosInsertar(PreparedStatement stmt, Especialidad entity) throws SQLException {
        stmt.setString(1, entity.getEspecialidad());
        stmt.setDouble(2, entity.getCostoConsulta());
        
        if (entity.getDescripcion() != null) {
            stmt.setString(3, entity.getDescripcion());
        } else {
            stmt.setNull(3, Types.VARCHAR);
        }
        
        stmt.setInt(4, entity.getConsultoriosAsignados());
        
        if (entity.getRequisitosEspeciales() != null) {
            stmt.setString(5, entity.getRequisitosEspeciales());
        } else {
            stmt.setNull(5, Types.VARCHAR);
        }
        
        stmt.setBoolean(6, entity.isEstado());
    }

    @Override
    protected String buildUpdateSQL() {
        return "UPDATE especialidad SET especialidad = ?, costo_consulta = ?, descripcion = ?, consultorios_asignados = ?, requisitosEspeciales = ?, estado = ? WHERE id_especialidad = ?";
    }

    @Override
    protected void establecerParametrosActualizar(PreparedStatement stmt, Especialidad entity) throws SQLException {
        stmt.setString(1, entity.getEspecialidad());
        stmt.setDouble(2, entity.getCostoConsulta());

        if (entity.getDescripcion() != null) {
            stmt.setString(3, entity.getDescripcion());
        } else {
            stmt.setNull(3, Types.VARCHAR);
        }
        
        stmt.setInt(4, entity.getConsultoriosAsignados());
        
        if (entity.getRequisitosEspeciales() != null) {
            stmt.setString(5, entity.getRequisitosEspeciales());
        } else {
            stmt.setNull(5, Types.VARCHAR);
        }

        stmt.setBoolean(6, entity.isEstado());
        stmt.setInt(7, entity.getIdEspecialidad());
    }

    @Override
    protected void actualizarEntidadConIdGenerado(Especialidad entity, ResultSet generatedKeys) throws SQLException {
        entity.setIdEspecialidad(generatedKeys.getInt(1));
    }

    // Métodos adicionales específicos para Especialidad

    /**
     * Busca una especialidad activa por su nombre exacto
     * 
     * @param nombreEspecialidad el nombre de la especialidad a buscar
     * @return Optional con la especialidad encontrada o vacío si no existe
     */
    public Optional<Especialidad> buscarPorNombre(String nombreEspecialidad) {
        String sql = "SELECT * FROM especialidad WHERE especialidad = ? AND estado = ?";
        return ejecutarConsultaSoloUnResultado(sql, nombreEspecialidad, true);
    }

    /**
     * Busca especialidades que contengan el texto especificado en su nombre y se
     * encuentren activas
     * 
     * @param texto el texto a buscar en el nombre de la especialidad
     * @return Lista de especialidades que contienen el texto
     */
    public List<Especialidad> buscarFiltrado(String texto) {
        String sql = "SELECT * FROM especialidad WHERE especialidad LIKE ? AND estado = ?";
        return ejecutarConsulta(sql, "%" + texto + "%", true);
    }

    /**
     * Obtiene todas las especialidades activas de la tabla.
     * 
     * @return Lista de todas las especialidades activas
     */
    @Override
    public List<Especialidad> buscarTodos() {
        String sql = "SELECT * FROM especialidad WHERE estado = ?";
        return ejecutarConsulta(sql, true);
    }

    /**
     * Desactiva una especialidad de la tabla.
     * 
     * @param id el ID de la especialidad a desactivar
     */
    public void desactivar(int id) {
        String sql = "UPDATE especialidad SET estado = ? WHERE id_especialidad = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Desativa la especialidad
            stmt.setBoolean(1, false);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            Mensajes.errorConexion("Error al eliminar la especialidad con ID: " + id + " de " + getTableName());
        }
    }

    /**
     * Busca una especialidad activa según su ID.
     * 
     * @param id el ID de la especialidad a buscar
     * @return un Optional que contiene la especialidad si se encuentra , o
     *         Optional.empty() si no existe
     */
    @Override
    public Optional<Especialidad> buscarPorId(int id) {
        String sql = "SELECT * FROM especialidad WHERE id_especialidad = ? AND estado = ?";
        return ejecutarConsultaSoloUnResultado(sql, id, true);
    }
}