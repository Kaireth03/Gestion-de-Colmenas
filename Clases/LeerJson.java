package Clases;
import Clases.Principales.*;
import java.io.*;
import java.nio.file.*;
import com.google.gson.Gson;


public class LeerJson {
   public static String JsonRuta = Paths.get(System.getProperty("user.home"), "Documents", "colmenas.json").toString();

public static void GuardarColmena(DatosApicola datosApicola){
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
        File archivo =  new File(JsonRuta);
        try {
            // Borro el archivo
            if (archivo.exists()) {
                if (!archivo.delete()) {
                    System.out.println("⚠️ No se pudo borrar el archivo anterior");
                    return;
                } else {
                    System.out.println("🗑️ Archivo anterior eliminado correctamente.");
                }
            }

            //Esta parte ya es la misma que guardar, podria llamar el metodo y listo
            // pero agregar codigo innecesario es el futuro
            Gson gson = new Gson();
            String json = gson.toJson(datosApicola);

            try (FileWriter writer = new FileWriter(archivo)) {
                writer.write(json);
            }

            System.out.println("✅ Datos actualizados correctamente en: " + JsonRuta);

        } catch (Exception e) {
            throw new RuntimeException("❌ Error al actualizar datos: " + e.getMessage(), e);
        }

    }
}




    
