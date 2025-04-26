package Clases.Principales;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Inspeccion implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Date fecha;
    private final String resultado;
    private final String acciones;
    private final String methodUsed;

    private Inspeccion(String resultado, String acciones, String methodUsed) {
        this.fecha = new Date();
        this.resultado = resultado;
        this.acciones = acciones;
        this.methodUsed = methodUsed;
    }

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

    public static void inspeccionarYGuardar(Colmena colmena, String metodo) {
        Inspeccion inspeccion = realizar(colmena, metodo);
        colmena.agregarInspeccion(inspeccion);
        DatosApicola.getInstancia().guardarColmena(colmena);
        System.out.println(inspeccion.resumen(colmena));
        System.out.printf("📦 Colmena %s actualizada. Estado: %s%n", colmena.getId(), colmena.getEstadoSalud());
    }

    public Date getFecha() {
        return fecha;
    }

    public String getResultado() {
        return resultado;
    }

    public String getAcciones() {
        return acciones;
    }

    public String resumen(Colmena colmena) {
        return String.format("""
            📋 Inspección de la colmena %s
            🗓 Fecha: %s
            ✅ Resultado: %s
            🛠 Acciones sugeridas: %s
            🧰 Método utilizado: %s
            """, colmena.getId(), fecha, resultado, acciones, methodUsed);
    }

    private static void delay() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(400, 1200));
        } catch (InterruptedException ignored) {}
    }

    private static String getResultado(int puntos) {
    return switch (puntos) {
        case 3, -> "Buen estado";
        case 2 -> "Revisar pronto";
        default -> "Atención urgente";
    };
}

    private static String getAccion(String resultado) {
        return switch (resultado) {
            case "Buen estado" -> "Revisar en 6 meses";
            case "Revisar pronto" -> "Revisar en 1 mes";
            default -> "Intervención inmediata";
        };
    }
}

class HiloInspeccion implements Runnable {
    private final Colmena colmena;
    private final String metodo;

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
