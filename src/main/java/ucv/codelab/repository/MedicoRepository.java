package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import ucv.codelab.enumerados.Sexo;
import ucv.codelab.model.Medico;
import ucv.codelab.util.Mensajes;

public class MedicoRepository extends BaseRepository<Medico> {

    public MedicoRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "medico";
    }

    @Override
    protected String getIdColumnName() {
        return "id_medico";
    }

    @Override
    protected Medico convertirResultSetEnEntidad(ResultSet rs) throws SQLException {
        Medico medico = new Medico();
        medico.setIdMedico(rs.getInt("id_medico"));
        medico.setIdEspecialidad(rs.getInt("id_especialidad"));
        medico.setNombre(rs.getString("nombre"));
        medico.setApellido(rs.getString("apellido"));
        medico.setDni(rs.getString("dni"));

        // Convertir DATE a LocalDate
        Date fechaNacimiento = rs.getDate("fecha_nacimiento");
        if (fechaNacimiento != null) {
            medico.setFechaNacimiento(fechaNacimiento.toLocalDate());
        }

        medico.setSexo(Sexo.fromString(rs.getString("sexo")));
        medico.setColegiatura(rs.getString("colegiatura"));
        medico.setGradoAcademico(rs.getString("grado_academico"));

        // Campos opcionales (pueden ser NULL)
        if (rs.getString("telefono") != null) {
            medico.setTelefono(rs.getString("telefono"));
        }

        if (rs.getString("email") != null) {
            medico.setEmail(rs.getString("email"));
        }

        // Convertir TIMESTAMP a LocalDateTime
        Timestamp fechaRegistro = rs.getTimestamp("fecha_registro");
        if (fechaRegistro != null) {
            medico.setFechaRegistro(fechaRegistro.toLocalDateTime());
        }

        medico.setEstado(rs.getBoolean("estado"));
        return medico;
    }

    @Override
    protected String buildInsertSQL() {
        return "INSERT INTO medico (id_especialidad, nombre, apellido, dni, fecha_nacimiento, " +
                "sexo, telefono, email, colegiatura, grado_academico, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void establecerParametrosInsertar(PreparedStatement stmt, Medico entity) throws SQLException {
        stmt.setInt(1, entity.getIdEspecialidad());
        stmt.setString(2, entity.getNombre());
        stmt.setString(3, entity.getApellido());
        stmt.setString(4, entity.getDni());

        // Convertir LocalDate a Date
        if (entity.getFechaNacimiento() != null) {
            stmt.setDate(5, Date.valueOf(entity.getFechaNacimiento()));
        } else {
            stmt.setNull(5, Types.DATE);
        }

        stmt.setString(6, entity.getSexo().getValor());

        // Campos opcionales
        if (entity.getTelefono() != null) {
            stmt.setString(7, entity.getTelefono());
        } else {
            stmt.setNull(7, Types.VARCHAR);
        }

        if (entity.getEmail() != null) {
            stmt.setString(8, entity.getEmail());
        } else {
            stmt.setNull(8, Types.VARCHAR);
        }

        stmt.setString(9, entity.getColegiatura());
        stmt.setString(10, entity.getGradoAcademico());
        stmt.setBoolean(11, entity.isEstado());
    }

    @Override
    protected String buildUpdateSQL() {
        return "UPDATE medico SET id_especialidad = ?, nombre = ?, apellido = ?, dni = ?, " +
                "fecha_nacimiento = ?, sexo = ?, telefono = ?, email = ?, colegiatura = ?, " +
                "grado_academico = ?, estado = ? WHERE id_medico = ?";
    }

    @Override
    protected void establecerParametrosActualizar(PreparedStatement stmt, Medico entity) throws SQLException {
        stmt.setInt(1, entity.getIdEspecialidad());
        stmt.setString(2, entity.getNombre());
        stmt.setString(3, entity.getApellido());
        stmt.setString(4, entity.getDni());

        // Convertir LocalDate a Date
        if (entity.getFechaNacimiento() != null) {
            stmt.setDate(5, Date.valueOf(entity.getFechaNacimiento()));
        } else {
            stmt.setNull(5, Types.DATE);
        }

        stmt.setString(6, entity.getSexo().getValor());

        // Campos opcionales
        if (entity.getTelefono() != null) {
            stmt.setString(7, entity.getTelefono());
        } else {
            stmt.setNull(7, Types.VARCHAR);
        }

        if (entity.getEmail() != null) {
            stmt.setString(8, entity.getEmail());
        } else {
            stmt.setNull(8, Types.VARCHAR);
        }

        stmt.setString(9, entity.getColegiatura());
        stmt.setString(10, entity.getGradoAcademico());
        stmt.setBoolean(11, entity.isEstado());
        stmt.setInt(12, entity.getIdMedico()); // WHERE clause
    }

    @Override
    protected void actualizarEntidadConIdGenerado(Medico entity, ResultSet generatedKeys) throws SQLException {
        entity.setIdMedico(generatedKeys.getInt(1));
    }

    // Métodos adicionales específicos para Medico
    public Optional<Medico> buscarPorColegiatura(String colegiatura) {
        String sql = "SELECT * FROM medico WHERE colegiatura = ?";
        return ejecutarConsultaSoloUnResultado(sql, colegiatura);
    }

    public Optional<Medico> buscarPorDni(String dni) {
        String sql = "SELECT * FROM medico WHERE dni = ?";
        return ejecutarConsultaSoloUnResultado(sql, dni);
    }

    public Optional<Medico> buscarPorEmail(String email) {
        String sql = "SELECT * FROM medico WHERE email = ?";
        return ejecutarConsultaSoloUnResultado(sql, email);
    }

    public List<Medico> buscarPorEspecialidad(int idEspecialidad) {
        String sql = "SELECT * FROM medico WHERE id_especialidad = ?";
        return ejecutarConsulta(sql, idEspecialidad);
    }

    public List<Medico> buscarPorGradoAcademico(String gradoAcademico) {
        String sql = "SELECT * FROM medico WHERE grado_academico = ?";
        return ejecutarConsulta(sql, gradoAcademico);
    }

    public List<Medico> buscarActivos() {
        String sql = "SELECT * FROM medico WHERE estado = true";
        return ejecutarConsulta(sql);
    }

    public List<Medico> buscarInactivos() {
        String sql = "SELECT * FROM medico WHERE estado = false";
        return ejecutarConsulta(sql);
    }

    public List<Medico> buscarPorRangoEdad(int edadMinima, int edadMaxima) {
        LocalDate fechaMaxima = LocalDate.now().minusYears(edadMinima);
        LocalDate fechaMinima = LocalDate.now().minusYears(edadMaxima + 1);

        String sql = "SELECT * FROM medico WHERE fecha_nacimiento BETWEEN ? AND ?";
        return ejecutarConsulta(sql, fechaMinima, fechaMaxima);
    }

    public void desactivar(int id) {
        String sql = "UPDATE medico SET estado = ? WHERE id_medico = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Desactiva el medico
            stmt.setBoolean(1, false);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            Mensajes.errorConexion("Error al eliminar el medico con ID: " + id + " de " + getTableName());
        }
    }
}