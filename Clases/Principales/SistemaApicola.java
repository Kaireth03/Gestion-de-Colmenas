package Clases.Principales;

import java.util.*;

public class SistemaApicola {
    private static final Scanner scanner = new Scanner(System.in);
    public static List<AbejaReyna> abejasExistentes = new ArrayList<>();
    public static Map<String, AbejaReyna> colmenasConAbejaReina = new HashMap<>();

    private static final Inspeccion inspeccion = new Inspeccion();
    private static final DatosApicola datosApicola = new DatosApicola();

    // ==== MÉTODOS DE REGISTRO ====

    public static void registrarApicultor() {
        System.out.println("REGISTRO DEL NUEVO APICULTOR");

        try {
            String nombre = Utils.solicitarCampo("Ingrese el nombre del apicultor: ");
            String telefono = Utils.solicitarCampo("Ingrese el teléfono del apicultor: ");
            byte edad = solicitarEdad();
            byte experiencia = solicitarExperiencia(edad);
            String direccion = Utils.solicitarCampo("Ingrese la dirección del apicultor: ");
            String identificacion = Utils.solicitarCampo("Ingrese la identificación (DNI/ID/Cédula): ");

            Apicultor nuevo = new Apicultor(nombre, telefono, edad, experiencia, direccion, identificacion);
            datosApicola.agregarApicultor(nuevo);

            System.out.println("✅ Apicultor registrado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public static void asignarAbejaReina() {
        System.out.println("CREACIÓN DE ABEJA REINA");

        String nombre = solicitarInput("Nombre de la Abeja Reina: ");
        byte edad = Byte.parseByte(solicitarInput("Edad: "));
        String estadoSalud = solicitarInput("Estado de salud: ");
        float productividad = Float.parseFloat(solicitarInput("Productividad: "));

        AbejaReyna nuevaReina = new AbejaReyna(estadoSalud, edad, nombre, productividad);
        abejasExistentes.add(nuevaReina);

        mostrarColmenas();

        String colmenaId = solicitarInput("ID de la colmena para asignar la reina: ");
        if (colmenasConAbejaReina.containsKey(colmenaId)) {
            System.out.println("❌ Esa colmena ya tiene una abeja reina.");
            return;
        }

        colmenasConAbejaReina.put(colmenaId, nuevaReina);
        System.out.println("✅ Abeja reina asignada correctamente.");
    }

    // ==== MOSTRAR INFORMACIÓN ====

    public static void mostrarInformacion() {
        System.out.println("""
                ¿Qué desea ver?
                1. Colmenas registradas
                2. Apicultores
                3. Abejas reinas
                4. Historial de inspección
                0. Volver
            """);

        byte opcion = scanner.nextByte();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1 -> mostrarColmenas();
            case 2 -> mostrarLista(datosApicola.apicultores);
            case 3 -> mostrarLista(abejasExistentes);
            case 4 -> mostrarLista(inspeccion.reportes);
            case 0 -> System.out.println("↩ Volviendo...");
            default -> System.out.println("Opción inválida.");
        }
    }

    // ==== ASIGNACIÓN DE APICULTOR ====

    public static void asignarApicultorAColmena() {
        mostrarColmenas();
        String colmenaId = solicitarInput("ID de la colmena para asignar el apicultor: ");

        mostrarLista(datosApicola.apicultores);
        int indice = Integer.parseInt(solicitarInput("Índice del apicultor a asignar (1, 2, ...): ")) - 1;

        if (indice < 0 || indice >= datosApicola.apicultores.size()) {
            System.out.println("❌ Índice inválido.");
            return;
        }

        Apicultor apicultor = datosApicola.apicultores.get(indice);
        datosApicola.asignarColmenaAPicultor(colmenaId, apicultor);
        System.out.println("✅ Apicultor asignado a la colmena.");
    }

    // ==== EDICIÓN DE DATOS ====

    public static void editarInformacion() {
        System.out.println("""
                ¿Qué desea editar?
                1. Colmenas
                2. Apicultores
                3. Abejas reinas
                0. Volver
            """);

        byte opcion = scanner.nextByte();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1 -> editarColmena();
            case 2 -> editarApicultor();
            case 3 -> editarAbejaReina();
            case 0 -> System.out.println("↩ Volviendo...");
            default -> System.out.println("Opción inválida.");
        }
    }

