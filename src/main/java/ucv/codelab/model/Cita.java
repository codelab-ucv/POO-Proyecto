package ucv.codelab.model;

import java.time.LocalDate;

public class Cita {

    private int id;
    private int idPaciente;
    private int idMedico;
    private Double talla;
    private Double peso;
    private LocalDate fechaAtencion;
    private String estadoPaciente;
    private String sintomas;
    private String diagnostico;
    private String tratamiento;
    private String observacion;

    private Paciente paciente;
    private Medico medico;

    public Cita(int id, int idPaciente, int idMedico, Double talla, Double peso, LocalDate fechaAtencion,
            String estadoPaciente, String sintomas, String diagnostico, String tratamiento, String observacion) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.talla = talla;
        this.peso = peso;
        this.fechaAtencion = fechaAtencion;
        this.estadoPaciente = estadoPaciente;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observacion = observacion;
    }

    public Cita(int id, Double talla, Double peso, LocalDate fechaAtencion, String estadoPaciente, String sintomas,
            String diagnostico, String tratamiento, String observacion, Paciente paciente, Medico medico) {
        this(id, paciente.getId(), medico.getId(), talla, peso, fechaAtencion, estadoPaciente, sintomas, diagnostico,
                tratamiento, observacion);
        // Configura los objetos
        this.paciente = paciente;
        this.medico = medico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getTalla() {
        return talla;
    }

    public void setTalla(Double talla) {
        this.talla = talla;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public LocalDate getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(LocalDate fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public String getEstadoPaciente() {
        return estadoPaciente;
    }

    public void setEstadoPaciente(String estadoPaciente) {
        this.estadoPaciente = estadoPaciente;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

}
