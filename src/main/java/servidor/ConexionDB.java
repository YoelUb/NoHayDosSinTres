package servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String MYSQLHOST = System.getenv("MYSQLHOST") != null ? System.getenv("MYSQLHOST") : "mysql-container.railway.app";
    private static final String MYSQLPORT = System.getenv("MYSQLPORT") != null ? System.getenv("MYSQLPORT") : "3306";
    private static final String MYSQLDATABASE = System.getenv("MYSQLDATABASE") != null ? System.getenv("MYSQLDATABASE") : "railway";
    private static final String MYSQLUSER = System.getenv("MYSQLUSER") != null ? System.getenv("MYSQLUSER") : "root";
    private static final String MYSQLPASSWORD = System.getenv("MYSQLPASSWORD") != null ? System.getenv("MYSQLPASSWORD") : "GSQYAZUDbkFMLsxkYWwYBZmTWciiMIht";

    private static final String URL = "jdbc:mysql://" + MYSQLHOST + ":" + MYSQLPORT + "/" + MYSQLDATABASE + "?useSSL=false&serverTimezone=UTC";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            // Intentamos conectar con la base de datos utilizando los valores obtenidos
            conexion = DriverManager.getConnection(URL, MYSQLUSER, MYSQLPASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos en Railway!");
        } catch (SQLException e) {
            // Capturamos el error si no se puede conectar
            System.err.println("❌ Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }
}
