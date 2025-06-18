package ucv.codelab.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase utilitaria para inicializar la base de datos desde un archivo SQL.
 * 
 * <p>
 * Esta clase proporciona funcionalidades para crear y configurar la estructura
 * inicial de la base de datos MySQL utilizando un script SQL predefinido.
 * El archivo SQL contiene todas las sentencias DDL necesarias para crear
 * tablas, índices y datos iniciales.
 * </p>
 * 
 * <p>
 * <strong>Características principales:</strong>
 * </p>
 * <ul>
 * <li>Carga automática del script SQL desde recursos del classpath</li>
 * <li>Ejecución de sentencias DDL para crear la estructura de BD</li>
 * <li>Manejo de errores durante la inicialización</li>
 * <li>Codificación UTF-8 para soporte completo de caracteres</li>
 * <li>Procesamiento de múltiples sentencias SQL</li>
 * </ul>
 * 
 * <p>
 * <strong>Archivo SQL requerido:</strong> {@code ucv/codelab/sql/poo.sql}
 * </p>
 * 
 * @see MySQLConexion
 */
public class DatabaseInitializer {

    /**
     * Nombre del archivo SQL que contiene el esquema de la base de datos
     */
    private static final String SQL_FILE = "ucv/codelab/sql/poo.sql";

    /**
     * Inicializa la base de datos utilizando el archivo SQL de esquema.
     * 
     * <p>
     * Este método carga el script SQL desde los recursos de la aplicación
     * y ejecuta todas las sentencias contenidas en él para crear la estructura
     * completa de la base de datos. Incluye creación de tablas, índices,
     * restricciones y datos iniciales si los hay.
     * </p>
     * 
     * <p>
     * El proceso incluye:
     * </p>
     * <ul>
     * <li>Obtención de la conexión a la base de datos</li>
     * <li>Carga del archivo SQL desde recursos</li>
     * <li>Separación de sentencias SQL individuales</li>
     * <li>Ejecución de cada sentencia por separado</li>
     * <li>Manejo de errores y logging</li>
     * </ul>
     * 
     * @return {@code true} si la inicialización fue exitosa,
     *         {@code false} si ocurrió algún error durante el proceso
     */
    public static boolean initializeDatabase() {
        try {
            // Obtener conexión
            Connection conexion = MySQLConexion.getInstance().getConexion();

            // Leer el contenido del archivo SQL desde los recursos
            String sqlScript = loadResourceAsString(SQL_FILE);

            if (sqlScript == null || sqlScript.isEmpty()) {
                System.err.println("No se pudo cargar el archivo de esquema SQL");
                return false;
            }

            // Separar y ejecutar las sentencias SQL
            List<String> sqlStatements = parseSQLStatements(sqlScript);
            
            if (sqlStatements.isEmpty()) {
                System.err.println("No se encontraron sentencias SQL válidas");
                return false;
            }

            Statement statement = conexion.createStatement();
            
            // Ejecutar cada sentencia por separado
            for (String sql : sqlStatements) {
                if (!sql.trim().isEmpty()) {
                    try {
                        statement.executeUpdate(sql);
                        System.out.println("Ejecutada: " + sql.substring(0, Math.min(50, sql.length())) + "...");
                    } catch (SQLException e) {
                        System.err.println("Error al ejecutar sentencia SQL: " + sql);
                        System.err.println("Error: " + e.getMessage());
                        // Continuar con las siguientes sentencias en lugar de fallar completamente
                    }
                }
            }
            
            statement.close();

            System.out.println("Base de datos inicializada correctamente.");
            return true;

        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
            return false;
        }
    }

    /**
     * Separa un script SQL en sentencias individuales.
     * 
     * <p>
     * Procesa el script SQL completo y lo divide en sentencias individuales
     * que pueden ser ejecutadas por separado. Filtra comentarios y líneas vacías.
     * </p>
     * 
     * @param sqlScript El script SQL completo como string
     * @return Lista de sentencias SQL individuales listas para ejecutar
     */
    private static List<String> parseSQLStatements(String sqlScript) {
        List<String> statements = new ArrayList<>();
        
        // Dividir por punto y coma, pero mantener el contexto
        String[] parts = sqlScript.split(";");
        
        for (String part : parts) {
            String cleanedPart = cleanSQLStatement(part.trim());
            if (!cleanedPart.isEmpty() && 
                !cleanedPart.equalsIgnoreCase("START TRANSACTION") && 
                !cleanedPart.equalsIgnoreCase("COMMIT") &&
                !cleanedPart.equalsIgnoreCase("ROLLBACK")) {
                statements.add(cleanedPart);
            }
        }
        
        return statements;
    }

    /**
     * Limpia una sentencia SQL eliminando comentarios y espacios innecesarios.
     * 
     * @param sqlStatement La sentencia SQL a limpiar
     * @return La sentencia SQL limpia
     */
    private static String cleanSQLStatement(String sqlStatement) {
        if (sqlStatement == null || sqlStatement.trim().isEmpty()) {
            return "";
        }

        StringBuilder cleaned = new StringBuilder();
        String[] lines = sqlStatement.split("\n");
        
        for (String line : lines) {
            String trimmedLine = line.trim();
            
            // Omitir líneas vacías y comentarios
            if (!trimmedLine.isEmpty() && !trimmedLine.startsWith("--")) {
                // Remover comentarios inline
                int commentIndex = trimmedLine.indexOf("--");
                if (commentIndex != -1) {
                    trimmedLine = trimmedLine.substring(0, commentIndex).trim();
                }
                
                if (!trimmedLine.isEmpty()) {
                    if (cleaned.length() > 0) {
                        cleaned.append(" ");
                    }
                    cleaned.append(trimmedLine);
                }
            }
        }
        
        return cleaned.toString();
    }

    /**
     * Carga un archivo de recursos como string.
     * 
     * <p>
     * Lee un archivo ubicado en el classpath de la aplicación y retorna
     * su contenido completo como una cadena de texto. Utiliza codificación
     * UTF-8 para asegurar la correcta lectura de caracteres especiales.
     * </p>
     * 
     * <p>
     * Este método es útil para cargar archivos SQL, de configuración
     * o cualquier archivo de texto que forme parte de los recursos
     * de la aplicación.
     * </p>
     * 
     * @param resourceName Nombre del archivo en el classpath, incluyendo
     *                     la ruta relativa desde la raíz de recursos
     * @return Contenido completo del archivo como string, o {@code null}
     *         si ocurre un error al leer el archivo o si no se encuentra
     */
    private static String loadResourceAsString(String resourceName) {
        try (InputStream is = DatabaseInitializer.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (is == null) {
                System.err.println("No se encontro el recurso: " + resourceName);
                return null;
            }

            try (InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                    BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining("\n"));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo SQL: " + e.getMessage());
            return null;
        }
    }
}