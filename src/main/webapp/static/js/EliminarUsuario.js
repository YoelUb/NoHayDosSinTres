async function eliminarUsuario(event) {
    event.preventDefault(); // Evita la recarga de la página

    let id = document.getElementById("id").value.trim(); // ✅ Obtener y limpiar el ID
    if (id === "") {
        alert("Por favor, ingresa un ID válido.");
        return;
    }

    // 🔹 Determinar la URL del servidor (local o en Render)
    let baseURL = window.location.origin.includes("localhost")
        ? "http://localhost:8080"
        : "https://nohaydossintres.onrender.com";

    let url = `${baseURL}/FormularioServlet?id=${id}`;

    try {
        console.log(`📡 Enviando solicitud DELETE a: ${url}`);

        let respuesta = await fetch(url, {
            method: "DELETE",
            headers: { "Content-Type": "application/json" },
        });

        let resultadoTexto = await respuesta.text(); // 🔹 Leer la respuesta como texto
        console.log("📌 Respuesta del servidor (RAW):", resultadoTexto);

        let resultado;
        try {
            resultado = JSON.parse(resultadoTexto);
        } catch (error) {
            console.error("❌ Error al convertir JSON:", error);
            alert("Error inesperado en el servidor. Verifica la consola.");
            return;
        }

        if (respuesta.ok) {
            alert(resultado.mensaje || "✅ Usuario eliminado correctamente.");
        } else {
            alert(resultado.error || "❌ No se pudo eliminar el usuario.");
        }

        // 🔄 Si `obtenerUsuarios` existe, actualizar la lista
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
    let formEliminar = document.getElementById("formEliminar");
    if (formEliminar) {
        formEliminar.addEventListener("submit", eliminarUsuario);
    } else {
        console.error("⚠️ Formulario para eliminar usuario no encontrado en el DOM.");
    }
});
