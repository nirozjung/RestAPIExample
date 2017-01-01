package com.karki.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.karki.messenger.database.DatabaseClass;
import com.karki.messenger.exception.DataNotFoundException;
import com.karki.messenger.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public MessageService() {
		messages.put(1L, new Message(1, "This is crazy", "Niroz"));
		messages.put(2L, new Message(2, "This is crazy", "Tom"));

	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}

	public Message getMessage(long id) {
		Message message= messages.get(id);
		if(message==null){ throw new DataNotFoundException("Message with id "+id+" not found");
			}
		return message;
		
	}
	
	public List<Message> getAllMessageForYear(int year){
		List<Message> messagesForYear=new ArrayList<Message>();
		Calendar cal=Calendar.getInstance();
		for(Message msg:messages.values()){
			cal.setTime(msg.getCreated());
			System.out.println("Calendar stuff **** "+cal);
			if(cal.get(Calendar.YEAR)==year)
				messagesForYear.add(msg);
		}
 		return messagesForYear;	
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size){
		List<Message> messagesPaginated=new ArrayList<Message>(messages.values());
		
		if(start+size>messagesPaginated.size()) return new ArrayList<>();
		return messagesPaginated.subList( start, start + size );
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() == 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
