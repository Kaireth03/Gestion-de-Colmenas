package Clases;
import Clases.Principales.*;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.google.gson.Gson;

public class LeerJson {

    public static void Guardar(ArrayList<Colmena> colmenas){
        String JsonDocumento = System.getProperty("user.home");
        String JsonRuta = Paths.get(JsonDocumento,"Documents", "colmenas.json").toString();

        try{
            //Con esto manejo el Guardado de colmenas en un Json el cual puedo modificar y cargar
                Gson Archivo = new Gson();
                String JSON = Archivo.toJson(colmenas);  //Convierto el arreglo a Json

            try (FileWriter guardo = new FileWriter(JsonRuta)) {
                guardo.write(JSON);
            }

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    public static void Cargar(ArrayList<Colmena> colmenas){


    }
}
