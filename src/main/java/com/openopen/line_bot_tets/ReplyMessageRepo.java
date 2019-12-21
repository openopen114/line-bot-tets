package com.openopen.line_bot_tets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils; 

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.Message;
import model.ReployMessage;

public class ReplyMessageRepo {
	
	String CHANNEL_ACCESS_TOKEN = "u9YohKzppDFvXWvd4fo7fYpPy20rZbjZFS1cBN+rXza162wRsQzObzyGj3FAm8SPwwK9YdI1M7lukvOvYOqSpxLom0HWDuY0BmtC1XefvM4BAmY1OccY2QQIxXH3WGujT+2CcVu6w9SruYnXxWJgvwdB04t89/1O/w1cDnyilFU=";
 
 
	
	/*
	 * 
	 * reply test
	 *
	 */
	public void reployEcho(String _replyToken,String _text) throws ParseException, IOException {
		System.out.println("==> reploy test ");
		String url ="https://api.line.me/v2/bot/message/reply";
		String charset = "UTF-8";
		 
 
		// Message List
		List<Message> messages = new ArrayList<Message>();
		
		//msg 1 
		Message msg = new Message();
		msg.setType("text");
		msg.setText("學你說：" + _text);
		
		messages.add(msg);
		
		
		ReployMessage reploy = new ReployMessage();
		reploy.setReplyToken(_replyToken);
		reploy.setMessages(messages);
		
 
		// 請求內容
		Gson gson = new Gson();
		String content  = gson.toJson(reploy);
		System.out.println(content);
		 
		
		// Create Http POST
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		
		// Header
		post.setHeader("Content-Type", "application/json;charset=UTF-8");
		post.setHeader("Authorization","Bearer " + CHANNEL_ACCESS_TOKEN);
		
		
		// Entity Body
		StringEntity entity = new StringEntity(content,charset);
		entity.setContentEncoding(charset);
		entity.setContentType("application/json");
		post.setEntity(entity);
		
		// Execute
		CloseableHttpResponse response = client.execute(post);
		
		// Response
		HttpEntity responseEntity = response.getEntity();
		String resData = EntityUtils.toString(responseEntity);
		
		System.out.println("===> resData");
		System.out.println(resData);
		
		// Close
		client.close();
		
	}

}
