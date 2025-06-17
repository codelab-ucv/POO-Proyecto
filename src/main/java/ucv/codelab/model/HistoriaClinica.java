package ucv.codelab.model;

import java.time.LocalDateTime;

public class HistoriaClinica {
    private int idHistoria;
    private int idPaciente;
    private int idMedico;
    private LocalDateTime fechaHora;
    private String motivoConsulta;
    private String antecedentes;
    private String tiempoEnfermedad;
    private String observaciones;

    // Constructor vacío
    public HistoriaClinica() {
        this.fechaHora = LocalDateTime.now();
    }

    // Constructor esencial (campos NOT NULL)
    public HistoriaClinica(int idPaciente, int idMedico, String motivoConsulta) {
        this();
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.motivoConsulta = motivoConsulta;
    }

    // Constructor completo (todos los campos)
    public HistoriaClinica(int idHistoria, int idPaciente, int idMedico,
            LocalDateTime fechaHora, String motivoConsulta,
            String antecedentes, String tiempoEnfermedad,
            String observaciones) {
        this(idPaciente, idMedico, motivoConsulta);
        this.idHistoria = idHistoria;
        this.fechaHora = fechaHora;
        this.antecedentes = antecedentes;
        this.tiempoEnfermedad = tiempoEnfermedad;
        this.observaciones = observaciones;
    }

    public int getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(int idHistoria) {
        this.idHistoria = idHistoria;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getTiempoEnfermedad() {
        return tiempoEnfermedad;
    }

    public void setTiempoEnfermedad(String tiempoEnfermedad) {
        this.tiempoEnfermedad = tiempoEnfermedad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}