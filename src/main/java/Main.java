import servidor.ConexionDB;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {


        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            System.out.println("La conexión fue exitosa.");
        } else {
            System.out.println("Error en la conexión.");
        }



    }
}