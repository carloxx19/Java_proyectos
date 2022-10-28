import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        System.out.println("""
                MENU
                1.-Leer fichero
                2.-Eliminar Fichero
                3.-Add Fichero
                4.-Salir""");
        metodos();
    }

    public static void metodos() {

        try {
            String comando;
            Scanner t = new Scanner(System.in);
            String opcion;
            while (true) {
                System.out.println("INGRESAR LA FUNCION QUE DESEA REALIZAR");
                comando = t.nextLine();
                switch (comando.toLowerCase()) {
                    case "leer" -> leerxml();
                    case "añadir" -> añadirnodo();
                    case "eliminar" -> eliminarnodo();
                    case "salir" -> salir();
                    default -> System.out.println("INGRESAR LAS OPCIONES DEL MENU");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void añadirnodo() {
        try {
            String titulo, genero, plataforma, fecha;
            Scanner t = new Scanner(System.in);
            File archivo = new File("src//juegos.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            document.getDocumentElement().normalize();

            Node nodoRaiz = document.getDocumentElement();

            Element nuevoJuego = document.createElement("juego");

            Element nuevoTitulo = document.createElement("titulo");
            System.out.println("Agregar el titulo del videojuego");
            titulo = t.nextLine();
            nuevoTitulo.setTextContent(titulo);

            Element nuevoGenero = document.createElement("genero");
            System.out.println("Agregar el genero del videojuego");
            genero = t.nextLine();
            nuevoGenero.setTextContent(genero);

            Element nuevoPlataforma = document.createElement("plataforma");
            System.out.println("Agregar la plataforma del videojuego");
            plataforma = t.nextLine();
            nuevoPlataforma.setTextContent(plataforma);

            Element nuevoFecha = document.createElement("fechadelanzamiento");
            System.out.println("Agregar la fecha de lanzamiento del videojuego");
            fecha = t.nextLine();
            nuevoFecha.setTextContent(fecha);

            nuevoJuego.appendChild(nuevoTitulo);
            nuevoJuego.appendChild(nuevoGenero);
            nuevoJuego.appendChild(nuevoPlataforma);
            nuevoJuego.appendChild(nuevoFecha);
            nodoRaiz.appendChild(nuevoJuego);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("src//juegos.xml"));
            transformer.transform(source, result);
            System.out.println("ELEMENTO AGREGADO");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void salir() {
        System.exit(-1);
    }

    public static void leerxml() {
        Path path = Path.of("src//juegos.xml");
        File xml = path.toFile();
        /*PRIMEROS PASOS DE CONFIGURACIN*/
        DocumentBuilder builder = createBuilder();
        Document document = null;
        try {
            document = builder.parse(xml);
        } catch (IOException | SAXException ex) {
            System.err.println("Error al crear el Document");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }

        /*TRATAR EL FICHERO*/
        NodeList listaInicial = document.getElementsByTagName("listadejuegos").item(0).getChildNodes();
        listarElementos(listaInicial);
    }

    private static DocumentBuilder createBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.err.println("Error al crear el DocumentBuilder");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        return builder;
    }

    private static void listarElementos(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) node;

                System.out.println("Titulo: " + getNodo("titulo", elemento));
                System.out.println("Genero: " + getNodo("genero", elemento));
                System.out.println("Plataforma: " + getNodo("plataforma", elemento));
                System.out.println("Fecha de lanzamiento: " + getNodo("fechadelanzamiento", elemento));
                System.out.println("");
            }
        }
    }

    private static String getNodo(String etiqueta, Element elem) {
        NodeList nodo = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node valornodo = (Node) nodo.item(0);
        return valornodo.getNodeValue();// devuelve el valor del nodo
    }

    public static void eliminarnodo() {

        try {
            Scanner t = new Scanner(System.in);
            String tema;
            System.out.println("Ingrese el nombre del nodo que desea eliminar");
            tema = t.nextLine();
            File archivo = new File("src//juegos.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            NodeList listaEmpleados = document.getElementsByTagName("juego");
            document.getDocumentElement().normalize();

            for (int i = 0; i < listaEmpleados.getLength(); i++) {

                Node node = listaEmpleados.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) node;
                    if (getNodo("titulo", elemento).equalsIgnoreCase(tema)) {
                        elemento.getParentNode().removeChild(elemento);
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("src//juegos.xml"));
            transformer.transform(source, result);
            System.out.println("ELEMENTO ELIMINADO");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}