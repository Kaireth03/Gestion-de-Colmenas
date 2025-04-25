package Clases.Principales;

import java.util.ArrayList;
import java.util.List;

public class BusquedaInspecciones {
    public static List<Inspeccion> buscarRecursivo(List<Inspeccion> lista, String tipo, String valor) {
        List<Inspeccion> resultados = new ArrayList<>();
        buscar(lista, tipo.toLowerCase(), valor.toLowerCase(), 0, resultados);
        return resultados;
    }

    private static void buscar(List<Inspeccion> lista, String tipo, String valor, int index, List<Inspeccion> resultados) {
        if (index >= lista.size()) return;

        Inspeccion actual = lista.get(index);
        boolean coincide = switch (tipo) {
            case "resultado" -> actual.getResultado().toLowerCase().contains(valor);
            case "accion" -> actual.getAcciones().toLowerCase().contains(valor);
            case "fecha" -> actual.getFecha().toString().toLowerCase().contains(valor);
            default -> false;
        };

        if (coincide) resultados.add(actual);
        buscar(lista, tipo, valor, index + 1, resultados);
    }
}
