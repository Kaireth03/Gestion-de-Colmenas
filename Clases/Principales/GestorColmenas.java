package Clases.Principales;

// Importaciones necesarias
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase GestorColmenas
 * Gestiona el registro, actualización e inspección de colmenas en una lista.
 */
public class GestorColmenas {

    // Lista estática que almacena todas las colmenas registradas
    private static final List<Colmena> colmenas = new ArrayList<>();
    
    // Objeto para manejar operaciones de datos apícolas
    private static final DatosApicola datosApicola = new DatosApicola();

    /**
     * Método para registrar una nueva colmena con sus datos básicos
     */
    public static void registrarColmena() {
        System.out.println("\n🐝 REGISTRO DE NUEVA COLMENA");
        try {
            // Solicita el ID de la colmena
            String id = Utils.solicitarCampo("Ingrese ID de la colmena: ");
            // Verifica si el ID ya existe en la lista
            if (Utils.idExiste(colmenas, id)) {
                System.out.println("❌ El ID " + id + " ya está registrado.\n");
                return;
            }

            // Solicita otros campos necesarios para registrar la colmena
            String ubicacion = Utils.solicitarCampo("Ingrese ubicación de la colmena: ");
            String estadoSalud = solicitarEstadoSalud();
            String tipo = Utils.solicitarCampo("Ingrese tipo de colmena (Ej: Langstroth, Warre, Top-Bar): ");
            byte cantidadAbejas = solicitarCantidadAbejas();
            float produccionMiel = solicitarProduccionMiel();

            // Crea una nueva instancia de Colmena con los datos ingresados
            Colmena nuevaColmena = new Colmena(id, ubicacion, tipo, estadoSalud, cantidadAbejas, produccionMiel);
            datosApicola.agregarColmena(nuevaColmena); // Agrega a la base de datos interna
            colmenas.add(nuevaColmena);                // Agrega a la lista general

            System.out.println("✅ Colmena registrada correctamente.");
        } catch (Exception e) {
            // Captura cualquier error durante el registro
            System.out.println("❌ Error al registrar la colmena: " + e.getMessage());
        }
    }

    /**
     * Método para actualizar la ubicación de una colmena específica
     */
    public static void actualizarUbicacion(String id) {
        Colmena colmena = buscarColmena(id); // Busca la colmena por ID
        if (colmena == null) {
            notFound(id);
            return;
        }

        // Solicita nueva ubicación y actualiza
        String nuevaUbicacion = Utils.solicitarCampo("Ingrese nueva ubicación: ");
        colmena.setUbicacion(nuevaUbicacion);
        System.out.println("✅ Ubicación actualizada correctamente.");
    }

    /**
     * Método para actualizar el estado de salud de una colmena
     */
    public static void actualizarEstadoSalud(String id) {
        Colmena colmena = buscarColmena(id);
        if (colmena == null) {
            notFound(id);
            return;
        }

        // Solicita nuevo estado de salud y actualiza
        String nuevoEstado = solicitarEstadoSalud();
        colmena.setEstadoSalud(nuevoEstado);
        System.out.println("✅ Estado de salud actualizado correctamente.");
    }

    /**
     * Método para registrar una inspección en una colmena
     */
    public static void registrarInspeccion(String id) {
        Colmena colmena = buscarColmena(id);
        if (colmena == null) {
            notFound(id);
            return;
        }

        // Solicita notas de inspección, actualiza la fecha y las notas
        String notas = Utils.solicitarCampo("Notas de la inspección: ");
        colmena.setUltimaInspeccion(new Date());
        colmena.setNotasInspeccion(notas);

        System.out.println("📋 Inspección registrada el " + colmena.getUltimaInspeccion());
    }

    /**
     * Método para mostrar todas las colmenas registradas
     */
    public static void mostrarTodasColmenas() {
        if (colmenas.isEmpty()) {
            System.out.println("⚠️ No hay colmenas registradas.");
            return;
        }

        // Imprime todas las colmenas en la lista
        System.out.println("🐝 Listado de colmenas:");
        colmenas.forEach(System.out::println);
    }

    // ───────────────────────────────────────────
    // MÉTODOS DE ENTRADA VALIDADA
    // ───────────────────────────────────────────

    /**
     * Solicita al usuario seleccionar un estado de salud válido
     * Valida las entradas permitidas
     */
    private static String solicitarEstadoSalud() {
        final String mensaje = """
        Estado de Salud:
        ├─ En plenitud
        ├─ Zumbido estable
        └─ Colmena en riesgo
        👉 Ingresa una opción:""";

        while (true) {
            String input = Utils.solicitarCampo(mensaje).trim().toLowerCase();
            switch (input) {
                case "en plenitud", "zumbido estable", "colmena en riesgo" ->
                        { return capitalize(input); } // Retorna con la primera letra en mayúscula
                default -> System.out.println("❌ Opción inválida. Intenta de nuevo.");
            }
        }
    }

    /**
     * Solicita al usuario una cantidad de abejas válida entre 0 y 25
     */
    private static byte solicitarCantidadAbejas() {
        while (true) {
            try {
                String input = Utils.solicitarCampo("Cantidad de abejas (0–25): ");
                byte cantidad = Byte.parseByte(input);
                if (cantidad < 0 || cantidad > 25) {
                    System.out.println("❌ La cantidad debe estar entre 0 y 25.");
                    continue;
                }
                return cantidad;
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Debe ser un número entero.");
            }
        }
    }

    /**
     * Solicita al usuario la cantidad estimada de miel en kilogramos
     */
    private static float solicitarProduccionMiel() {
        while (true) {
            try {
                String input = Utils.solicitarCampo("Producción estimada de miel (kg): ");
                float produccion = Float.parseFloat(input);
                if (produccion < 0) {
                    System.out.println("❌ La producción no puede ser negativa.");
                    continue;
                }
                return produccion;
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Debe ser un número decimal.");
            }
        }
    }

    // ───────────────────────────────────────────
    // MÉTODOS AUXILIARES
    // ───────────────────────────────────────────

    /**
     * Busca una colmena en la lista por su ID
     * @param id ID de la colmena
     * @return objeto Colmena o null si no se encuentra
     */
    private static Colmena buscarColmena(String id) {
        return colmenas.stream()
                .filter(colmena -> colmena.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Imprime un mensaje de error cuando una colmena no se encuentra
     * @param id ID de la colmena no encontrada
     */
    private static void notFound(String id) {
        System.out.println("❌ No se encontró la colmena con ID " + id + ".");
    }

    /**
     * Capitaliza la primera letra de un texto
     * @param text Texto a capitalizar
     * @return texto con la primera letra en mayúscula
     */
    private static String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
