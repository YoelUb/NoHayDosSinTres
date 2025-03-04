package servidor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL_DB = "jdbc:mysql://localhost:8889/nohaydossintres";
    private static final String USUARIO_DB = "pichichi"; // Ajusta tu usuario
    private static final String CONTRASEÑA_DB = "123456789"; // Ajusta tu contraseña


    public static Connection conectar() {
        try {
            // Registrar el driver de MySQL manualmente
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL_DB, USUARIO_DB, CONTRASEÑA_DB);

        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver MySQL.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("Error: No se pudo conectar a la base de datos.");
            e.printStackTrace();
            return null;
        }
    }
}
