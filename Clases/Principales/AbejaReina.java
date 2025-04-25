package Clases.Principales;

import java.util.List;

public class AbejaReina {
    public static final List<String> ESTADOS_SALUD_VALIDOS = List.of(
            "En plenitud",
            "Zumbido estable",
            "Enferma"
    );

    private final String estadoSalud;
    private final byte edad;
    private final float productividad;

    public AbejaReina(String estadoSalud, byte edad, float productividad) {
        this.productividad = productividad;
        this.edad = edad;
        this.estadoSalud = estadoSalud;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    @Override
    public String toString() {
        return "🐝 Abeja Reina {" +
                "Productividad = '" + productividad + '\'' +
                ", Edad = " + edad +
                ", Estado de Salud = '" + estadoSalud + '\'' +
                '}';
    }
}
