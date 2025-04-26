package Clases.Principales;

// ─────────────────────────────────────────────────────────────
// Imports
// ─────────────────────────────────────────────────────────────
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

// ─────────────────────────────────────────────────────────────
// Clase principal: Inspeccion
// ─────────────────────────────────────────────────────────────
public class Inspeccion implements Serializable {

    // ─────────────────────────────────────────────────────────────
    // Atributos
    // ─────────────────────────────────────────────────────────────
    @Serial
    private static final long serialVersionUID = 1L;

    private final Date fecha;
    private final String resultado;
    private final String acciones;
    private final String methodUsed;

    // ─────────────────────────────────────────────────────────────
    // Constructor privado
    // ─────────────────────────────────────────────────────────────
    private Inspeccion(String resultado, String acciones, String methodUsed) {
        this.fecha = new Date();
        this.resultado = resultado;
        this.acciones = acciones;
        this.methodUsed = methodUsed;
    }

    // ─────────────────────────────────────────────────────────────
    // Métodos públicos principales
    // ─────────────────────────────────────────────────────────────

    /**
     * Realiza una inspección sobre una colmena y genera un informe.
     */
    public static Inspeccion realizar(Colmena colmena, String methodUsed) {
        System.out.println("🔍 Realizando inspección sobre colmena " + colmena.getId() + "...");
        delay();

        int puntos = 0;

        if (colmena.getAbejaReinaAsignada() != null &&
                "Buena".equalsIgnoreCase(colmena.getAbejaReinaAsignada().getEstadoSalud())) {
            puntos += 2;
        }

        puntos += switch (colmena.getEstadoSalud()) {
            case "En plenitud" -> 2;
            case "Zumbido estable" -> 1;
            default -> 0;
        };

        return new Inspeccion(
                getResultado(puntos),
                getAccion(getResultado(puntos)),
                methodUsed
        );
    }

    /**
     * Inspecciona todas las colmenas concurrentemente utilizando hilos.
     */
    public static void inspeccionarTodasColmenasConHilos() {
        List<Colmena> colmenas = DatosApicola.getInstancia().obtenerColmenas();

        if (colmenas.isEmpty()) {
            System.out.println("❌ No hay colmenas para inspeccionar.");
            return;
        }

        System.out.println("🔧 Iniciando inspecciones concurrentes...");

        List<Thread> hilos = new ArrayList<>();
        for (Colmena colmena : colmenas) {
            Thread hilo = new Thread(new HiloInspeccion(colmena, "Concurrente"));
            hilos.add(hilo);
            hilo.start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("⚠️ Un hilo fue interrumpido.");
            }
        }

        System.out.println("✅ Inspecciones completas.");
    }

    /**
     * Realiza una inspección sobre una colmena, guarda los resultados, y actualiza los datos.
     */
    public static void inspeccionarYGuardar(Colmena colmena, String metodo) {
        Inspeccion inspeccion = realizar(colmena, metodo);
        colmena.agregarInspeccion(inspeccion);
        DatosApicola.getInstancia().guardarColmena(colmena);
        System.out.println(inspeccion.resumen(colmena));
        System.out.printf("📦 Colmena %s actualizada. Estado: %s%n", colmena.getId(), colmena.getEstadoSalud());
    }

    // ─────────────────────────────────────────────────────────────
    // Getters
    // ─────────────────────────────────────────────────────────────

    public Date getFecha() {
        return fecha;
    }

    public String getResultado() {
        return resultado;
    }

    public String getAcciones() {
        return acciones;
    }

    /**
     * Genera un resumen de la inspección realizada.
     */
    public String resumen(Colmena colmena) {
        return String.format("""
            📋 Inspección de la colmena %s
            🗓 Fecha: %s
            ✅ Resultado: %s
            🛠 Acciones sugeridas: %s
            🧰 Método utilizado: %s
            """, colmena.getId(), fecha, resultado, acciones, methodUsed);
    }

    // ─────────────────────────────────────────────────────────────
    // Métodos privados auxiliares
    // ─────────────────────────────────────────────────────────────

    /**
     * Simula una demora para dar realismo a la inspección.
     */
    private static void delay() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(400, 1200));
        } catch (InterruptedException ignored) {}
    }

    /**
     * Determina el resultado de la inspección basado en puntos.
     */
    private static String getResultado(int puntos) {
        return switch (puntos) {
            case 3, 4 -> "Buen estado";
            case 2 -> "Revisar pronto";
            default -> "Atención urgente";
        };
    }

    /**
     * Determina las acciones recomendadas basadas en el resultado.
     */
    private static String getAccion(String resultado) {
        resultado = capitalizeFirstLetter(resultado);
        return switch (resultado) {
            case "Buen estado" -> "Revisar en 6 meses";
            case "Revisar pronto" -> "Revisar en 1 mes";
            default -> "Intervención inmediata";
        };
    }

    /**
     * Capitaliza la primera letra de un texto.
     */
    private static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}

// ─────────────────────────────────────────────────────────────
// Clase secundaria: HiloInspeccion
// ─────────────────────────────────────────────────────────────
class HiloInspeccion implements Runnable {

    private final Colmena colmena;
    private final String metodo;

    // Constructor
    public HiloInspeccion(Colmena colmena, String metodo) {
        this.colmena = colmena;
        this.metodo = metodo;
    }

    @Override
    public void run() {
        Inspeccion inspeccion = Inspeccion.realizar(colmena, metodo);
        colmena.agregarInspeccion(inspeccion);
        DatosApicola.getInstancia().guardarColmena(colmena);
        System.out.println(inspeccion.resumen(colmena));
        System.out.printf("📦 Colmena %s actualizada. Estado: %s%n", colmena.getId(), colmena.getEstadoSalud());
    }
}
