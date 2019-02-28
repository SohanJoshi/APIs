package com.sohan.restf.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;

import com.sohan.restf.messenger.database.Database;
import com.sohan.restf.messenger.model.Comment;
import com.sohan.restf.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = Database.getAllMessages();
	
	public MessageService() {
		messages.putIfAbsent(1L , new Message(1L, "Message 1 ", "Author 1"));
		messages.putIfAbsent(2L , new Message(2L , "Message 2", "Author 2"));
		messages.get(1L).getComments().put(1L, new Comment(1L, "Comment 1", "Author 4"));
		messages.get(1L).getComments().put(2L, new Comment(2L, "Comment 2", "Author 3"));
		messages.get(2L).getComments().put(3L, new Comment(3L, "Comment 3", "Author 1"));
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<>(messages.values());
				
	}
	
	public List<Message> getMessagesForYear(int year) {
		List<Message> messagesForYear = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		
		for(Message message : messages.values()) {
			calendar.setTime(message.getCreated());
			int messageYear = calendar.get(Calendar.YEAR);
			if(messageYear == year)
				messagesForYear.add(message);
		}
		
		return messagesForYear;
	}
	
	public List<Message> getMessagesWithStartAndSize(int start, int size) {
		List<Message> messagesFromStart = new ArrayList<>();
		
		if(start >= 0 && size > 0) {
			messagesFromStart = new ArrayList<>(messages.values()).subList(start, start + size);
		}
		
		return messagesFromStart;
	}
	
	public Message getMessage(long id) {
		Message message = messages.get(id);
		if(message == null)
			throw new WebApplicationException("Message was not found with id : " + id);
		return message;
	}
	
	public Message addMessage(Message message) {
		long id = messages.size() + 1;
		message.setId(id);
		messages.put(id, message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		long id = message.getId();
		if(id <= 0 )
			return null;
		message = messages.put(id, message);
		return message;
	}
	
	public Message deleteMessage(long id) {
		return messages.remove(id);
	}
}
