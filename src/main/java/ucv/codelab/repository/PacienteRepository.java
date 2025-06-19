package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
    public Optional<Paciente> buscarPorDni(String dni) {
        String sql = "SELECT * FROM paciente WHERE dni = ?";
        return ejecutarConsultaSoloUnResultado(sql, dni);
    }

    public List<Paciente> buscarPorNombreApellido(String nombre, String apellido) {
        String sql = "SELECT * FROM paciente WHERE nombre LIKE ? AND apellido LIKE ?";
        return ejecutarConsulta(sql, "%" + nombre + "%", "%" + apellido + "%");
    }

    public List<Paciente> buscarActivos() {
        String sql = "SELECT * FROM paciente WHERE estado = true";
        return ejecutarConsulta(sql);
    }

    public List<Paciente> buscarInactivos() {
        String sql = "SELECT * FROM paciente WHERE estado = false";
        return ejecutarConsulta(sql);
    }

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
}