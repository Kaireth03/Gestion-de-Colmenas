package Clases.Principales;

import java.io.Serializable;
import java.util.*;
import Clases.Principales.util; // tu clase de utilidades

public class Inspección implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date fecha;
    private String resultado;
    private String acciones;

    public Inspección(String resultado, String acciones) {
        this.fecha = new Date(); // Fecha actual al crear la inspección
        this.resultado = resultado;
        this.acciones = acciones;
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

    /**
     * Realiza una inspección a todas las colmenas en la lista.
     * Evalúa su estado y agrega un informe de inspección correspondiente.
     */
    public static void inspeccionarColmenas(List<Colmena> colmenas) {
        if (colmenas.isEmpty()) {
            System.out.println("No hay colmenas para inspeccionar.\n");
            return;
        }

        List<String> reportes = Collections.synchronizedList(new ArrayList<>());
        List<Thread> hilos = new ArrayList<>();

        for (Colmena colmena : colmenas) {
            Thread hilo = new Thread(() -> {
                try {
                    System.out.println("🔍 Inspeccionando colmena " + colmena.getId() + "...");
                    Thread.sleep(new Random().nextInt(800) + 400); // Simulación de tiempo de inspección

                    int puntos = 0;
                    if (colmena.getAbejaReina() != null && "Buena".equalsIgnoreCase(colmena.getAbejaReina().getEstadoSalud())) {
                        puntos += 2;
                    }

                    puntos += switch (colmena.getEstadoSalud()) {
                        case "Buena" -> 2;
                        case "Regular" -> 1;
                        default -> 0;
                    };

                    String resultado = switch (puntos) {
                        case 3, 4 -> "Buen estado";
                        case 2 -> "Revisar pronto";
                        default -> "Atención urgente";
                    };

                    String acciones = switch (resultado) {
                        case "Buen estado" -> "Revisar en 6 meses";
                        case "Revisar pronto" -> "Revisar en 1 mes";
                        default -> "Intervención inmediata";
                    };

                    Inspección nuevaInspeccion = new Inspección(resultado, acciones);
                    colmena.agregarInspeccion(nuevaInspeccion);
                    reportes.add("📄 Colmena " + colmena.getId() + ": " + resultado + " - " + acciones);

                } catch (InterruptedException e) {
                    reportes.add("❌ Error inspeccionando colmena " + colmena.getId());
                }
            });

            hilo.start();
            hilos.add(hilo);
        }

        // Esperamos a que todos los hilos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException ignored) {}
        }

        // Mostramos resultados
        System.out.println("\nRESULTADOS DE INSPECCIÓN:");
        reportes.forEach(System.out::println);
        System.out.println("\nAnimación de abejas 🐝 (simulada)\n");
    }
}
