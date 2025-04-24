import java.util.*;
import java.io.*;

// Importamos todas las clases principales
import Clases.Principales.*;
import Clases.LeerJson;

public class PrinColmena {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Colmena> Colmenas = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // TODO: Cargar datos si existen

            boolean continuar = true;
            while (continuar) {
                mostrarMenu();
                continuar = manejarOpcion(scanner.nextLine());
            }

            LeerJson.Guardar(Colmenas);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Función que muestra el menú
    public static void mostrarMenu() {
        // TODO: Mostrar el menu (para los que van hacer menu)
        System.out.println("""
        // hagan el menu aca,
        pude ultilizar multiples lineas
        //
        """);
        System.out.print("Selecciona una opción: "); // cambien
    }

    // Función que maneja la opción del menú
    public static boolean manejarOpcion(String opcion) {
        switch (opcion) {
            case "1" -> {
                // TODO: Llamar a función para registrar colmena
            }
            case "2" -> {
                // TODO: Llamar a función para registrar apicultor
            }
            case "3" -> {
                // TODO: Llamar a función para asignar abeja reina
            }
            case "4" -> {
                // TODO: Llamar a función para realizar inspección
            }
            case "5" -> {
                // TODO: Llamar a función para mostrar información
            }
            case "6" -> {
                // TODO: Llamar a función para asignar apicultor a colmena
            }
            case "7" -> {
                // TODO: Llamar a función para editar información
            }
            case "8" -> {
                // TODO: Confirmar salida, guardar datos si es necesario
                return false;
            }
            default -> System.out.println("⚠️ Opción no válida. Intenta nuevamente."); // los de menu, cambien eso
        }
        return true;
    }
    // Función para registrar una nueva colmena
    public static void registrarColmena() {
        // TODO: Pedir datos al usuario (ID, ubicación, tipo, etc.)
        // TODO: Validar los datos ingresados
        // TODO: Crear objeto Colmena con los datos
        // TODO: Agregar la colmena al ArrayList Colmenas
        // TODO: Confirmar al usuario que se registró correctamente
    }

    // Función para registrar un nuevo apicultor
    public static void registrarApicultor() {
        // TODO: Pedir datos del apicultor (nombre, edad, experiencia, etc.)
        // TODO: Validar los datos
        // TODO: Crear objeto Apicultor
        // TODO: Agregarlo a la lista correspondiente
        // TODO: Confirmar el registro
    }

    // Función para asignar una abeja reina a una colmena
    public static void asignarAbejaReina() {
        // TODO: Mostrar lista de colmenas sin reina
        // TODO: Permitir elegir colmena
        // TODO: Pedir datos de la abeja reina (nombre, edad, etc.)
        // TODO: Validar datos
        // TODO: Asignar la abeja reina a la colmena seleccionada
        // TODO: Confirmar asignación
    }

    // Función para realizar una inspección
    public static void realizarInspeccion() {
        // TODO: Mostrar lista de colmenas disponibles
        // TODO: Permitir al usuario seleccionar una
        // TODO: Pedir datos de la inspección (fecha, observaciones, etc.)
        // TODO: Agregar inspección a la colmena seleccionada
        // TODO: Confirmar que la inspección fue registrada
    }

    // Función para mostrar información
    public static void mostrarInformacion() {
        // TODO: Mostrar submenú con tipos de información a mostrar
        // TODO: Dependiendo de la opción, mostrar:
        //   - Colmenas registradas
        //   - Apicultores
        //   - Abejas reinas
        //   - Historial de inspecciones
        // TODO: Permitir volver al menú principal
    }

    // Función para asignar un apicultor a una colmena
    public static void asignarApicultorAColmena() {
        // TODO: Mostrar lista de colmenas
        // TODO: Mostrar lista de apicultores
        // TODO: Permitir seleccionar ambos
        // TODO: Validar que el apicultor no esté ya asignado
        // TODO: Asignar apicultor a la colmena
        // TODO: Confirmar asignación
    }

    // Función para editar información existente
    public static void editarInformacion() {
        // TODO: Mostrar submenú: ¿qué desea editar? (colmena, apicultor, abeja reina, inspección)
        // TODO: Según elección, mostrar lista de elementos
        // TODO: Permitir seleccionar uno
        // TODO: Pedir nuevos datos
        // TODO: Validar y aplicar cambios
        // TODO: Confirmar edición
    }
}
