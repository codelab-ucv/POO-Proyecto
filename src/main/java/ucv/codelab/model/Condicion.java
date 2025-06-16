package ucv.codelab.model;

import ucv.codelab.enumerados.Gravedad;

public class Condicion {

    private int id;
    private String tipo;
    private String condicion;
    private Gravedad gravedad;

    public Condicion(int id, String tipo, String condicion, Gravedad gravedad) {
        this.id = id;
        this.tipo = tipo;
        this.condicion = condicion;
        this.gravedad = gravedad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Gravedad getGravedad() {
        return gravedad;
    }

    public void setGravedad(Gravedad gravedad) {
        this.gravedad = gravedad;
    }

}
