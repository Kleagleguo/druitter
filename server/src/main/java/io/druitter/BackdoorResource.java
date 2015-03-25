package io.druitter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Druitter
 */

@Path("/backdoor")
public class BackdoorResource
{

  @GET
  @Produces("text/plain")
  public Response doGet()
  {
    return Response.ok("You've reached the backdoor").build();
  }

}
