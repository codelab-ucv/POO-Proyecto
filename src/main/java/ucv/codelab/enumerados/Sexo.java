package ucv.codelab.enumerados;

public enum Sexo {
    MASCULINO("masculino"),
    FEMENINO("femenino");

    private final String valor;

    Sexo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static Sexo fromString(String texto) {
        for (Sexo sexo : Sexo.values()) {
            if (sexo.getValor().equalsIgnoreCase(texto) ||
                    sexo.name().equalsIgnoreCase(texto)) {
                return sexo;
            }
        }
        throw new IllegalArgumentException("Sexo no válido: " + texto);
    }
}
