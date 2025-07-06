package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ucv.codelab.model.HistoriaClinica;

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

        sql.append(" ORDER BY hc.fecha_hora DESC");

        return ejecutarConsulta(sql.toString(), parametros.toArray());
    }
}