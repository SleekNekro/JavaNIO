# Introducción de Java NIO

## En Java 1.4

Java NIO (New Input/Output) se introdujo en Java 1.4 (lanzado en 2002) con el objetivo de proporcionar una API más eficiente y flexible para gestionar la entrada y salida de datos. Antes de NIO, el modelo tradicional de I/O en Java se basaba en flujos (streams), que eran simultáneos y bloqueaban el hilo de ejecución hasta que la operación de I/O terminaba.

Java NIO introdujo nuevos conceptos como:

- **Buffers**: Contenedores para almacenar datos a leer o escribir, permitiendo un acceso aleatorio a los datos en lugar de secuencial.
- **Channels**: Interfaces para interactuar de manera más eficiente con fuentes de datos, permitiendo operaciones no bloqueantes.
- **Selectors**: Permiten que un solo hilo controle múltiples canales, mejorando el rendimiento en aplicaciones de red que gestionan múltiples conexiones simultáneas.

## Mejoras con Java NIO2 a partir de Java 7

En Java 7 (lanzado en 2011), se introdujo Java NIO.2 (también conocido como `java.nio.file`), que amplió significativamente la API NIO, mejorando la gestión de archivos y directorios. Algunas mejoras clave fueron:

- **API de archivos más robusta**: Sustituyó en gran parte a `java.io.File`, ofreciendo una forma más flexible de trabajar con archivos y directorios.
- **Path y Files**: Introducción de la clase `Path` para representar rutas de archivos y directorios de manera más potente, y `Files` para realizar operaciones de I/O de manera más sencilla.
- **Operaciones no bloqueantes**: NIO2 facilitó la implementación de operaciones de I/O de archivos y red de manera no bloqueante, mejorando la eficiencia en escenarios de alta concurrencia.
- **Soporte para sistemas de archivos distribuidos**: NIO2 permitió acceder a recursos en sistemas de archivos remotos o en red.
- **WatchService**: Introducción de una API para monitorear directorios en tiempo real, detectando cambios como la adición, modificación o eliminación de archivos.

## Necesidades cubiertas en el desarrollo de software

Antes de Java NIO, el modelo de flujos tenía limitaciones en cuanto a rendimiento y flexibilidad, especialmente en aplicaciones de alta concurrencia o con operaciones de I/O complejas. Java NIO y NIO2 cubrieron varias necesidades clave en el desarrollo de software:

- **Eficiencia en I/O**: NIO mejora la gestión de datos, especialmente en aplicaciones que manejan archivos grandes o múltiples conexiones de red simultáneas sin bloquear los hilos.
- **Operaciones no bloqueantes**: Esencial para aplicaciones de alta concurrencia como servidores o bases de datos, permitiendo manejar muchas conexiones sin bloquear los hilos.
- **Simplificación de gestión de archivos**: Con NIO2, trabajar con archivos y directorios se vuelve más sencillo mediante clases como `Path`, `Files` y `WatchService`.
- **Mejora en la gestión de errores**: NIO ofrece una mejor gestión de excepciones durante las operaciones de entrada/salida.
- **Soporte para alta concurrencia**: Permite crear aplicaciones más eficientes mediante multiplexación de I/O, gestionando múltiples conexiones concurrentemente.
- **Soporte para redes y sistemas distribuidos**: NIO2 facilita la interacción con archivos locales y remotos, mejorando la creación de sistemas de archivos distribuidos.

## Resumen

Java NIO y NIO2 respondieron a las limitaciones de los modelos tradicionales de I/O, mejorando tanto el rendimiento como la flexibilidad, especialmente en aplicaciones que requieren alta concurrencia o operaciones complejas de I/O. Fueron fundamentales para el desarrollo de aplicaciones de servidor, redes, sistemas de archivos y aplicaciones multimedia, permitiendo una mayor eficiencia y escalabilidad.

---

# Principales Conceptos de Java NIO

## 1. **Canales (Channels)**

