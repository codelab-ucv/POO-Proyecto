package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio base abstracto para todas las entidades.
 * Esta clase proporciona la implementación común para operaciones CRUD,
 * trabajando con SQLite como base de datos.
 * 
 * @param <T> Tipo de entidad que maneja el repositorio
 */
public abstract class BaseRepository<T> {

    /**
     * Conexión a la base de datos MySQL utilizada para todas las operaciones.
     */
    protected Connection connection;

    /**
     * Constructor que recibe la conexión a la base de datos
     * 
     * @param connection Conexión activa a la base de datos MySQL
     */
    public BaseRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Obtiene el nombre de la tabla asociada a la entidad.
     * 
     * @return el nombre de la tabla en la base de datos
     */
    protected abstract String getTableName();

    /**
     * Obtiene el nombre de la columna que representa la clave primaria.
     * <p>
     * Por defecto retorna "id", pero puede ser sobrescrito si la tabla
     * utiliza un nombre diferente para la clave primaria.
     * </p>
     * 
     * @return el nombre de la columna de la clave primaria (por defecto "id")
     */
    protected String getIdColumnName() {
        // Por defecto todas las columnas con el ID tienen el valor ID
        return "id";
    }

    /**
     * Convierte un ResultSet en una instancia de la entidad.
     * 
     * @param rs el ResultSet que contiene los datos de la base de datos
     * @return una instancia de la entidad con los datos del ResultSet
     * @throws SQLException si ocurre un error al acceder a los datos del ResultSet
     */
    protected abstract T convertirResultSetEnEntidad(ResultSet rs) throws SQLException;

    /**
     * Construye la consulta SQL para insertar una nueva entidad.
     * 
     * @return la consulta SQL de inserción (INSERT)
     */
    protected abstract String buildInsertSQL();

    /**
     * Establece los parámetros en el PreparedStatement para la operación de
     * inserción.
     * 
     * @param stmt   el PreparedStatement donde se establecerán los parámetros
     * @param entity la entidad cuyos datos se utilizarán para los parámetros
     * @throws SQLException si ocurre un error al establecer los parámetros
     */
    protected abstract void establecerParametrosInsertar(PreparedStatement stmt, T entity) throws SQLException;

    /**
     * Construye la consulta SQL para actualizar una entidad existente.
     * 
     * @return la consulta SQL de actualización (UPDATE)
     */
    protected abstract String buildUpdateSQL();

    /**
     * Establece los parámetros en el PreparedStatement para la operación de
     * actualización.
     * 
     * @param stmt   el PreparedStatement donde se establecerán los parámetros
     * @param entity la entidad cuyos datos se utilizarán para los parámetros
     * @throws SQLException si ocurre un error al establecer los parámetros
     */
    protected abstract void establecerParametrosActualizar(PreparedStatement stmt, T entity) throws SQLException;

    /**
     * Actualiza la entidad con el ID generado automáticamente por la base de datos.
     * <p>
     * Este método se llama después de una inserción exitosa para asignar
     * el ID generado automáticamente a la entidad.
     * </p>
     * 
     * @param entity        la entidad que se actualizará con el ID generado
     * @param generatedKeys el ResultSet que contiene las claves generadas
     * @throws SQLException si ocurre un error al obtener o asignar el ID generado
     */
    protected abstract void actualizarEntidadConIdGenerado(T entity, ResultSet generatedKeys) throws SQLException;

    /**
     * Crea una nueva entidad en la base de datos.
     * <p>
     * Este método inserta la entidad en la base de datos y actualiza
     * automáticamente el ID generado en la entidad proporcionada.
     * </p>
     * 
     * @param entidad la entidad a crear en la base de datos
     * @throws RuntimeException si ocurre un error durante la operación de inserción
     */
    public void crear(T entidad) {
        String sql = buildInsertSQL();

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            establecerParametrosInsertar(stmt, entidad);
            stmt.executeUpdate();

            // Obtener ID generado
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Actualiza el modelo colocando el ID generado
                    actualizarEntidadConIdGenerado(entidad, generatedKeys);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar entidad en " + getTableName(), e);
        }
    }

    /**
     * Busca una entidad por su ID.
     * 
     * @param id el ID de la entidad a buscar
     * @return un Optional que contiene la entidad si se encuentra, o
     *         Optional.empty() si no existe
     * @throws RuntimeException si ocurre un error durante la consulta
     */
    public Optional<T> buscarPorId(int id) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            // Se cierra en automatico al cerrar el stmt
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(convertirResultSetEnEntidad(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar entidad por ID: " + id, e);
        }
    }

    /**
     * Obtiene todas las entidades de la tabla.
     * 
     * @return una lista con todas las entidades encontradas en la tabla
     * @throws RuntimeException si ocurre un error durante la consulta
     */
    public List<T> buscarTodos() {
        List<T> resultado = new ArrayList<>();
        String sql = "SELECT * FROM " + getTableName();

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                resultado.add(convertirResultSetEnEntidad(rs));
            }
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todas las entidades de " + getTableName(), e);
        }
    }

    /**
     * Actualiza una entidad existente en la base de datos.
     * <p>
     * La entidad debe tener un ID válido que corresponda a un registro existente.
     * </p>
     * 
     * @param entidad la entidad con los datos actualizados
     * @throws RuntimeException si ocurre un error durante la operación de
     *                          actualización
     */
    public void actualizar(T entidad) {
        String sql = buildUpdateSQL();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            establecerParametrosActualizar(stmt, entidad);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar entidad en " + getTableName(), e);
        }
    }

    /**
     * Ejecuta una consulta personalizada
     * 
     * @param sql        Sentencia SQL a ejecutar
     * @param parameters Parámetros para la sentencia preparada
     * @return Lista de entidades que coinciden con la consulta
     */
    protected List<T> ejecutarConsulta(String sql, Object... parameters) {
        List<T> result = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Establecer parámetros
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof Integer) {
                    stmt.setInt(i + 1, (Integer) parameters[i]);
                } else if (parameters[i] instanceof String) {
                    stmt.setString(i + 1, (String) parameters[i]);
                } else if (parameters[i] instanceof Double) {
                    stmt.setDouble(i + 1, (Double) parameters[i]);
                } else if (parameters[i] instanceof LocalDate) {
                    stmt.setDate(i + 1, Date.valueOf((LocalDate) parameters[i]));
                } else if (parameters[i] == null) {
                    stmt.setNull(i + 1, Types.NULL);
                } else {
                    throw new IllegalArgumentException(
                            "Tipo de parámetro no soportado: " + parameters[i].getClass().getName());
                }
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(convertirResultSetEnEntidad(rs));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar consulta personalizada: " + sql, e);
        }
    }

    /**
     * Ejecuta una consulta que devuelve una única entidad o ninguna
     * 
     * @param sql        Sentencia SQL a ejecutar
     * @param parameters Parámetros para la sentencia preparada
     * @return Optional con la entidad encontrada o vacío si no existe
     */
    protected Optional<T> ejecutarConsultaSoloUnResultado(String sql, Object... parameters) {
        List<T> results = ejecutarConsulta(sql, parameters);
        if (results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(results.get(0));
    }
}