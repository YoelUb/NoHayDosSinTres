package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/FormularioServlet")
public class FormularioServlet extends HttpServlet {

    // 📌 Método POST: Registrar usuario
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        JSONObject jsonRequest = new JSONObject(sb.toString());
        String nombre = jsonRequest.optString("nombre", "").trim();
        JSONObject jsonResponse = new JSONObject();

        if (nombre.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            jsonResponse.put("error", "⚠ El nombre es obligatorio.");
            response.getWriter().write(jsonResponse.toString());
            return;
        }

        try (Connection con = ConexionDB.conectar()) {
            String sql = "INSERT INTO usuarios (Nombre) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                jsonResponse.put("mensaje", "✅ Usuario registrado correctamente.");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                jsonResponse.put("error", "❌ No se pudo registrar el usuario.");
            }
            response.getWriter().write(jsonResponse.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            jsonResponse.put("error", "❌ Error en la base de datos.");
            response.getWriter().write(jsonResponse.toString());
        }
    }

    // 📌 Método GET: Listar usuarios o buscar usuario por ID
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        JSONObject jsonResponse = new JSONObject();

        try (Connection con = ConexionDB.conectar()) {
            if (idStr != null && !idStr.trim().isEmpty()) {
                // 🔹 Buscar un usuario por ID
                String sql = "SELECT * FROM usuarios WHERE ID = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(idStr));
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    JSONObject usuario = new JSONObject();
                    usuario.put("id", rs.getInt("ID"));
                    usuario.put("nombre", rs.getString("Nombre"));

                    System.out.println("🔍 Usuario encontrado: " + usuario.toString());
                    response.getWriter().write(usuario.toString());

                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    jsonResponse.put("error", "⚠ Usuario no encontrado.");
                    response.getWriter().write(jsonResponse.toString());
                    System.out.println("⚠ Usuario con ID " + idStr + " no encontrado.");
                }

            } else {
                // 🔹 Listar todos los usuarios
                JSONArray usuariosArray = new JSONArray();
                String sql = "SELECT * FROM usuarios";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    JSONObject usuario = new JSONObject();
                    usuario.put("id", rs.getInt("ID"));
                    usuario.put("nombre", rs.getString("Nombre"));
                    usuariosArray.put(usuario);
                }

                System.out.println("📋 Lista de usuarios enviada.");
                response.getWriter().write(usuariosArray.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            jsonResponse.put("error", "❌ Error al obtener usuarios.");
            response.getWriter().write(jsonResponse.toString());
        }
    }

    // 📌 Método DELETE: Eliminar usuario por ID
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        JSONObject jsonResponse = new JSONObject();

        if (idStr == null || idStr.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            jsonResponse.put("error", "⚠ ID inválido para eliminación.");
            response.getWriter().write(jsonResponse.toString());
            return;
        }

        try (Connection con = ConexionDB.conectar()) {
            String sql = "DELETE FROM usuarios WHERE ID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(idStr));
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                jsonResponse.put("mensaje", "✅ Usuario eliminado correctamente.");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                jsonResponse.put("error", "⚠ Usuario no encontrado.");
            }
            response.getWriter().write(jsonResponse.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            jsonResponse.put("error", "❌ Error en la base de datos al eliminar el usuario.");
            response.getWriter().write(jsonResponse.toString());
        }
    }
}
