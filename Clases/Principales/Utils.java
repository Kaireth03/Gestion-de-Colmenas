package Clases.Principales;

import java.util.*;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);

    // ───────────────────────────────────────────
    // TEXT INPUT
    // ───────────────────────────────────────────

    public static String solicitarCampo(String mensaje) {
        String input;
        while (true) {
            System.out.print(mensaje);
            input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("❌ El campo no puede estar vacío. Intenta de nuevo.");
            } else {
                return input;
            }
        }
    }

    // ───────────────────────────────────────────
    // NUMERIC INPUTS
    // ───────────────────────────────────────────

    public static byte solicitarByteEnRango(String mensaje, byte min, byte max) {
        while (true) {
            try {
                String input = solicitarCampo(mensaje);
                byte valor = Byte.parseByte(input);
                if (valor < min || valor > max) {
                    System.out.println("❌ El valor debe estar entre " + min + " y " + max + ".");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Debe ser un número entero.");
            }
        }
    }

    public static float solicitarFloatRango(String mensaje, float min, float max) {
        while (true) {
            try {
                String input = solicitarCampo(mensaje);
                float valor = Float.parseFloat(input);
                if (valor < min || valor > max) {
                    System.out.println("❌ El valor debe estar entre " + min + " y " + max + ".");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Debe ser un número decimal.");
            }
        }
    }

    // ───────────────────────────────────────────
    // ID CHECKING
    // ───────────────────────────────────────────

    public static boolean idExiste(List<Colmena> colmenas, String id) {
        return colmenas.stream().anyMatch(c -> c.getId().equals(id));
    }

    // ───────────────────────────────────────────
    // APICULTOR CHECKS
    // ───────────────────────────────────────────

    public static String solicitarNombre() {
        while (true) {
            String nombre = solicitarCampo("Ingrese el nombre:");

            // Normalizar: quitar espacios dobles o triples
            nombre = nombre.trim().replaceAll("\\s+", " ");

            // Verificación:
            // ^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$ => Solo letras y espacios
            // nombre.replace(" ", "").length() >= 3 => Al menos 3 letras (sin contar espacios)
            if (nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$") && nombre.replace(" ", "").length() >= 3) {
                return nombre;
            } else {
                System.out.println("❌ Nombre inválido. Debe contener solo letras y al menos 3 letras reales.");
            }
        }
    }

    public static String solicitarTelefono() {
        while (true) {
            String telefono = solicitarCampo("Ingrese el teléfono (8-15 dígitos, puede iniciar con '+'):");
            if (telefono.matches("^\\+?\\d{8,15}$")) {
                return telefono;
            } else {
                System.out.println("Teléfono inválido. Intente de nuevo.");
            }
        }
    }

    public static String solicitarIdentificacion() {
        while (true) {
            String identificacion = solicitarCampo("Ingrese la identificación (Pasaporte: XX123456 o Cédula: 8-888-888 o 8-888-888-8888):");
            if (identificacion.matches("^[A-Z]{2}\\d{6}$") || identificacion.matches("^\\d-\\d{3}-\\d{3}(-\\d{4})?$")) {
                return identificacion;
            } else {
                System.out.println("Identificación inválida. Intente de nuevo.");
            }
        }
    }

    // ───────────────────────────────────────────
    // Delayed Printing
    // ───────────────────────────────────────────

    public static void delayPrint(String msg, long ms) {
        System.out.println(msg);
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}
