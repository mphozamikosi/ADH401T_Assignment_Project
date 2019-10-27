package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import controller.TweetController;
import twitter4j.TwitterException;

@Path("/")
@Stateless
public class RestService {

	@EJB
	TweetEJB tweetEJB;

	@GET
	@Path("/tweets")
	@Produces("application/json")
	public Response getTweets() {
		
		return Response
				.ok()
				.entity(tweetEJB.getTweets())
				.build();

	}

	@POST
	@Path("/create")
	@Produces("application/json")
	public Response create(@QueryParam("body") String body) {

		String response = null;

		tweetEJB.create(body);

		try {

			TweetController tweetController = new TweetController();
			
			response = tweetController.post(body);

		} catch (TwitterException e) {

			e.printStackTrace();

		}
		
		return Response
				.ok()
				.entity(response)
				.build();

	}
	
}