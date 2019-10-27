package service;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.TweetEntity;

@Stateless
@LocalBean
public class TweetEJB {

	@PersistenceContext
	private EntityManager entityManager; 
	
    public TweetEJB() {
    	
    }

	public void create(String body) {

		TweetEntity tweetEntity = new TweetEntity();
		
    	tweetEntity.setTimeStamp(new Date());

		tweetEntity.setBody(body);
    	
    	entityManager.persist(tweetEntity);
    	
    }

	public void create(TweetEntity tweetEntity) {

    	tweetEntity.setTimeStamp(new Date());
    	
    	entityManager.persist(tweetEntity);
    	
    }
	 
	public List<TweetEntity> getTweets() {
		
		return entityManager.createQuery("SELECT t FROM tweets t", TweetEntity.class).getResultList();
		
	}
	
}