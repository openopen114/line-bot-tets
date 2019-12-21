package com.openopen.line_bot_tets;

import java.io.IOException;
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

import org.apache.http.ParseException;
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
	
	ReplyMessageRepo replyMsgRepo;
	public Webhook() {
		replyMsgRepo = new ReplyMessageRepo();
	}
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
	 
        return "webhook Got it!";
    }
	
	
	//TODO:確認 request header 中的 X-Line-Signature signature 與 digest 相符。
	//https://developers.line.biz/zh-hant/docs/messaging-api/building-bot/#getting-content-sent-by-users
	
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
	    
	    // replyToken
	    String replyToken = lineBotReq.getEvents().get(0).getReplyToken();
	    
	    //時間戳計
	    Date timestamp =  lineBotReq.getEvents().get(0).getTimestamp();
	    
	    System.out.println(timestamp.toString());
	    System.out.println("replyToken:" + replyToken);
	    System.out.println("userid:" + userid);
	    System.out.println("text:" + text);
	    
	    
	    System.out.println("---------");
	     
	    try {
			replyMsgRepo.reployEcho(replyToken, text);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	     
	 
	    return Response
	      .status(Response.Status.OK)
	      .entity(message)
	      .build();
	}

}
