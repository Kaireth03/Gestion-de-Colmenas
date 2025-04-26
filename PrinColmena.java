import java.util.Scanner;
import Clases.LeerJson;
import Clases.Principales.*;
import java.io.*;
import java.nio.file.*;


public class PrinColmena {
    static final DatosApicola datosApicola = DatosApicola.getInstancia();
    static final Scanner scanner = new Scanner(System.in);

    String ruta = Paths.get(System.getProperty("user.home"), "Documents", "colmenas.json").toString();

    public static void main(String[] args) {
        try {
            boolean continuar = true;
            while (continuar) {
                mostrarMenu();
                continuar = manejarOpcion(scanner.nextLine());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

public void Verificar(){
    File Json=new File(ruta);

    if (Json.exists()) {

    System.out.println("Se encontró un archivo de datos en: " + ruta);
            System.out.println("¿Desea cargarlo? (si/no)");
            String respuesta = scanner.nextLine().trim().toLowerCase();

            if (respuesta.equals("si")) {
                System.out.println("🐝Cargando archivo...🐝");
                
            } else {
                System.out.println("Advertencia: No cargar el archivo puede hacer que se pierdan los datos anteriores.");
            }
        } 
        else {
          
            System.out.println("No se encontró archivo de datos en: " + ruta);
            System.out.println("Se continuará normalmente.");
        }
    }

   
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
        9🔚  Salir del Sistema
        """);
        System.out.print("📜 Indica tu próximo movimiento: ");
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
            case "8" -> { LeerJson.CargarColmena(); yield true; }
            case "9" -> {

                // Guardar los datos al salir

                String ruta = Paths.get(System.getProperty("user.home"), "Documents", "colmenas.json").toString();
                File Json = new File(ruta);
                if(Json.exists()){
                    System.out.print("📜 El archivo ya existe. Reescribiendo datos ");
                    LeerJson.Actualizar(datosApicola);
                }else{
                    LeerJson.GuardarColmena(datosApicola);
                }
                yield false; // Finalizar el ciclo y salir del programa
            }

            default -> {
                System.out.println("⚠️ Opción no válida. Intenta nuevamente.");
                yield true;
            }
        };
    }
}