package Clases.Principales;

public class AbejaReina {

    // Atributos privados: solo accesibles desde dentro de la clase
    private String estadoSalud;      
    private int edad;                
    private double productividad;    

    // Constructor de la clase: se ejecuta al crear una nueva AbejaReina
    public AbejaReina(String estadoSalud, int edad, double productividad) {
        this.estadoSalud = estadoSalud;         
        this.edad = edad;                       
        this.productividad = productividad;     
    }
    
    // Devuelve toda la información de la abeja reina
    public String obtenerEstado() {
        return String.format("""
            👑 Abeja Reina:
            🩺 Salud: %s
            🕰️ Edad: %d años
            🍯 Productividad: %.2f kg de miel
            """, estadoSalud, edad, productividad);
    }
}
}
