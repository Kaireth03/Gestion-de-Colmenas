package Clases.Principales;

import java.util.*;

public class SistemaApicola {
    private static final Scanner scanner = new Scanner(System.in);
    public static final List<AbejaReina> abejasExistentes = new ArrayList<>();
    public static final Map<String, AbejaReina> colmenasConAbejaReina = new HashMap<>();
    private static final DatosApicola datos = DatosApicola.getInstancia();

    public static void registrarApicultor() {
        try {
            String nombre = Utils.solicitarCampo("Ingrese el nombre del apicultor: ");
            String telefono = Utils.solicitarCampo("Ingrese el teléfono del apicultor: ");
            byte edad = solicitarEdad();
            byte experiencia = solicitarExperiencia(edad);
            String direccion = Utils.solicitarCampo("Ingrese la dirección del apicultor: ");
            String id = Utils.solicitarCampo("Ingrese la identificación (DNI/ID/Cédula): ");

            datos.agregarApicultor(new Apicultor(nombre, telefono, edad, experiencia, direccion, id));
            Utils.delayPrint("✅ Apicultor registrado correctamente.", 700);
        } catch (Exception e) {
            Utils.delayPrint("❌ Error: " + e.getMessage(), 700);
        }
    }

    public static void asignarAbejaReina() {
        // Solicitar ID para la nueva reina
        String idReina = Utils.solicitarCampo("ID para la nueva Abeja Reina: ");
        // Verificar si ya existe una reina con ese ID (opcional pero recomendado)
        if (abejasExistentes.stream().anyMatch(r -> r.getId().equalsIgnoreCase(idReina))) {
            System.out.println("❌ Ya existe una Abeja Reina con el ID: " + idReina);
            return;
        }
        byte edad = Utils.solicitarByteEnRango("Edad (días): ", (byte) 0, (byte) (5 * 365)); // Asumiendo edad en días
        String salud = solicitarEstadoSaludReina();
        float productividad = Utils.solicitarFloatMin("Productividad: ", 0);

        // Usar el nuevo constructor con ID
        AbejaReina reina = new AbejaReina(idReina, salud, edad, productividad);
        abejasExistentes.add(reina);

        mostrarColmenas();
        String idColmena = solicitarInput("ID de la colmena para asignar la reina: ");

        if (colmenasConAbejaReina.containsKey(idColmena)) {
            System.out.println("❌ Esa colmena ya tiene una abeja reina.");
            return;
        }

        colmenasConAbejaReina.put(idColmena, reina);
        Utils.delayPrint("✅ Abeja reina asignada correctamente.", 700);
    }

    public static void mostrarInformacion() {
        System.out.println("""
            ¿Qué desea ver?
            1. Colmenas registradas
            2. Apicultores
            3. Abejas reinas
            4. Historial de inspección
            5. Buscar información
            6. Generar Informes Con Lambda
            0. Volver
        """);

        byte opcion = Utils.solicitarByteEnRango("👉 Opción: ", (byte) 0, (byte) 6);

        switch (opcion) {
            case 1 -> mostrarColmenas();
            case 2 -> mostrarLista(datos.apicultores);
            case 3 -> mostrarLista(abejasExistentes);
            case 4 -> mostrarHistorialInspeccion();
            case 5 -> buscarElemento();
            case 6 -> generarInformesConLambda();
            case 0 -> System.out.println("↩ Volviendo...");
            default -> System.out.println("❌ Opción inválida.");
        }
    }

    public static void asignarApicultorAColmena() {
        mostrarColmenas();
        String colmenaId = solicitarInput("ID de la colmena para asignar el apicultor: ");
        mostrarLista(datos.apicultores);

        int i = Integer.parseInt(solicitarInput("Índice del apicultor a asignar: ")) - 1;
        if (i < 0 || i >= datos.apicultores.size()) {
            System.out.println("❌ Índice inválido.");
            return;
        }

        datos.asignarColmenaAPicultor(colmenaId, datos.apicultores.get(i));
        Utils.delayPrint("✅ Apicultor asignado a la colmena.", 700);
    }

    public static void editarInformacion() {
        System.out.println("""
            ¿Qué desea editar?
            1. Colmenas
            2. Apicultores
            3. Abejas reinas
            0. Volver
        """);

        byte opcion = Utils.solicitarByteEnRango("👉 Opción: ", (byte) 0, (byte) 3);

        switch (opcion) {
            case 1 -> editarColmena();
            case 2 -> editarApicultor();
            case 3 -> editarAbejaReina();
            case 0 -> System.out.println("↩ Volviendo...");
            default -> System.out.println("❌ Opción inválida.");
        }
    }

    private static void mostrarColmenas() {
        mostrarLista(datos.colmenas);
        Utils.delayPrint("Lista de colmenas", 5000);
    }

