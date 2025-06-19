package ucv.codelab.model;

public class Medico {
    private int idMedico;
    private int idEspecialidad;
    private String nombre;
    private String apellido;
    private String colegiatura;
    private String telefono;
    private boolean estado;

    // Objetos relacionados al Médico, si están vacíos se mantienen null
    private Especialidad especialidad = null;

    // Constructor vacío
    public Medico() {
    }

    // Constructor esencial (campos NOT NULL)
    public Medico(int idEspecialidad, String nombre, String apellido,
            String colegiatura, boolean estado) {
        this.idEspecialidad = idEspecialidad;
        this.nombre = nombre;
        this.apellido = apellido;
        this.colegiatura = colegiatura;
        this.estado = estado;
    }

    // Constructor completo (todos los campos)
    public Medico(int idMedico, int idEspecialidad, String nombre,
            String apellido, String colegiatura, String telefono,
            boolean estado) {
        this(idEspecialidad, nombre, apellido, colegiatura, estado);
        this.idMedico = idMedico;
        this.telefono = telefono;
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

    public String getColegiatura() {
        return colegiatura;
    }

    public void setColegiatura(String colegiatura) {
        this.colegiatura = colegiatura;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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