async function eliminarUsuario(event) {
    event.preventDefault();

    let id = document.getElementById("id").value.trim();
    if (id === "") {
        alert("Por favor, ingresa un ID válido.");
        return;
    }

    let baseURL = window.location.origin.includes("localhost")
        ? "http://localhost:8080"
        : "https://nohaydossintres.onrender.com";

    let url = `${baseURL}/FormularioServlet?id=${id}`;

    try {
        let respuesta = await fetch(url, {
            method: "DELETE",
            headers: { "Content-Type": "application/json" },
        });

        let resultadoTexto = await respuesta.text();
        if (!resultadoTexto || resultadoTexto.trim() === "") {
            throw new Error("Respuesta vacía del servidor.");
        }

        let resultado = JSON.parse(resultadoTexto);

        if (respuesta.ok) {
            alert(resultado.mensaje || "✅ Usuario eliminado correctamente.");
        } else {
            alert(resultado.error || "❌ No se pudo eliminar el usuario.");
        }

        if (typeof obtenerUsuarios === "function") {
            obtenerUsuarios(); // Actualiza la lista de usuarios después de eliminar
        }

    } catch (error) {
        console.error("❌ Error en la solicitud:", error);
        alert("Hubo un problema al eliminar el usuario.");
    }
}

// 📌 Asegurar que el evento se registre correctamente
document.addEventListener("DOMContentLoaded", function () {
    let btnEliminar = document.getElementById("btnEliminar");
    if (btnEliminar) {
        btnEliminar.addEventListener("click", eliminarUsuario);
    }
});
