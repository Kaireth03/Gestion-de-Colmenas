import java.util.Scanner;
// import Clases.LeerJson;
import Clases.Principales.*;
import Clases.Utils; // ✅ Importa a classe de utilitários visuais

public class PrinColmena {
    static final DatosApicola datosApicola = new DatosApicola();
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            boolean continuar = true;
            while (continuar) {
                mostrarMenu();
                continuar = manejarOpcion(scanner.nextLine());
            }

            // LeerJson.Guardar(Colmenas);
        } catch (Exception e) {
            Utils.error("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    public static void mostrarMenu() {
        System.out.println(Utils.ANSI_BLACK + """
        ╔═════════════════════════════════════════════════════════════╗
            🌼🐝  BIENVENIDO AL SISTEMA DE GESTIÓN APÍCOLA  🐝🌼
                     ¡Administra & Registra tus colmenas! 🍯
        ╚═════════════════════════════════════════════════════════════╝
        """ + Utils.ANSI_RESET);

        Utils.amarillo("1️👥  Registrar nueva Colmena");
        Utils.amarillo("2️‍🆕  Registrar nuevo Apicultor");
        Utils.amarillo("3️👑  Asignar Abeja Reina a una Colmena");
        Utils.amarillo("4️👀  Realizar Inspección a Colmena");
        Utils.amarillo("5️📝  Mostrar Información Registrada");
        Utils.amarillo("6️🧑🏻‍🌾  Asignar Apicultor a Colmena");
        Utils.amarillo("7️📩  Editar Información Existente");
        Utils.amarillo("8️🔚  Salir del Sistema");

        System.out.print(Utils.ANSI_GRAY + "\n📜 Indica tu próximo movimiento: " + Utils.ANSI_RESET);
    }

    public static boolean manejarOpcion(String opcion) {
        return switch (opcion) {
            case "1" -> { GestorColmenas.registrarColmena(); yield true; }
            case "2" -> { SistemaApicola.registrarApicultor(); yield true; }
            case "3" -> { SistemaApicola.asignarAbejaReina(); yield true; }
            case "4" -> { GestorColmenas.registrarInspeccion(); yield true; }
            case "5" -> { SistemaApicola.mostrarInformacion(); yield true; }
            case "6" -> { SistemaApicola.asignarApicultorAColmena(); yield true; }
            case "7" -> { SistemaApicola.editarInformacion(); yield true; }
            case "8" -> {
                Utils.negro("🔚 Saliendo del sistema... ¡Hasta pronto!");
                // LeerJson.Guardar(datosApicola); // Guardar la información
                yield false;
            }
            default -> {
                Utils.error("⚠️ Opción no válida. Intenta nuevamente.");
                yield true;
            }
        };
    }
}
