package com.openopen.line_bot_tets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.collection.StringIgnoreCaseKeyComparator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

 
import model.LineBotRequest;
import util.ImprovedDateTypeAdapter;

@Path("webhook")
public class Webhook {
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "webhook Got it!";
    }
	
	
	
	
	@POST
	@Consumes("application/json")
	@Path("/callback")
	public Response callback(String _json) {
		
	 
		System.out.println("==> claaback");
		
		
	    String message = "This is a test response";
	    
	    System.out.println(_json);
	    
	    // 解析 json  to lineBotReq
	    GsonBuilder builder = new GsonBuilder();  
	    builder.registerTypeAdapter(Date.class, new ImprovedDateTypeAdapter());
	    Gson gson = builder.create();
	     
	 
	    LineBotRequest lineBotReq = gson.fromJson(_json, LineBotRequest.class);
	    System.out.println(lineBotReq.getDestination());
	    
	    //訊息
	    String text =  lineBotReq.getEvents().get(0).getMessage().getText();
	    
	    //使用者 line id
	    String userid = lineBotReq.getEvents().get(0).getSource().getUserId();
	    
	    //時間戳計
	    Date timestamp =  lineBotReq.getEvents().get(0).getTimestamp();
	    
	    System.out.println(timestamp.toString() + " ==> " + userid + " : " + text);
	 
	    return Response
	      .status(Response.Status.OK)
	      .entity(message)
	      .build();
	}

}
