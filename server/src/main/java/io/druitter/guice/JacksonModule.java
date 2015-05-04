package io.druitter.guice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;

/**
 * Druitter
 */
public class JacksonModule implements Module
{

  public void configure(Binder binder)
  {
  }

  @Provides
  public ObjectMapper getObjectMapper() {
    return new ObjectMapper();
  }
}
