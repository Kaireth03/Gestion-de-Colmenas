import java.util.*;
import java.io.*;

// Importamos todas las clases principales
import Clases.Principales.*;
import Clases.LeerJson;

public class PrinColmena {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Colmena> Colmenas = new ArrayList<>();
    static DatosApicola datosApicola = new DatosApicola();

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
            case "1" -> GestorColmenas.registrarColmena(datosApicola);
            case "2" -> GestorColmenas.registrarApicultor();
            case "3" -> GestorColmenas.asignarAbejaReina();
            case "4" -> GestorColmenas.realizarInspeccion();
            case "5" -> GestorColmenas.mostrarInformacion();
            case "6" -> GestorColmenas.asignarApicultorAColmena();
            case "7" -> GestorColmenas.editarInformacion();
            case "8" -> {
            // Guardar los datos al salir
            LeerJson.Guardar(datosApicola); // Guardar la información
            return false; // Finalizar el ciclo y salir del programa
            }
            default -> System.out.println("⚠️ Opción no válida. Intenta nuevamente.");
        }
        return true;
    }
}
