package org.eclipse.jetty.example;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.servlet.ServletHandler;

public class JettyEmbedded
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server();
        Connector connector = new SocketConnector();
        connector.setPort(8080);
        server.setConnectors(new Connector[]
        { connector });

        ErrorHandler errorHandler = new ErrorHandler();
        errorHandler.setShowStacks(true);
        server.addBean(errorHandler);
        
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(NaughtyServlet.class.getName(),"/");

        server.setHandler(servletHandler);

        server.start();
        server.join();
    }
}
