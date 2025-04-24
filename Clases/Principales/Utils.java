import java.util.Scanner;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);
    public static String solicitarCampo(String mensaje) {
        System.out.print(mensaje);
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            throw new IllegalArgumentException("El campo no puede estar vacío.");
        }

        return input;
    }
}
