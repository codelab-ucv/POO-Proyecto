package ucv.codelab.model;

public class Medico {

    private int id;
    private int idPersona;
    private String area;
    private String email;
    private Integer experiencia;
    private String colegiatura;
    private String universidad;
    private String grado;

    private Persona persona;

    public Medico(int id, int idPersona, String area, String email, Integer experiencia, String colegiatura,
            String universidad, String grado) {
        this.id = id;
        this.idPersona = idPersona;
        this.area = area;
        this.email = email;
        this.experiencia = experiencia;
        this.colegiatura = colegiatura;
        this.universidad = universidad;
        this.grado = grado;
    }

    public Medico(int id, String area, String email, Integer experiencia, String colegiatura,
            String universidad, String grado, Persona persona) {
        this(id, persona.getId(), area, email, experiencia, colegiatura, universidad, grado);
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public String getColegiatura() {
        return colegiatura;
    }

    public void setColegiatura(String colegiatura) {
        this.colegiatura = colegiatura;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}