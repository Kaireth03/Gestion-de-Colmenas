package Clases.Principales;

import java.io.Serializable;
import java.util.*;

public class Inspección implements Serializable {
    public Date fecha;
    public String resultado;
    public String acciones;

    public Inspección(String resultado, String acciones) {
        this.fecha = new Date();
        this.resultado = resultado;
        this.acciones = acciones;
    }

    public static void inspeccionarColmenas(List<Colmena> colmenas) {
        if (colmenas.isEmpty()) {
            system.out.println(" No hay colmenas para inspeccionar.\n", 50);
            return;
        }

        List<String> reportes = Collections.synchronizedList(new ArrayList<>());

        for (Colmena colmena : colmenas) {
            new Thread(() -> {
                try {
                    system.out.println(" Inspeccionando colmena " + colmena.id + "...\n", 40);
                    Thread.sleep(new Random().nextInt(800) + 400);

                    int puntos = 0;
                    if (colmena.abejaReina != null && "Buena".equals(colmena.abejaReina.estadoSalud)) puntos += 2;
                    puntos += switch (colmena.estadoSalud) {
                        case "Buena" -> 2;
                        case "Regular" -> 1;
                        default -> 0;
                    };

                    String resultado = switch (puntos) {
                        case 3, 4 -> "✅ Buen estado";
                        case 2 -> "⚠️ Revisar pronto";
                        default -> "🚨 Atención urgente";
                    };

                    String acciones = switch (resultado) {
                        case " Buen estado" -> "Revisar en 6 meses";
                        case " Revisar pronto" -> "Revisar en 1 mes";
                        default -> "Intervención inmediata";
                    };

                    colmena.agregarInspeccion(new Inspección(resultado, acciones));
                    reportes.add(" Colmena " + colmena.id + ": " + resultado);

                } catch (InterruptedException e) {
                    reportes.add(" Error inspeccionando colmena " + colmena.id);
                }
            }).start();
        }

        try { Thread.sleep(2500); } catch (InterruptedException ignored) {}

        reportes.forEach(r -> system.out.println(r + "\n", 30));
        animacionAbejas();
    }
}
