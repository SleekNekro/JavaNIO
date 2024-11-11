# Trabajo de Investigación: Java NIO

## Apartado Teórico

### 1. Historia y evolución

#### 1.1. En Java 1.4

Java NIO (New Input/Output) se introdujo en Java 1.4 (lanzado en 2002) con el objetivo de proporcionar una API más eficiente y flexible para gestionar la entrada y salida de datos. Antes de NIO, el modelo tradicional de I/O en Java se basaba en flujos (streams), que eran simultáneos y bloqueaban el hilo de ejecución hasta que la operación de I/O terminaba.

Java NIO introdujo nuevos conceptos como:

- **Buffers**: Contenedores para almacenar datos a leer o escribir, permitiendo un acceso aleatorio a los datos en lugar de secuencial.
- **Channels**: Interfaces para interactuar de manera más eficiente con fuentes de datos, permitiendo operaciones no bloqueantes.
- **Selectors**: Permiten que un solo hilo controle múltiples canales, mejorando el rendimiento en aplicaciones de red que gestionan múltiples conexiones simultáneas.

#### 1.2. Mejoras con Java NIO2 a partir de Java 7

En Java 7 (lanzado en 2011), se introdujo Java NIO.2 (también conocido como `java.nio.file`), que amplió significativamente la API NIO, mejorando la gestión de archivos y directorios. Algunas mejoras clave fueron:

- **API de archivos más robusta**: Sustituyó en gran parte a `java.io.File`, ofreciendo una forma más flexible de trabajar con archivos y directorios.
- **Path y Files**: Introducción de la clase `Path` para representar rutas de archivos y directorios de manera más potente, y `Files` para realizar operaciones de I/O de manera más sencilla.
- **Operaciones no bloqueantes**: NIO2 facilitó la implementación de operaciones de I/O de archivos y red de manera no bloqueante, mejorando la eficiencia en escenarios de alta concurrencia.
- **Soporte para sistemas de archivos distribuidos**: NIO2 permitió acceder a recursos en sistemas de archivos remotos o en red.
- **WatchService**: Introducción de una API para monitorear directorios en tiempo real, detectando cambios como la adición, modificación o eliminación de archivos.

#### 1.3. Necesidades cubiertas en el desarrollo de software

Antes de Java NIO, el modelo de flujos tenía limitaciones en cuanto a rendimiento y flexibilidad, especialmente en aplicaciones de alta concurrencia o con operaciones de I/O complejas. Java NIO y NIO2 cubrieron varias necesidades clave en el desarrollo de software:

- **Eficiencia en I/O**: NIO mejora la gestión de datos, especialmente en aplicaciones que manejan archivos grandes o múltiples conexiones de red simultáneas sin bloquear los hilos.
- **Operaciones no bloqueantes**: Esencial para aplicaciones de alta concurrencia como servidores o bases de datos, permitiendo manejar muchas conexiones sin bloquear los hilos.
- **Simplificación de gestión de archivos**: Con NIO2, trabajar con archivos y directorios se vuelve más sencillo mediante clases como `Path`, `Files` y `WatchService`.
- **Mejora en la gestión de errores**: NIO ofrece una mejor gestión de excepciones durante las operaciones de entrada/salida.
- **Soporte para alta concurrencia**: Permite crear aplicaciones más eficientes mediante multiplexación de I/O, gestionando múltiples conexiones concurrentemente.
- **Soporte para redes y sistemas distribuidos**: NIO2 facilita la interacción con archivos locales y remotos, mejorando la creación de sistemas de archivos distribuidos.

---

### 2. Principales Conceptos de Java NIO

#### 2.1. **Canales (Channels)**

Los **canales** son un elemento crucial de Java NIO y actúan como vías de comunicación para la lectura y escritura de datos de manera más eficaz que las operaciones convencionales de entrada y salida (Java IO). A diferencia de las clases de flujo, como `InputStream` y `OutputStream`, los canales permiten operaciones bidireccionales y tienen la capacidad de gestionar tanto archivos como sockets o cualquier otra fuente de datos. Esto los hace muy útiles para realizar operaciones de entrada/salida de forma más rápida y, sobre todo, más escalable.

Algunos de los canales más utilizados son:

- **FileChannel**
- **SocketChannel**
- **ServerSocketChannel**

---

#### 2.2. **Buffers: ¿Qué son y cómo funcionan?**

Los **buffers** son esencialmente espacios de memoria temporal donde se almacenan los datos durante el proceso de lectura o escritura. Un buen ejemplo de esto es la reproducción de videos en páginas web, que para asegurar que el video se vea fluido a pesar de las posibles pérdidas de internet, siempre carga unos segundos adicionales de contenido.

