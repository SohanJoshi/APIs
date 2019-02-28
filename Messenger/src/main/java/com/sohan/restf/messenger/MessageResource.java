package com.sohan.restf.messenger;

import java.net.URI;
import java.net.URISyntaxException;
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

import com.sohan.restf.messenger.bean.MessageFilterBean;
import com.sohan.restf.messenger.model.Message;
import com.sohan.restf.messenger.service.MessageService;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	private MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getMessages (@BeanParam MessageFilterBean filterBean) {
		
		if(filterBean.getYear() > 0)
			return messageService.getMessagesForYear(filterBean.getYear());
		if(filterBean.getStart() >= 0 && filterBean.getSize() > 0)
			return messageService.getMessagesWithStartAndSize(filterBean.getStart(),
									filterBean.getSize());
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") Long id, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(id);
		
		addSelfLink(message, uriInfo);
		addProfileLink(message, uriInfo);
		addCommentsLink(message, uriInfo);
		
		return message;
	}
	
	private void addCommentsLink(Message message, UriInfo uriInfo) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build()
				.toString();
		message.addLink(uri, "comments");
	}

	private void addProfileLink(Message message, UriInfo uriInfo) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build()
				.toString();
		message.addLink(uri, "profile");		
	}

	private void addSelfLink(Message message, UriInfo uriInfo) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(""+message.getId())
				.build()
				.toString();
		message.addLink(uri, "self");
		
	}

	@POST
	public Response addMessage(@Context UriInfo uriInfo, 
						Message message) throws URISyntaxException {
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				.entity(newMessage)
				.build();
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(Message message,
			@PathParam("messageId") long id) {
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.deleteMessage(id);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
