// 📌 Función para buscar un usuario por ID
async function buscarUsuario(event) {
    event.preventDefault(); // Evita la recarga de la página

    let id = document.getElementById("idBuscar").value.trim();
    console.log("🔍 ID ingresado:", id); // <-- Verifica si el ID se está capturando

    if (id === "") {
        alert("⚠️ Por favor, ingresa un ID válido.");
        return;
    }

    try {
        let respuesta = await fetch(`https://nohaydossintres.onrender.com/FormularioServlet?id=${id}`, {
            method: "GET",
            headers: { "Content-Type": "application/json" }
        });

        if (!respuesta.ok) {
            throw new Error(`HTTP error! Status: ${respuesta.status}`);
        }

        let resultadoTexto = await respuesta.text();
        if (!resultadoTexto || resultadoTexto.trim() === "") {
            throw new Error("❌ Respuesta vacía del servidor.");
        }

        let usuario = JSON.parse(resultadoTexto);
        console.log("✅ Usuario encontrado:", usuario); // <-- Asegúrate de que recibe datos

        let resultadoBusqueda = document.getElementById("resultadoBusqueda");

        if (usuario.error) {
            resultadoBusqueda.innerHTML = `<p style="color:red;">❌ ${usuario.error}</p>`;
        } else {
            resultadoBusqueda.innerHTML = `
                <div class="alert alert-success">
                    <strong>✅ ID:</strong> ${usuario.id} <br>
                    <strong>👤 Nombre:</strong> ${usuario.nombre}
                </div>`;
        }

    } catch (error) {
        console.error("❌ Error en buscarUsuario():", error);
        alert("Hubo un problema al buscar el usuario.");
    }
}

// 📌 Asegurar que el evento de búsqueda se registre correctamente
document.addEventListener("DOMContentLoaded", function () {
    let formBuscar = document.getElementById("formBuscar");
    if (formBuscar) {
        formBuscar.addEventListener("submit", buscarUsuario);
    }
});
