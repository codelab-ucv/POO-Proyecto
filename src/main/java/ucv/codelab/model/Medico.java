package ucv.codelab.model;

public class Medico {
    private int idMedico;
    private String nombre;
    private String apellido;
    private String colegiatura;
    private String especialidad;
    private String telefono;
    private boolean estado;

    // Constructor vacío
    public Medico() {
    }

    // Constructor esencial (campos NOT NULL)
    public Medico(String nombre, String apellido, String colegiatura, boolean estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.colegiatura = colegiatura;
        this.estado = estado;
    }

    // Constructor completo (todos los campos)
    public Medico(int idMedico, String nombre, String apellido, String colegiatura,
            String especialidad, String telefono, boolean estado) {
        this(nombre, apellido, colegiatura, estado);
        this.idMedico = idMedico;
        this.especialidad = especialidad;
        this.telefono = telefono;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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
}