    private static <T> void mostrarLista(List<T> lista) {
        for (int i = 0; i < lista.size(); i++)
            System.out.println((i + 1) + ". " + lista.get(i));
    }

    private static void mostrarHistorialInspeccion() {
        System.out.println("\n📋 HISTORIAL DE INSPECCIONES:");
        for (Colmena colmena : datos.obtenerColmenas()) {
            List<Inspeccion> inspecciones = colmena.getInspecciones();
            if (!inspecciones.isEmpty()) {
                System.out.println("🐝 Colmena ID: " + colmena.getId());
                Inspeccion ins = inspecciones.getFirst();
                System.out.println("  📅 Fecha: " + ins.getFecha());
                System.out.println("  📊 Resultado: " + ins.getResultado());
                System.out.println("  🛠️ Acciones: " + ins.getAcciones());
                System.out.println();
            }
        }
    }

    private static void editarColmena() {
        mostrarColmenas();
        byte indiceColmena = (byte) (Utils.solicitarByteEnRango("Índice de la colmena: ", (byte) 0, (byte) datos.colmenas.size()) - 1);

        Colmena vieja = datos.colmenas.get(indiceColmena);
        String idViejo = vieja.getId();
        String nuevoId = Utils.solicitarCampo("Nuevo ID: ");
        if (!nuevoId.equals(idViejo) && existeColmenaConId(nuevoId)) {
            System.out.println("❌ Ya existe una colmena con ese ID.");
            return;
        }

        Colmena nueva = new Colmena(nuevoId,
                Utils.solicitarCampo("Nueva ubicación: "),
                Utils.solicitarCampo("Tipo: "),
                solicitarEstadoSalud(),
                (byte) solicitarCantidadAbejas(),
                solicitarProduccionMiel());

        datos.colmenas.set(indiceColmena, nueva);
        actualizarReferenciasColmena(idViejo, nuevoId);
        Utils.delayPrint("✅ Colmena editada correctamente.", 700);
    }

    private static boolean existeColmenaConId(String id) {
        return datos.colmenas.stream().anyMatch(c -> c.getId().equals(id));
    }

    private static void actualizarReferenciasColmena(String viejo, String nuevo) {
        if (!nuevo.equals(viejo)) {
            if (colmenasConAbejaReina.containsKey(viejo))
                colmenasConAbejaReina.put(nuevo, colmenasConAbejaReina.remove(viejo));

            Apicultor apicultor = datos.obtenerApicultorPorColmena(viejo);
            if (apicultor != null) {
                datos.desasignarColmenaDeApicultor(viejo);
                datos.asignarColmenaAPicultor(nuevo, apicultor);
            }
        }
    }

    private static void editarApicultor() {
        mostrarLista(datos.apicultores);
        int indiceApicultor = (byte) (Utils.solicitarByteEnRango("Índice del apicultor: ", (byte) 0, (byte) datos.colmenas.size()) - 1);

        String id = Utils.solicitarCampo("Identificación: ");
        if (!id.equals(datos.apicultores.get(indiceApicultor).getIdentificacion()) && existeIdentificacion(id)) {
            System.out.println("❌ Ya existe un apicultor con esa identificación.");
            return;
        }

        datos.apicultores.set(indiceApicultor, new Apicultor(
                Utils.solicitarCampo("Nombre: "),
                Utils.solicitarCampo("Teléfono: "),
                solicitarEdad(),
                solicitarExperiencia(solicitarEdad()),
                Utils.solicitarCampo("Dirección: "),
                id
        ));

        Utils.delayPrint("✅ Apicultor editado correctamente.", 700);
    }

    private static boolean existeIdentificacion(String id) {
        return datos.apicultores.stream().anyMatch(a -> a.getIdentificacion().equals(id));
    }

