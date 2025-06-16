package ucv.codelab.model;

import java.time.LocalDate;
import java.util.Objects;

import ucv.codelab.enumerados.Sexo;

public class Persona {

    private int idPersona;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private Sexo sexo;
    private String direccion;
    private String telefono;

    // Inicializa con Id -1 para indicar que no tiene valores
    protected Persona() {
        idPersona = -1;
    }

    public Persona(int idPersona, String nombre, String apellido, String dni, LocalDate fechaNacimiento, Sexo sexo,
            String direccion, String telefono) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Persona)) {
            return false;
        }
        Persona p = (Persona) obj;

        return p.getIdPersona() == this.idPersona &&
                Objects.equals(p.getNombre(), this.nombre) &&
                Objects.equals(p.getApellido(), this.apellido) &&
                Objects.equals(p.getDni(), this.dni) &&
                Objects.equals(p.getFechaNacimiento(), this.fechaNacimiento) &&
                Objects.equals(p.getSexo(), this.sexo) &&
                Objects.equals(p.getDireccion(), this.direccion) &&
                Objects.equals(p.getTelefono(), this.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersona, nombre, apellido, dni, fechaNacimiento, sexo, direccion, telefono);
    }

    public void modificarDatosPersona(Persona persona) {
        this.idPersona = persona.getIdPersona();
        this.nombre = persona.getNombre();
        this.apellido = persona.getApellido();
        this.dni = persona.getDni();
        this.fechaNacimiento = persona.getFechaNacimiento();
        this.sexo = persona.getSexo();
        this.direccion = persona.getDireccion();
        this.telefono = persona.getTelefono();
    }

    public boolean datosValidos() {
        // Si los datos obligatorios no son nulos
        if (validarDatosObligatorios(nombre, apellido, dni, fechaNacimiento, sexo)) {
            return true;
        }
        return false;
    }

    public Persona getPersona() {
        return this;
    }

    protected boolean validarDatosObligatorios(Object... datos) {
        for (Object o : datos) {
            // Si es nulo
            if (Objects.isNull(o)) {
                return false;
            }
            // Si es un String vacio
            if (o instanceof String && ((String) o).trim().equals("")) {
                return false;
            }
        }
        return true;
    }
}
