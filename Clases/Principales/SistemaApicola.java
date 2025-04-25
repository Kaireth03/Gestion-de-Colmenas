package Clases.Principales;

import Clases.Principales.*;
import java.util.*;

public class SistemaApicola {
    public List<AbejaReyna> abejas_existentes;
    public Map< String, AbejaReyna > Colmenas_con_abeja_reyna;
    private static Inspeccion inspeccion = new Inspeccion();  
    private static DatosApicola datosApicola = new DatosApicola();
    
    public static void registrarApicultor() {
        System.out.println("REGISTRO DEL NUEVO APICULTOR");
    
        try {
            // SE piden los datos del nuevo apicultor
            String nombre = Utils.solicitarCampo("Ingrese el nombre del apicultor: ");
            String telefono = Utils.solicitarCampo("Ingrese el teléfono del apicultor: ");
            int edad = solicitarEdad();
            int experiencia = solicitarExperiencia(edad);
            String direccion = Utils.solicitarCampo("Ingrese la dirección del apicultor: ");
            String identificacion = Utils.solicitarCampo("Ingrese la identificación (DNI/ID/Cédula) del apicultor: ");
    
            // Crear objeto Apicultor
            Apicultor nuevoApicultor = new Apicultor(nombre, telefono, edad, experiencia, direccion, identificacion);
    
            // Agregar el apicultor a la lista correspondiente (en este caso, a datosApicola)
            datosApicola.agregarApicultor(nuevoApicultor);
    
            // Confirmar el registro
            System.out.println("✅ Apicultor registrado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al registrar el apicultor: " + e.getMessage());
        }
    }

    public static void asignarAbejaReina() {
        System.out.println("Crear Abeja reyna");
        System.out.println("Nombre de la Abeja reyna");
        String nombre_abeja_reyna = scanner.nextLine();
        System.out.println("Edad de la Abeja reyna");
        Int edad_abeja_reyna = scanner.nextInt();
        
        System.out.println("Salud de la Abeja reyna"); 
        String estado_abeja_reyna = scanner.nextLine();
        
        System.out.println("Productividad de la Abeja reyna"); 
        double productividad = scanner.nextDouble();
   
        // Se crea una abeja reyna nueva y se agrega a la lista de abejas reyna
        AbejaReyna abejareyna = new AbejaReyna(estado_abeja_reyna, edad_abeja_reyna, nombre_abeja_reyna, productividad);
        abejas_existentes.add(abejareyna);
        
        
        for(int i = 0 ; i < datosApicola.colmenas.size(); i++){
                    System.out.println((i+1) + ". " + datosApicola.colmenas.get(i));  
        }
        System.out.println("Que id de colmena se le va a asignar");
        String colmena = scanner.nextLine();
        
        if (Colmenas_con_abeja_reyna.stream().anyMatch(c -> c.getId().equals(id))) {
             // Acción si hay alguna colmena con ese ID
             System.out.println("La colmena con ese ID ya tiene una abeja reina.");
        }else{
            //Agrega a la lista de abejasreynas con colmenas
            Colmenas_con_abeja_reyna.add(colmena,abejareyna)
            System.out.println("Se asigno la Abeja reyna a la Colmena");
            
        }
        
        // TODO: Mostrar lista de colmenas sin reina
        // TODO: Permitir elegir colmena
        // TODO: Pedir datos de la abeja reina (nombre, edad, etc.)
        // TODO: Validar datos
        // TODO: Asignar la abeja reina a la colmena seleccionada
        // TODO: Confirmar asignación
    }

    public static void mostrarInformacion() {
        System.out.println("Que informacion desea ver:");
        System.out.println("1.Colmenas registradas");
        System.out.println("2.Apicultores");
        System.out.println("3.Abejas reynas");
        System.out.println("4.Historial de inspeccion");
        System.out.println("0.Volver al inicio");
        Int opcion = scanner.nextInt(); 
        switch(opcion){
            case 1{
                for(int i = 0 ; i < datosApicola.colmenas.size(); i++){
                    System.out.println((i+1) + ". " + datosApicola.colmenas.get(i));  
                }
                break
            }
            case 2{
                for(int i = 0 ; i < datosApicola.apicultores.size(); i++){
                    System.out.println((i+1) + ". " + datosApicola.apicultores.get(i));
                }
                break
            }
            case 3{
                for(int i = 0 ; i < abejas_existentes.size(); i++){
                    System.out.println((i+1) + ". " + abejas_existentes.get(i));
                }
                
                break
            }
            case 4{
                for(int i = 0 ; i < inspeccion.reportes.size(); i++){
                    System.out.println((i+1) + ". " + inspeccion.reportes.get(i));
                }
                break

            }
            case 0{
                System.out.println("SALIENDO");
                break
            }
        // TODO: Mostrar submenú con tipos de información a mostrar
        // TODO: Dependiendo de la opción, mostrar:
        //   - Colmenas registradas
        //   - Apicultores
        //   - Abejas reinas
        //   - Historial de inspecciones
        // TODO: Permitir volver al menú principal
        }
    }

