package ucv.codelab.model;

import java.util.ArrayList;
import java.util.List;

import ucv.codelab.enumerados.TipoSangre;

public class Paciente extends Persona {

    private int id;
    private TipoSangre tipoSangre;
    private String antecedentes;

    private List<Condicion> condiciones = new ArrayList<>();

    public Paciente(int id, int idPersona, TipoSangre tipoSangre, String antecedentes) {
        super();
        // Establece el ID de la persona en bdd
        setIdPersona(idPersona);
        // Establece los parámetros del paciente
        this.id = id;
        this.tipoSangre = tipoSangre;
        this.antecedentes = antecedentes;
    }

    public Paciente(int id, TipoSangre tipoSangre, String antecedentes, Persona persona) {
        // Inicia con todos los datos del constructor colocados
        super(persona.getIdPersona(), persona.getNombre(), persona.getApellido(), persona.getDni(),
                persona.getFechaNacimiento(), persona.getSexo(), persona.getDireccion(), persona.getTelefono());
        // Establece los parámetros del médico
        this.id = id;
        this.tipoSangre = tipoSangre;
        this.antecedentes = antecedentes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoSangre getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(TipoSangre tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
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

    public void setCondiciones(List<Condicion> condiciones) {
        this.condiciones = condiciones;
    }

    public void clearCondiciones() {
        condiciones.clear();
    }

    @Override
    public boolean datosValidos() {
        // Si los datos obligatorios no son nulos
        if (super.datosValidos() && validarDatosObligatorios(tipoSangre)) {
            return true;
        }
        return false;
    }
}
