package Clases.Principales;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Colmena {
    private static int contadorGlobal = 0;

    private final int indice;
    private final List<Inspeccion> inspecciones = new ArrayList<>();
    private final Date fechaCreacion;

    private String id;
    private String ubicacion;
    private String tipo;
    private String estadoSalud;
    private byte cantidadAbejas;
    private float produccionMiel;
    private AbejaReina abejaReinaAsignada;

    public Colmena(String id, String ubicacion, String tipo, String estadoSalud, byte cantidadAbejas, float produccionMiel) {
        this.indice = ++contadorGlobal;
        this.id = id;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.estadoSalud = estadoSalud;
        this.cantidadAbejas = cantidadAbejas;
        this.produccionMiel = produccionMiel;
        this.fechaCreacion = new Date();
    }

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

    public boolean tieneAbejaReina() {
        return abejaReinaAsignada != null;
    }

    public void quitarAbejaReina() {
        abejaReinaAsignada = null;
    }

    public void agregarInspeccion(Inspeccion inspeccion) {
        inspecciones.add(inspeccion);
    }

    public List<Inspeccion> getInspecciones() {
        return inspecciones;
    }

    @Override
    public String toString() {
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
