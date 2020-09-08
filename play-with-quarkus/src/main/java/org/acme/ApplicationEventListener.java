package org.acme;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ApplicationEventListener {


    private static Logger logger = Logger.getLogger(ApplicationEventListener.class);

    public void onStart1(@Observes StartupEvent event) {
        //...
        logger.info("Application starting1...");
    }

    public void onStart2(@Observes StartupEvent event) {
        //...
        logger.info("Application starting2...");
    }

    public void onStop(@Observes ShutdownEvent event) {
        //..
        logger.info("Application shutting down...");
    }

}