En Java NIO, el buffer más común es el **`ByteBuffer`**, aunque también existen otros tipos, como **`CharBuffer`** o **`IntBuffer`**, para manejar diferentes tipos de datos.

##### Funcionamiento:
- Cuando se leen datos desde un canal, estos se almacenan primero en el buffer.
- Posteriormente, podemos manipular los datos o escribirlos en otro canal, optimizando la gestión de la memoria.

---

#### 2.3. **Operaciones No Bloqueantes**

Una de las características más destacadas de Java NIO es que permite realizar **operaciones no bloqueantes**. Esto significa que podemos seguir realizando otras tareas mientras una operación de lectura o escritura está en proceso, sin que el sistema se quede detenido esperando a que se complete la operación.

### Ventajas:
- Mayor eficiencia en el uso de recursos.
- Mejora el rendimiento en aplicaciones concurrentes o con múltiples conexiones.

---

#### 2.4. **Selectores: Definición y Usos**

Los **selectores** son herramientas que permiten gestionar múltiples canales simultáneamente para saber cuándo están listos para realizar operaciones de entrada/salida, como leer o escribir.

##### Usos:
- Los selectores son esenciales en servidores o aplicaciones que gestionan muchas conexiones al mismo tiempo, como los servidores web o bases de datos.
- Gracias a los selectores, podemos gestionar múltiples conexiones de forma eficiente y realizar operaciones **asíncronas** sin interrumpir el proceso principal de la aplicación.

---

#### 2.5 Comparativa entre Java NIO y Java IO

##### 2.5.1. **Lectura/Escritura de Ficheros**

- **Java IO**: Utiliza flujos como `FileInputStream` y `FileOutputStream`, que son operaciones bloqueantes. El proceso de lectura o escritura se detiene hasta que se finaliza la operación de I/O.
  
- **Java NIO**: Usa **canales** (`FileChannel`) y **buffers**, lo que permite realizar operaciones de lectura/escritura de manera **no bloqueante**, siendo mucho más eficiente en situaciones con grandes archivos o cuando se deben manejar múltiples operaciones simultáneas.

##### 2.5.2 **Gestión de Rutas y Ficheros**

- **Java IO**: Utiliza la clase **`File`**, que es más limitada y menos flexible en la gestión de rutas y ficheros. No está tan bien adaptada para tareas modernas como la creación, lectura, copia o traslado de ficheros en sistemas operativos actuales.
  
- **Java NIO**: Introduce la clase **`Path`**, que ofrece una forma mucho más moderna y flexible para manejar rutas y ficheros. Es compatible con diversas plataformas y facilita tareas de gestión de ficheros.


##### 2.5.3. **Operaciones de Red**

- **Java IO**: Utiliza clases tradicionales como **`Socket`** y **`ServerSocket`** que son bloqueantes, lo que puede generar ineficiencia cuando se manejan muchas conexiones simultáneas.

- **Java NIO**: Proporciona **`SocketChannel`** y **`ServerSocketChannel`**, que permiten gestionar conexiones de red de manera **asíncrona**, aumentando la rapidez y escalabilidad, lo cual es ideal para servidores que manejan muchas conexiones de forma concurrente.

## 3. **Clases clave de Java NIO**

### Path: ¿Qué es, métodos principales y ejemplos?

La clase `Path` en Java NIO representa una ruta de archivo o directorio. Es una mejora significativa sobre la clase `File` de Java IO, proporcionando una forma más flexible y potente de trabajar con rutas de archivos.

#### Métodos principales:
- `getFileName()`: Obtiene el nombre del archivo o directorio.
- `getParent()`: Obtiene la ruta del directorio padre.
- `toAbsolutePath()`: Convierte la ruta en una ruta absoluta.
- `resolve()`: Combina dos rutas.

#### Ejemplo:
```java
Path path = Paths.get("/ruta/al/archivo.txt");
System.out.println("Nombre del archivo: " + path.getFileName());
System.out.println("Ruta absoluta: " + path.toAbsolutePath());
```

### Files: Operaciones con ficheros y directorios

La clase `Files` proporciona métodos estáticos para realizar operaciones de I/O con archivos y directorios de manera sencilla y eficiente.

#### Operaciones comunes:
- `createFile()`: Crea un nuevo archivo.
- `delete()`: Elimina un archivo o directorio.
- `copy()`: Copia un archivo o directorio.
- `move()`: Mueve o renombra un archivo o directorio.

