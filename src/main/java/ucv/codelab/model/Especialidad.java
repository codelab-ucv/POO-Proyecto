package ucv.codelab.model;

public class Especialidad {
    private int idEspecialidad;
    private String especialidad;
    private Double costoConsulta;
    private String descripcion;
    private Integer consultoriosAsignados;
    private String requisitosEspeciales;
    private boolean estado;

    // Constructor vacío
    public Especialidad() {
    }

    // Constructor esencial (solo campos NOT NULL)
    public Especialidad(String especialidad, Double costoConsulta, Integer consultoriosAsignados, boolean estado) {
        this.especialidad = especialidad;
        this.costoConsulta = costoConsulta;
        this.consultoriosAsignados = consultoriosAsignados;
        this.estado = estado;
    }

    // Constructor completo (todos los campos)
    public Especialidad(int idEspecialidad, String especialidad, Double costoConsulta, String descripcion,
            Integer consultoriosAsignados, String requisitosEspeciales, boolean estado) {
        this(especialidad, costoConsulta, consultoriosAsignados, estado);
        this.idEspecialidad = idEspecialidad;
        this.descripcion = descripcion;
        this.requisitosEspeciales = requisitosEspeciales;
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

    public Double getCostoConsulta() {
        return costoConsulta;
    }

    public void setCostoConsulta(Double costoConsulta) {
        this.costoConsulta = costoConsulta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getConsultoriosAsignados() {
        return consultoriosAsignados;
    }

    public void setConsultoriosAsignados(Integer consultoriosAsignados) {
        this.consultoriosAsignados = consultoriosAsignados;
    }

    public String getRequisitosEspeciales() {
        return requisitosEspeciales;
    }

    public void setRequisitosEspeciales(String requisitosEspeciales) {
        this.requisitosEspeciales = requisitosEspeciales;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Object[] registro() {
        return new Object[] { idEspecialidad, especialidad, costoConsulta, descripcion, consultoriosAsignados,
                requisitosEspeciales };
    }
}