package servidor;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.util.resource.Resource;

public class Servidor {
    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        Server server = new Server(port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setBaseResource(Resource.newResource("/var/lib/jetty/webapps/static"));
        context.addServlet(DefaultServlet.class, "/");

        server.setHandler(context);

        System.out.println("🚀 Servidor Jetty corriendo en el puerto: " + port);
        server.start();
        server.join();
    }
}
