package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ucv.codelab.enumerados.GradoAcademico;
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
        medico.setGradoAcademico(GradoAcademico.fromString(rs.getString("grado_academico")));

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
        stmt.setString(10, entity.getGradoAcademico().getValor());
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
        stmt.setString(10, entity.getGradoAcademico().getValor());
        stmt.setBoolean(11, entity.isEstado());
        stmt.setInt(12, entity.getIdMedico()); // WHERE clause
    }

    @Override
    protected void actualizarEntidadConIdGenerado(Medico entity, ResultSet generatedKeys) throws SQLException {
        entity.setIdMedico(generatedKeys.getInt(1));
    }

    // Métodos adicionales específicos para Medico

    /**
     * Busca medicos que coincidan con el DNI, nombre y apellido indicados y se
     * encuentren activos. Debe indicarse por lo menos un filtro, los demás son
     * opcionales
     * 
     * @param dni      el DNI del medico buscado, si es NUll lo omite
     * @param nombre   el Nombre del medico buscado, si es NULL lo omite
     * @param apellido el Apellido del medico buscado, si es NULL lo omite
     * @return Lista con medicos que coinciden con los filtros
     */
    public List<Medico> buscarFiltrado(String dni, String nombre, String apellido) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM medico WHERE estado = ?");

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
     * Obtiene todos los medicos activos de la tabla.
     * 
     * @return Lista de todos los medicos activas
     */
    @Override
    public List<Medico> buscarTodos() {
        String sql = "SELECT * FROM medico WHERE estado = ?";
        return ejecutarConsulta(sql, true);
    }

    /**
     * Desactiva un medico de la tabla.
     * 
     * @param id el ID del medico a desactivar
     */
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

    /**
     * Busca un medico activo según su ID.
     * 
     * @param id el ID del medico a buscar
     * @return un Optional que contiene el medico si se encuentra , o
     *         Optional.empty() si no existe
     */
    @Override
    public Optional<Medico> buscarPorId(int id) {
        String sql = "SELECT * FROM medico WHERE id_medico = ? AND estado = ?";
        return ejecutarConsultaSoloUnResultado(sql, id, true);
    }

    public Optional<Medico> buscarPorDni(String dni) {
        String sql = "SELECT * FROM medico WHERE dni = ? AND estado = ?";
        return ejecutarConsultaSoloUnResultado(sql, dni, true);
    }
}