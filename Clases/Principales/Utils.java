package Clases.Principales;

import java.util.*;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * Solicita un valor textual al usuario asegurándose de que no esté vacío.
     * 
     * @param mensaje Mensaje que se mostrará al usuario.
     * @return El input válido del usuario.
     * @throws IllegalArgumentException si el input no es válido.
     */
public static String solicitarCampo(String mensaje) {
    // Se muestra el mensaje al usuario para indicar qué información debe ingresar.
    System.out.print(mensaje);

    // Se lee la entrada del usuario y se eliminan los espacios en blanco al inicio y al final.
    String input = scanner.nextLine().trim();

    // Se verifica que el usuario no haya dejado el campo vacío, ya que eso impediría continuar con datos válidos.
    if (input.isEmpty()) {
        // Si está vacío, se lanza una excepción para forzar que el campo sea obligatorio.
        throw new IllegalArgumentException("❌ El campo no puede estar vacío.");
    }

    // Si todo está bien, se devuelve el texto ingresado por el usuario.
    return input;
}

// Verifica si un ID ya existe en la lista de colmenas para evitar duplicados.
public static boolean idExiste(List<Colmena> colmenas, String id) {
    // Se usa un stream para recorrer todas las colmenas y comparar cada ID con el que se quiere registrar.
    // Se devuelve 'true' apenas se encuentra una coincidencia, lo que lo hace eficiente.
    return colmenas.stream().anyMatch(c -> c.id.equals(id));
}

}

