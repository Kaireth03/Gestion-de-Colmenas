package Colmena;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class DatosApicola implements Serializable {
    // Lista de colmenas registradas en el sistema.
    public List<Colmena> colmenas;

    // Lista de apicultores disponibles en el sistema.
    public List<Apicultor> apicultores;

    // Mapa que asocia el ID de cada colmena con el apicultor asignado.
    public Map<String, Apicultor> asignaciones;

    // Constructor por defecto inicializando las colecciones
    public DatosApicola() {
        this.colmenas = new ArrayList<>();
        this.apicultores = new ArrayList<>();
        this.asignaciones = new HashMap<>();
    }

    // Constructor que recibe listas y asignaciones
    public DatosApicola(List<Colmena> colmenas, List<Apicultor> apicultores, Map<String, Apicultor> asignaciones) {
        this.colmenas = colmenas != null ? colmenas : new ArrayList<>();
        this.apicultores = apicultores != null ? apicultores : new ArrayList<>();
        this.asignaciones = asignaciones != null ? asignaciones : new HashMap<>();
    }

    // Métodos para agregar elementos
    public void agregarColmena(Colmena colmena) {
        if (colmena != null) {
            this.colmenas.add(colmena);
        } else {
            throw new IllegalArgumentException("Colmena no puede ser nula.");
        }
    }

    public void agregarApicultor(Apicultor apicultor) {
        if (apicultor != null) {
            this.apicultores.add(apicultor);
        } else {
            throw new IllegalArgumentException("Apicultor no puede ser nulo.");
        }
    }

    public void asignarColmenaAPicultor(String idColmena, Apicultor apicultor) {
        if (idColmena != null && !idColmena.trim().isEmpty() && apicultor != null) {
            asignaciones.put(idColmena, apicultor);
        } else {
            throw new IllegalArgumentException("ID de colmena o apicultor no válidos.");
        }
    }

    // Métodos para buscar elementos
    public Apicultor obtenerApicultorPorColmena(String idColmena) {
        return asignaciones.get(idColmena);
    }

    public List<Colmena> obtenerColmenas() {
        return colmenas;
    }

    public List<Apicultor> obtenerApicultores() {
        return apicultores;
    }

    @Override
    public String toString() {
        return "Datos Apícolas: \n" +
               "Número de colmenas: " + colmenas.size() + "\n" +
               "Número de apicultores: " + apicultores.size() + "\n" +
               "Número de asignaciones: " + asignaciones.size();
    }
}
