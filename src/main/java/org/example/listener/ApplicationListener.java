package org.example.listener;

import org.example.pool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.INSTANCE.initializeConnections();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.INSTANCE.destroy();
    }
}
