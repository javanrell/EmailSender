package com.primerajunta.emailsender.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * VM arguments:
 * 
 * -Dapplication.properties=classpath:/config/app.properties -Xmx512m -Xms64m -javaagent:lib/devel/instrument-agent.jar=com.livra.connect.config.ClassInstrumentor
 * 
 * */
public class Launcher {

    public static void main(String[] args) throws Exception {
		ApplicationContext context = 
		          new AnnotationConfigApplicationContext("com.primerajunta.emailsender");
        new WebApplicationLauncher()
        	.addContext("/", "src/webapp", "src/webapp/context.xml")
            .setPort(args.length > 0 ? Integer.parseInt(args[0]): 8080)
            .setAjpPort(args.length > 1 ? Integer.parseInt(args[1]): null)
            .launch();
    }
}