import java.net.ConnectException;
import java.security.PublicKey;
import java.security.SignedObject;
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

    //Imprime los fabricantes
    public void imprimir_fabricante(Connection connection) throws SQLException {
        String query = "select * from fabricante";
        Statement pt = conexion.prepareStatement(query);
        ResultSet rs = pt.executeQuery(query);

        while (rs.next()) {
            System.out.println("Codigo fabricante: " + rs.getInt(1) + " || Nombre: " + rs.getString(2));
        }
        System.out.println("");
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
        System.out.println("");
    }

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
        cod_fabricante = teclado.nextInt();

        PreparedStatement ps = connection.prepareStatement(consulta);
        ps.setString(1, nombre);
        ps.setDouble(2, precio);
        ps.setInt(3, cod_fabricante);

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

    public void mostrar_baratos(Connection connection) throws SQLException {
        String consulta = "SELECT nombre,precio FROM tienda.producto ORDER BY precio ASC LIMIT ?";
        int num_productos = 5;

        PreparedStatement ps = connection.prepareStatement(consulta);
        ps.setInt(1, num_productos);
        ResultSet rs = ps.executeQuery();

        System.out.println("***LOS 5 PRODUCTOS MAS BARATOS SON:***");
        while (rs.next()) {
            System.out.println("Nombre: " + rs.getString(1) + " || Precio: " + rs.getDouble(2));
        }
    }

    public void editar_precio(Connection connection) throws SQLException {
        String consulta = "UPDATE tienda.producto SET precio = ? WHERE (codigo = ?);";
        int codigo;
        double precio;

        System.out.println("Introduce el codigo del producto a modificar");
        codigo = teclado.nextInt();
        System.out.println("Introduce el nuevo precio");
        precio = teclado.nextDouble();

        PreparedStatement ps = connection.prepareStatement(consulta);
        ps.setDouble(1, precio);
        ps.setInt(2, codigo);
        ps.executeUpdate();
    }

    public void borrar_producto(Connection connection) throws SQLException {
        String consulta = "DELETE FROM tienda.producto WHERE (codigo = ?);";
        int codigo;

        System.out.println("Introduce el codigo del producto a eliminar");
        codigo = teclado.nextInt();

        PreparedStatement ps = connection.prepareStatement(consulta);
        ps.setInt(1, codigo);
        ps.executeUpdate();
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
                    5.-MOSTRAR LOS 5 PRODUCTOS MAS BARATOS.
                    6.-EDITAR PRECIO DE UN PRODUCTO.
                    7.-ELIMINAR PRODUCTO.
                    salir.-CERRAR APLICACION.
                    """);
            System.out.println("Introduce la opcion a realizar");
            opcion = teclado.next();

            switch (opcion) {
                case "1" -> imprimir_fabricante(conexion);
                case "2" -> imprimir_producto(conexion);
                case "3" -> insertar_productos(conexion);
                case "4" -> mostrar_producto_fabricante(conexion);
                case "5" -> mostrar_baratos(conexion);
                case "6" -> editar_precio(conexion);
                case "7" -> borrar_producto(conexion);

                case "salir" -> System.err.println("CERRADO");
                case "SALIR" -> System.err.println("CERRADO");
            }
        } while (!opcion.equalsIgnoreCase("salir"));
    }
}