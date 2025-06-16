package ucv.codelab.enumerados;

public enum TipoSangre {
    A_POSITIVO("A+"),
    A_NEGATIVO("A-"),
    B_POSITIVO("B+"),
    B_NEGATIVO("B-"),
    AB_POSITIVO("AB+"),
    AB_NEGATIVO("AB-"),
    O_POSITIVO("O+"),
    O_NEGATIVO("O-");

    private final String valor;

    TipoSangre(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static TipoSangre fromString(String texto) {
        for (TipoSangre ts : TipoSangre.values()) {
            if (ts.getValor().equalsIgnoreCase(texto) ||
                    ts.name().equalsIgnoreCase(texto)) {
                return ts;
            }
        }
        throw new IllegalArgumentException("Tipo de sangre no válido: " + texto);
    }
}
