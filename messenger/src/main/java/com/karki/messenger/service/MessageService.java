package com.karki.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.karki.messenger.database.DatabaseClass;
import com.karki.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages=DatabaseClass.getMessages();
	private Map<Long, Message> profiles=DatabaseClass.getProfiles();
	
	public MessageService(){
		messages.put(1L, new Message(1,"This is crazy", "Niroz"));
		messages.put(2L, new Message(2,"This is crazy", "Tom"));

	}

	public List<Message> getAllMessages() {
		
		return new ArrayList<Message>(messages.values());

	}
	
	public Message getMessage(long id){
		return messages.get(id);
		
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId()==0){
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);
	}
}
