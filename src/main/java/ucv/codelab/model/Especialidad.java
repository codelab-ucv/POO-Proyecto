package ucv.codelab.model;

public class Especialidad {
    private int idEspecialidad;
    private String especialidad;
    private String descripcion;

    // Constructor vacío
    public Especialidad() {
    }

    // Constructor esencial (solo campos NOT NULL)
    public Especialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    // Constructor completo (todos los campos)
    public Especialidad(int idEspecialidad, String especialidad, String descripcion) {
        this.idEspecialidad = idEspecialidad;
        this.especialidad = especialidad;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}