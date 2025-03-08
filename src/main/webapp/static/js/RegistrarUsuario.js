async function registrarUsuario(event) {
    event.preventDefault(); // Evita la recarga de la página

    let nombre = document.getElementById("nombre").value;
    if (nombre.trim() === "") {
        alert("Por favor, ingresa un nombre válido.");
        return;
    }

    let datos = { nombre: nombre };

    try {
        let respuesta = await fetch('https://nohaydossintres.onrender.com/FormularioServlet', {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(datos)
        });

        let contentType = respuesta.headers.get("content-type");

        if (!respuesta.ok) {
            throw new Error(`Error ${respuesta.status}: ${respuesta.statusText}`);
        }

        // Si la respuesta es JSON, la parseamos
        if (contentType && contentType.includes("application/json")) {
            let resultado = await respuesta.json();
            alert(resultado.mensaje || "Usuario registrado correctamente.");
        } else {

            let texto = await respuesta.text();
            console.error("Respuesta no esperada:", texto);
            alert("Error inesperado en el servidor.");
        }

    } catch (error) {
        console.error("Error en la solicitud:", error);
        alert("Hubo un problema al registrar el usuario.");
    }
}

// Asegurar que el script se ejecuta después de que se cargue el DOM
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("formRegistro").addEventListener("submit", registrarUsuario);
});
