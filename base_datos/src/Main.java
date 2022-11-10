import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        Metodos conexion = new Metodos("jdbc:mysql://localhost:8889/tienda?characterEconding=utf8", "root", "root");

        conexion.driver("com.mysql.cj.jdbc.Driver"); // "com.mysql.cj.jdbc.Driver"
        conexion.conectar();
        conexion.menu();
    }
}