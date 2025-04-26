package Clases.Principales;

// ─────────────────────────────────────────────────────────────
// Imports
// ─────────────────────────────────────────────────────────────
import java.util.ArrayList;
import java.util.List;

// ─────────────────────────────────────────────────────────────
// Clase principal
// ─────────────────────────────────────────────────────────────
public class GestorColmenas {

    // ─────────────────────────────────────────────────────────────
    // Métodos públicos principales
    // ─────────────────────────────────────────────────────────────

    /**
     * Registra una nueva colmena solicitando los datos al usuario.
     */
    public static void registrarColmena() {
        Utils.delayPrint("\n🐝 REGISTRO DE NUEVA COLMENA", 500);

        try {
            String id = solicitarIdUnico();
            String ubicacion = Utils.solicitarCampo("Ingrese ubicación de la colmena: ");
            String estadoSalud = solicitarEstadoSalud();
            String tipo = Utils.solicitarCampo("Ingrese tipo de colmena (Ej: Langstroth, Warre, Top-Bar): ");
            byte cantidadAbejas = Utils.solicitarByteEnRango("Cantidad de abejas (0–25): ", (byte) 0, (byte) 25);
            float produccionMiel = Utils.solicitarFloatMin("Producción estimada de miel (kg): ", 0f);

            Colmena nuevaColmena = new Colmena(id, ubicacion, tipo, estadoSalud, cantidadAbejas, produccionMiel);

            DatosApicola.getInstancia().agregarColmena(nuevaColmena);

            Utils.delayPrint("✅ Colmena registrada correctamente.", 500);
        } catch (Exception e) {
            Utils.delayPrint("❌ Error al registrar la colmena: " + e.getMessage(), 500);
        }
    }

    /**
     * Permite al usuario registrar inspecciones de colmenas.
     */
    public static void registrarInspeccion() {
        String modo = Utils.solicitarCampo("""
        🔍 ¿Desea inspeccionar una sola colmena o todas?
        Escriba: 'una' o 'todas'
        👉 """).trim().toLowerCase();

        switch (modo) {
            case "una" -> inspeccionarUnaColmena();
            case "todas" -> inspeccionarMultiplesColmenas();
            default -> Utils.delayPrint("❌ Opción no válida. Intente de nuevo.", 500);
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Métodos privados auxiliares (inspecciones)
    // ─────────────────────────────────────────────────────────────

    /**
     * Realiza una inspección manual sobre una sola colmena seleccionada.
     */
    private static void inspeccionarUnaColmena() {
        String id = Utils.solicitarCampo("Ingrese el ID de la colmena a inspeccionar: ");
        Colmena colmena = buscarColmena(id);

        if (colmena == null) {
            Utils.delayPrint("❌ No se encontró la colmena con ID " + id + ".\n", 500);
            return;
        }

        Inspeccion.inspeccionarYGuardar(colmena, "manual");
        Utils.delayPrint("✅ Inspección realizada correctamente para la colmena ID: " + id, 500);
    }

    /**
     * Realiza inspecciones automáticas sobre todas las colmenas existentes usando hilos.
     */
    private static void inspeccionarMultiplesColmenas() {
        Inspeccion.inspeccionarTodasColmenasConHilos();
    }

    // ─────────────────────────────────────────────────────────────
    // Métodos privados auxiliares (registro de datos)
    // ─────────────────────────────────────────────────────────────

    /**
     * Solicita al usuario un estado de salud válido para la colmena.
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
            if (input.matches("en plenitud|zumbido estable|colmena en riesgo")) {
                return capitalize(input);
            }
            Utils.delayPrint("❌ Opción inválida. Intenta de nuevo.", 500);
        }
    }

    /**
     * Solicita al usuario un ID único de colmena siguiendo el formato COL###.
     */
    private static String solicitarIdUnico() {
        while (true) {
            String id = Utils.solicitarCampo("Ingrese ID de la colmena (formato COL###): ").toUpperCase();

            if (!id.matches("COL\\d{3}")) {
                Utils.delayPrint("❌ Formato inválido. Debe ser COL seguido de 3 dígitos (ej. COL001).", 500);
                continue;
            }

            if (Utils.idExiste(DatosApicola.getInstancia().obtenerColmenas(), id)) {
                Utils.delayPrint("❌ El ID " + id + " ya está registrado.\n", 500);
                continue;
            }

            return id;
        }
    }

    /**
     * Busca una colmena por su ID en el sistema.
     */
    private static Colmena buscarColmena(String id) {
        return DatosApicola.getInstancia().obtenerColmenas().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Capitaliza el primer carácter de un texto.
     */
    private static String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
