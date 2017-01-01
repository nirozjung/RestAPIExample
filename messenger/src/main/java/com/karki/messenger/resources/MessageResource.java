package com.karki.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.karki.messenger.model.Message;
import com.karki.messenger.model.Profile;
import com.karki.messenger.service.MessageService;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class MessageResource {
	MessageService messageService = new MessageService();

	// get all messages
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		
		System.out.println("JSON method called up ***************");
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessageForYear(filterBean.getYear());
		}
		if (filterBean.getStart()>=0 && filterBean.getSize()>0){
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}

	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage=messageService.addMessage(message);
		System.out.println(uriInfo.getAbsolutePath());
		String newId=String.valueOf(newMessage.getId());
		URI uri=uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
						.entity(newMessage).build();
		//return messageService.addMessage(message);

	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);

	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
	 Message message= messageService.getMessage(messageId);
	 message.addLink(getUriForSelf(uriInfo, message),"self");
	 message.addLink(getUriForProfile(uriInfo, message),"profile");
	 message.addLink(getUriForComments(uriInfo, message),"comments");


	 return message;
	}

	private String getUriForComments(UriInfo uriInfo, Message message) {
		String uri=uriInfo. getBaseUriBuilder() // http://localhost:8080/webapi/
				 .path( MessageResource.class ) // Message
				 .path( MessageResource.class, "getCommentResource" ) // Message
			     .path( CommentResource.class ) // /comments
			     .resolveTemplate("messageId", message.getId())
			     .build()
			     .toString();
return uri;
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri=uriInfo. getBaseUriBuilder() // http://localhost:8080/webapi/
			     .path( ProfileResource.class ) // /profiles
			     .path(message.getAuthor() ) // /authorname 
			     .build()
			     .toString();
return uri;
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri=uriInfo. getBaseUriBuilder() // http://localhost:8080/webapi/
							     .path( MessageResource.class ) // /messages
							     .path( Long.toString( message.getId() ) ) // /{messageId}
							     .build()
							     .toString();
		return uri;
	}

	@DELETE
	@Path("/{messageId}")
	public Message deleteMessage(@PathParam("messageId") long messageId) {
		return messageService.removeMessage(messageId);
	}
	
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	

}
