package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ucv.codelab.enumerados.Sexo;
import ucv.codelab.enumerados.TipoSangre;
import ucv.codelab.model.Paciente;
import ucv.codelab.util.Mensajes;

public class PacienteRepository extends BaseRepository<Paciente> {

    public PacienteRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "paciente";
    }

    @Override
    protected String getIdColumnName() {
        return "id_paciente";
    }

    @Override
    protected Paciente convertirResultSetEnEntidad(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(rs.getInt("id_paciente"));
        paciente.setNombre(rs.getString("nombre"));
        paciente.setApellido(rs.getString("apellido"));
        paciente.setDni(rs.getString("dni"));
        paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
        paciente.setSexo(Sexo.fromString(rs.getString("sexo")));
        paciente.setDireccion(rs.getString("direccion"));
        paciente.setTelefono(rs.getString("telefono"));
        paciente.setTipoSangre(TipoSangre.fromString(rs.getString("tipo_sangre")));
        paciente.setEstado(rs.getBoolean("estado"));
        return paciente;
    }

    @Override
    protected String buildInsertSQL() {
        return "INSERT INTO paciente (nombre, apellido, dni, fecha_nacimiento, sexo, direccion, telefono, tipo_sangre, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void establecerParametrosInsertar(PreparedStatement stmt, Paciente entity) throws SQLException {
        stmt.setString(1, entity.getNombre());
        stmt.setString(2, entity.getApellido());
        stmt.setString(3, entity.getDni());
        stmt.setDate(4, Date.valueOf(entity.getFechaNacimiento()));
        stmt.setString(5, entity.getSexo().getValor());

        if (entity.getDireccion() != null) {
            stmt.setString(6, entity.getDireccion());
        } else {
            stmt.setNull(6, Types.VARCHAR);
        }

        if (entity.getTelefono() != null) {
            stmt.setString(7, entity.getTelefono());
        } else {
            stmt.setNull(7, Types.VARCHAR);
        }

        stmt.setString(8, entity.getTipoSangre().getValor());
        stmt.setBoolean(9, entity.isEstado());
    }

    @Override
    protected String buildUpdateSQL() {
        return "UPDATE paciente SET nombre = ?, apellido = ?, dni = ?, fecha_nacimiento = ?, " +
                "sexo = ?, direccion = ?, telefono = ?, tipo_sangre = ?, estado = ? WHERE id_paciente = ?";
    }

    @Override
    protected void establecerParametrosActualizar(PreparedStatement stmt, Paciente entity) throws SQLException {
        stmt.setString(1, entity.getNombre());
        stmt.setString(2, entity.getApellido());
        stmt.setString(3, entity.getDni());
        stmt.setDate(4, Date.valueOf(entity.getFechaNacimiento()));
        stmt.setString(5, entity.getSexo().getValor());

        if (entity.getDireccion() != null) {
            stmt.setString(6, entity.getDireccion());
        } else {
            stmt.setNull(6, Types.VARCHAR);
        }

        if (entity.getTelefono() != null) {
            stmt.setString(7, entity.getTelefono());
        } else {
            stmt.setNull(7, Types.VARCHAR);
        }

        stmt.setString(8, entity.getTipoSangre().getValor());
        stmt.setBoolean(9, entity.isEstado());
        stmt.setInt(10, entity.getIdPaciente());
    }

    @Override
    protected void actualizarEntidadConIdGenerado(Paciente entity, ResultSet generatedKeys) throws SQLException {
        entity.setIdPaciente(generatedKeys.getInt(1));
    }

    // Métodos adicionales específicos para Paciente

    /**
     * Busca pacientes que coincidan con el DNI, nombre y apellido indicados y se
     * encuentren activos. Debe indicarse por lo menos un filtro, los demás son
     * opcionales
     * 
     * @param dni      el DNI del paciente buscado, si es NUll lo omite
     * @param nombre   el Nombre del paciente buscado, si es NULL lo omite
     * @param apellido el Apellido del paciente buscado, si es NULL lo omite
     * @return Lista con pacientes que coinciden con los filtros
     */
    public List<Paciente> buscarFiltrado(String dni, String nombre, String apellido) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM paciente WHERE estado = ?");

        List<Object> parametros = new ArrayList<>();

        parametros.add(true);

        if (dni != null) {
            sql.append(" AND dni LIKE ?");
            parametros.add("%" + dni + "%");
        }

        if (nombre != null) {
            sql.append(" AND nombre LIKE ?");
            parametros.add("%" + nombre + "%");
        }

        if (apellido != null) {
            sql.append(" AND apellido LIKE ?");
            parametros.add("%" + apellido + "%");
        }

        return ejecutarConsulta(sql.toString(), parametros.toArray());
    }

    /**
     * Obtiene todos los pacientes activos de la tabla.
     * 
     * @return Lista de todos los pacientes activas
     */
    @Override
    public List<Paciente> buscarTodos() {
        String sql = "SELECT * FROM paciente WHERE estado = ?";
        return ejecutarConsulta(sql, true);
    }

    /**
     * Desactiva un paciente de la tabla.
     * 
     * @param id el ID del paciente a desactivar
     */
    public void desactivar(int id) {
        String sql = "UPDATE paciente SET estado = ? WHERE id_paciente = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Desativa el paciente
            stmt.setBoolean(1, false);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            Mensajes.errorConexion("Error al eliminar el paciente con ID: " + id + " de " + getTableName());
        }
    }

    /**
     * Busca un paciente activo según su ID.
     * 
     * @param id el ID del paciente a buscar
     * @return un Optional que contiene el paciente si se encuentra , o
     *         Optional.empty() si no existe
     */
    @Override
    public Optional<Paciente> buscarPorId(int id) {
        String sql = "SELECT * FROM paciente WHERE id_paciente = ? AND estado = ?";
        return ejecutarConsultaSoloUnResultado(sql, id, true);
    }

    public Optional<Paciente> buscarPorDni(String dni) {
        String sql = "SELECT * FROM paciente WHERE dni = ? AND estado = ?";
        return ejecutarConsultaSoloUnResultado(sql, dni, true);
    }
}