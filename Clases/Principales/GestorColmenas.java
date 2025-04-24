package Clases.Principales;

import java.util.*;

public class GestorColmenas {
    private static final List<Colmena> colmenas = new ArrayList<>();
    private static final DatosApicola datosApicola = new DatosApicola();

    public static void registrarColmena(DatosApicola datosApicola) {
        System.out.println("\n🐝 REGISTRO DE NUEVA COLMENA");

        try {
            String id = Utils.solicitarCampo("Ingrese ID de la colmena: ");
            if (Utils.idExiste(GestorColmenas.datosApicola.obtenerColmenas(), id)) {
                System.out.println("El ID " + id + " ya está registrado.\n");
                return;
            }

            String ubicacion = Utils.solicitarCampo("Ingrese ubicación de la colmena: ");
            String estadoSalud = solicitarEstadoSalud();
            String tipo = Utils.solicitarCampo("Ingrese tipo de colmena (Ej: Langstroth, Warre, Top-Bar): ");
            byte cantidadAbejas = solicitarCantidadAbejas();
            float produccionMiel = solicitarProduccionMiel();

            Colmena nueva = new Colmena(id, ubicacion, tipo, estadoSalud, cantidadAbejas, produccionMiel);
            GestorColmenas.datosApicola.agregarColmena(nueva);
            colmenas.add(nueva);

            System.out.println("✅ Colmena registrada correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al registrar la colmena: " + e.getMessage());
        }
    }

    public static void actualizarUbicacion(String id, String nuevaUbicacion) {
        Colmena colmena = buscarColmena(id);
        if (colmena != null) {
            colmena.setUbicacion(nuevaUbicacion);
            System.out.println("📍 Ubicación actualizada a " + nuevaUbicacion);
        } else mostrarNoEncontrada(id);
    }

    public static void actualizarEstadoSalud(String id, String nuevoEstado) {
        Colmena colmena = buscarColmena(id);
        if (colmena != null) {
            colmena.setEstadoSalud(nuevoEstado);
            System.out.println("❤️ Estado de salud actualizado a " + nuevoEstado);
        } else mostrarNoEncontrada(id);
    }

    public static void registrarInspeccion(String id, String notas) {
        Colmena colmena = buscarColmena(id);
        if (colmena != null) {
            colmena.setUltimaInspeccion(new Date());
            colmena.setNotasInspeccion(notas);
            System.out.println("🕵️ Inspección registrada para la colmena " + id + " el " + colmena.getUltimaInspeccion() + ". Notas: " + notas);
        } else mostrarNoEncontrada(id);
    }

    public static void mostrarTodasColmenas() {
        if (colmenas.isEmpty()) {
            System.out.println("🚫 No hay colmenas registradas.");
        } else {
            System.out.println("📋 Listado de colmenas:");
            for (Colmena colmena : colmenas) {
                System.out.println(colmena);
            }
        }
    }

    private static Colmena buscarColmena(String id) {
        for (Colmena c : colmenas) {
            if (c.getId().equalsIgnoreCase(id)) return c;
        }
        return null;
    }

    private static void mostrarNoEncontrada(String id) {
        System.out.println("❌ No se encontró la colmena con ID " + id);
    }

    private static String solicitarEstadoSalud() {
        String mensaje = """
            Estado de Salud:
            ├─ En plenitud
            ├─ Zumbido estable
            └─ Colmena en riesgo
            👉 Ingresa una opción:""";
        String input = Utils.solicitarCampo(mensaje);
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    private static byte solicitarCantidadAbejas() {
        while (true) {
            try {
                String input = Utils.solicitarCampo("Cantidad de abejas (0–25): ");
                byte cantidad = Byte.parseByte(input);
                if (cantidad < 0 || cantidad > 25) {
                    System.out.println("❌ Cantidad fuera de rango.");
                    continue;
                }
                return cantidad;
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Debe ser un número.");
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
                System.out.println("❌ Entrada inválida. Usa punto como separador decimal.");
            }
        }
    }
}
