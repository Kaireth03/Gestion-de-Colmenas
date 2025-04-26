package Clases.Principales;

import java.util.List;

public class AbejaReina extends Abeja {
    // Constantes de estado de salud podrían moverse a la clase Abeja si son generales,
    // o mantenerse aquí si son específicas de la Reina. Por ahora se quedan aquí.
    public static final List<String> ESTADOS_SALUD_VALIDOS = List.of(
            "En plenitud",
            "Zumbido estable",
            "Enferma"
    );

    // Atributos específicos de la Reina
    private final float productividad;

    // Constructor
    public AbejaReina(String id, String estadoSalud, byte edad, float productividad) {
        // Llamada al constructor de la clase padre (Abeja)
        super(id, "Reina", estadoSalud, (int) edad); // Se asume que 'edad' en AbejaReina son días y se convierte a int
        // Inicialización de atributos propios de AbejaReina
        this.productividad = productividad;
        // Los atributos id, tipo, estadoSalud, edadDias son manejados por la clase Abeja
    }

    // Getters específicos de AbejaReina
    public float getProductividad() {
        return productividad;
    }


    // toString modificado para incluir información de la clase base
    @Override
    public String toString() {
        return "🐝 Abeja Reina {" +
                "id='" + getId() + '\'' +
                ", tipo='" + getTipo() + '\'' +
                ", estadoSalud='" + getEstadoSalud() + '\'' +
                ", edadDias=" + getEdadDias() +
                ", productividad=" + productividad +
                '}';
    }
}
