package Clases.Principales;

import java.util.*;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);

    // ANSI escape codes
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    // ───────────────────────────────────────────
    // TEXT INPUT
    // ───────────────────────────────────────────
    public static String solicitarCampo(String mensaje) {
        String input;
        while (true) {
            System.out.print(CYAN + "👉 " + mensaje + RESET);
            input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(RED + "❌ El campo no puede estar vacío. Intenta de nuevo." + RESET);
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
                    System.out.println(RED + "⚠️ El valor debe estar entre " + min + " y " + max + "." + RESET);
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println(RED + "❌ Entrada inválida. Debe ser un número entero." + RESET);
            }
        }
    }

    public static float solicitarFloatMin(String mensaje, float min) {
        while (true) {
            try {
                String input = solicitarCampo(mensaje);
                float valor = Float.parseFloat(input);
                if (valor < min) {
                    System.out.println(RED + "⚠️ El valor debe ser mayor o igual a " + min + "." + RESET);
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println(RED + "❌ Entrada inválida. Debe ser un número decimal." + RESET);
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
    // Delayed Printing
    // ───────────────────────────────────────────

    public static void delayPrint(String msg, long ms) {
        System.out.println(YELLOW + msg + RESET);
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }

    // ───────────────────────────────────────────
    // Stylish Banner
    // ───────────────────────────────────────────

    public static void printBanner(String titulo) {
        String decorado = BOLD + CYAN + "╔" + "═".repeat(titulo.length() + 4) + "╗\n" +
                          "║  " + titulo + "  ║\n" +
                          "╚" + "═".repeat(titulo.length() + 4) + "╝" + RESET;
        System.out.println(decorado);
    }
}
