package org.eclipse.jetty.example;

import java.io.File;

import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.toolchain.test.MavenTestingUtils;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyEmbedded
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server();
        Connector connector = new SocketConnector();
        connector.setPort(8080);
        server.setConnectors(new Connector[]
        { connector });
        
        // New Session/Login requirement
        File realmFile = MavenTestingUtils.getTestResourceFile("realm.properties");
        HashLoginService login = new HashLoginService();
        login.setName("Test Realm");
        login.setConfig(realmFile.getAbsolutePath());
        server.addBean(login);

        // Add error handler bean
        ErrorHandler errorHandler = new ErrorHandler();
        errorHandler.setShowStacks(true);
        server.addBean(errorHandler);
        
        // Add webapp
        File war = MavenTestingUtils.getTargetFile("error-page-from-jsp.war");

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(war.getAbsolutePath());
        webapp.setServer(server);
        
        server.setHandler(webapp);
        
        server.start();
        server.join();
    }
}
