package ucv.codelab.model;

import ucv.codelab.enumerados.TipoDiagnostico;

public class Diagnostico {
    private int idDiagnostico;
    private int idHistoria;
    private TipoDiagnostico tipo;
    private String descripcion;
    private String codigoCIE10;

    // Constructor vacío
    public Diagnostico() {
    }

    // Constructor esencial (campos NOT NULL implícitos)
    public Diagnostico(int idHistoria, TipoDiagnostico tipo) {
        this.idHistoria = idHistoria;
        this.tipo = tipo;
    }

    // Constructor completo (todos los campos)
    public Diagnostico(int idDiagnostico, int idHistoria, TipoDiagnostico tipo,
            String descripcion, String codigoCIE10) {
        this(idHistoria, tipo);
        this.idDiagnostico = idDiagnostico;
        this.descripcion = descripcion;
        this.codigoCIE10 = codigoCIE10;
    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public int getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(int idHistoria) {
        this.idHistoria = idHistoria;
    }

    public TipoDiagnostico getTipo() {
        return tipo;
    }

    public void setTipo(TipoDiagnostico tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoCIE10() {
        return codigoCIE10;
    }

    public void setCodigoCIE10(String codigoCIE10) {
        this.codigoCIE10 = codigoCIE10;
    }
}