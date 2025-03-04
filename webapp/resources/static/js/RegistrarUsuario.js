async function registrarUsuario(event) {
    event.preventDefault(); // Evita la recarga de la página

    let nombre = document.getElementById("nombre").value;
    if (nombre.trim() === "") {
        alert("Por favor, ingresa un nombre válido.");
        return;
    }

    let datos = { nombre: nombre };

    try {
        let respuesta = await fetch("/api/registerUser", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(datos)
        });

        let resultado = await respuesta.json();
        alert(resultado.mensaje || "Usuario registrado correctamente.");
    } catch (error) {
        console.error("Error en la solicitud:", error);
        alert("Hubo un problema al registrar el usuario.");
    }
}

// Asegurar que el script se ejecuta después de que se cargue el DOM
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("formRegistro").addEventListener("submit", registrarUsuario);
});
