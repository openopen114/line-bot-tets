package model;

import java.util.List;

public class ReployMessage { 
	
	public String replyToken;
	public List<Message> messages; 
	
	
	
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public String getReplyToken() {
		return replyToken;
	}
	public void setReplyToken(String replyToken) {
		this.replyToken = replyToken;
	}
	
	
	
	
	
	

}



