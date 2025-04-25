package Clases.Principales;

import java.util.*;

public class SistemaApicola {
    public static List<AbejaReyna> abejas_existentes = new ArrayList<>();
    public static Map<String, AbejaReyna> Colmenas_con_abeja_reyna = new HashMap<>();
    private static Inspeccion inspeccion = new Inspeccion();  
    private static DatosApicola datosApicola = new DatosApicola();
    private static Scanner scanner = new Scanner(System.in);

    public static void registrarApicultor() {
        System.out.println("REGISTRO DEL NUEVO APICULTOR");
        try {
            String nombre = Utils.solicitarCampo("Ingrese el nombre del apicultor: ");
            String telefono = Utils.solicitarCampo("Ingrese el teléfono del apicultor: ");
            byte edad = solicitarEdad();
            byte experiencia = solicitarExperiencia(edad);
            String direccion = Utils.solicitarCampo("Ingrese la dirección del apicultor: ");
            String identificacion = Utils.solicitarCampo("Ingrese la identificación (DNI/ID/Cédula) del apicultor: ");

            Apicultor nuevoApicultor = new Apicultor(nombre, telefono, edad, experiencia, direccion, identificacion);
            datosApicola.agregarApicultor(nuevoApicultor);

            System.out.println("✅ Apicultor registrado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al registrar el apicultor: " + e.getMessage());
        }
    }

    public static void asignarAbejaReina() {
        System.out.println("Crear Abeja reina");
        System.out.println("Nombre de la Abeja reina:");
        String nombre_abeja_reyna = scanner.nextLine();
        System.out.println("Edad de la Abeja reina:");
        int edad_abeja_reyna = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        System.out.println("Salud de la Abeja reina:");
        String estado_abeja_reyna = scanner.nextLine();

        System.out.println("Productividad de la Abeja reina:");
        double productividad = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        AbejaReyna abejareyna = new AbejaReyna(estado_abeja_reyna, edad_abeja_reyna, nombre_abeja_reyna, productividad);
        abejas_existentes.add(abejareyna);

        for (int i = 0; i < datosApicola.colmenas.size(); i++) {
            System.out.println((i + 1) + ". " + datosApicola.colmenas.get(i));
        }

        System.out.println("Ingrese el ID de la colmena a la que se asignará:");
        String colmenaId = scanner.nextLine();

        if (Colmenas_con_abeja_reyna.containsKey(colmenaId)) {
            System.out.println("❌ La colmena con ese ID ya tiene una abeja reina.");
        } else {
            Colmenas_con_abeja_reyna.put(colmenaId, abejareyna);
            System.out.println("✅ Se asignó la Abeja reina a la colmena.");
        }
    }

    public static void mostrarInformacion() {
        System.out.println("¿Qué información desea ver?");
        System.out.println("1. Colmenas registradas");
        System.out.println("2. Apicultores");
        System.out.println("3. Abejas reinas");
        System.out.println("4. Historial de inspección");
        System.out.println("0. Volver al inicio");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                GestorColmenas.mostrarTodasColmenas();
                break;
            case 2:
                for (int i = 0; i < datosApicola.apicultores.size(); i++) {
                    System.out.println((i + 1) + ". " + datosApicola.apicultores.get(i));
                }
                break;
            case 3:
                for (int i = 0; i < abejas_existentes.size(); i++) {
                    System.out.println((i + 1) + ". " + abejas_existentes.get(i));
                }
                break;
            case 4:
                for (int i = 0; i < inspeccion.reportes.size(); i++) {
                    System.out.println((i + 1) + ". " + inspeccion.reportes.get(i));
                }
                break;
            case 0:
                System.out.println("SALIENDO");
                break;
            default:
                System.out.println("❌ Opción inválida.");
        }
    }

    public static void asignarApicultorAColmena() {
        GestorColmenas.mostrarTodasColmenas();

        System.out.println("Ingrese el ID de la colmena:");
        String colmena = scanner.nextLine();

        for (int i = 0; i < datosApicola.apicultores.size(); i++) {
            System.out.println((i + 1) + ". " + datosApicola.apicultores.get(i));
        }

        System.out.println("Ingrese el número del apicultor:");
        int index = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        datosApicola.asignarColmenaAApicultor(colmena, datosApicola.apicultores.get(index - 1));
        System.out.println("✅ Apicultor asignado a la colmena.");
    }

    // Métodos auxiliares (suposiciones, puedes cambiarlos)
    private static byte solicitarEdad() {
        System.out.print("Edad: ");
        return scanner.nextByte();
    }

    private static byte solicitarExperiencia(int edad) {
        System.out.print("Años de experiencia: ");
        return scanner.nextByte();
    }

    private static String solicitarEstadoSalud() {
        System.out.print("Estado de salud: ");
        return scanner.nextLine();
    }

    private static byte solicitarCantidadAbejas() {
        System.out.print("Cantidad de abejas: ");
        return scanner.nextByte();
    }

    private static float solicitarProduccionMiel() {
        System.out.print("Producción de miel (kg): ");
        return scanner.nextFloat();
    }
}
