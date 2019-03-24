package com.alexandria.config;

import com.alexandria.persistence.PersistenceUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitialisationPersistence implements ServletContextListener {

    @Override
    public void contextInitialized( ServletContextEvent event ) {

        // Init persistence unit
        PersistenceUtils.init();
    }

    @Override
    public void contextDestroyed( ServletContextEvent event ) {

        // Persistence unit shutdown
        PersistenceUtils.shutdown();
    }
}