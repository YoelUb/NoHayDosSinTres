// 📌 Función para obtener y mostrar todos los usuarios en la tabla
async function obtenerUsuarios(event) {
    if (event) event.preventDefault(); // Evita la recarga de la página

    try {
        let respuesta = await fetch("https://nohaydossintres.onrender.com/FormularioServlet", {
            method: "GET",
            headers: { "Content-Type": "application/json" }
        });

        // 🔹 Verificar si la respuesta es exitosa
        if (!respuesta.ok) {
            throw new Error(`HTTP error! Status: ${respuesta.status}`);
        }

        let usuariosTexto = await respuesta.text();
        console.log("🔍 Respuesta obtenida (RAW):", usuariosTexto);

        let usuarios;
        try {
            usuarios = JSON.parse(usuariosTexto);
            console.log("🔍 Usuarios parseados:", usuarios);
        } catch (error) {
            console.error("❌ Error al convertir JSON:", error);
            alert("Error en la respuesta del servidor. Verifica la consola.");
            return;
        }

        let tablaUsuarios = document.getElementById("tablaUsuarios");
        tablaUsuarios.innerHTML = ""; // Limpiar la tabla antes de agregar nuevos datos

        if (!usuarios || usuarios.length === 0) {
            tablaUsuarios.innerHTML = `<tr><td colspan="2" class="text-center">❌ No hay usuarios registrados.</td></tr>`;
            return;
        }

        usuarios.forEach(usuario => {
            let fila = document.createElement("tr");
            fila.innerHTML = `
                <td>${usuario.id}</td>
                <td>${usuario.nombre}</td>
            `;
            tablaUsuarios.appendChild(fila);
        });

    } catch (error) {
        console.error("❌ Error en obtenerUsuarios():", error);
        alert("Hubo un problema al obtener la lista de usuarios.");
    }
}

// 📌 Asegurar que los eventos se registren correctamente
document.addEventListener("DOMContentLoaded", function () {
    let btnObtenerUsuarios = document.getElementById("btnObtenerUsuarios");
    if (btnObtenerUsuarios) {
        btnObtenerUsuarios.addEventListener("click", obtenerUsuarios);
    }
});
