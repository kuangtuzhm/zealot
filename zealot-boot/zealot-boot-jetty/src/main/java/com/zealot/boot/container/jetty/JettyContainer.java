package com.zealot.boot.container.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zealot.boot.container.Container;


/**
 * JettyContainer. (SPI, Singleton, ThreadSafe)
 * 
 */
public class JettyContainer implements Container {

    private static final Logger logger = LoggerFactory.getLogger(JettyContainer.class);

    public static final String DEFAULT_SPRING_CONFIG = "classpath*:META-INF/zealot/zealot-boot-jetty.xml";

    static ClassPathXmlApplicationContext context;
    
	private static long startTime = System.currentTimeMillis();
    
    public static ClassPathXmlApplicationContext getContext() {
		return context;
	}

	public void start() {
        String configPath = DEFAULT_SPRING_CONFIG;
        context = new ClassPathXmlApplicationContext(configPath.split("[,\\s]+"));
        
        WebAppContext webAppContext = context.getBean("webAppContext", WebAppContext.class);
        webAppContext.setMaxFormContentSize(-1);
  
        logger.warn("Start jetty web context context= " + webAppContext.getContextPath() 
        		+ ";resource base=" + webAppContext.getResourceBase());
        startTime = System.currentTimeMillis();
        try {
            Server server = context.getBean("jettyServer", Server.class);
            server.start();
            server.join();
            logger.warn("启动成功");
        } catch (Exception e) {
        	logger.error("Failed to start jetty server on " + ":" + ", cause: " + e.getMessage(), e);
        }
    }

	public void stop() {
        try {
        	
            if (context != null) {
                context.stop();
                context.close();
                context = null;
            }
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }

	@Override
	public String getName() {
		return "jetty";
	}

}