public class EscrituraAsincronaEjemplo{
    public static void main(String[] args) {
        Path pathSalida = Path.of("archivo_salida.txt");

        String contenido = "DEBEMOS VOLVER A 1503 A.C DONDE TODO FUNCIONABA BIEN.";
        ByteBuffer buffer = ByteBuffer.wrap(contenido.getBytes());

        try (AsynchronousFileChannel asyncChannel = AsynchronousFileChannel.open(pathSalida, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            Future<Integer> resultado = asyncChannel.write(buffer, 0);

            while (!resultado.isDone()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Bytes: " + resultado.get());
        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
