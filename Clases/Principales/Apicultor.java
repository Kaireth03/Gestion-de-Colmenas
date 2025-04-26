package Clases.Principales;

import java.io.Serial;
import java.io.Serializable;

public class Apicultor implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String telefono;
    private byte edad;
    private byte experiencia;
    private String direccion;
    private String identificacion;

    public Apicultor(String nombre, String telefono, int edad, int experiencia, String direccion, String identificacion) {
        setNombre(nombre);
        setTelefono(telefono);
        setEdad((byte) edad);
        setExperiencia((byte) experiencia);
        setDireccion(direccion);
        setIdentificacion(identificacion);
    }

    public void setNombre(String nombre) {
        if (isValidString(nombre)) this.nombre = nombre;
        else throw new IllegalArgumentException("Nombre inválido.");
    }

    public void setTelefono(String telefono) {
        if (telefono.matches("^\\+?\\d{8,15}$")) this.telefono = telefono;
        else throw new IllegalArgumentException("Teléfono inválido.");
    }

    public void setEdad(byte edad) {
        if (edad >= 18 && edad <= 100) this.edad = edad;
        else throw new IllegalArgumentException("Edad inválida.");
    }

    public void setExperiencia(byte experiencia) {
        if (experiencia >= 0 && experiencia <= edad) this.experiencia = experiencia;
        else throw new IllegalArgumentException("Experiencia inválida.");
    }

    public void setDireccion(String direccion) {
        if (isValidString(direccion)) this.direccion = direccion;
        else throw new IllegalArgumentException("Dirección inválida.");
    }

    public void setIdentificacion(String identificacion) {
        if (isValidString(identificacion)) this.identificacion = identificacion;
        else throw new IllegalArgumentException("Identificación inválida.");
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }


    @Override
    public String toString() {
        return " |Apicultor: " + nombre +
                " | Teléfono: " + telefono +
                " | Edad: " + edad +
                " | Experiencia: " + experiencia + " años" +
                " | Dirección: " + direccion +
                " | Identificación: " + identificacion;
    }

    private boolean isValidString(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
