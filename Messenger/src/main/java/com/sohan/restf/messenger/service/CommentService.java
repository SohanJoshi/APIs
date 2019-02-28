package com.sohan.restf.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sohan.restf.messenger.database.Database;
import com.sohan.restf.messenger.model.Comment;
import com.sohan.restf.messenger.model.Message;

public class CommentService {

	private Map<Long, Message> messages = Database.getAllMessages();

	public Comment getComment(long messageId, long commentId) {
		if(messageId < 0 || commentId < 0)
			return null;
		else
			return messages.get(messageId).getComments().get(commentId);
	}

	public Comment addComment(long messageId, Comment comment) {
		if(messageId < 0 || comment == null)
			return null;
		else {
			Message message = messages.get(messageId);
			long id = message.getComments().size() + 1;
			comment.setId(id);
			return message.getComments().put(id, comment);
		}
	}

	public List<Comment> getAllComment(long messageId) {
		if (messageId <= 0 )
			return null;
		else
			return new ArrayList<>(messages.get(messageId).getComments().values());
	}

	public Comment updateComment(long messageId, long commentId, Comment comment) {
		if(messageId <= 0 || comment == null)
			return null;
		else {
			comment.setId(commentId);
			return messages.get(messageId).getComments().put(commentId, comment);
		}
	}

	public Comment deleteComment(long messageId, long commentId) {
		if(messageId <= 0 || commentId <= 0)
			return null;
		else {
			Message message = messages.get(messageId);
			if(message == null)
				return null;
			return message.getComments().remove(commentId);
		}
	}
	
}
