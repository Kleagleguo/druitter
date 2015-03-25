package io.druitter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Druitter
 */
@Path("/search")
public class TwitterResource
{

  private Twitter twitter;
  private ObjectMapper objectMapper;

  @Inject
  public TwitterResource(Twitter twitter, ObjectMapper objectMapper)
  {
    this.twitter = twitter;
    this.objectMapper = objectMapper;
  }

  @GET
  @Path("{keyword}")
  public Response getTweetsByKeyword(@PathParam("keyword") String keyword) throws JsonProcessingException
  {
    Query query = new Query(keyword);
    try {
      QueryResult result = twitter.search(query);
      return Response.ok(objectMapper.writeValueAsString(result.getTweets())).build();
    }
    catch (TwitterException e) {
      return Response.ok(null).build();
    }
  }

  @GET
  @Path("/backdoor/secret")
  public Response getTweetsByKeyword1(@PathParam("id") String keyword)
  {
    return Response.ok("got it").build();
  }

}