#### Ejemplo:
```java
Path source = Paths.get("/ruta/origen.txt");
Path target = Paths.get("/ruta/destino.txt");
Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
```

### FileChannel: Gestión de ficheros a bajo nivel

`FileChannel` permite realizar operaciones de I/O a bajo nivel con archivos, proporcionando un control más preciso sobre la lectura y escritura de datos.

#### Ejemplo:
```java
try (FileChannel fileChannel = FileChannel.open(Paths.get("/ruta/archivo.txt"), StandardOpenOption.READ)) {
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    int bytesRead = fileChannel.read(buffer);
    while (bytesRead != -1) {
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }
        buffer.clear();
        bytesRead = fileChannel.read(buffer);
    }
}
```

### AsynchronousFileChannel: Operaciones asíncronas con ejemplos

`AsynchronousFileChannel` permite realizar operaciones de I/O de manera asíncrona, mejorando el rendimiento en aplicaciones que requieren alta concurrencia.

#### Ejemplo:
```java
Path path = Paths.get("/ruta/archivo.txt");
AsynchronousFileChannel asyncFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
ByteBuffer buffer = ByteBuffer.allocate(1024);
Future<Integer> result = asyncFileChannel.read(buffer, 0);
while (!result.isDone()) {
    // Realizar otras tareas mientras se completa la lectura
}
buffer.flip();
System.out.println("Leído: " + new String(buffer.array(), 0, result.get()));
```

### Selector y SocketChannel: Aplicación en comunicaciones de red

`Selector` y `SocketChannel` son fundamentales para gestionar conexiones de red de manera eficiente, permitiendo manejar múltiples conexiones simultáneamente sin bloquear el hilo principal.

#### Ejemplo:
```java
Selector selector = Selector.open();
SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8080));
socketChannel.configureBlocking(false);
socketChannel.register(selector, SelectionKey.OP_READ);

while (true) {
    selector.select();
    Set<SelectionKey> selectedKeys = selector.selectedKeys();
    Iterator<SelectionKey> iterator = selectedKeys.iterator();
    while (iterator.hasNext()) {
        SelectionKey key = iterator.next();
        if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            channel.read(buffer);
            buffer.flip();
            System.out.println("Mensaje recibido: " + new String(buffer.array(), 0, buffer.limit()));
        }
        iterator.remove();
    }
}
```
## Apartado Práctico

### 1. Ejemplos Prácticos Desarrollados

#### Lectura y escritura de ficheros con Path y Files

##### Ejemplo de lectura de líneas de un fichero
```java
Path path = Paths.get("/ruta/al/archivo.txt");
try (Stream<String> lines = Files.lines(path)) {
    lines.forEach(System.out::println);
} catch (IOException e) {
    e.printStackTrace();
}
```

##### Ejemplo de escritura de un texto en un fichero
```java
Path path = Paths.get("/ruta/al/archivo.txt");
String contenido = "Este es el contenido a escribir en el archivo.";
try {
    Files.write(path, contenido.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
} catch (IOException e) {
    e.printStackTrace();
}
```

#### Uso de FileChannel para leer/escribir en ficheros

