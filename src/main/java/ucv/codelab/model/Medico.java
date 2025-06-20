package ucv.codelab.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import ucv.codelab.enumerados.Sexo;

public class Medico {
    private int idMedico;
    private int idEspecialidad;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private Sexo sexo;
    private String telefono;
    private String email;
    private String colegiatura;
    private String gradoAcademico;
    private LocalDateTime fechaRegistro;
    private boolean estado;

    // Objetos relacionados al Médico, si están vacíos se mantienen null
    private Especialidad especialidad = null;

    // Constructor vacío
    public Medico() {
    }

    // Constructor esencial (campos NOT NULL)
    public Medico(int idEspecialidad, String nombre, String apellido,
            String dni, LocalDate fechaNacimiento, Sexo sexo,
            String colegiatura, String gradoAcademico, boolean estado) {
        this.idEspecialidad = idEspecialidad;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.colegiatura = colegiatura;
        this.gradoAcademico = gradoAcademico;
        this.estado = estado;
    }

    // Constructor completo (todos los campos)
    public Medico(int idMedico, int idEspecialidad, String nombre,
            String apellido, String dni, LocalDate fechaNacimiento,
            Sexo sexo, String telefono, String email, String colegiatura,
            String gradoAcademico, LocalDateTime fechaRegistro, boolean estado) {
        this(idEspecialidad, nombre, apellido, dni, fechaNacimiento, sexo,
                colegiatura, gradoAcademico, estado);
        this.idMedico = idMedico;
        this.telefono = telefono;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getColegiatura() {
        return colegiatura;
    }

    public void setColegiatura(String colegiatura) {
        this.colegiatura = colegiatura;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}