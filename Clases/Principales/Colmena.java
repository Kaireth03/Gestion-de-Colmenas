package Clases.Principales;

import java.util.Date;

public class Colmena {
    private String id;
    private String ubicacion;
    private String tipo;
    private byte cantidadAbejas;
    private final Date fechaCreacion;
    private String estadoSalud;
    private float produccionMiel;
    private Date ultimaInspeccion;
    private String notasInspeccion;

    public Colmena(String id, String ubicacion, String tipo, String estadoSalud, byte cantidadAbejas, float produccionMiel) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.estadoSalud = estadoSalud;
        this.cantidadAbejas = cantidadAbejas;
        this.produccionMiel = produccionMiel;
        this.fechaCreacion = new Date();
        this.ultimaInspeccion = null;
        this.notasInspeccion = "";
    }

    // Getters y Setters
    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public byte getCantidadAbejas() { return cantidadAbejas; }
    public void setCantidadAbejas(byte cantidadAbejas) { this.cantidadAbejas = cantidadAbejas; }

    public float getProduccionMiel() { return produccionMiel; }
    public void setProduccionMiel(float produccionMiel) { this.produccionMiel = produccionMiel; }

    public Date getFechaCreacion() { return fechaCreacion; }

    public String getEstadoSalud() { return estadoSalud; }
    public void setEstadoSalud(String estadoSalud) { this.estadoSalud = estadoSalud; }

    public Date getUltimaInspeccion() { return ultimaInspeccion; }
    public void setUltimaInspeccion(Date ultimaInspeccion) { this.ultimaInspeccion = ultimaInspeccion; }

    public String getNotasInspeccion() { return notasInspeccion; }
    public void setNotasInspeccion(String notasInspeccion) { this.notasInspeccion = notasInspeccion; }

    @Override
    public String toString() {
        return "Colmena{" +
                "id='" + id + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cantidadAbejas=" + cantidadAbejas +
                ", fechaCreacion=" + fechaCreacion +
                ", estadoSalud='" + estadoSalud + '\'' +
                ", produccionMiel=" + produccionMiel +
                ", ultimaInspeccion=" + ultimaInspeccion +
                ", notasInspeccion='" + notasInspeccion + '\'' +
                '}';
    }
}