    // ==== MÉTODOS AUXILIARES ====

    private static void mostrarColmenas() {
        mostrarLista(datosApicola.colmenas);
    }

    private static <T> void mostrarLista(List<T> lista) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
    }

    private static void editarColmena() {
        mostrarColmenas();
        int index = Integer.parseInt(solicitarInput("Índice de colmena a editar: ")) - 1;

        if (index >= 0 && index < datosApicola.colmenas.size()) {
            System.out.println("Editando: " + datosApicola.colmenas.get(index));
            String id = Utils.solicitarCampo("Nuevo ID: ");
            String ubicacion = Utils.solicitarCampo("Nueva ubicación: ");
            String estadoSalud = solicitarEstadoSalud();
            String tipo = Utils.solicitarCampo("Tipo: ");
            byte cantidad = (byte) solicitarCantidadAbejas();
            float miel = solicitarProduccionMiel();

            Colmena nueva = new Colmena(id, ubicacion, tipo, estadoSalud, cantidad, miel);
            datosApicola.colmenas.set(index, nueva);
        }
    }

    private static void editarApicultor() {
        mostrarLista(datosApicola.apicultores);
        int index = Integer.parseInt(solicitarInput("Índice del apicultor a editar: ")) - 1;

        if (index >= 0 && index < datosApicola.apicultores.size()) {
            System.out.println("Editando: " + datosApicola.apicultores.get(index));
            String nombre = Utils.solicitarCampo("Nombre: ");
            String telefono = Utils.solicitarCampo("Teléfono: ");
            byte edad = solicitarEdad();
            byte experiencia = solicitarExperiencia(edad);
            String direccion = Utils.solicitarCampo("Dirección: ");
            String identificacion = Utils.solicitarCampo("Identificación: ");

            Apicultor nuevo = new Apicultor(nombre, telefono, edad, experiencia, direccion, identificacion);
            datosApicola.apicultores.set(index, nuevo);
        }
    }

    private static void editarAbejaReina() {
        mostrarLista(abejasExistentes);
        int index = Integer.parseInt(solicitarInput("Índice de abeja reina a editar: ")) - 1;

        if (index >= 0 && index < abejasExistentes.size()) {
            System.out.println("Editando: " + abejasExistentes.get(index));
            String nombre = solicitarInput("Nombre: ");
            byte edad = Byte.parseByte(solicitarInput("Edad: "));
            String estado = solicitarInput("Estado de salud: ");
            float productividad = Float.parseFloat(solicitarInput("Productividad: "));

            AbejaReyna nueva = new AbejaReyna(estado, edad, nombre, productividad);
            abejasExistentes.set(index, nueva);
        }
    }

    // ==== MÉTODOS DE INPUT ====

    private static String solicitarInput(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private static byte solicitarEdad() {
        return Byte.parseByte(solicitarInput("Edad: "));
    }

    private static byte solicitarExperiencia(byte edad) {
        byte exp;
        do {
            exp = Byte.parseByte(solicitarInput("Años de experiencia: "));
            if (exp > edad) {
                System.out.println("❌ La experiencia no puede ser mayor a la edad.");
            }
        } while (exp > edad);
        return exp;
    }

    private static String solicitarEstadoSalud() {
        return Utils.solicitarCampo("Estado de salud: ");
    }

    private static int solicitarCantidadAbejas() {
        return Integer.parseInt(Utils.solicitarCampo("Cantidad de abejas: "));
    }

    private static float solicitarProduccionMiel() {
        return Float.parseFloat(Utils.solicitarCampo("Producción de miel (kg): "));
    }
}
