package org.rypox.sample;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jan.pfeil
 *
 */
@ApplicationScoped
public class ApplicationLifecycleHook {
  private static final Logger LOG = LoggerFactory.getLogger(ApplicationLifecycleHook.class);

  public void init(@Observes @Initialized(ApplicationScoped.class) final Object init) {
    System.out.println("*********************");
    System.out.println("*********************");
    System.out.println("***** Startup *******");
    System.out.println("** pure primefaces **");
    System.out.println("*********************");
    System.out.println("*********************");
  }

  public void destroy(@Observes @Destroyed(ApplicationScoped.class) final Object init) {
    System.out.println("*********************");
    System.out.println("*********************");
    System.out.println("****** Finish  ******");
    System.out.println("** pure primefaces **");
    System.out.println("*********************");
    System.out.println("*********************");
    LOG.debug("application finished");

  }
}
