package ucv.codelab.model;

public class ExamenFisico {
    private int idExamen;
    private int idHistoria;
    private Double peso;
    private Double talla;
    private String presionArterial;
    private Double temperatura;
    private Integer frecuenciaCardiaca;
    private Integer frecuenciaRespiratoria;

    // Constructor vacío
    public ExamenFisico() {
    }

    // Constructor esencial (campos clave)
    public ExamenFisico(int idHistoria) {
        this.idHistoria = idHistoria;
    }

    // Constructor completo (todos los campos)
    public ExamenFisico(int idExamen, int idHistoria, Double peso, Double talla,
            String presionArterial, Double temperatura,
            Integer frecuenciaCardiaca, Integer frecuenciaRespiratoria) {
        this(idHistoria);
        this.idExamen = idExamen;
        this.peso = peso;
        this.talla = talla;
        this.presionArterial = presionArterial;
        this.temperatura = temperatura;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public int getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(int idHistoria) {
        this.idHistoria = idHistoria;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getTalla() {
        return talla;
    }

    public void setTalla(Double talla) {
        this.talla = talla;
    }

    public String getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(String presionArterial) {
        this.presionArterial = presionArterial;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Integer getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(Integer frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public Integer getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public void setFrecuenciaRespiratoria(Integer frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }
}