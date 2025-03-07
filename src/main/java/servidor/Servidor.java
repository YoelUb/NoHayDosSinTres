package servidor;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Servidor {
    public static void main(String[] args) {
        // Obtener el puerto desde la variable de entorno PORT (Render asigna el puerto dinámicamente)
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));

        // Crear el servidor en el puerto obtenido
        Server server = new Server(port);

        // Configurar el contexto del servidor
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        // Configurar Jetty para servir archivos estáticos
        context.setResourceBase("webapp/resources/static");
        context.addServlet(DefaultServlet.class, "/");

        // Registrar FormularioServlet manualmente
        context.addServlet(new ServletHolder(new FormularioServlet()), "/FormularioServlet");

        // Asignar el contexto al servidor
        server.setHandler(context);

        try {
            // Iniciar el servidor
            server.start();
            System.out.println("Servidor iniciado en http://0.0.0.0:" + port);
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al iniciar el servidor.");
        }
    }
}
