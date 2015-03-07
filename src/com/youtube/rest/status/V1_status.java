/**
 * 
 */
package com.youtube.rest.status;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * My First Restful service
 * @author Asanka Siriwardena
 *
 */

@Path("/v1/status/")
public class V1_status {

	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getTitle(){
		
		return "<h2>First Restful Service</h2>";
	}
	
	
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
		return Response.status(200).entity(output).build();
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion(){
		return "<p>Version: 1.0.0</p>";
	}
}
