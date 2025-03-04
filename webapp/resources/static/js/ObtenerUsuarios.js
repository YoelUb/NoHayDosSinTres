async function obtenerUsuarios(event) {
    event.preventDefault(); // Evita la recarga de la página

    try {
        let respuesta = await fetch("/api/getUsers", {
            method: "GET",
            headers: { "Content-Type": "application/json" }
        });

        let usuarios = await respuesta.json();
        let listaUsuarios = document.getElementById("listaUsuarios");
        listaUsuarios.innerHTML = "";

        if (usuarios.length === 0) {
            listaUsuarios.innerHTML = "<p>No hay usuarios registrados.</p>";
            return;
        }

        let ul = document.createElement("ul");
        usuarios.forEach(usuario => {
            let li = document.createElement("li");
            li.textContent = usuario.nombre;
            ul.appendChild(li);
        });
        listaUsuarios.appendChild(ul);
    } catch (error) {
        console.error("Error en la solicitud:", error);
        alert("Hubo un problema al obtener la lista de usuarios.");
    }
}

// Asegurar que el script se ejecuta después de que se cargue el DOM
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("btnObtenerUsuarios").addEventListener("click", obtenerUsuarios);
});
