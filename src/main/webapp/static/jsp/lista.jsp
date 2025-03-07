<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*, servidor.ConexionDB" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Invitados</title>
</head>
<body>
<h2>Lista de Invitados</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Acción</th>
    </tr>
    <%
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = ConexionDB.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM invitados");

            while (rs.next()) {
    %>
    <tr>
        <td><%= rs.getInt("ID") %></td>
        <td><%= rs.getString("Nombre") %></td>
        <td>
            <form action="FormularioServlet" method="post">
                <input type="hidden" name="id" value="<%= rs.getInt("ID") %>">
                <input type="hidden" name="action" value="baja">
                <button type="submit">Eliminar</button>
            </form>
        </td>
    </tr>
    <%
        }
    } catch (Exception e) {
    %>
    <tr>
        <td colspan="3">Error: <%= e.getMessage() %></td>
    </tr>
    <%
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    %>
</table>
</body>
</html>
