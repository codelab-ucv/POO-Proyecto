package ucv.codelab.util;

import java.time.LocalDate;

public class ComprobarDatos {

    public static String limpiarString(String parametro) {
        if (parametro != null) {
            parametro = parametro.trim();
            if (parametro.isEmpty()) {
                return null;
            }
        }
        return parametro;
    }

    public static LocalDate obtenerFecha(String fechaString) {
        // Obtiene la fecha de un formato dd/mm/yyyy
        if (fechaString != null) {
            String[] fechaSeparada = fechaString.split("/");
            try {
                return LocalDate.of(Integer.valueOf(fechaSeparada[2]), Integer.valueOf(fechaSeparada[1]),
                        Integer.valueOf(fechaSeparada[0]));
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static Integer validarEntero(String strEntero) {
        if (strEntero != null) {
            try {
                return Integer.parseInt(strEntero);
            } catch (NumberFormatException e) {
            }
        }
        return null;
    }

    public static Double validarDecimal(String strDecimal) {
        if (strDecimal != null) {
            try {
                return Double.parseDouble(strDecimal);
            } catch (NumberFormatException e) {
            }
        }
        return null;
    }
}
