/**
 * 
 */
package com.youtube.rest.status;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.youtube.dao.DBAccessor;

/**
 * My First Restful service
 * @author Asanka Siriwardena
 *
 */

@Path("/v1/status/") // route to java class
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
	
	@Path("/version")  // route to specific method
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion(){
		return "<p>Version: 1.0.0</p>";
	}
	
	@Path("/db/emp")  
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response findEmployees(){
		
		StringBuilder sb = new StringBuilder();
		List<String> empList = new DBAccessor().getEmployees();
		
		sb.append("<ul>");
		
		for(String empName: empList){
			
			sb.append("<li>").append(empName).append("</li>");
		}
		
		sb.append("</ul>");
		
		return Response.ok(sb.toString()).build();
	}
}
