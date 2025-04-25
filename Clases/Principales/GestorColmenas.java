package Clases.Principales;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestorColmenas {
    private static final List<Colmena> colmenas = new ArrayList<>();
    private static final DatosApicola datosApicola = new DatosApicola();

    public static void registrarColmena() {
        System.out.println("\n🐝 REGISTRO DE NUEVA COLMENA");

        try {
            String id = Utils.solicitarCampo("Ingrese ID de la colmena: ");
            if (Utils.idExiste(colmenas, id)) {
                System.out.println("❌ El ID " + id + " ya está registrado.\n");
                return;
            }

            String ubicacion = Utils.solicitarCampo("Ingrese ubicación de la colmena: ");
            String estadoSalud = solicitarEstadoSalud();
            String tipo = Utils.solicitarCampo("Ingrese tipo de colmena (Ej: Langstroth, Warre, Top-Bar): ");
            byte cantidadAbejas = solicitarCantidadAbejas();
            float produccionMiel = solicitarProduccionMiel();

            Colmena nuevaColmena = new Colmena(id, ubicacion, tipo, estadoSalud, cantidadAbejas, produccionMiel);
            datosApicola.agregarColmena(nuevaColmena);
            colmenas.add(nuevaColmena);

            System.out.println("✅ Colmena registrada correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al registrar la colmena: " + e.getMessage());
        }
    }

    public static void actualizarUbicacion(String id) {
        Colmena colmena = buscarColmena(id);
        if (colmena == null) {
            notFound(id);
            return;
        }

        String nuevaUbicacion = Utils.solicitarCampo("Ingrese nueva ubicación: ");
        colmena.setUbicacion(nuevaUbicacion);
        System.out.println("✅ Ubicación actualizada correctamente.");
    }

    public static void actualizarEstadoSalud(String id) {
        Colmena colmena = buscarColmena(id);
        if (colmena == null) {
            notFound(id);
            return;
        }

        String nuevoEstado = solicitarEstadoSalud();
        colmena.setEstadoSalud(nuevoEstado);
        System.out.println("✅ Estado de salud actualizado correctamente.");
    }

    public static void registrarInspeccion(String id) {
        Colmena colmena = buscarColmena(id);
        if (colmena == null) {
            notFound(id);
            return;
        }

        String notas = Utils.solicitarCampo("Notas de la inspección: ");
        colmena.setUltimaInspeccion(new Date());
        colmena.setNotasInspeccion(notas);

        System.out.println("📋 Inspección registrada el " + colmena.getUltimaInspeccion());
    }

    public static void mostrarTodasColmenas() {
        if (colmenas.isEmpty()) {
            System.out.println("⚠️ No hay colmenas registradas.");
            return;
        }

        System.out.println("🐝 Listado de colmenas:");
        colmenas.forEach(System.out::println);
    }

    // ───────────────────────────────────────────
    // MÉTODOS DE ENTRADA VALIDADA
    // ───────────────────────────────────────────

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
                        { return capitalize(input); }
                default -> System.out.println("❌ Opción inválida. Intenta de nuevo.");
            }
        }
    }

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

    private static Colmena buscarColmena(String id) {
        return colmenas.stream()
                .filter(colmena -> colmena.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private static void notFound(String id) {
        System.out.println("❌ No se encontró la colmena con ID " + id + ".");
    }

    private static String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
