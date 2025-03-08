async function eliminarUsuario(event) {
    event.preventDefault(); // Evita la recarga de la página

    let id = document.getElementById("id").value.trim(); // ✅ Obtener y limpiar el ID
    if (id === "") {
        alert("Por favor, ingresa un ID válido.");
        return;
    }

    try {
        let respuesta = await fetch(`https://nohaydossintres.onrender.com/FormularioServlet?id=${id}`, {
            method: "DELETE"
        });

        let resultadoTexto = await respuesta.text(); // 🔹 Leer la respuesta como texto
        console.log("📌 Respuesta del servidor (RAW):", resultadoTexto);

        let resultado = JSON.parse(resultadoTexto); // 🔹 Intentar convertir a JSON

        if (respuesta.ok) {
            alert(resultado.mensaje || "✅ Usuario eliminado correctamente.");
        } else {
            alert(resultado.error || "❌ No se pudo eliminar el usuario.");
        }

        // 🔹 Si `obtenerUsuarios` existe, actualizar la lista
        if (typeof obtenerUsuarios === "function") {
            console.log("🔄 Recargando lista de usuarios...");
            obtenerUsuarios();
        }

    } catch (error) {
        console.error("❌ Error en la solicitud:", error);
        alert("Hubo un problema al eliminar el usuario.");
    }
}

// 📌 Asegurar que el evento se registre correctamente
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("formEliminar").addEventListener("submit", eliminarUsuario);
});
