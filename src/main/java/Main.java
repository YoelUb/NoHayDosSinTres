import servidor.ConexionDB;
import servidor.Servidor;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {


        ConexionDB.conectar();


        // Iniciar el servidor
        try {
            System.out.println("Iniciando el servidor...");
            Servidor.main(new String[]{}); // Llamar al método main de Servidor
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al iniciar el servidor.");
        }

    }
}