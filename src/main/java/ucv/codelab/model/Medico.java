package ucv.codelab.model;

public class Medico extends Persona {

    private int id;
    private String area;
    private String email;
    private Integer experiencia;
    private String colegiatura;
    private String universidad;
    private String grado;

    public Medico(int id, int idPersona, String area, String email, Integer experiencia, String colegiatura,
            String universidad, String grado) {
        super();
        // Establece el ID de la persona en bdd
        setIdPersona(idPersona);
        // Establece los parámetros del médico
        this.id = id;
        this.area = area;
        this.email = email;
        this.experiencia = experiencia;
        this.colegiatura = colegiatura;
        this.universidad = universidad;
        this.grado = grado;
    }

    public Medico(int id, String area, String email, Integer experiencia, String colegiatura,
            String universidad, String grado, Persona persona) {
        // Inicia con todos los datos del constructor colocados
        super(persona.getIdPersona(), persona.getNombre(), persona.getApellido(), persona.getDni(),
                persona.getFechaNacimiento(), persona.getSexo(), persona.getDireccion(), persona.getTelefono());
        // Establece los parámetros del médico
        this.id = id;
        this.area = area;
        this.email = email;
        this.experiencia = experiencia;
        this.colegiatura = colegiatura;
        this.universidad = universidad;
        this.grado = grado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Integer experiencia) {
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

    @Override
    public boolean datosValidos() {
        // Si los datos obligatorios no son nulos
        if (super.datosValidos() &&
                validarDatosObligatorios(area, email, experiencia, colegiatura, universidad, grado)) {
            return true;
        }
        return false;
    }
}