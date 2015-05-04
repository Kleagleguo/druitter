package io.druitter.guice;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import io.druitter.TwitterResource;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 * Druitter
 */
public class Twitter4JModule implements Module
{

  public void configure(Binder binder)
  {
    Multibinder multibinder = Multibinder.newSetBinder(
        binder, new TypeLiteral<Class<?>>()
    {
    }, DruitterResources.class
    );
    multibinder.addBinding().toInstance(TwitterResource.class);
  }

  @Provides
  @Singleton
  public Twitter getTwitter()
  {
    return TwitterFactory.getSingleton();
  }
}
