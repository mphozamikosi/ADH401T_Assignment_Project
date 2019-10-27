package model;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="tweet")
@SessionScoped
public class Tweet {

	private long id;
	private Date timeStamp;
	private String body;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public TweetEntity getEntity() {
		
		TweetEntity tweetEntity = new TweetEntity();

		tweetEntity.setId(id);
		tweetEntity.setTimeStamp(timeStamp);
		tweetEntity.setBody(body);
		
		return tweetEntity;
		
	}

}