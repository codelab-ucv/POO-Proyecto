package ucv.codelab.model;

import java.util.ArrayList;
import java.util.List;

public class Paciente {

    private int id;
    private int idPersona;
    private String tipoSangre;
    private String antecedentes;

    private Persona persona;

    private List<Condicion> condiciones = new ArrayList<>();

    public Paciente(int id, int idPersona, String tipoSangre, String antecedentes) {
        this.id = id;
        this.idPersona = idPersona;
        this.tipoSangre = tipoSangre;
        this.antecedentes = antecedentes;
    }

    public Paciente(int id, String tipoSangre, String antecedentes, Persona persona) {
        this(id, persona.getId(), tipoSangre, antecedentes);
        // Configura el objeto
        this.persona = persona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void addCondicion(Condicion condicion) {
        condiciones.add(condicion);
    }

    public void removeCondicion(int index) {
        condiciones.remove(index);
    }

    public List<Condicion> getCondiciones() {
        return condiciones;
    }

    public void clearCondiciones() {
        condiciones.clear();
    }
}
