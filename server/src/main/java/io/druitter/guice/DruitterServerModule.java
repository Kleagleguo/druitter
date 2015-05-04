package io.druitter.guice;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.spi.container.servlet.WebConfig;
import io.druitter.BackdoorResource;

import javax.servlet.ServletException;
import java.util.Map;
import java.util.Set;

/**
 * Druitter
 */
public class DruitterServerModule extends JerseyServletModule
{

  @Override
  protected void configureServlets()
  {
    Binder binder = binder();
    binder.bind(GuiceContainer.class).to(DruitterGuiceContainer.class);
    binder.bind(DruitterGuiceContainer.class).in(Singleton.class);

    serve("/*").with(DruitterGuiceContainer.class);

    Multibinder multibinder = Multibinder.newSetBinder(
        binder, new TypeLiteral<Class<?>>()
        {
        }, DruitterResources.class
    );
    multibinder.addBinding().toInstance(BackdoorResource.class);
  }

  public static class DruitterGuiceContainer extends GuiceContainer
  {

    private final Set<Class<?>> resources;

    @Inject
    public DruitterGuiceContainer(Injector injector, @DruitterResources Set<Class<?>> resources)
    {
      super(injector);
      this.resources = resources;
    }

    @Override
    protected ResourceConfig getDefaultResourceConfig(Map<String, Object> props, WebConfig webConfig)
        throws ServletException
    {
      return new DefaultResourceConfig(this.resources);
    }
  }
}
