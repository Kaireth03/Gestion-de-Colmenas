package Clases.Principales;

import java.util.ArrayList;

public class Colmena {
    // TODO: Declarar los atributos:
    // - String id
    // - String ubicacion
    // - String tipo
    // - String estadoDeSalud
    // - byte cantidadAbejas
    // - float produccionMiel

    // TODO: Crear un constructor que reciba todos los atributos como parámetros

    // TODO: Crear métodos getters para cada atributo

    // TODO: Crear métodos setters para estadoDeSalud, cantidadAbejas y produccionMiel

    // TODO: Sobrescribir el método toString para mostrar toda la información de la colmena
}


public class GestorApicultores {
    private ArrayList<Apicultor> apicultores;

    public GestorApicultores() {
        apicultores = new ArrayList<>();
    }

    public void agregarApicultor(Apicultor apicultor) {
        apicultores.add(apicultor);
    }

    public void mostrarApicultores() {
        for (Apicultor a : apicultores) {
            System.out.println(a);
        }
    }
}


