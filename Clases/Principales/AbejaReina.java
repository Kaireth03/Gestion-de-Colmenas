package Clases.Principales;

import java.util.List;

public class AbejaReina extends Abeja {

    // ───────────────────────────────────────────
    // CONSTANTES
    // ───────────────────────────────────────────

    // Lista de estados de salud válidos para una Abeja Reina
    // Se mantiene aquí en lugar de Abeja general porque estos estados aplican específicamente a la Reina
    public static final List<String> ESTADOS_SALUD_VALIDOS = List.of(
            "En plenitud",
            "Zumbido estable",
            "Enferma"
    );

    // ───────────────────────────────────────────
    // ATRIBUTOS
    // ───────────────────────────────────────────

    // Productividad de la reina medida como un número decimal (porcentaje o índice relativo)
    private final float productividad;

    // ───────────────────────────────────────────
    // CONSTRUCTOR
    // ───────────────────────────────────────────

    public AbejaReina(String id, String estadoSalud, byte edad, float productividad) {
        // Se llama primero al constructor de la clase base (Abeja) para inicializar los atributos heredados
        super(id, "Reina", estadoSalud, (int) edad); // 'edad' se recibe como byte para optimizar memoria, pero Abeja espera int
        this.productividad = productividad; // Se inicializa el atributo específico de la Reina
    }

    // ───────────────────────────────────────────
    // MÉTODOS GETTER
    // ───────────────────────────────────────────

    // Permite acceder a la productividad de la Reina de manera segura
    public float getProductividad() {
        return productividad;
    }

    // ───────────────────────────────────────────
    // TO STRING
    // ───────────────────────────────────────────

    // Se sobrescribe toString() para mostrar toda la información relevante
    // Incluye los atributos de la clase base + la productividad específica de la Reina
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
