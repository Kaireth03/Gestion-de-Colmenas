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

            // LeerJson.Guardar(Colmenas);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Función que muestra el menú
    public static void mostrarMenu() {
        System.out.println("""
        ╔═════════════════════════════════════════════════════════════╗
            🌼🐝  BIENVENIDO AL SISTEMA DE GESTIÓN APÍCOLA  🐝🌼     
                     ¡Administra & Registra tus colmenas! 🍯         
        ╚═════════════════════════════════════════════════════════════╝
       
       1️👥  Registrar nueva Colmena
       2️‍🆕  Registrar nuevo Apicultor
       3️👑  Asignar Abeja Reina a una Colmena
       4️👀  Realizar Inspección a Colmena
       5️📝  Mostrar Información Registrada
       6️🧑🏻‍🌾  Asignar Apicultor a Colmena
       7️📩  Editar Información Existente
       8️🔚  Salir del Sistema
        """);
        System.out.print("📜 Indica tu próximo movimiento en esta jornada apícola. Elige una opción del 1 al 8: ");
    }

    // Función que maneja la opción del menú
    public static boolean manejarOpcion(String opcion) {
        switch (opcion) {
            case "1" -> 
                GestorColmenas.registrarColmena();
            case "2" -> 
                GestorColmenas.registrarApicultor();
            case "3" ->
                GestorColmenas.asignarAbejaReina();
            case "4" ->
                GestorColmenas.realizarInspeccion();
            case "5" ->
                GestorColmenas.mostrarInformacion();
            case "6" ->
                GestorColmenas.asignarApicultorAColmena();
            case "7" ->
                GestorColmenas.editarInformacion();
            case "8" -> {
                // TODO: Confirmar salida, guardar datos si es necesario
                return false;
            }
            default -> System.out.println("⚠️ Opción no válida. Intenta nuevamente."); // los de menu, cambien eso
        }
        return true;
    }
}

public class GestorColmenas {
    public static void registrarColmena() {
        System.out.println("\n🐝 REGISTRO DE NUEVA COLMENA");

        try {
            String id = Utils.solicitarCampo("Ingrese ID de la colmena: ");
            String ubicacion = Utils.solicitarCampo("Ingrese ubicación de la colmena: ");
            String tipo = Utils.solicitarCampo("Ingrese tipo de colmena (Ej: Langstroth, Warre, Top-Bar): ");

            Colmena nuevaColmena = new Colmena(id, ubicacion, tipo);
            colmenas.add(nuevaColmena);

            System.out.println("✅ Colmena registrada correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al registrar la colmena: " + e.getMessage());
        }
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
