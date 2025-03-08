// 📌 Función para eliminar un usuario
async function eliminarUsuario(event) {
    event.preventDefault(); // Evita la recarga de la página

    let id = document.getElementById("idEliminar").value.trim();
    console.log("🔍 ID capturado para eliminación:", id); // <-- Verifica si el ID se está capturando

    if (id === "") {
        alert("⚠️ Por favor, ingresa un ID válido.");
        return;
    }

    let url = `https://nohaydossintres.onrender.com/FormularioServlet?id=${id}`;

    try {
        let respuesta = await fetch(url, {
            method: "DELETE",
            headers: { "Content-Type": "application/json" }
        });

        let resultadoTexto = await respuesta.text();
        console.log("🔍 Respuesta del servidor:", resultadoTexto); // <-- Verificar qué devuelve la API

        if (!resultadoTexto || resultadoTexto.trim() === "") {
            throw new Error("❌ Respuesta vacía del servidor.");
        }

        let resultado = JSON.parse(resultadoTexto);

        if (respuesta.ok) {
            alert(resultado.mensaje || "✅ Usuario eliminado correctamente.");
        } else {
            alert(resultado.error || "❌ No se pudo eliminar el usuario.");
        }

        if (typeof obtenerUsuarios === "function") {
            obtenerUsuarios(); // Refrescar la lista después de eliminar
        }

    } catch (error) {
        console.error("❌ Error en eliminarUsuario():", error);
        alert("Hubo un problema al eliminar el usuario.");
    }
}

// 📌 Asegurar que el evento de eliminación se registre correctamente
document.addEventListener("DOMContentLoaded", function () {
    let formEliminar = document.getElementById("formEliminar");
    if (formEliminar) {
        formEliminar.addEventListener("submit", eliminarUsuario);
    }
});
