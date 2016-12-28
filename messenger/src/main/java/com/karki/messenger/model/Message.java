package com.karki.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
private Long id;
private String author;
private String message;
private Date created;

public Message(){
	
}

public Message(long id, String message, String author) {
	super();
	this.id = id;
	this.author = author;
	this.message = message;
	this.created=new Date();
}

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public Date getCreated() {
	return created;
}
public void setCreated(Date created) {
	this.created = created;
}


}
