package Clases.Principales;

import java.io.Serializable;

public class Apicultor implements Serializable {
    public String nombre;
    public String telefono;

    public Apicultor(String nombre, String telefono) {
        setNombre(nombre);
        setTelefono(telefono);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("Nombre inválido.");
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono.matches("\\d{8,15}")) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("Teléfono inválido. Solo dígitos entre 8 y 15 caracteres.");
        }
    }

    @Override
    public String toString() {
        return "Apicultor: " + nombre + " | Teléfono: " + telefono;
    }
}
