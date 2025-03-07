async function eliminarUsuario(event) {
    event.preventDefault(); // Evita la recarga de la página

    let id = document.getElementById("id").value; // ✅ Obtener ID
    if (id.trim() === "") {
        alert("Por favor, ingresa un ID válido.");
        return;
    }

    try {
        let respuesta = await fetch(`/FormularioServlet?id=${id}`, {
            method: "DELETE"
        });

        let resultadoTexto = await respuesta.text(); // 🔹 Leer la respuesta como texto
        console.log("Respuesta del servidor (RAW):", resultadoTexto);

        let resultado = JSON.parse(resultadoTexto); // 🔹 Intentar convertir a JSON

        alert(resultado.mensaje || "Usuario eliminado correctamente.");

        // 🔹 Si hay error en `obtenerUsuarios()`, lo detectamos
        if (typeof obtenerUsuarios === "function") {
            console.log("Recargando lista de usuarios...");
            obtenerUsuarios();
        }

    } catch (error) {
        console.error("Error en la solicitud:", error);
        alert("Hubo un problema al eliminar el usuario.");
    }
}

// Asegurar que el evento se registre correctamente
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("formEliminar").addEventListener("submit", eliminarUsuario);
});
