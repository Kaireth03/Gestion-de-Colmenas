import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class GestionColmenas {
    private List<Colmena> colmenas;

    public GestionColmenas() {
        this.colmenas = new ArrayList<>();
    }

    public void agregarColmena(String id, String ubicacion, String razaAbejas) {
        Colmena nuevaColmena = new Colmena(id, ubicacion, razaAbejas);
        colmenas.add(nuevaColmena);
        System.out.println("Colmena con ID " + id + " agregada.");
    }

    public Colmena buscarColmena(String id) {
        for (Colmena colmena : colmenas) {
            if (colmena.getId().equals(id)) {
                return colmena;
            }
        }
        return null;
    }

    public void actualizarUbicacion(String id, String nuevaUbicacion) {
        Colmena colmena = buscarColmena(id);
        if (colmena != null) {
            colmena.setUbicacion(nuevaUbicacion);
            System.out.println("Ubicación de la colmena con ID " + id + " actualizada a " + nuevaUbicacion + ".");
        } else {
            System.out.println("No se encontró la colmena con ID " + id + ".");
        }
    }

    public void actualizarRaza(String id, String nuevaRaza) {
        Colmena colmena = buscarColmena(id);
        if (colmena != null) {
            colmena.setRazaAbejas(nuevaRaza);
            System.out.println("Raza de la colmena con ID " + id + " actualizada a " + nuevaRaza + ".");
        } else {
            System.out.println("No se encontró la colmena con ID " + id + ".");
        }
    }

    public void actualizarEstadoSalud(String id, String nuevoEstado) {
        Colmena colmena = buscarColmena(id);
        if (colmena != null) {
            colmena.setEstadoSalud(nuevoEstado);
            System.out.println("Estado de salud de la colmena con ID " + id + " actualizado a " + nuevoEstado + ".");
        } else {
            System.out.println("No se encontró la colmena con ID " + id + ".");
        }
    }

    public void actualizarNumeroMarcos(String id, int nuevoNumeroMarcos) {
        Colmena colmena = buscarColmena(id);
        if (colmena != null) {
            colmena.setNumeroMarcos(nuevoNumeroMarcos);
            System.out.println("Número de marcos de la colmena con ID " + id + " actualizado a " + nuevoNumeroMarcos + ".");
        } else {
            System.out.println("No se encontró la colmena con ID " + id + ".");
        }
    }

    public void registrarInspeccion(String id, String notas) {
        Colmena colmena = buscarColmena(id);
        if (colmena != null) {
            colmena.setUltimaInspeccion(new Date());
            colmena.setNotasInspeccion(notas);
            System.out.println("Inspección registrada para la colmena con ID " + id + " el " + colmena.getUltimaInspeccion() + ". Notas: " + notas);
            // Aquí podrías actualizar el estado de salud basado en las notas de la inspección si fuera necesario.
        } else {
            System.out.println("No se encontró la colmena con ID " + id + ".");
        }
    }

    public void mostrarTodasColmenas() {
        if (colmenas.isEmpty()) {
            System.out.println("No hay colmenas registradas.");
        } else {
            System.out.println("Listado de colmenas:");
            for (Colmena colmena : colmenas) {
                System.out.println(colmena);
            }
        }
    }

    // Otros métodos de gestión podrían ir aquí (eliminar colmena, etc.)
}

public class MainGestionColmenas {
    public static void main(String[] args) {
        GestionColmenas gestion = new GestionColmenas();

        // Agregar nuevas colmenas
        gestion.agregarColmena("COL001", "Apiario Central", "Apis mellifera");
        gestion.agregarColmena("COL002", "Apiario Norte", "Apis cerana");

        // Mostrar todas las colmenas
        gestion.mostrarTodasColmenas();

        // Actualizar información de una colmena
        gestion.actualizarUbicacion("COL001", "Nuevo Apiario");
        gestion.actualizarNumeroMarcos("COL002", 12);

        // Registrar una inspección
        gestion.registrarInspeccion("COL001", "Reina observada, buena postura. Se añadieron dos marcos.");
        gestion.registrarInspeccion("COL002", "Poca miel, se alimentó con jarabe.");

        // Mostrar nuevamente las colmenas con la información actualizada
        gestion.mostrarTodasColmenas();
    }
}
