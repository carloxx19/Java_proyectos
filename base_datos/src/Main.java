
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Metodos conexion = new Metodos("jdbc:mysql://localhost:8889/tienda?characterEconding=utf8", "root", "root");

        conexion.driver("com.mysql.cj.jdbc.Driver"); // "com.mysql.cj.jdbc.Driver"
        conexion.conectar();

        conexion.menu();
    }
}
/*
* import java.security.PublicKey;
import java.sql.*;
import java.util.Scanner;

public class Metodos {

    //variables
    Scanner teclado = new Scanner(System.in);
    public Connection conexion;
    private String url;
    private String usuario;
    private String contrasenia;

    //constructor
    public Metodos(String url, String usuario, String contrasenia) {
        this.url = url;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    //Metodos
    //driver, cambia segun el motor utilizado.
    public void driver(String conector) {
        try {
            Class.forName(conector);
        } catch (ClassNotFoundException e) {
            System.err.println("No se ha encontrado el Driver: " + conector);
            System.exit(-1);
        }
    }

    //establece la conexion con la base de datos.
    public void conectar() throws SQLException {
        conexion = DriverManager.getConnection(url, usuario, contrasenia);
    }
//prepare
    //Imprime los fabricantes
    public void imprimir_fabricante(Connection connection) throws SQLException {
        String query = "select * from fabricante";
        Statement pt = conexion.prepareStatement(query);
        ResultSet rs = pt.executeQuery(query);

        while (rs.next()) {
            System.out.println("Codigo fabricante: " + rs.getInt(1) + " | Nombre: " + rs.getString(2));
        }
    }

    //Imprime los productos
    public void imprimir_producto(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String query = "select * from producto";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.println("Id Producto: " + rs.getInt(1) + " || Nombre: " + rs.getString(2) +
                    " || Precio: " + rs.getDouble(3) + " || Codigo fabricante: " + rs.getInt(4));
        }
    }

    //Insertar productos
    public void insertar_productos(Connection connection) throws SQLException {
        String consulta = "INSERT INTO producto (nombre, precio, codigo_fabricante) VALUES (?, ?, ?);";
        String nombre;
        double precio;
        int cod_fabricante;

        System.out.println("Introduce el nombre del prodcuto");
        nombre = teclado.next();
        System.out.println("Introduce el precio del producto");
        precio = teclado.nextDouble();
        System.out.println("Introduce el codido del fabricante");
        cod_fabricante= teclado.nextInt();

        PreparedStatement ps = connection.prepareStatement(consulta);
        ps.setString(1,nombre);
        ps.setDouble(2,precio);
        ps.setInt(3,cod_fabricante);

        System.out.println(ps);
        ps.executeUpdate();

    }

    public void mostrar_producto_fabricante(Connection connection) throws SQLException {
        String consulta = "SELECT producto.nombre from producto JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo where fabricante.nombre=?";
        String fabricante;

        System.out.println("Introduce el nombre del fabricante");
        fabricante = teclado.next();

        PreparedStatement ps = connection.prepareStatement(consulta);
        ps.setString(1, fabricante);


        ResultSet rs = ps.executeQuery();
        System.out.println("***PRODUCTOS***");
        while (rs.next()) {
            System.out.println("Nombre: " + rs.getString(1));


        }
    }

    //Menu para llamar a los metodos.
    public void menu() throws SQLException {
        String opcion;
        do {
            System.out.println("""

                ***MENU***
                1.-MONSTRAR DATOS FABRICANTE.
                2.-MOSTRAR DATOS PRODUCTOS.
                3.-INSERTAR PRODUCTO.
                4.-MOSTRAR PRODUCTO DE FABRICANTE.
                """);
            System.out.println("Introduce la opcion a realizar");
            opcion = teclado.next();

            switch (opcion) {
                case "1" -> imprimir_fabricante(conexion);
                case "2" -> imprimir_producto(conexion);
                case "3" -> insertar_productos(conexion);
                case "4" ->mostrar_producto_fabricante(conexion);
            }
        }while (!opcion.equalsIgnoreCase("salir"));
    }
}
* */