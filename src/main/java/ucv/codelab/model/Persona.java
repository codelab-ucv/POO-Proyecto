package ucv.codelab.model;

import java.time.LocalDate;
import java.util.Objects;

public class Persona {

    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private String sexo;
    private String direccion;
    private String telefono;

    public Persona(int id, String nombre, String apellido, String dni, LocalDate fechaNacimiento, String sexo,
            String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
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

        return p.getId() == this.id &&
                Objects.equals(p.getNombre(), this.nombre) &&
                Objects.equals(p.getApellido(), this.apellido) &&
                Objects.equals(p.getDni(), this.dni) &&
                Objects.equals(p.getFechaNacimiento(), this.fechaNacimiento) &&
                Objects.equals(p.getSexo(), this.sexo) &&
                Objects.equals(p.getDireccion(), this.direccion) &&
                Objects.equals(p.getTelefono(), this.telefono);
    }

}
