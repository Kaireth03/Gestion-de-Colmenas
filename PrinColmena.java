import java.util.*;
import java.io.*;

//Importamos todas las clases principales
import Clases.Principales.*;
import Clases.LeerJson;


public class PrinColmena {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Colmena> Colmenas = new ArrayList<>();

    public static void main(String[] args) {
        try{





            LeerJson.Guardar(Colmenas);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
