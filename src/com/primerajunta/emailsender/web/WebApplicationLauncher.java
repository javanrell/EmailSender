package com.primerajunta.emailsender.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.NCSARequestLog;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.ajp.Ajp13SocketConnector;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.handler.RequestLogHandler;
import org.mortbay.jetty.security.HashUserRealm;
import org.mortbay.jetty.security.UserRealm;
import org.mortbay.jetty.webapp.WebAppContext;

public class WebApplicationLauncher {

	private List<WebAppContext> contexts = new ArrayList<WebAppContext>();
    private int port = 8080;
    private UserRealm userRealm = null;
    private String logPath = null;
    private Integer ajpPort = null;

    public WebApplicationLauncher setPort(int port) {
        this.port = port;
        return this;
    }
    
    public WebApplicationLauncher setAjpPort(Integer port) {
        this.ajpPort = port;
        return this;
    }
    
    public WebApplicationLauncher addContext(String contextPath, String webappPath, String descriptor) {
        WebAppContext context = new WebAppContext();
        context.setContextPath(contextPath);
        context.setWar(webappPath);
        context.setDescriptor(descriptor);
        contexts.add(context);
        return this;
    }
    
    private WebAppContext getUniqueContext() {
    	WebAppContext context;
    	if (contexts.isEmpty())
    		contexts.add(context = new WebAppContext());
    	else
    		context = contexts.get(0); 
    	return context;
    }

    @Deprecated
    public WebApplicationLauncher setContextPath(String contextPath) {
    	getUniqueContext().setContextPath(contextPath);
        return this;
    }

    @Deprecated
    public WebApplicationLauncher setWebappPath(String webappPath) {
    	getUniqueContext().setWar(webappPath);
        return this;
    }
    
    @Deprecated
    public WebApplicationLauncher setDescriptor(String descriptor) {
    	getUniqueContext().setDescriptor(descriptor);
		return this;
	}
        
    public WebApplicationLauncher setUserRealm(String name, String configPath) throws IOException {
        userRealm = new HashUserRealm(name, configPath);
        return this;
    }

    public WebApplicationLauncher logRequests(String logPath) {
        this.logPath = logPath;
        return this;
    }

    public void launch() {
        Server server = new Server();
        setConnector(server);
        addContexts(server);
        setUserRealm(server);
        setLogRequests(server);
        run(server);
    }

    private void setConnector(Server server) {
        List<SocketConnector> connectors = new ArrayList<SocketConnector>();

    	SocketConnector connector = new SocketConnector();
        connector.setPort(port);
        connectors.add(connector);
                
        if(ajpPort != null) {
        	SocketConnector ajpConnector = new Ajp13SocketConnector();
        	ajpConnector.setPort(ajpPort);
        	connectors.add(ajpConnector);
        }
        
        server.setConnectors(connectors.toArray(new Connector[]{}));
    }

    private void addContexts(Server server) {
    	for (WebAppContext context : contexts ) {
        	context.setServer(server);
	        server.addHandler(context);
        }
    }

    private void setUserRealm(Server server) {
        if (userRealm == null) { return; }
        server.setUserRealms(new UserRealm[] {userRealm });
    }

    private void setLogRequests(Server server) {
        if (logPath == null) { return; }
        RequestLogHandler requestLogHandler = new RequestLogHandler();
        NCSARequestLog requestLog = new NCSARequestLog(logPath);
        requestLog.setRetainDays(90);
        requestLog.setAppend(true);
        requestLog.setExtended(false);
        requestLog.setLogTimeZone("GMT");
        requestLogHandler.setRequestLog(requestLog);
        server.addHandler(requestLogHandler);
    }

    private void run(Server server) {
        try {
            long startTime = System.currentTimeMillis();
            System.out.println(">>> Iniciando web server");
            server.start();
            System.out.println("\n>>> Web server iniciado despues de " +
                (System.currentTimeMillis() - startTime) + " ms");
            System.out.println("\n>>> Presione cualquier tecla para finalizar ejecucion");
            while (System.in.available() == 0) {
                Thread.sleep(500);
            }
            server.stop();
            server.join();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
