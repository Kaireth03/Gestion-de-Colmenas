package Clases.Principales;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class DatosApicola implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static DatosApicola instancia;

    public static DatosApicola getInstancia() {
        if (instancia == null) {
            instancia = new DatosApicola();
        }
        return instancia;
    }

    public final List<Colmena> colmenas;
    public final List<Apicultor> apicultores;
    public final Map<String, Apicultor> asignaciones;

    public DatosApicola() {
        this(null, null, null);
    }

    public DatosApicola(List<Colmena> colmenas, List<Apicultor> apicultores, Map<String, Apicultor> asignaciones) {
        this.colmenas = colmenas != null ? colmenas : new ArrayList<>();
        this.apicultores = apicultores != null ? apicultores : new ArrayList<>();
        this.asignaciones = asignaciones != null ? asignaciones : new HashMap<>();
    }

    public void agregarColmena(Colmena colmena) {
        if (colmena == null) throw new IllegalArgumentException("Colmena no puede ser nula.");
        colmenas.add(colmena);
    }

    public void guardarColmena(Colmena colmenaActualizada) {
        for (int i = 0; i < colmenas.size(); i++) {
            if (colmenas.get(i).getId().equals(colmenaActualizada.getId())) {
                colmenas.set(i, colmenaActualizada);
                return;
            }
        }
        colmenas.add(colmenaActualizada);
    }

    public void agregarApicultor(Apicultor apicultor) {
        if (apicultor == null) throw new IllegalArgumentException("Apicultor no puede ser nulo.");
        apicultores.add(apicultor);
    }

    public void asignarColmenaAPicultor(String idColmena, Apicultor apicultor) {
        if (idColmena == null || idColmena.trim().isEmpty() || apicultor == null) {
            throw new IllegalArgumentException("ID de colmena o apicultor no válidos.");
        }
        asignaciones.put(idColmena, apicultor);
    }

    public void desasignarColmenaDeApicultor(String idColmena) {
        if (idColmena == null || !asignaciones.containsKey(idColmena)) {
            throw new IllegalArgumentException("La colmena no está asignada o el ID es inválido.");
        }
        asignaciones.remove(idColmena);
    }

    public Apicultor obtenerApicultorPorColmena(String idColmena) {
        return asignaciones.get(idColmena);
    }

    public List<Colmena> obtenerColmenas() {
        return colmenas;
    }

    public List<Apicultor> obtenerApicultores() {
        return apicultores;
    }

    public Map<String, Apicultor> obtenerAsignaciones() {
        return asignaciones;
    }

    @Override
    public String toString() {
        return "Datos Apícolas:\n" +
                "Colmenas: " + colmenas.size() + "\n" +
                "Apicultores: " + apicultores.size() + "\n" +
                "Asignaciones: " + asignaciones.size();
    }
}
