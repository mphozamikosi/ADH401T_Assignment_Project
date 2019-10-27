package controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.Tweet;
import model.TweetEntity;
import service.TweetEJB;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@ManagedBean(name="tweetController")
@SessionScoped
public class TweetController {

	private static final String CUST_KEY = "Cj1QTEg5djMJp1C7cga82Vifs";
	private static final String CUST_SECRET = "YxyR1n3jMVPBUZwbMRVS3W3zXlWtMaETlgWtkrV5f0dYFK8P3p";
	private static final String ACC_TOKEN = "847925174859165698-qN5QMgPmcVtPLzascUvXH4dgONBesLj";
	private static final String ACC_TOKEN_SECRET = "j6zZZVpMojlEWDYZRjgrYNAziHgrx3jKuogQ7zSZuN6mT";
	private static final String TWITTER_HANDLE = "@AdhLecturer";

	@EJB
	TweetEJB tweetEJB;
	
	@ManagedProperty(value = "#{tweet}")
	private Tweet tweet;

	public Tweet getTweet() {
		return tweet;
	}

	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}

	public String post(String body) throws TwitterException {

    	ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

    	configurationBuilder.setDebugEnabled(true)
    	  .setOAuthConsumerKey(CUST_KEY)
    	  .setOAuthConsumerSecret(CUST_SECRET)
    	  .setOAuthAccessToken(ACC_TOKEN)
    	  .setOAuthAccessTokenSecret(ACC_TOKEN_SECRET);

    	TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());

    	Twitter twitter = twitterFactory.getInstance();    	

    	if (!body.startsWith(TWITTER_HANDLE))
    		
    		body = TWITTER_HANDLE + " " + body;
    	
		Status status = twitter.updateStatus(body);

		return status.getText();

	}
	
	public void create() {

		create(tweet.getEntity().getBody());
		
	}
	
	public void create(String body) {

		TweetEntity tweetEntity = tweet.getEntity();

		tweetEntity.setBody(body);
		
		tweetEJB.create(tweetEntity);

		try {

			post(body);

		} catch (TwitterException e) {

			e.printStackTrace();

		}
		
	}
	
	public List<TweetEntity> getTweets() {
	
		return tweetEJB.getTweets();
		
	}

}