// ───────────────────────────────────────────
// IMPORTACIONES
// ───────────────────────────────────────────

package Clases.Principales;
import java.util.*;

// ───────────────────────────────────────────
// DEFINICIÓN DE LA CLASE PRINCIPAL
// ───────────────────────────────────────────

/**
 * Clase principal que gestiona el sistema apícola.
 * Administra apicultores, abejas reinas y colmenas.
 */
public class SistemaApicola {

    // ───────────────────────────────────────────
    // VARIABLES GLOBALES
    // ───────────────────────────────────────────

    private static final Scanner scanner = new Scanner(System.in); // Para capturar entradas del usuario

    public static final List<AbejaReina> abejasExistentes = new ArrayList<>(); // Lista de todas las abejas reinas
    public static final Map<String, AbejaReina> colmenasConAbejaReina = new HashMap<>(); // Relación colmena-ID -> abeja reina

    private static final DatosApicola datos = DatosApicola.getInstancia(); // Singleton para manejar los datos

    // ───────────────────────────────────────────
    // REGISTRO DE APICULTORES
    // ───────────────────────────────────────────

    /**
     * Permite registrar un nuevo apicultor en el sistema.
     */
    public static void registrarApicultor() {
        try {
            // Solicita y captura todos los datos del apicultor
            String nombre = Utils.solicitarNombre();
            String telefono = Utils.solicitarTelefono();
            byte edad = solicitarEdad();
            byte experiencia = solicitarExperiencia(edad);
            String direccion = Utils.solicitarCampo("Ingrese la dirección del apicultor: ");
            String id = Utils.solicitarIdentificacion();

            // Crea el apicultor y lo agrega a los datos
            datos.agregarApicultor(new Apicultor(nombre, telefono, edad, experiencia, direccion, id));
            Utils.delayPrint("✅ Apicultor registrado correctamente.", 700);
        } catch (Exception e) {
            Utils.delayPrint("❌ Error: " + e.getMessage(), 700);
        }
    }

    // ───────────────────────────────────────────
    // ASIGNACIÓN DE ABEJAS REINAS
    // ───────────────────────────────────────────

    /**
     * Permite asignar una abeja reina a una colmena.
     */
    public static void asignarAbejaReina() {
        String idReina = Utils.solicitarCampo("ID para la nueva Abeja Reina: ");

        // Verifica si ya existe una reina con ese ID
        if (abejasExistentes.stream().anyMatch(r -> r.getId().equalsIgnoreCase(idReina))) {
            System.out.println("❌ Ya existe una Abeja Reina con el ID: " + idReina);
            return;
        }

        // Captura datos adicionales de la nueva abeja reina
        byte edad = Utils.solicitarByteEnRango("Edad (días): ", (byte) 0, (byte) (5 * 365));
        String salud = solicitarEstadoSaludReina();
        float productividad = Utils.solicitarFloatRango("Productividad de la abeja reina (0 a 10): ", 0f, 10f);

        // Crea y agrega la nueva abeja reina
        AbejaReina reina = new AbejaReina(idReina, salud, edad, productividad);
        abejasExistentes.add(reina);

        // Asigna la abeja reina a una colmena
        mostrarColmenas();
        String idColmena = solicitarInput("ID de la colmena para asignar la reina: ");

        // Verifica si ya hay una reina asignada
        if (colmenasConAbejaReina.containsKey(idColmena)) {
            System.out.println("❌ Esa colmena ya tiene una abeja reina.");
            return;
        }

        colmenasConAbejaReina.put(idColmena, reina);
        Utils.delayPrint("✅ Abeja reina asignada correctamente.", 700);
    }

    // ───────────────────────────────────────────
    // MENÚ DE INFORMACIÓN
    // ───────────────────────────────────────────

    /**
     * Muestra el menú de opciones de visualización de información.
     */
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

        // Menú de navegación basado en la opción seleccionada
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

    // ───────────────────────────────────────────
    // FUNCIONES DE ASIGNACIÓN Y EDICIÓN
    // ───────────────────────────────────────────

    /**
     * Asigna un apicultor a una colmena.
     */
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

    /**
     * Permite editar colmenas, apicultores o abejas reinas.
     */
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

// ───────────────────────────────────────────
// FUNCIONES AUXILIARES DE MOSTRAR Y BUSCAR
// ───────────────────────────────────────────