Los **canales** son un elemento crucial de Java NIO y actúan como vías de comunicación para la lectura y escritura de datos de manera más eficaz que las operaciones convencionales de entrada y salida (Java IO). A diferencia de las clases de flujo, como `InputStream` y `OutputStream`, los canales permiten operaciones bidireccionales y tienen la capacidad de gestionar tanto archivos como sockets o cualquier otra fuente de datos. Esto los hace muy útiles para realizar operaciones de entrada/salida de forma más rápida y, sobre todo, más escalable.

Algunos de los canales más utilizados son:

- **FileChannel**
- **SocketChannel**
- **ServerSocketChannel**

---

## 2. **Buffers: ¿Qué son y cómo funcionan?**

Los **buffers** son esencialmente espacios de memoria temporal donde se almacenan los datos durante el proceso de lectura o escritura. Un buen ejemplo de esto es la reproducción de videos en páginas web, que para asegurar que el video se vea fluido a pesar de las posibles pérdidas de internet, siempre carga unos segundos adicionales de contenido.

En Java NIO, el buffer más común es el **`ByteBuffer`**, aunque también existen otros tipos, como **`CharBuffer`** o **`IntBuffer`**, para manejar diferentes tipos de datos.

### Funcionamiento:
- Cuando se leen datos desde un canal, estos se almacenan primero en el buffer.
- Posteriormente, podemos manipular los datos o escribirlos en otro canal, optimizando la gestión de la memoria.

---

## 3. **Operaciones No Bloqueantes**

Una de las características más destacadas de Java NIO es que permite realizar **operaciones no bloqueantes**. Esto significa que podemos seguir realizando otras tareas mientras una operación de lectura o escritura está en proceso, sin que el sistema se quede detenido esperando a que se complete la operación.

### Ventajas:
- Mayor eficiencia en el uso de recursos.
- Mejora el rendimiento en aplicaciones concurrentes o con múltiples conexiones.

---

## 4. **Selectores: Definición y Usos**

Los **selectores** son herramientas que permiten gestionar múltiples canales simultáneamente para saber cuándo están listos para realizar operaciones de entrada/salida, como leer o escribir.

### Usos:
- Los selectores son esenciales en servidores o aplicaciones que gestionan muchas conexiones al mismo tiempo, como los servidores web o bases de datos.
- Gracias a los selectores, podemos gestionar múltiples conexiones de forma eficiente y realizar operaciones **asíncronas** sin interrumpir el proceso principal de la aplicación.

---

# Comparativa entre Java NIO y Java IO

## 1. **Lectura/Escritura de Ficheros**

- **Java IO**: Utiliza flujos como `FileInputStream` y `FileOutputStream`, que son operaciones bloqueantes. El proceso de lectura o escritura se detiene hasta que se finaliza la operación de I/O.
  
- **Java NIO**: Usa **canales** (`FileChannel`) y **buffers**, lo que permite realizar operaciones de lectura/escritura de manera **no bloqueante**, siendo mucho más eficiente en situaciones con grandes archivos o cuando se deben manejar múltiples operaciones simultáneas.

---

## 2. **Gestión de Rutas y Ficheros**

- **Java IO**: Utiliza la clase **`File`**, que es más limitada y menos flexible en la gestión de rutas y ficheros. No está tan bien adaptada para tareas modernas como la creación, lectura, copia o traslado de ficheros en sistemas operativos actuales.
  
- **Java NIO**: Introduce la clase **`Path`**, que ofrece una forma mucho más moderna y flexible para manejar rutas y ficheros. Es compatible con diversas plataformas y facilita tareas de gestión de ficheros.

---

## 3. **Operaciones de Red**

- **Java IO**: Utiliza clases tradicionales como **`Socket`** y **`ServerSocket`** que son bloqueantes, lo que puede generar ineficiencia cuando se manejan muchas conexiones simultáneas.

- **Java NIO**: Proporciona **`SocketChannel`** y **`ServerSocketChannel`**, que permiten gestionar conexiones de red de manera **asíncrona**, aumentando la rapidez y escalabilidad, lo cual es ideal para servidores que manejan muchas conexiones de forma concurrente.

---

