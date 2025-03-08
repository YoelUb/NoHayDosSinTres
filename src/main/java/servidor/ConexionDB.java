package servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    // Obtiene los valores desde las variables de entorno en Render
    private static final String MYSQL_HOST = System.getenv("MYSQL_HOST");
    private static final String MYSQL_PORT = System.getenv("MYSQL_PORT");
    private static final String MYSQL_DATABASE = System.getenv("MYSQL_DATABASE");
    private static final String MYSQL_USER = System.getenv("MYSQL_USER");
    private static final String MYSQL_PASSWORD = System.getenv("MYSQL_PASSWORD");

    // Construcción de la URL de conexión
    private static final String URL = "jdbc:mysql://" + MYSQL_HOST + ":" + MYSQL_PORT + "/" + MYSQL_DATABASE
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            System.out.println("🔗 Intentando conectar a la base de datos...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, MYSQL_USER, MYSQL_PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos!");
        } catch (SQLException e) {
            System.err.println("❌ Error SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver MySQL no encontrado: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Error desconocido: " + e.getMessage());
        }
        return conexion;
    }

    // Método para probar la conexión
    public static void main(String[] args) {
        Connection conn = conectar();
        if (conn != null) {
            System.out.println("🎉 La conexión fue exitosa en RENDER!");
        } else {
            System.out.println("⚠️ Fallo en la conexión.");
        }
    }
}
