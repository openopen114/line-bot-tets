package model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/*
 * 
 * Line Bot Request 
 *
 */
public class LineBotRequest {
	public List<Events> events;
	public String destination;

	public List<Events> getEvents() {
		return events;
	}

	public void setEvents(List<Events> events) {
		this.events = events;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
}

 

 
 
