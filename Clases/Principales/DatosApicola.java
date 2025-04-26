package Clases.Principales;

// ─────────────────────────────────────────────────────────────
// Imports
// ─────────────────────────────────────────────────────────────
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ─────────────────────────────────────────────────────────────
// Clase principal
// ─────────────────────────────────────────────────────────────
public class DatosApicola implements Serializable {

    // ─────────────────────────────────────────────────────────────
    // Constantes
    // ─────────────────────────────────────────────────────────────

    @Serial
    private static final long serialVersionUID = 1L;   // Identificador único de serialización

    // ─────────────────────────────────────────────────────────────
    // Variables de instancia
    // ─────────────────────────────────────────────────────────────

    private static DatosApicola instancia;             // Instancia única (Singleton)

    public final List<Colmena> colmenas;                // Lista de colmenas
    public final List<Apicultor> apicultores;           // Lista de apicultores
    public final Map<String, Apicultor> asignaciones;   // Asignaciones de colmenas a apicultores

    // ─────────────────────────────────────────────────────────────
    // Constructores
    // ─────────────────────────────────────────────────────────────

    // Constructor privado del Singleton
    public DatosApicola() {
        this(null, null, null);
    }

    // Constructor general, permite inicializar con datos existentes
    public DatosApicola(List<Colmena> colmenas, List<Apicultor> apicultores, Map<String, Apicultor> asignaciones) {
        this.colmenas = (colmenas != null) ? colmenas : new ArrayList<>();
        this.apicultores = (apicultores != null) ? apicultores : new ArrayList<>();
        this.asignaciones = (asignaciones != null) ? asignaciones : new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // Métodos de Singleton
    // ─────────────────────────────────────────────────────────────

    /**
     * Obtiene la instancia única de DatosApicola (patrón Singleton).
     * Si no existe, la crea.
     */
    public static DatosApicola getInstancia() {
        if (instancia == null) {
            instancia = new DatosApicola();
        }
        return instancia;
    }

    // ─────────────────────────────────────────────────────────────
    // Métodos para manipular Colmenas
    // ─────────────────────────────────────────────────────────────

    public void agregarColmena(Colmena colmena) {
        if (colmena == null) {
            throw new IllegalArgumentException("Colmena no puede ser nula.");
        }
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

    // ─────────────────────────────────────────────────────────────
    // Métodos para manipular Apicultores
    // ─────────────────────────────────────────────────────────────

    public void agregarApicultor(Apicultor apicultor) {
        if (apicultor == null) {
            throw new IllegalArgumentException("Apicultor no puede ser nulo.");
        }
        apicultores.add(apicultor);
    }

    // ─────────────────────────────────────────────────────────────
    // Métodos para asignar/desasignar Colmenas a Apicultores
    // ─────────────────────────────────────────────────────────────

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

    // ─────────────────────────────────────────────────────────────
    // Métodos para obtener datos
    // ─────────────────────────────────────────────────────────────

    public List<Colmena> obtenerColmenas() {
        return colmenas;
    }

    public List<Apicultor> obtenerApicultores() {
        return apicultores;
    }

    public Map<String, Apicultor> obtenerAsignaciones() {
        return asignaciones;
    }

    // ─────────────────────────────────────────────────────────────
    // Representación en texto
    // ─────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "Datos Apícolas:\n" +
                "Colmenas: " + colmenas.size() + "\n" +
                "Apicultores: " + apicultores.size() + "\n" +
                "Asignaciones: " + asignaciones.size();
    }
}