    public static void asignarApicultorAColmena() {
        

        for(int i = 0 ; i < datosApicola.colmenas.size(); i++){
            System.out.println((i+1) + ". " + datosApicola.colmenas.get(i));  
        }
        System.out.println("Que id de colmena se le va a asignar");
        String colmena = scanner.nextLine(); 
        
        for(int i = 0 ; i < datosApicola.apicultores.size(); i++){
            System.out.println((i+1) + ". " + datosApicola.apicultores.get(i));
        }
        
        System.out.println("Que apicultor se quiere emparejar");
        int apicultor = scanner.nextLine(); 
        datosApicola.asignarColmenaAPicultor(colmena, datosApicola.apicultor.get(apicultor));
        System.out.println("Se a agregado el apicultor a la colmena");

        
        // TODO: Mostrar lista de colmenas
        // TODO: Mostrar lista de apicultores
        // TODO: Permitir seleccionar ambos
        // TODO: Validar que el apicultor no esté ya asignado
        // TODO: Asignar apicultor a la colmena
        // TODO: Confirmar asignación
    }

    public static void editarInformacion() {
        System.out.println("Que informacion desea editar:");
        System.out.println("1.Colmenas registradas");
        System.out.println("2.Apicultores");
        System.out.println("3.Abejas reynas");
        System.out.println("0.Volver al inicio");
        int opcion = scanner.nextInt(); 
        switch(opcion){
            case 1{
                for(int i = 0 ; i < datosApicola.colmenas.size(); i++){
                    System.out.println((i+1) + ". " + datosApicola.colmenas.get(i));  
                }
                System.out.println("Que colmena se va a editar");
                int colmena_editar = (scanner.nextInt() - 1) ; 
                
                System.out.println(datosApicola.colmenas.get(colmena_editar ));
                System.out.println("\n Se cambiaran los datos. Agregue cada uno de ellos");
                
                String id = Utils.solicitarCampo("Ingrese ID de la colmena: ");
                String ubicacion = Utils.solicitarCampo("Ingrese ubicación de la colmena: ");
                String estadoSalud = solicitarEstadoSalud();
                String tipo = Utils.solicitarCampo("Ingrese tipo de colmena (Ej: Langstroth, Warre, Top-Bar): ");
                byte cantidadAbejas = solicitarCantidadAbejas();
                float produccionMiel = solicitarProduccionMiel();
                
                Colmena editada = new Colmena(id, ubicacion, tipo, estadoSalud, cantidadAbejas, produccionMiel);
                
                datosApicola.colmenas.set(colmena_editar, editada );
                break
            }
            case 2{
                for(int i = 0 ; i < datosApicola.apicultores.size(); i++){
                    System.out.println((i+1) + ". " + datosApicola.apicultores.get(i));
                }
                System.out.println("Que apicultor se va a editar");
                int apicultor_editar = (scanner.nextInt() - 1) ; 
                System.out.println(datosApicola.apicultores.get(apicultor_editar));
                System.out.println("\n Se cambiaran los datos. Agregue cada uno de ellos");
                
                String nombre = Utils.solicitarCampo("Ingrese el nombre del apicultor: ");
                String telefono = Utils.solicitarCampo("Ingrese el teléfono del apicultor: ");
                int edad = solicitarEdad();
                int experiencia = solicitarExperiencia(edad);
                String direccion = Utils.solicitarCampo("Ingrese la dirección del apicultor: ");
                String identificacion = Utils.solicitarCampo("Ingrese la identificación (DNI/ID/Cédula) del apicultor: ");
        
               
                Apicultor editado = new Apicultor(nombre, telefono, edad, experiencia, direccion, identificacion);
                datosApicola.colmenas.set(apicultor_editar, editado );
                
                break
            }
            case 3{
                for(int i = 0 ; i < abejas_existentes.size(); i++){
                    System.out.println((i+1) + ". " + abejas_existentes.get(i));
                }
                System.out.println("Que abeja se va a editar");
                int abeja_editada = (scanner.nextInt() - 1) ; 
                System.out.println(abejas_existentes.get(abeja_editada));
                System.out.println("\n Se cambiaran los datos. Agregue cada uno de ellos");
                
                System.out.println("Nombre de la Abeja reyna");
                String nombre_abeja_reyna = scanner.nextLine();
                System.out.println("Edad de la Abeja reyna");
                Int edad_abeja_reyna = scanner.nextInt();
                
                System.out.println("Salud de la Abeja reyna"); 
                String estado_abeja_reyna = scanner.nextLine();
                
                System.out.println("Productividad de la Abeja reyna"); 
                double productividad = scanner.nextDouble();
           
                // Se crea una abeja reyna nueva y se agrega a la lista de abejas reyna
                AbejaReyna editada_abeja = new AbejaReyna(estado_abeja_reyna, edad_abeja_reyna, nombre_abeja_reyna, productividad);
                abejas_existentes.get(abeja_editada, editada_abeja);
                
                
                break
            }
            case 0{
                System.out.println("SALIENDO");
                break
            }
        }
        // TODO: Mostrar submenú: ¿qué desea editar? (colmena, apicultor, abeja reina, inspección)
        // TODO: Según elección, mostrar lista de elementos
        // TODO: Permitir seleccionar uno
        // TODO: Pedir nuevos datos
        // TODO: Validar y aplicar cambios
        // TODO: Confirmar edición
    }
}
