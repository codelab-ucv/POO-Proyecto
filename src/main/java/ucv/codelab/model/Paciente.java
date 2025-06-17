package ucv.codelab.model;

import java.time.LocalDate;

import ucv.codelab.enumerados.Sexo;
import ucv.codelab.enumerados.TipoSangre;

public class Paciente {
    private int idPaciente;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private Sexo sexo;
    private String direccion;
    private String telefono;
    private TipoSangre tipoSangre;

    // Constructor vacío
    public Paciente() {
    }

    // Constructor esencial (campos NOT NULL)
    public Paciente(String nombre, String apellido, String dni,
            LocalDate fechaNacimiento, Sexo sexo, TipoSangre tipoSangre) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.tipoSangre = tipoSangre;
    }

    // Constructor completo (todos los campos)
    public Paciente(int idPaciente, String nombre, String apellido, String dni,
            LocalDate fechaNacimiento, Sexo sexo, String direccion,
            String telefono, TipoSangre tipoSangre) {
        this(nombre, apellido, dni, fechaNacimiento, sexo, tipoSangre);
        this.idPaciente = idPaciente;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoSangre getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(TipoSangre tipoSangre) {
        this.tipoSangre = tipoSangre;
    }
}