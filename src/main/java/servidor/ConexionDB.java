package servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class ConexionDB {
    private static final Dotenv dotenv = Dotenv.load();  // Cargar el archivo .env

    static {
        // 🔍 Verificar que las variables se están cargando correctamente
        System.out.println("🔍 MYSQLHOST: " + dotenv.get("MYSQLHOST"));
        System.out.println("🔍 MYSQLPORT: " + dotenv.get("MYSQLPORT"));
        System.out.println("🔍 MYSQLDATABASE: " + dotenv.get("MYSQLDATABASE"));
        System.out.println("🔍 MYSQLUSER: " + dotenv.get("MYSQLUSER"));
        System.out.println("🔍 MYSQLPASSWORD: " + (dotenv.get("MYSQLPASSWORD") != null ? "********" : "NULL"));
    }

    private static final String MYSQLHOST = dotenv.get("MYSQLHOST");
    private static final String MYSQLPORT = dotenv.get("MYSQLPORT");
    private static final String MYSQLDATABASE = dotenv.get("MYSQLDATABASE");
    private static final String MYSQLUSER = dotenv.get("MYSQLUSER");
    private static final String MYSQLPASSWORD = dotenv.get("MYSQLPASSWORD");

    private static final String URL = "jdbc:mysql://" + MYSQLHOST + ":" + MYSQLPORT + "/" + MYSQLDATABASE + "?useSSL=false&serverTimezone=UTC";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            System.out.println("🔗 Intentando conectar a la base de datos...");
            Class.forName("com.mysql.cj.jdbc.Driver"); // 🔹 Cargar driver de MySQL manualmente
            conexion = DriverManager.getConnection(URL, MYSQLUSER, MYSQLPASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos en Railway!");
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar a la base de datos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver de MySQL no encontrado: " + e.getMessage());
        }
        return conexion;
    }
}
