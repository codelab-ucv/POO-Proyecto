package ucv.codelab.enumerados;

public enum TipoDiagnostico {
    PRESUNTIVO("presuntivo"),
    DEFINITIVO("definitivo"),
    DIFERENCIAL("diferencial");

    private final String valor;

    TipoDiagnostico(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static TipoDiagnostico fromString(String texto) {
        for (TipoDiagnostico td : TipoDiagnostico.values()) {
            if (td.getValor().equalsIgnoreCase(texto) ||
                    td.name().equalsIgnoreCase(texto)) {
                return td;
            }
        }
        throw new IllegalArgumentException("Tipo de diagnóstico no válido: " + texto);
    }
}
