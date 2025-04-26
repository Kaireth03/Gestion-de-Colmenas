package Clases.Principales;

/**
 * Representa una abeja genérica en la colmena.
 * Esta clase puede ser extendida por tipos específicos de abejas (Reina, Obrera, Zángano).
 */
public class Abeja {
    private String id;
    private String tipo; // Podría ser "Reina", "Obrera", "Zángano"
    private String estadoSalud; // Ej: "Saludable", "Enferma"
    private int edadDias; // Edad en días

    // Constructor
    public Abeja(String id, String tipo, String estadoSalud, int edadDias) {
        this.id = id;
        this.tipo = tipo;
        this.estadoSalud = estadoSalud;
        this.edadDias = edadDias;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public int getEdadDias() {
        return edadDias;
    }

    // Setters (opcional, dependiendo de si los atributos pueden cambiar)
    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    // Método toString para representación
    @Override
    public String toString() {
        return "Abeja{" +
               "id='" + id + '\'' +
               ", tipo='" + tipo + '\'' +
               ", estadoSalud='" + estadoSalud + '\'' +
               ", edadDias=" + edadDias +
               '}';
    }

    // Otros métodos relevantes para una abeja podrían ir aquí
    // Por ejemplo: recolectarNectar(), polinizar(), etc. (quizás en subclases)
}