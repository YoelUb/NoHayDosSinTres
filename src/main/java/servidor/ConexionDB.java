package servidor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    //Creo que el punto esta en esta linea, la cosa es atinar la url (es decir cambiar la url que esta entre comillas)
    private static final String URL = "jdbc:postgresql://[123456789]@db.ruvzzpjzyutqcdzedkcg.supabase.co:5432/postgres";
    // no le hagas caso a esta linea private static final String SUPABASE_KEY ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJ1dnp6cGp6eXV0cWNkemVka2NnIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDExOTM4MjAsImV4cCI6MjA1Njc2OTgyMH0.CHRnvFtIC8mhlv6EtJG8H7VQScfI_cviVKUv1uwQmec";
    private static final String USER = "9be58cfb-18ac-4fab-83c2-6edc01baccd3"; // Ajusta tu usuario
    private static final String PASSWORD = "123456789"; // Ajusta tu contraseña

    public static Connection conectar() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver"); // Cargar el driver
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a Supabase.");
        } catch (ClassNotFoundException e) {
            System.err.println(" Error: Driver JDBC no encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error en la conexión.");
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace(); // Muestra el error en la consola
            return null; // Devuelve null si falla la conexión
        }
    }
}


