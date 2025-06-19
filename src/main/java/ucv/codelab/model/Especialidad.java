package ucv.codelab.model;

public class Especialidad {
    private int idEspecialidad;
    private String especialidad;

    // Constructor vacío
    public Especialidad() {
    }

    // Constructor esencial (todos los campos son NOT NULL)
    public Especialidad(int idEspecialidad, String especialidad) {
        this.idEspecialidad = idEspecialidad;
        this.especialidad = especialidad;
    }

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
}