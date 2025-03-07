<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Eliminar Invitado</title>
</head>
<body>
<h2>Eliminar Invitado</h2>
<form action="FormularioServlet" method="post">
    <label for="id">ID del Invitado:</label>
    <input type="number" id="id" name="id" required>
    <input type="hidden" name="action" value="baja">
    <button type="submit">Eliminar</button>
</form>
</body>
</html>
