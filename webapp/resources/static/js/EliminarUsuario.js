async function eliminarUsuario(event) {
    event.preventDefault(); // Evita la recarga de la página

    let nombre = document.getElementById("nombre").value;
    if (nombre.trim() === "") {
        alert("Por favor, ingresa un nombre válido.");
        return;
    }

    let datos = { nombre: nombre };

    try {
        let respuesta = await fetch("/api/deleteUser", {
            method: "DELETE",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(datos)
        });

        let resultado = await respuesta.json();
        alert(resultado.mensaje || "Usuario eliminado correctamente.");
    } catch (error) {
        console.error("Error en la solicitud:", error);
        alert("Hubo un problema al eliminar el usuario.");
    }
}

// Asegurar que el script se ejecuta después de que se cargue el DOM
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("formEliminar").addEventListener("submit", eliminarUsuario);
});
