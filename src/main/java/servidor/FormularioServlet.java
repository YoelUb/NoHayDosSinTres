/*
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
*/
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

@WebServlet("/FormularioServlet")
public class FormularioServlet<HttpServletRequest, HttpServletResponse> extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FormularioServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("alta".equals(action)) {
            registrarInvitado(request, response);
        } else if ("baja".equals(action)) {
            eliminarInvitado(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listarInvitados(request, response);
    }

    private void registrarInvitado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");

        try (Connection con = ConexionDB.getConnection()) {
            String sql = "INSERT INTO invitados (Nombre) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("lista.jsp");
    }

    private void eliminarInvitado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        try (Connection con = ConexionDB.getConnection()) {
            String sql = "DELETE FROM invitados WHERE ID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("lista.jsp");
    }

    private void listarInvitados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection con = ConexionDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM invitados")) {

            request.setAttribute("resultSet", rs);
            request.getRequestDispatcher("lista.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp");
        }
    }
}
*/