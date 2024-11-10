import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.List;

public class LecturaYEscrituraEjemplo {
    public static void main(String[] args) {
        Path pathEntrada = Path.of("fitxero.txt"); 
        Path pathSalida = Path.of("fit_salida.txt");

        try {
            List<String> lineas = Files.readAllLines(pathEntrada);
            System.out.println("Contenido del fitxer d'entrada:");
            for (String linea : lineas) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error en la lectura del fitxero de entrada.");
            e.printStackTrace();
        }

        String contenido = "Volvemos a 1300 a.c donde todo funcionaba bien";
        try {
            Files.writeString(pathSalida, contenido);
            System.out.println("El texto se ha escrito con exito.");
        } catch (IOException e) {
            System.out.println("Error en la escritura del fitxero de salida.");
            e.printStackTrace();
        }
    }
}
