package Clases.Principales;

public class AbejaReina package Clases.Principales;

public class AbejaReina {
    private String estadoSalud;
    private int edad;
    private double productividad;

    public AbejaReina(String estadoSalud, int edad, double productividad) {
        this.estadoSalud = estadoSalud;
        this.edad = edad;
        this.productividad = productividad;
    }

    public String getInfo() {
        return String.format("""
            👑 Abeja Reina:
            🩺 Salud: %s
            🕰️ Edad: %d años
            🍯 Productividad: %.2f kg de miel
            """, estadoSalud, edad, productividad);
    }
}
