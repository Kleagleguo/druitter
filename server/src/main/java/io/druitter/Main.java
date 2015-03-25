package io.druitter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import io.druitter.guice.DruitterServerModule;
import io.druitter.guice.JacksonModule;
import io.druitter.guice.Twitter4JModule;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * Druitter
 */
public class Main
{

  public static void main(String args[])
  {

    Injector injector = Guice.createInjector(new DruitterServerModule(), new Twitter4JModule(), new JacksonModule());

    Server server = new Server(8080);

    ServletContextHandler servletContextHandler = new ServletContextHandler(
        server,
        "/",
        ServletContextHandler.SESSIONS
    );
    servletContextHandler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));

    servletContextHandler.addServlet(DefaultServlet.class, "/");
    try {
      server.start();
      server.join();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
