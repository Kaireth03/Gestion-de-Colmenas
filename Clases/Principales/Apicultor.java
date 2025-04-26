package Clases.Principales;

import java.io.Serial;
import java.io.Serializable;

public class Apicultor implements Serializable {

    // ───────────────────────────────────────────
    // SERIALIZACIÓN
    // ───────────────────────────────────────────

    // Número de versión para garantizar la compatibilidad al serializar
    @Serial
    private static final long serialVersionUID = 1L;

    // ───────────────────────────────────────────
    // ATRIBUTOS
    // ───────────────────────────────────────────

    // Datos básicos del apicultor
    private String nombre;
    private String telefono;
    private byte edad;          // Guardado como byte para optimizar memoria
    private byte experiencia;   // Experiencia en años, también optimizado como byte
    private String direccion;
    private String identificacion;

    // ───────────────────────────────────────────
    // CONSTRUCTOR
    // ───────────────────────────────────────────

    public Apicultor(String nombre, String telefono, int edad, int experiencia, String direccion, String identificacion) {
        // Se usan setters para aplicar validaciones automáticas durante la construcción
        setNombre(nombre);
        setTelefono(telefono);
        setEdad((byte) edad);
        setExperiencia((byte) experiencia);
        setDireccion(direccion);
        setIdentificacion(identificacion);
    }

    // ───────────────────────────────────────────
    // MÉTODOS SETTERS
    // ───────────────────────────────────────────

    // Asigna el nombre validando que no sea nulo o vacío
    public void setNombre(String nombre) {
        if (isValidString(nombre)) this.nombre = nombre;
        else throw new IllegalArgumentException("Nombre inválido.");
    }

    // Valida que el teléfono cumpla el formato internacional o nacional
    public void setTelefono(String telefono) {
        if (telefono.matches("^\\+?\\d{8,15}$")) this.telefono = telefono;
        else throw new IllegalArgumentException("Teléfono inválido.");
    }

    // La edad debe estar en un rango lógico para un apicultor
    public void setEdad(byte edad) {
        if (edad >= 18 && edad <= 100) this.edad = edad;
        else throw new IllegalArgumentException("Edad inválida.");
    }

    // La experiencia no puede ser negativa ni mayor que la edad del apicultor
    public void setExperiencia(byte experiencia) {
        if (experiencia >= 0 && experiencia <= edad) this.experiencia = experiencia;
        else throw new IllegalArgumentException("Experiencia inválida.");
    }

    // Dirección debe ser un texto válido
    public void setDireccion(String direccion) {
        if (isValidString(direccion)) this.direccion = direccion;
        else throw new IllegalArgumentException("Dirección inválida.");
    }

    // Identificación debe ser un texto no vacío
    public void setIdentificacion(String identificacion) {
        if (isValidString(identificacion)) this.identificacion = identificacion;
        else throw new IllegalArgumentException("Identificación inválida.");
    }

    // ───────────────────────────────────────────
    // MÉTODOS GETTERS
    // ───────────────────────────────────────────

    // Devuelve la identificación del apicultor
    public String getIdentificacion() {
        return identificacion;
    }

    // Devuelve el nombre del apicultor
    public String getNombre() {
        return nombre;
    }

    // ───────────────────────────────────────────
    // TO STRING
    // ───────────────────────────────────────────

    // Representa el objeto de forma textual, incluyendo todos los datos relevantes
    @Override
    public String toString() {
        return "🐝 Apicultor {\n" +
                "  Nombre: '" + nombre + "',\n" +
                "  Teléfono: '" + telefono + "',\n" +
                "  Edad: " + edad + " años,\n" +
                "  Experiencia: " + experiencia + " años,\n" +
                "  Dirección: '" + direccion + "',\n" +
                "  Identificación: '" + identificacion + "'\n" +
                '}';
    }

    // ───────────────────────────────────────────
    // MÉTODOS DE APOYO
    // ───────────────────────────────────────────

    // Verifica que una cadena no sea nula ni esté vacía después de limpiar espacios
    private boolean isValidString(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
