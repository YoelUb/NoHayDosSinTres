async function eliminarUsuario(event) {
    event.preventDefault(); // Evita la recarga de la página

    let id = document.getElementById("id").value.trim(); // ✅ Obtener ID
    if (id === "") {
        alert("Por favor, ingresa un ID válido.");
        return;
    }

    let baseURL = window.location.origin.includes("localhost")
        ? "http://localhost:8080"
        : "https://nohaydossintres.onrender.com";

    let url = `${baseURL}/FormularioServlet`;

    let datos = {
        action: "delete",  // 🔹 Identificamos que es una solicitud de eliminación
        id: id
    };

    try {
        console.log(`🗑️ Enviando solicitud POST a: ${url} para eliminar usuario con ID: ${id}`);

        let respuesta = await fetch(url, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(datos),
        });

        let resultadoTexto = await respuesta.text();
        console.log("📌 Respuesta del servidor (RAW):", resultadoTexto);

        let resultado = JSON.parse(resultadoTexto);

        if (respuesta.ok) {
            alert(resultado.mensaje || "✅ Usuario eliminado correctamente.");
        } else {
            alert(resultado.error || "❌ No se pudo eliminar el usuario.");
        }

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
        console.error("⚠️ Formulario de eliminación no encontrado en el DOM.");
    }
});
