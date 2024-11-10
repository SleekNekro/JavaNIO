import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import java.io.IOException;

public class FileChannelReadWriteExample {
    public static void main(String[] args) {
        Path pathEntrada = Path.of("fitxer_entrada.txt");
        Path pathSalida = Path.of("fitxer_salida.txt");

        try (FileChannel fileChannelEntrada = FileChannel.open(pathEntrada, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024); // Buffer de 1 KB
            int bytesLlegits = fileChannelEntrada.read(buffer);

            System.out.println("Contenido fitxero");
            while (bytesLlegits != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
                bytesLlegits = fileChannelEntrada.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String contenidodeSalida = "Necesitamos volver 1300 a.c donde todo funcionaba bine";
        ByteBuffer bufferSalida = ByteBuffer.wrap(contenidodeSalida.getBytes());

        try (FileChannel fileChannelSalida = FileChannel.open(pathSalida, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            while (bufferSalida.hasRemaining()) {
                fileChannelSalida.write(bufferSalida);
            }
            System.out.println("\nTexto escrito en el fitxero de salida.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
