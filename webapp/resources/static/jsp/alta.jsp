<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Invitado</title>
</head>
<body>
<h2>Registrar Nuevo Invitado</h2>
<form action="FormularioServlet" method="post">
    <label for="nombre">Nombre del Invitado:</label>
    <input type="text" id="nombre" name="nombre" required>
    <input type="hidden" name="action" value="alta">
    <button type="submit">Registrar</button>
</form>
</body>
</html>
