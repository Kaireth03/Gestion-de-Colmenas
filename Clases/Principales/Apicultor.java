package Clases.Principales;

import java.io.Serializable;

public class Apicultor implements Serializable {
    private static final long serialVersionUID = 1L;

    public String nombre;
    public String telefono;
    public int edad;
    public int experiencia;
    public String direccion;
    public String identificacion;

    public Apicultor(String nombre, String telefono, int edad, int experiencia, String direccion, String identificacion) {
        setNombre(nombre);
        setTelefono(telefono);
        setEdad(edad);
        setExperiencia(experiencia);
        setDireccion(direccion);
        setIdentificacion(identificacion);
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("Nombre inválido.");
        }
    }

    public void setTelefono(String telefono) {
        if (telefono.matches("^\\+?\\d{8,15}$")) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("Teléfono inválido. Solo dígitos (opcionalmente con +) entre 8 y 15 caracteres.");
        }
    }

    public void setEdad(int edad) {
        if (edad >= 18 && edad <= 100) {
            this.edad = edad;
        } else {
            throw new IllegalArgumentException("Edad inválida. Debe ser entre 18 y 100 años.");
        }
    }

    public void setExperiencia(int experiencia) {
        if (experiencia >= 0 && experiencia <= edad) {
            this.experiencia = experiencia;
        } else {
            throw new IllegalArgumentException("Experiencia inválida. Debe ser entre 0 y la edad del apicultor.");
        }
    }

    public void setDireccion(String direccion) {
        if (direccion != null && !direccion.trim().isEmpty()) {
            this.direccion = direccion;
        } else {
            throw new IllegalArgumentException("Dirección inválida.");
        }
    }

    public void setIdentificacion(String identificacion) {
        if (identificacion != null && !identificacion.trim().isEmpty()) {
            this.identificacion = identificacion;
        } else {
            throw new IllegalArgumentException("Identificación inválida.");
        }
    }

    @Override
    public String toString() {
        return  " |Apicultor: " + nombre +
                " | Teléfono: " + telefono +
                " | Edad: " + edad +
                " | Experiencia: " + experiencia + " años" +
                " | Dirección: " + direccion +
                " | Identificación: " + identificacion;
    }
}
