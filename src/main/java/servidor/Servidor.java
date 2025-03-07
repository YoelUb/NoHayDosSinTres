package servidor;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Servidor {
    public static void main(String[] args) {
        // Obtener el puerto desde la variable de entorno PORT
        String port = System.getenv("PORT");

        // Si la variable de entorno no está configurada, usar el puerto 8080 por defecto
        int serverPort = (port != null) ? Integer.parseInt(port) : 8080;

        // Crear el servidor en el puerto obtenido
        Server server = new Server(serverPort);

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
            System.out.println("Servidor iniciado en http://localhost:" + serverPort);
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al iniciar el servidor.");
        }
    }
}
