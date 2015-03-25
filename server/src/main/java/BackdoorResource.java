import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Druitter
 */

@Path("/backdoor")
public class BackdoorResource {

  @GET
  @Produces("text/plain")
  public Response doGet() {
    System.out.println("|| do get");
    return Response.ok("You've reached the backdoor").build();
  }

}
