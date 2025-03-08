async function registrarUsuario(event) {
    event.preventDefault(); // Evita la recarga de la página

    let nombre = document.getElementById("nombre").value.trim();
    if (nombre === "") {
        alert("Por favor, ingresa un nombre válido.");
        return;
    }

    let datos = { nombre: nombre };

    // 🔹 Determinar la URL del servidor (local o en Render)
    let baseURL = window.location.origin.includes("localhost")
        ? "http://localhost:8080"
        : "https://nohaydossintres.onrender.com";

    let url = `${baseURL}/FormularioServlet`;

    try {
        console.log(`📡 Enviando solicitud a: ${url}`);
        console.log("📨 Datos enviados:", datos);

        let respuesta = await fetch(url, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(datos),
        });

        let contentType = respuesta.headers.get("content-type");

        if (!respuesta.ok) {
            throw new Error(`Error ${respuesta.status}: ${respuesta.statusText}`);
        }

        if (contentType && contentType.includes("application/json")) {
            let resultado = await respuesta.json();
            console.log("✅ Respuesta del servidor:", resultado);
            alert(resultado.mensaje || "Usuario registrado correctamente.");
        } else {
            let texto = await respuesta.text();
            console.error("⚠️ Respuesta inesperada:", texto);
            alert("Error inesperado en el servidor.");
        }

    } catch (error) {
        console.error("❌ Error en la solicitud:", error);
        alert("Hubo un problema al registrar el usuario. Revisa la consola para más detalles.");
    }
}

// 🔹 Asegurar que el script se ejecuta después de que se cargue el DOM
document.addEventListener("DOMContentLoaded", function () {
    let form = document.getElementById("formRegistro");
    if (form) {
        form.addEventListener("submit", registrarUsuario);
    } else {
        console.error("⚠️ Formulario no encontrado en el DOM.");
    }
});
