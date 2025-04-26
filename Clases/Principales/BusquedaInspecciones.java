package Clases.Principales;

import java.util.ArrayList;
import java.util.List;

public class BusquedaInspecciones {

    // Método principal para iniciar la búsqueda recursiva
    // Se utiliza para ocultar los detalles internos al usuario del método
    public static List<Inspeccion> buscarRecursivo(List<Inspeccion> lista, String tipo, String valor) {
        List<Inspeccion> resultados = new ArrayList<>();
        buscar(lista, tipo.toLowerCase(), valor.toLowerCase(), 0, resultados); // Llamada inicial
        return resultados;
    }

    // Método recursivo que recorre la lista inspección por inspección
    private static void buscar(List<Inspeccion> lista, String tipo, String valor, int index, List<Inspeccion> resultados) {
        if (index >= lista.size()) return; // Caso base: si llegamos al final de la lista, terminamos la recursión

        Inspeccion actual = lista.get(index); // Tomamos la inspección actual según el índice

        // Evaluamos si la inspección actual coincide con los criterios de búsqueda
        boolean coincide = switch (tipo) {
            case "resultado" -> actual.getResultado().toLowerCase().contains(valor); // Búsqueda en el resultado
            case "accion" -> actual.getAcciones().toLowerCase().contains(valor);     // Búsqueda en las acciones
            case "fecha" -> actual.getFecha().toString().toLowerCase().contains(valor); // Búsqueda en la fecha
            default -> false; // Si el tipo no es reconocido, no hay coincidencia
        };

        if (coincide) {
            resultados.add(actual); // Si coincide, se añade a los resultados
        }

        // Llamada recursiva para procesar el siguiente elemento
        buscar(lista, tipo, valor, index + 1, resultados);
    }
}
