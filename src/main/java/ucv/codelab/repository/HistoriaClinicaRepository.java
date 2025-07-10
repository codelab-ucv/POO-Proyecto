package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ucv.codelab.model.HistoriaClinica;
import ucv.codelab.util.Mensajes;

public class HistoriaClinicaRepository extends BaseRepository<HistoriaClinica> {

    public HistoriaClinicaRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "historia_clinica";
    }

    @Override
    protected String getIdColumnName() {
        return "id_historia";
    }

    @Override
    protected HistoriaClinica convertirResultSetEnEntidad(ResultSet rs) throws SQLException {
        HistoriaClinica hc = new HistoriaClinica();
        hc.setIdHistoria(rs.getInt("id_historia"));
        hc.setIdPaciente(rs.getInt("id_paciente"));
        hc.setIdMedico(rs.getInt("id_medico"));
        hc.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
        hc.setMotivoConsulta(rs.getString("motivo_consulta"));
        hc.setAntecedentes(rs.getString("antecedentes"));
        hc.setTiempoEnfermedad(rs.getString("tiempo_enfermedad"));
        hc.setObservaciones(rs.getString("observaciones"));
        // Nuevo campo estado
        hc.setEstado(rs.getBoolean("estado"));
        return hc;
    }

    @Override
    protected String buildInsertSQL() {
        return "INSERT INTO historia_clinica (id_paciente, id_medico, motivo_consulta, antecedentes, tiempo_enfermedad, observaciones, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void establecerParametrosInsertar(PreparedStatement stmt, HistoriaClinica entity) throws SQLException {
        stmt.setInt(1, entity.getIdPaciente());
        stmt.setInt(2, entity.getIdMedico());
        stmt.setString(3, entity.getMotivoConsulta());

        if (entity.getAntecedentes() != null) {
            stmt.setString(4, entity.getAntecedentes());
        } else {
            stmt.setNull(4, Types.VARCHAR);
        }

        if (entity.getTiempoEnfermedad() != null) {
            stmt.setString(5, entity.getTiempoEnfermedad());
        } else {
            stmt.setNull(5, Types.VARCHAR);
        }

        if (entity.getObservaciones() != null) {
            stmt.setString(6, entity.getObservaciones());
        } else {
            stmt.setNull(6, Types.VARCHAR);
        }

        stmt.setBoolean(7, entity.isEstado());
    }

    @Override
    protected String buildUpdateSQL() {
        return "UPDATE historia_clinica SET id_paciente = ?, id_medico = ?, motivo_consulta = ?, " +
                "antecedentes = ?, tiempo_enfermedad = ?, observaciones = ?, estado = ? WHERE id_historia = ?";
    }

    @Override
    protected void establecerParametrosActualizar(PreparedStatement stmt, HistoriaClinica entity) throws SQLException {
        stmt.setInt(1, entity.getIdPaciente());
        stmt.setInt(2, entity.getIdMedico());
        stmt.setString(3, entity.getMotivoConsulta());

        if (entity.getAntecedentes() != null) {
            stmt.setString(4, entity.getAntecedentes());
        } else {
            stmt.setNull(4, Types.VARCHAR);
        }

        if (entity.getTiempoEnfermedad() != null) {
            stmt.setString(5, entity.getTiempoEnfermedad());
        } else {
            stmt.setNull(5, Types.VARCHAR);
        }

        if (entity.getObservaciones() != null) {
            stmt.setString(6, entity.getObservaciones());
        } else {
            stmt.setNull(6, Types.VARCHAR);
        }

        stmt.setBoolean(7, entity.isEstado());
        stmt.setInt(8, entity.getIdHistoria());
    }

    @Override
    protected void actualizarEntidadConIdGenerado(HistoriaClinica entity, ResultSet generatedKeys) throws SQLException {
        entity.setIdHistoria(generatedKeys.getInt(1));
    }

    public List<HistoriaClinica> buscarPorPaciente(int idPaciente) {
        String sql = "SELECT * FROM historia_clinica WHERE id_paciente = ?";
        return ejecutarConsulta(sql, idPaciente);
    }

    public List<HistoriaClinica> buscarPorMedico(int idMedico) {
        String sql = "SELECT * FROM historia_clinica WHERE id_medico = ?";
        return ejecutarConsulta(sql, idMedico);
    }

    public List<HistoriaClinica> buscarPorMedicoYFecha(int idMedico, LocalDate date) {
        String sql = "SELECT * FROM historia_clinica WHERE id_medico = ? AND DATE(fecha_hora) = ?";
        return ejecutarConsulta(sql, idMedico, date);
    }

    public List<HistoriaClinica> buscarPorDni(String dniPaciente, String dniMedico) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT hc.* FROM historia_clinica hc ");

        List<Object> parametros = new ArrayList<>();
        boolean joinPaciente = dniPaciente != null;
        boolean joinMedico = dniMedico != null;

        if (joinPaciente) {
            sql.append("JOIN paciente p ON p.id_paciente = hc.id_paciente ");
        }

        if (joinMedico) {
            sql.append("JOIN medico m ON m.id_medico = hc.id_medico ");
        }

        sql.append("WHERE ");
        boolean primeraCondicion = true;

        if (joinPaciente) {
            sql.append("p.dni = ?");
            parametros.add(dniPaciente);
            primeraCondicion = false;
        }

        if (joinMedico) {
            if (!primeraCondicion) {
                sql.append(" AND ");
            }
            sql.append("m.colegiatura = ?");
            parametros.add(dniMedico);
        }

        if (!primeraCondicion) {
            sql.append(" AND hc.estado = 1");
        }
        sql.append(" ORDER BY hc.fecha_hora DESC");

        return ejecutarConsulta(sql.toString(), parametros.toArray());
    }

    public List<HistoriaClinica> buscarPorNombre(String nombrePaciente, String apellidoPaciente,
            String nombreMedico, String apellidoMedico) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT hc.* FROM historia_clinica hc ");

        List<Object> parametros = new ArrayList<>();
        boolean joinPaciente = nombrePaciente != null || apellidoPaciente != null;
        boolean joinMedico = nombreMedico != null || apellidoMedico != null;

        if (joinPaciente) {
            sql.append("JOIN paciente p ON p.id_paciente = hc.id_paciente ");
        }

        if (joinMedico) {
            sql.append("JOIN medico m ON m.id_medico = hc.id_medico ");
        }

        sql.append("WHERE ");
        boolean primeraCondicion = true;

        // Condiciones para paciente
        if (joinPaciente) {
            if (nombrePaciente != null) {
                sql.append("p.nombre LIKE ?");
                parametros.add("%" + nombrePaciente + "%");
                primeraCondicion = false;
            }

            if (apellidoPaciente != null) {
                if (!primeraCondicion) {
                    sql.append(" AND ");
                }
                sql.append("p.apellido LIKE ?");
                parametros.add("%" + apellidoPaciente + "%");
                primeraCondicion = false;
            }
        }

        // Condiciones para médico
        if (joinMedico) {
            if (!primeraCondicion) {
                sql.append(" AND ");
            }

            if (nombreMedico != null) {
                sql.append("m.nombre LIKE ?");
                parametros.add("%" + nombreMedico + "%");
                primeraCondicion = false;
            }

            if (apellidoMedico != null) {
                if (!primeraCondicion) {
                    sql.append(" AND ");
                }
                sql.append("m.apellido LIKE ?");
                parametros.add("%" + apellidoMedico + "%");
            }
        }

        if (!primeraCondicion) {
            sql.append(" AND hc.estado = 1");
        }
        sql.append(" ORDER BY hc.fecha_hora DESC");

        return ejecutarConsulta(sql.toString(), parametros.toArray());
    }

    public List<Object[]> atencionesPorFecha(LocalDate fechaInicio, LocalDate fechaFin, String especialidad) {
        if (fechaInicio == null || fechaFin == null) {
            return new ArrayList<>();
        }

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT e.especialidad, ");
        sql.append("DATE(hc.fecha_hora) as fecha_atencion, ");
        sql.append("COUNT(hc.id_historia) as cantidad_atenciones ");
        sql.append("FROM historia_clinica hc ");
        sql.append("JOIN medico m ON hc.id_medico = m.id_medico ");
        sql.append("JOIN especialidad e ON m.id_especialidad = e.id_especialidad ");
        sql.append("WHERE DATE(hc.fecha_hora) BETWEEN ? AND ? "); // Entre fecha Inicio y Fin
        sql.append(" AND hc.estado = TRUE AND m.estado = TRUE AND e.estado = TRUE ");

        if (especialidad != null) {
            sql.append(" AND e.especialidad = ? "); // Añade la condicional para la especialidad
        }

        sql.append("GROUP BY DATE(hc.fecha_hora), e.id_especialidad, e.especialidad ");
        sql.append("ORDER BY especialidad ASC, fecha_atencion ASC, cantidad_atenciones DESC");

        List<Object[]> resultados = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql.toString())) {
            // Establecer parámetros
            pstmt.setDate(1, Date.valueOf(fechaInicio));
            pstmt.setDate(2, Date.valueOf(fechaFin));

            // Si hay especialidad, establecer el tercer parámetro
            if (especialidad != null) {
                pstmt.setString(3, especialidad);
            }

            // Ejecutar consulta
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Object[] fila = new Object[3];
                    fila[0] = rs.getString("especialidad");
                    fila[1] = rs.getDate("fecha_atencion");
                    fila[2] = rs.getInt("cantidad_atenciones");
                    resultados.add(fila);
                }
            }
        } catch (Exception e) {
        }
        return resultados;
    }

    /**
     * Desactiva una historia clinica de la tabla.
     * 
     * @param id el ID de la historia clinica a desactivar
     */
    public void desactivar(int id) {
        String sql = "UPDATE historia_clinica SET estado = ? WHERE id_historia = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Desativa la especialidad
            stmt.setBoolean(1, false);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            Mensajes.errorConexion("Error al eliminar la historia clínica con ID: " + id + " de " + getTableName());
        }
    }

    @Override
    public Optional<HistoriaClinica> buscarPorId(int id) {
        String sql = "SELECT * FROM historia_clinica WHERE id_historia = ? AND estado = ?";
        return ejecutarConsultaSoloUnResultado(sql, id, true);
    }
}