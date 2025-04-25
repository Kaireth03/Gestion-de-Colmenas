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

    public static float solicitarFloatMin(String mensaje, float min) {
        while (true) {
            try {
                String input = solicitarCampo(mensaje);
                float valor = Float.parseFloat(input);
                if (valor < min) {
                    System.out.println("❌ El valor debe ser mayor o igual a " + min + ".");
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
    // Delayed Printing
    // ───────────────────────────────────────────

    public static void delayPrint(String msg, long ms) {
        System.out.println(msg);
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

}
