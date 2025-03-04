package servidor;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Servidor {

    public static void main(String[] args) {
        // Crear el servidor en el puerto 8080
        Server server = new Server(8080);

        // Configurar los servlets
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Registrar el servlet del formulario
        context.addServlet(new ServletHolder(new FormularioServlet()), "/formulario");

        try {
            // Iniciar el servidor
            server.start();
            System.out.println("Servidor iniciado en http://localhost:8080");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}