##### Lectura de bytes en un fichero y almacenamiento en un ByteBuffer
```java
Path path = Paths.get("/ruta/al/archivo.txt");
try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    int bytesRead = fileChannel.read(buffer);
    while (bytesRead != -1) {
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }
        buffer.clear();
        bytesRead = fileChannel.read(buffer);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

##### Escritura de datos de un ByteBuffer en un fichero
```java
Path path = Paths.get("/ruta/al/archivo.txt");
try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
    ByteBuffer buffer = ByteBuffer.wrap("Datos a escribir en el archivo".getBytes());
    while (buffer.hasRemaining()) {
        fileChannel.write(buffer);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

#### Ejemplo de operación asíncrona con AsynchronousFileChannel

##### Escritura asíncrona en un fichero y monitoreo del estado de la operación
```java
Path path = Paths.get("/ruta/al/archivo.txt");
try (AsynchronousFileChannel asyncFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
    ByteBuffer buffer = ByteBuffer.wrap("Datos asíncronos a escribir en el archivo".getBytes());
    Future<Integer> result = asyncFileChannel.write(buffer, 0);
    while (!result.isDone()) {
        // Realizar otras tareas mientras se completa la escritura
    }
    System.out.println("Bytes escritos: " + result.get());
} catch (IOException | InterruptedException | ExecutionException e) {
    e.printStackTrace();
}
```

#### Cómo utilizar un Selector para gestionar conexiones de red

##### Ejemplo de servidor simple con Selector y SocketChannel
```java
try (Selector selector = Selector.open();
     ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
    serverSocketChannel.bind(new InetSocketAddress(8080));
    serverSocketChannel.configureBlocking(false);
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    while (true) {
        selector.select();
        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectedKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            if (key.isAcceptable()) {
                ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                SocketChannel socketChannel = serverChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else if (key.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int bytesRead = socketChannel.read(buffer);
                if (bytesRead == -1) {
                    socketChannel.close();
                } else {
                    buffer.flip();
                    socketChannel.write(buffer);
                    buffer.clear();
                }
            }
            iterator.remove();
        }
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

### 2. Comparativa con Ejemplos Concretos

#### Comparación entre un programa de lectura de ficheros con Java IO y uno con Java NIO

##### Lectura de ficheros con Java IO
```java
try (BufferedReader reader = new BufferedReader(new FileReader("/ruta/al/archivo.txt"))) {
    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

##### Lectura de ficheros con Java NIO
```java
Path path = Paths.get("/ruta/al/archivo.txt");
try (Stream<String> lines = Files.lines(path)) {
    lines.forEach(System.out::println);
} catch (IOException e) {
    e.printStackTrace();
}
```

##### Análisis del rendimiento y las diferencias de complejidad de código
- **Java IO**: La lectura de ficheros con Java IO es sencilla y directa, utilizando clases como `BufferedReader` y `FileReader`. Sin embargo, estas operaciones son bloqueantes, lo que puede afectar el rendimiento en aplicaciones que requieren alta concurrencia.
- **Java NIO**: La lectura de ficheros con Java NIO es más eficiente en términos de rendimiento, especialmente para aplicaciones que manejan grandes volúmenes de datos o múltiples conexiones simultáneas. Utiliza clases como `Path` y `Files`, que permiten operaciones no bloqueantes y una gestión más flexible de los ficheros.

##### Ventajas y desventajas de cada enfoque según el caso de uso
- **Java IO**:
  - **Ventajas**:
    - Simplicidad y facilidad de uso.
    - Adecuado para aplicaciones pequeñas o con pocas operaciones de I/O.
  - **Desventajas**:
    - Operaciones bloqueantes que pueden afectar el rendimiento en aplicaciones concurrentes.
    - Menos flexible y eficiente en la gestión de grandes volúmenes de datos.

- **Java NIO**:
  - **Ventajas**:
    - Operaciones no bloqueantes que mejoran el rendimiento en aplicaciones concurrentes.
    - Mayor flexibilidad y eficiencia en la gestión de ficheros y directorios.
    - Mejor soporte para sistemas de archivos distribuidos y operaciones de red.
  - **Desventajas**:
    - Mayor complejidad en la implementación.
    - Requiere un mayor conocimiento de la API NIO y su funcionamiento.

En resumen, la elección entre Java IO y Java NIO depende del caso de uso específico. Para aplicaciones simples y con pocas operaciones de I/O, Java IO puede ser suficiente. Sin embargo, para aplicaciones que requieren alta concurrencia, manejo de grandes volúmenes de datos o eficiencia en operaciones de red, Java NIO es la mejor opción.

---

### Recursos y Bibliografía

- [Documentación: Java IO](https://docs.oracle.com/javase/7/docs/api/java/io/package-summary.html)
- [Documentación: Java NIO](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/package-summary.html)
- [Curso: Java IO Makigas](https://www.makigas.es/series/java-io)
- [Youtube: Diferencias entre Java IO y Java NIO](https://www.youtube.com/watch?v=YWvR7kizhhc)
- [GitHub: Busqueda de ejemplos](https://github.com/search?q=java+nio)
- [Artículo: ¿Java NIO es más rápido que IO?](https://medium-com.translate.goog/@yaojianhe15/home-truth-series-java-nio-faster-than-io-0d3305fe321c?_x_tr_sl=en&_x_tr_tl=es&_x_tr_hl=es&_x_tr_pto=rq&_x_tr_hist=true)
- [Artículo: Kill the myth please. NIO is *not* faster than IO](https://mailinator.blogspot.com/2008/02/kill-myth-please-nio-is-not-faster-than.html)
- [Guía: Markdown Syntax](https://www.markdownguide.org/basic-syntax/)

---

  ### Autores
  Luis Miguel Benítez Moreno
  Íker Pérez Mata
  Daniel González Rico
  Martín Sánchez Pedrero
