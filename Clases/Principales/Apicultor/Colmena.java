package Clases.Principales;

import java.util.ArrayList;

class Colmena {
    private String id;
    private String ubicacion;
    private Date fechaCreacion;
    private String razaAbejas;
    private String estadoSalud;
    private int numeroMarcos;
    private Date ultimaInspeccion;
    private String notasInspeccion;

    public Colmena(String id, String ubicacion, String razaAbejas) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.fechaCreacion = new Date();
        this.razaAbejas = razaAbejas;
        this.estadoSalud = "Saludable"; // Estado inicial
        this.numeroMarcos = 10; // Número de marcos inicial
        this.ultimaInspeccion = null;
        this.notasInspeccion = "";
    }

    // Métodos getter y setter para todos los atributos
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public String getRazaAbejas() {
        return razaAbejas;
    }

    public void setRazaAbejas(String razaAbejas) {
        this.razaAbejas = razaAbejas;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public int getNumeroMarcos() {
        return numeroMarcos;
    }

    public void setNumeroMarcos(int numeroMarcos) {
        this.numeroMarcos = numeroMarcos;
    }

    public Date getUltimaInspeccion() {
        return ultimaInspeccion;
    }

    public void setUltimaInspeccion(Date ultimaInspeccion) {
        this.ultimaInspeccion = ultimaInspeccion;
    }

    public String getNotasInspeccion() {
        return notasInspeccion;
    }

    public void setNotasInspeccion(String notasInspeccion) {
        this.notasInspeccion = notasInspeccion;
    }

    @Override
    public String toString() {
        return "Colmena{" +
               "id='" + id + '\'' +
               ", ubicacion='" + ubicacion + '\'' +
               ", fechaCreacion=" + fechaCreacion +
               ", razaAbejas='" + razaAbejas + '\'' +
               ", estadoSalud='" + estadoSalud + '\'' +
               ", numeroMarcos=" + numeroMarcos +
               ", ultimaInspeccion=" + ultimaInspeccion +
               ", notasInspeccion='" + notasInspeccion + '\'' +
               '}';
    }
}


public class GestorApicultores {
    private ArrayList<Apicultor> apicultores;

    public GestorApicultores() {
        apicultores = new ArrayList<>();
    }

    public void agregarApicultor(Apicultor apicultor) {
        apicultores.add(apicultor);
    }

    public void mostrarApicultores() {
        for (Apicultor a : apicultores) {
            System.out.println(a);
        }
    }
}


