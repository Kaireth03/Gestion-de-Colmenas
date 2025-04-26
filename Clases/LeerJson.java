package Clases;
import Clases.Principales.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;

import com.google.gson.Gson;

public class LeerJson {

public static void GuardarColmena(DatosApicola datosApicola){
        String JsonDocumento = System.getProperty("user.home");
        String JsonRuta = Paths.get(JsonDocumento,"Documents", "colmenas.json").toString();

        try{
            //Con esto manejo el Guardado de colmenas en un Json el cual puedo modificar y cargar
                Gson Archivo = new Gson();
                String JSON = Archivo.toJson(datosApicola);  //Convierto el arreglo a Json

            try (FileWriter guardo = new FileWriter(JsonRuta)) {
                guardo.write(JSON);
            }

            System.out.println("✅ Datos guardados correctamente en: " + JsonRuta);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }
   
    public static DatosApicola CargarColmena() {
    String JsonRuta = Paths.get(System.getProperty("user.home"), "Documents", "colmenas.json").toString();
        try (FileReader lector = new FileReader(JsonRuta)) {
            Gson Archivo = new Gson();
            DatosApicola datosApicola = Archivo.fromJson(lector, DatosApicola.class);
            System.out.println("✅ Datos cargados correctamente desde archivo.");
            return datosApicola;
        } catch (Exception e) {
            throw new RuntimeException("❌ Error al cargar los datos desde el archivo JSON", e);
        }
    }

    public static void Actualizar(DatosApicola datosApicola){


    }
}




    
