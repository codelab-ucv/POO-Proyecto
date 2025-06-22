package ucv.codelab.enumerados;

public enum GradoAcademico {
    LICENCIADO("licenciado"),
    FEMENINO("magister"),
    DOCTOR("doctor");

    private final String valor;

    GradoAcademico(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static GradoAcademico fromString(String texto) {
        for (GradoAcademico gradoAcademico : GradoAcademico.values()) {
            if (gradoAcademico.getValor().equalsIgnoreCase(texto) ||
                    gradoAcademico.name().equalsIgnoreCase(texto)) {
                return gradoAcademico;
            }
        }
        throw new IllegalArgumentException("Grado académico no válido: " + texto);
    }
}
