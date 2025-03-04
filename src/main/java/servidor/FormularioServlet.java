package servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/formulario")
public class FormularioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Obtener parámetros del formulario
        String nombre = request.getParameter("nombre");

        // Validar datos
        if (nombre == null || nombre.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\": \"Falta el nombre\"}");
            return;
        }

        // Guardar en base de datos
        try (Connection conn = ConexionDB.conectar()) {
            String sql = "INSERT INTO usuarios (nombre) VALUES (?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                stmt.executeUpdate();
            }
            response.setStatus(HttpServletResponse.SC_OK);
            out.print("{\"mensaje\": \"Nombre guardado correctamente\"}");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"Error en la base de datos\"}");
            e.printStackTrace();
        }
    }
}