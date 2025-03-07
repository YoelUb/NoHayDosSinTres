package servidor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL_DB = "https://ruvzzpjzyutqcdzedkcg.supabase.co";
    private static final String SUPABASE_KEY ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJ1dnp6cGp6eXV0cWNkemVka2NnIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDExOTM4MjAsImV4cCI6MjA1Njc2OTgyMH0.CHRnvFtIC8mhlv6EtJG8H7VQScfI_cviVKUv1uwQmec";
    private static final String USUARIO_DB = "9be58cfb-18ac-4fab-83c2-6edc01baccd3"; // Ajusta tu usuario
    private static final String CONTRASEÑA_DB = "123456789"; // Ajusta tu contraseña

    public static Connection conectar() throws SQLException {
        try {
            // Registrar el driver de MySQL manualmente
            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(URL_DB,SUPABASE_KEY, USUARIO_DB, CONTRASEÑA_DB);

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

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL_DB, USUARIO_DB, CONTRASEÑA_DB);
        } catch (SQLException e) {
            e.printStackTrace(); // Muestra el error en la consola
            return null; // Devuelve null si falla la conexión
        }
    }
}