    /**
     * Muestra la lista de colmenas.
     */
    private static void mostrarColmenas() {
        mostrarLista(datos.colmenas);
        Utils.delayPrint("Lista de colmenas", 5000);
    }

    /**
     * Muestra cualquier lista con índice.
     */
    private static <T> void mostrarLista(List<T> lista) {
        for (int i = 0; i < lista.size(); i++)
            System.out.println((i + 1) + ". " + lista.get(i));
    }

    /**
     * Muestra el historial de inspecciones realizadas.
     */
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

// ───────────────────────────────────────────
// FUNCIONES DE EDICIÓN
// ───────────────────────────────────────────

    /**
     * Edita una colmena existente.
     */
    private static void editarColmena() {
        mostrarColmenas();

        byte maxIndice = (byte) (datos.colmenas.size() - 1);
        if (maxIndice < 0) {
            System.out.println("❌ No hay colmenas registradas.");
            return;
        }

        byte indiceColmena = Utils.solicitarByteEnRango(
                "Índice de la colmena (0 a " + maxIndice + "): ",
                (byte) 0, maxIndice
        );

        Colmena vieja = datos.colmenas.get(indiceColmena);
        String idViejo = vieja.getId();
        String nuevoId;
        do {
            nuevoId = Utils.solicitarCampo("Nuevo ID (formato COL###): ");
            if (validarFormatoColmenaId(nuevoId)) {
                System.out.println("❌ Formato inválido. Debe ser COL seguido de 3 dígitos (Ej: COL001).");
            }
        } while (validarFormatoColmenaId(nuevoId));

        if (!nuevoId.equals(idViejo) && existeColmenaConId(nuevoId)) {
            System.out.println("❌ Ya existe una colmena con ese ID.");
            return;
        }

        Colmena nueva = new Colmena(
                nuevoId,
                Utils.solicitarCampo("Nueva ubicación: "),
                Utils.solicitarCampo("Tipo: "),
                solicitarEstadoSalud(),
                (byte) solicitarCantidadAbejas(),
                solicitarProduccionMiel()
        );

        datos.colmenas.set(indiceColmena, nueva);
        actualizarReferenciasColmena(idViejo, nuevoId);
        Utils.delayPrint("✅ Colmena editada correctamente.", 700);
    }

    /**
     * Edita un apicultor existente.
     */
    private static void editarApicultor() {
        if (datos.apicultores.isEmpty()) {
            System.out.println("❌ No hay apicultores registrados.");
            return;
        }

        mostrarLista(datos.apicultores);
        byte maxIndice = (byte) (datos.apicultores.size() - 1);

        byte indiceApicultor = Utils.solicitarByteEnRango(
                "Índice del apicultor (0 a " + maxIndice + "): ",
                (byte) 0, maxIndice
        );

        String id = Utils.solicitarIdentificacion();
        if (!id.equals(datos.apicultores.get(indiceApicultor).getIdentificacion()) && existeIdentificacion(id)) {
            System.out.println("❌ Ya existe un apicultor con esa identificación.");
            return;
        }

        datos.apicultores.set(indiceApicultor, new Apicultor(
                Utils.solicitarNombre(),
                Utils.solicitarTelefono(),
                solicitarEdad(),
                solicitarExperiencia(solicitarEdad()),
                Utils.solicitarCampo("Dirección: "),
                id
        ));

        Utils.delayPrint("✅ Apicultor editado correctamente.", 700);
    }

    /**
     * Edita una abeja reina existente.
     */
    private static void editarAbejaReina() {
        if (abejasExistentes.isEmpty()) {
            System.out.println("❌ No hay abejas reinas disponibles.");
            return;
        }

        mostrarLista(abejasExistentes);
        byte maxIndice = (byte) (abejasExistentes.size() - 1);

        byte indiceAbejaReina = Utils.solicitarByteEnRango(
                "Índice de la abeja reina a editar (0 a " + maxIndice + "): ",
                (byte) 0, maxIndice
        );

        AbejaReina reinaExistente = abejasExistentes.get(indiceAbejaReina);
        String idExistente = reinaExistente.getId();

        String nuevoEstadoSalud = solicitarEstadoSaludReina();
        byte nuevaEdad = Utils.solicitarByteEnRango("Nueva Edad (días): ", (byte) 0, (byte) (5 * 365));
        float nuevaProductividad = Utils.solicitarFloatRango("Nueva Productividad: ", 0f, 1000f);

        abejasExistentes.set(indiceAbejaReina, new AbejaReina(
                idExistente,
                nuevoEstadoSalud,
                nuevaEdad,
                nuevaProductividad
        ));

        Utils.delayPrint("✅ Abeja reina editada correctamente.", 700);
    }

// ───────────────────────────────────────────
// FUNCIONES DE VALIDACIÓN Y ACTUALIZACIÓN
// ───────────────────────────────────────────