    private static void editarAbejaReina() {
        mostrarLista(abejasExistentes);
        int indiceAbejaReina = Utils.solicitarByteEnRango("Índice de la abeja reina a editar: ", (byte) 1, (byte) abejasExistentes.size()) - 1; // Ajustar rango a 1-based

        // Obtener el ID de la reina existente
        AbejaReina reinaExistente = abejasExistentes.get(indiceAbejaReina);
        String idExistente = reinaExistente.getId();

        // Solicitar nuevos datos
        String nuevoEstadoSalud = solicitarEstadoSaludReina();
        byte nuevaEdad = Utils.solicitarByteEnRango("Nueva Edad (días): ", (byte) 0, (byte) (5 * 365)); // Asumiendo edad en días
        float nuevaProductividad = Utils.solicitarFloatMin("Nueva Productividad: ", 0);

        // Crear la nueva instancia usando el ID existente y los nuevos datos
        abejasExistentes.set(indiceAbejaReina, new AbejaReina(
                idExistente, // Usar el ID existente
                nuevoEstadoSalud,
                nuevaEdad,
                nuevaProductividad
        ));

        Utils.delayPrint("✅ Abeja reina editada correctamente.", 700);
    }

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
            if (exp > edad) System.out.println("❌ La experiencia no puede ser mayor a la edad.");
        } while (exp > edad);
        return exp;
    }

    private static String solicitarEstadoSaludReina() {
        System.out.println("""
            Estado de Salud de la Abeja Reina:
            1. En plenitud
            2. Zumbido estable
            3. Enferma
        """);

        while (true) {
            return switch (Utils.solicitarCampo("👉 Ingresa el número correspondiente: ")) {
                case "1" -> AbejaReina.ESTADOS_SALUD_VALIDOS.getFirst();
                case "2" -> AbejaReina.ESTADOS_SALUD_VALIDOS.get(1);
                case "3" -> AbejaReina.ESTADOS_SALUD_VALIDOS.get(2);
                default -> {
                    System.out.println("❌ Opción inválida.");
                    yield "";
                }
            };
        }
    }

    private static void buscarElemento() {
        System.out.println("""
            ¿Qué desea buscar?
            1. Colmena por ID
            2. Apicultor por nombre o ID
            3. Abeja Reina por estado de salud
            0. Volver
        """);

        byte opcion = Utils.solicitarByteEnRango("👉 Opción: ", (byte) 0, (byte) 3);

        switch (opcion) {
            case 1 -> {
                String id = solicitarInput("Ingrese el ID de la colmena: ");
                Optional<Colmena> colmena = datos.colmenas.stream()
                        .filter(c -> c.getId().equalsIgnoreCase(id))
                        .findFirst();
                colmena.ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("❌ No se encontró la colmena.")
                );
            }
            case 2 -> {
                String termino = solicitarInput("Ingrese el nombre o ID del apicultor: ").toLowerCase();
                var resultados = datos.apicultores.stream()
                        .filter(a -> a.getNombre().toLowerCase().contains(termino) || a.getIdentificacion().equalsIgnoreCase(termino))
                        .toList(); // Coleta resultados

                Optional.of(resultados)
                        .filter(list -> !list.isEmpty())
                        .ifPresentOrElse(
                                list -> list.forEach(a -> System.out.println("🔍 " + a)),
                                () -> System.out.println("❌ No se encontraron apicultores.")
                        );
            }
            case 3 -> {
                String salud = solicitarInput("Ingrese el estado de salud de la abeja reina: ").toLowerCase();
                var resultados = abejasExistentes.stream()
                        .filter(r -> r.getEstadoSalud().toLowerCase().contains(salud))
                        .toList();

                Optional.of(resultados)
                        .filter(list -> !list.isEmpty())
                        .ifPresentOrElse(
                                list -> list.forEach(r -> System.out.println("👑 " + r)),
                                () -> System.out.println("❌ No se encontraron abejas reinas.")
                        );
            }
            case 0 -> System.out.println("↩ Volviendo...");
            default -> System.out.println("❌ Opción inválida.");
        }
    }

    public static void generarInformesConLambda() {
        System.out.println("""
            ¿Qué informe desea generar?
            1. Colmenas con alta producción de miel (>50kg)
            2. Colmenas con estado de salud crítico
            3. Abejas reina altamente productivas (>80 de productividad)
            4. IDs de colmenas altamente productivas (>50) -> Solo retorna el ID
            0. Volver
        """);

        byte opcion = Utils.solicitarByteEnRango("👉 Opción: ", (byte) 0, (byte) 4);

        switch (opcion) {
            case 1 -> datos.colmenas.stream()
                    .filter(c -> c.getProduccionMiel() > 50)
                    .forEach(c -> System.out.println("🍯 Alta producción: " + c));
            case 2 -> datos.colmenas.stream()
                    .filter(c -> c.getEstadoSalud().toLowerCase().contains("enferma"))
                    .forEach(c -> System.out.println("🚨 Colmena crítica: " + c));
            case 3 -> abejasExistentes.stream()
                    .filter(r -> r.getProductividad() > 80)
                    .forEach(r -> System.out.println("👑 Reina súper productiva: " + r));
            case 4 -> {
                List<String> idsColmenasProductivas = datos.colmenas.stream()
                        .filter(c -> c.getProduccionMiel() > 50)
                        .map(Colmena::getId)
                        .toList();

                if (idsColmenasProductivas.isEmpty()) {
                    System.out.println("❌ No hay colmenas altamente productivas.");
                } else {
                    System.out.println("🔎 IDs de colmenas altamente productivas:");
                    idsColmenasProductivas.forEach(id -> System.out.println(" - " + id));
                }
            }
            case 0 -> System.out.println("↩ Volviendo...");
            default -> System.out.println("❌ Opción inválida.");
        }
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
