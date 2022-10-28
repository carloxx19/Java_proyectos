import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class Terminal {
    //variables globales.
    //aqui creo entrada que almacena lo que se introduce por teclado.
    String entrada;

    //creo un string que almacenara lo introducido por teclado para luego poder separar por espacios lo introducido
    //Ejemplo, si pongo cd y un espacio y direccion va a separar el cd y lo almacena en un espacio del array{0}
    //y direccion lo almacena en otro espacio del array{1}
    String[] separado;

    //en estas 2 lienas creo un string que va a almacenar la ruta actual en la que nos encontramos
    //y luego almaceno la ruta en un File para poder trabajar con la importacion File y sus metodos.
    String ruta = System.getProperty("user.dir");
    File direccion = new File(ruta);
    File fichero;

    //este estring es de los mas importantes, va a hacer que el 'String ruta' cuando lo modifiquemos nos deje trabajar
    //sobre esa nueva ruta y no solo en la inicial.

    String rutaActual;

    //metodo Principal, este metodo lo que va a hacer es dar valor a la entrada del teclado separar esa entrada y
    //llamar al resto de metodos, tiene un bucle que permite trabajar con la consola hasta que se introduzca un salir
    //por teclado
    public void iniciar() {
        //creo el teclado, como no se va a usar en otro sitio lo creo aqui dentro.
        Scanner teclado = new Scanner(System.in);

        //muestra un texto solo al inicio para que se sepa que poniendo help te mostrara los comandos disponibles
        //y llamo al metodo que muestra la direccion actual donde estamos.
        System.out.println("introduce 'HELP' para mostrar la ayuda");
        mostar_dir_actual();

        //el bucle repetira la entrada de teclado y la separacion hasta que pongamos salir por teclado
        do {
            //almacenamos la entra de teclado en entrada
            entrada = teclado.nextLine();

            //split, para separar la entrada en partes y guardarlo en un arrays y poder mostar un parte en concreto
            separado = entrada.split(" ");

            menu();
        } while (!entrada.equalsIgnoreCase("close"));

        //cuando se sale del bucle y se termina de trabajar mostrara este mensaje
        System.out.println("Cerrado exitosamente");
    }

    //menu con los casos que se van a hacer con lo introducido en 'entrada' consiste en un swich de String y solo
    //utilizara la primera parte de lo introducido por teclado, es decir, el comando
    public void menu() {
        switch (separado[0]) {
            case "help" -> mostrar_ayuda();

            case "cd.." -> disminuir_directorio();

            case "dir" -> mostrar_contenido_directorio_actual();

            case "cda" -> avanzar_carpeta_absoluta();

            case "cd" -> avanzar_relativa();

            case "mkdir" -> crear_carpeta();

            case "mkfile" -> crear_fichero();

            case "cat" -> leer_fichero();

            case "write" -> escribir_fichero();

            case "info" -> informacion_fichero();

            case "delete" -> eliminar();

            case "top" -> contar_lineas();

            case "readpoint" -> puntero();

            default -> {
                System.err.println("valor introducido erroneo");
                mostar_dir_actual();
            }

        }
    }

    //Metodo help para mostrar la lista de comandos
    public void mostrar_ayuda() {
        System.out.println("""
                todos los comandos se escriben sin comillas.
                'cd ..' para retroceder en el directorio.
                'info nombre_archivo' muestra la informacion del archivo.
                'cda nombre_del_directorio' para ruta absoluta.
                'cd nombre_del_directorio' para ruta relativa.
                'mkdir nombre' para crear un directorio en la ruta actual.
                'cat nombre_fichero' para mostrar el contenido del fichero.
                'top numerolinea nombre_fichero' muestra las lineas especificas de un fichero.
                'mkfile nombrefichero texto' crea un fichero con ese nombre y el contenido de texto.
                'write nombrefichero texto' añade texto al final del fichero especifico.
                'dir' lista los archivos del directorio actual.
                'readpoint nombrefichero posicion' lee un archivo desde una determinada posicion de puntero. /////
                'delete nombre' borra un fichero si es directorio borra todo el contenido.
                'close' cierra el programa.
                """);
        mostar_dir_actual();
    }

    //metodo para mostar la ruta inicial, solo se usa una vez al inicio de la aplicacion.
    public void mostar_dir_actual() {
        try {
            System.out.println(direccion.getCanonicalPath());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //con cd.. reducira la carpeta en la que estamos trabajando
    public void disminuir_directorio() {
        rutaActual = direccion.getParent();
        System.out.println(rutaActual);
        direccion = new File(rutaActual);
    }

    //con dir mostrara el contenido de la ruta actual en la que estemos
    public void mostrar_contenido_directorio_actual() {
        for (String i : direccion.list()) {
            System.out.println(i);
        }
        System.out.println(direccion);
    }

    public void avanzar_carpeta_absoluta() {
        File esta = new File(separado[1]);

        if (esta.exists() && esta.isDirectory()) {
            try {
                esta.getCanonicalPath();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            direccion = new File(String.valueOf(esta));

            System.out.println(direccion);
        } else if (esta.exists() && esta.isFile()){
            System.out.println("no se puede listar un archivo");
            System.out.println(direccion);
        } else {
            System.out.println("ruta introducida Erronea");
            System.out.println(direccion);
        }


    }

    public void avanzar_relativa() {
        File rutaActual = direccion;
        direccion = new File(direccion + separado[1]);

        if (direccion.exists()) {
            System.out.println(direccion);
        } else {
            System.out.println("ruta erronea");
            direccion = rutaActual;
            System.out.println(direccion);
        }
    }

    //creamos una carpeta
    private void crear_carpeta() {
        //se crea archivo directorio que almacena el nombre que va a tener
        File directorio = new File(direccion + "/" + separado[1]);

        //si lo puede creear, lo hara y saldra creado correctamente, si no deja crearlo otro mensaje
        //que diga que no se a podido crear
        if (directorio.mkdir()) {
            System.out.println("creado correctamente");
        } else {
            System.out.println("no se a podido crear");
            System.out.println(direccion);
        }
    }

    private void crear_fichero() {
        fichero = new File(direccion + "/" + separado[1] + ".txt");

        try {
            if (fichero.createNewFile()) {
                System.out.println("creado");
                System.out.println(direccion);
            } else {
                System.out.println("no se a podido crear el archivo");
                System.out.println(direccion);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void escribir_fichero() {
        Scanner teclado = new Scanner(System.in);
        String texto;
        System.out.println("Introduce el texto a escribir");
        texto = teclado.nextLine();

        String[] texto_entero;
        texto_entero = texto.split(" ");

        FileWriter fichero = null;
        PrintWriter pw = null;

        try {
            fichero = new FileWriter(separado[1] + ".txt", true);
            pw = new PrintWriter(fichero);

            for (int i = 0; i < texto_entero.length; i++) {
                pw.println(texto_entero[i]);
            }
            System.out.println("escrito correctamente");
            System.out.println(direccion);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void leer_fichero() {
        //almacenamos el nombre del fichero que queremos leer en un file
        File archivo = new File(separado[1]);

        if (archivo.exists() && archivo.isFile()) {
            try {
                FileReader leer = new FileReader(archivo);
                int i;
                while ((i = leer.read()) != -1) {
                    System.out.print((char) i);
                }
                System.out.println(direccion);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("error al intentar leer fichero, comprueba si existe o si es un fichero.");
            System.out.println(direccion);
        }
    }

    private void informacion_fichero() {
        File informacion = new File(direccion + "/" + separado[1]);
        int contador_directorios = 0;
        int contador_archivos = 0;
        if (informacion.exists() && informacion.isDirectory() == true) {
            System.out.println("es un directorio");
            File[] numero_elementos = informacion.listFiles();

            for (File file : numero_elementos) {
                if (file.isDirectory()) {
                    contador_directorios++;
                } else {
                    contador_archivos++;
                }
            }

            contador_archivos = contador_archivos - 1;
            System.out.println("Contiene: " + contador_directorios + " Directorios");
            System.out.println("Contiene: " + contador_archivos + " Archivos");
        } else if (informacion.exists() && informacion.isDirectory() == false) {
            System.out.println("es un archivo");
            System.out.println("no contiene elementos");
        }

        if (informacion.exists()) {
            //variable para calcular mg
            long mb = 1024L * 1024L;

            //espacio libre
            long espacio = informacion.getFreeSpace();
            long total = espacio / mb;
            System.out.println("Espacio libre: " + total + " Mb");

            //espacio total
            espacio = informacion.getTotalSpace();
            total = espacio / mb;
            System.out.println("Espacio total: " + total + " Mb");

            //espacio usable
            espacio = informacion.getUsableSpace();
            total = espacio / mb;
            System.out.println("Espacio usable: " + total + " Mb");
        } else {
            System.out.println("no se encontro el archivo/directorio escrito, revise");
        }
        System.out.println(direccion);
    }

    //de forma relativa
    private void eliminar() {
        File eliminar = new File(direccion + "/" + separado[1]);

        if (eliminar.exists() && eliminar.isDirectory()) {
            String[] borrar = eliminar.list();
            for (String s : borrar) {
                File currentFile = new File(eliminar.getPath(), s);
                currentFile.delete();
            }

            try {
                Files.delete(eliminar.toPath());
                System.out.println("Eliminado correctamente");
                System.out.println(direccion);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (eliminar.exists()) {
            try {
                Files.delete(eliminar.toPath());
                System.out.println("Eliminado correctamente");
                System.out.println(direccion);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("No se a podido eliminar, revise");
        }
    }

    private void contar_lineas() {
        File contar = new File(direccion + "/" + separado[1]);
        int lineas = 0;
        try {
            Scanner sc = new Scanner(contar);
            while (sc.hasNextLine()) {
                sc.nextLine();
                lineas++;
            }
            System.out.println("el archivo tiene: " + lineas + " lineas");
            sc.close();
            System.out.println(direccion);
        } catch (FileNotFoundException e) {
            System.out.println("archivo introducido erroneo/no encontrado, revise");
            System.out.println(direccion);
        }
    }

    private void puntero() {
        try {

            // attach the file to FileInputStream
            FileInputStream saltar_caracter = new FileInputStream(direccion + "/" + separado[1]);

            int puntero = Integer.parseInt(separado[2]);
            int i = 0;

            // ingnora el numero de caracteres introducidos y empieza a leer a partir de el, es decir
            // si pones 2 ignora los 2 primeros y lee desde el 3.
            saltar_caracter.skip(puntero);

            // read from the file
            System.out.print("Printing text from index 8: ");

            while ((i = saltar_caracter.read()) != -1) {

                System.out.print((char) i);
            }

            saltar_caracter.close();
            System.out.println(direccion);
        } catch (Exception e) {
            System.err.println("Error al leer, revise si es un archivo,si  el nombre del archivo a leer esta bien" +
                    " o si el numero del puntero es una letra y no un numero");
            System.out.println(direccion);
        }
    }
}