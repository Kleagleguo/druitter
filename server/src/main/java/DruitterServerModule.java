import com.google.inject.Binder;
import com.google.inject.Singleton;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

/**
 * Druitter
 */
public class DruitterServerModule extends JerseyServletModule {

  @Override
  protected void configureServlets() {
    System.out.println("here");
    Binder binder = binder();
    binder.bind(GuiceContainer.class);
    serve("/*").with(GuiceContainer.class);

    binder.bind(BackdoorResource.class).in(Singleton.class);
  }
}
