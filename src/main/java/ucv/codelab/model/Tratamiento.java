package ucv.codelab.model;

public class Tratamiento {
    private int idTratamiento;
    private int idHistoria;
    private String descripcion;
    private String indicaciones;

    // Constructor vacío
    public Tratamiento() {
    }

    // Constructor esencial (campos NOT NULL)
    public Tratamiento(int idHistoria, String descripcion) {
        this.idHistoria = idHistoria;
        this.descripcion = descripcion;
    }

    // Constructor completo (todos los campos)
    public Tratamiento(int idTratamiento, int idHistoria, String descripcion,
            String indicaciones) {
        this(idHistoria, descripcion);
        this.idTratamiento = idTratamiento;
        this.indicaciones = indicaciones;
    }

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public int getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(int idHistoria) {
        this.idHistoria = idHistoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }
}