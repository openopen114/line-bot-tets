package model;

import java.util.List;

public class PushMessage {
	
	public String to;
	
	public List<Message> messages;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	} 

	
	
	
}
