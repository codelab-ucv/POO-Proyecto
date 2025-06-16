package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import ucv.codelab.enumerados.Sexo;
import ucv.codelab.model.Persona;

public class PersonaRepository extends BaseRepository<Persona> {

    public PersonaRepository(Connection conexion) {
        super(conexion);
    }

    @Override
    protected String getTableName() {
        return "persona";
    }

    @Override
    protected Persona convertirResultSetEnEntidad(ResultSet rs) throws SQLException {
        return new Persona(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("dni"),
                rs.getDate("fecha_nacimiento").toLocalDate(),
                Sexo.fromString(rs.getString("sexo")),
                rs.getString("direccion"),
                rs.getString("telefono"));
    }

    @Override
    protected String buildInsertSQL() {
        return "INSERT INTO persona(nombre, apellido, dni, fecha_nacimiento, sexo, direccion, telefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void establecerParametrosInsertar(PreparedStatement stmt, Persona persona) throws SQLException {
        stmt.setString(1, persona.getNombre());
        stmt.setString(2, persona.getApellido());
        stmt.setString(3, persona.getDni());
        stmt.setDate(4, Date.valueOf(persona.getFechaNacimiento()));
        stmt.setString(5, persona.getSexo().getValor());
        stmt.setString(6, persona.getDireccion());
        stmt.setString(7, persona.getTelefono());
    }

    @Override
    protected String buildUpdateSQL() {
        return "UPDATE persona SET nombre = ?, apellido = ?, dni = ?, fecha_nacimiento = ?, sexo = ?, direccion = ?, telefono = ? WHERE id = ?";
    }

    @Override
    protected void establecerParametrosActualizar(PreparedStatement stmt, Persona persona) throws SQLException {
        stmt.setString(1, persona.getNombre());
        stmt.setString(2, persona.getApellido());
        stmt.setString(3, persona.getDni());
        stmt.setDate(4, Date.valueOf(persona.getFechaNacimiento()));
        stmt.setString(5, persona.getSexo().getValor());
        stmt.setString(6, persona.getDireccion());
        stmt.setString(7, persona.getTelefono());
        // El ID va al final para el WHERE
        stmt.setInt(8, persona.getIdPersona());
    }

    @Override
    protected void actualizarEntidadConIdGenerado(Persona persona, ResultSet generatedKeys) throws SQLException {
        persona.setIdPersona(generatedKeys.getInt(1));
    }

    public Optional<Persona> buscarPorDni(String dni) {
        String sql = "SELECT * FROM persona WHERE dni = ?";
        return ejecutarConsultaSoloUnResultado(sql, dni);
    }
}