    /**
     * Verifica si existe una colmena con el ID dado.
     */
    private static boolean existeColmenaConId(String id) {
        return datos.colmenas.stream().anyMatch(c -> c.getId().equals(id));
    }

    /**
     * Verifica si existe un apicultor con la identificación dada.
     */
    private static boolean existeIdentificacion(String id) {
        return datos.apicultores.stream().anyMatch(a -> a.getIdentificacion().equals(id));
    }

    /**
     * Actualiza las referencias de una colmena al editar su ID.
     */
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

// ───────────────────────────────────────────
// FUNCIONES DE BÚSQUEDA
// ───────────────────────────────────────────

    /**
     * Permite buscar colmenas, apicultores o abejas reinas.
     */
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
                        .toList();

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

// ───────────────────────────────────────────
// FUNCIONES DE GENERACIÓN DE INFORMES
// ───────────────────────────────────────────

    /**
     * Genera informes personalizados usando expresiones lambda.
     */
    public static void generarInformesConLambda() {
        System.out.println("""
                    ¿Qué informe desea generar?
                    1. Colmenas con alta producción de miel (>50kg)
                    2. Colmenas con estado de salud crítico
                    3. Abejas reina altamente productivas (>80 de productividad)
                    4. IDs de colmenas altamente productivas (>50kg)
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

// ───────────────────────────────────────────
// FUNCIONES DE SOLICITUD DE DATOS
// ───────────────────────────────────────────

    /**
     * Solicita un input de texto.
     */
    private static String solicitarInput(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    /**
     * Solicita la edad del apicultor.
     */
    private static byte solicitarEdad() {
        return Byte.parseByte(solicitarInput("Edad: "));
    }

    /**
     * Solicita los años de experiencia del apicultor, validando que no superen la edad.
     */
    private static byte solicitarExperiencia(byte edad) {
        byte exp;
        do {
            exp = Byte.parseByte(solicitarInput("Años de experiencia: "));
            if (exp > edad) System.out.println("❌ La experiencia no puede ser mayor a la edad.");
        } while (exp > edad);
        return exp;
    }

    /**
     * Solicita el estado de salud de una abeja reina.
     */
    private static String solicitarEstadoSaludReina() {
        System.out.println("""
                    Estado de Salud de la Abeja Reina:
                    1. En plenitud
                    2. Zumbido estable
                    3. Enferma
                """);

        while (true) {
            String opcion = Utils.solicitarCampo("👉 Ingresa el número correspondiente: ");
            switch (opcion) {
                case "1" -> {
                    return AbejaReina.ESTADOS_SALUD_VALIDOS.getFirst();
                }
                case "2" -> {
                    return AbejaReina.ESTADOS_SALUD_VALIDOS.get(1);
                }
                case "3" -> {
                    return AbejaReina.ESTADOS_SALUD_VALIDOS.get(2);
                }
                default -> System.out.println("❌ Opción inválida. Intente de nuevo.");
            }
        }
    }

    /**
     * Solicita el estado de salud de una colmena.
     */
    private static String solicitarEstadoSalud() {
        List<String> estadosValidos = List.of("En plenitud", "Zumbido estable", "Enferma");
        System.out.println("Estados válidos: " + estadosValidos);
        String estado;
        do {
            estado = Utils.solicitarCampo("Estado de salud: ");
            if (!estadosValidos.contains(estado)) {
                System.out.println("❌ Estado no válido. Elige uno de los estados listados.");
            }
        } while (!estadosValidos.contains(estado));
        return estado;
    }

    /**
     * Solicita la cantidad de abejas.
     */
    private static int solicitarCantidadAbejas() {
        return Integer.parseInt(Utils.solicitarCampo("Cantidad de abejas: "));
    }

    /**
     * Solicita la producción de miel en kilogramos.
     */
    private static float solicitarProduccionMiel() {
        return Float.parseFloat(Utils.solicitarCampo("Producción de miel (kg): "));
    }

    /**
     * Valida el formato del ID de una colmena.
     */
    private static boolean validarFormatoColmenaId(String id) {
        return !id.matches("^COL\\d{3}$");
    }
}
