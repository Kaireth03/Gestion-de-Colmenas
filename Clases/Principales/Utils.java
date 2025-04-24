package Clases.Util;

import java.util.Scanner;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * Solicita un campo que cumpla con un patrón regex específico.
     * 
     * @param mensaje Mensaje que se mostrará al usuario.
     * @param regex Expresión regular que debe cumplir el input.
     * @return El input válido del usuario.
     * @throws IllegalArgumentException si el input no es válido.
     */
    public static String solicitarCampo(String mensaje, String regex) {
        System.out.print(mensaje);
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            throw new IllegalArgumentException("❌ El campo no puede estar vacío.");
        }

        if (!input.matches(regex)) {
            throw new IllegalArgumentException("❌ El formato ingresado no es válido. Intenta de nuevo.");
        }

        return input;
    }
}

