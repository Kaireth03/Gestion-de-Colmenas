package Clases.Principales;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Colmena {

    // Contador global para asignar un índice único a cada colmena creada
    private static int contadorGlobal = 0;

    // Atributos básicos de una colmena
    private final int indice;                       // Índice único basado en el contador global
    private final List<Inspeccion> inspecciones;     // Lista de inspecciones realizadas
    private final Date fechaCreacion;                // Fecha de creación de la colmena

    private String id;                               // Identificador único de la colmena
    private String ubicacion;                        // Ubicación geográfica de la colmena
    private String tipo;                             // Tipo de colmena
    private String estadoSalud;                      // Estado general de salud
    private byte cantidadAbejas;                     // Número de abejas
    private float produccionMiel;                    // Cantidad de miel producida
    private AbejaReina abejaReinaAsignada;            // Abeja reina asociada (puede ser nula)

    // Constructor principal
    public Colmena(String id, String ubicacion, String tipo, String estadoSalud, byte cantidadAbejas, float produccionMiel) {
        this.indice = ++contadorGlobal;              // Incrementa el contador global para asignar el índice
        this.inspecciones = new ArrayList<>();        // Inicializa la lista de inspecciones
        this.fechaCreacion = new Date();              // Asigna la fecha actual al momento de la creación
        this.id = id;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.estadoSalud = estadoSalud;
        this.cantidadAbejas = cantidadAbejas;
        this.produccionMiel = produccionMiel;
    }

    // ───────────────────────────────────────────
    // Getters y Setters
    // ───────────────────────────────────────────

    public int getIndice() {
        return indice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public byte getCantidadAbejas() {
        return cantidadAbejas;
    }

    public void setCantidadAbejas(byte cantidadAbejas) {
        this.cantidadAbejas = cantidadAbejas;
    }

    public float getProduccionMiel() {
        return produccionMiel;
    }

    public void setProduccionMiel(float produccionMiel) {
        this.produccionMiel = produccionMiel;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public AbejaReina getAbejaReinaAsignada() {
        return abejaReinaAsignada;
    }

    public void setAbejaReinaAsignada(AbejaReina abejaReina) {
        this.abejaReinaAsignada = abejaReina;
    }

    // ───────────────────────────────────────────
    // Métodos específicos
    // ───────────────────────────────────────────

    // Verifica si esta colmena tiene una abeja reina asignada
    public boolean tieneAbejaReina() {
        return abejaReinaAsignada != null;
    }

    // Quita la abeja reina de esta colmena
    public void quitarAbejaReina() {
        abejaReinaAsignada = null;
    }

    // Agrega una nueva inspección a la lista
    public void agregarInspeccion(Inspeccion inspeccion) {
        inspecciones.add(inspeccion);
    }

    // Retorna la lista de inspecciones
    public List<Inspeccion> getInspecciones() {
        return inspecciones;
    }

    // ───────────────────────────────────────────
    // Representación en texto
    // ───────────────────────────────────────────

    @Override
    public String toString() {
        // Se usa String.format y texto multilínea para mejor claridad visual al imprimir
        return String.format("""
        🔢 Índice: %d
        🆔 ID: %s
        📍 Ubicación: %s
        🐝 Tipo: %s
        ❤️ Salud: %s
        🐝 Cantidad de abejas: %d
        🍯 Producción de miel: %.2f kg
        """, indice, id, ubicacion, tipo, estadoSalud, cantidadAbejas, produccionMiel);
    }
}
