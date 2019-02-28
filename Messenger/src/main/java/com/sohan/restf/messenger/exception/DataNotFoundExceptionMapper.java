package com.sohan.restf.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.sohan.restf.messenger.model.ErrorMessage;

public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorMessage message = new ErrorMessage(exception.getMessage(), 404, "http:www.google.com");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(message)
				.build();
	}

}
