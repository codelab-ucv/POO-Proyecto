package ucv.codelab.enumerados;

public enum Gravedad {
    LEVE("LEVE"),
    MODERADA("MODERADA"),
    SEVERA("SEVERA");

    private final String valor;

    Gravedad(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static Gravedad fromString(String texto) {
        for (Gravedad gravedad : Gravedad.values()) {
            if (gravedad.getValor().equalsIgnoreCase(texto) ||
                    gravedad.name().equalsIgnoreCase(texto)) {
                return gravedad;
            }
        }
        throw new IllegalArgumentException("Gravedad no válida: " + texto);
    }
}
