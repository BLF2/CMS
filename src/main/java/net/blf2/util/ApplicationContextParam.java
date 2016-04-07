package net.blf2.util;

import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by blf2 on 16-4-7.
 * 获得web.xml中的参数
 */
public class ApplicationContextParam implements ServletContextListener{

    private static WebApplicationContext webApplicationContext;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        webApplicationContext = (WebApplicationContext) servletContextEvent.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
