package Clases;
import Clases.Principales.*;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
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
   
    public static void Cargar(ArrayList<Colmena> colmenas){


    }
}
