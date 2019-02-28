package com.sohan.restf.messenger;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectDemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	@Path("annotations")
	public String getParameterUsingAnnotations(
			@MatrixParam("param") String matrixParam,
			@HeaderParam("headerParam") String headerParam,
			@CookieParam("cookieParam") String cookieParam) {
		return "Matrix Parma : " + matrixParam + 
				" Header Param : " + headerParam
				+ " Cookie Param : " + cookieParam;
	}
	
	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uriInfo,
										@Context HttpHeaders httpHeader) {
		String path = uriInfo.getAbsolutePath().toString();
		String cookieInfo = httpHeader.getCookies().toString();
		return path + " \n " + cookieInfo ;
	}
	
}
 