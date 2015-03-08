/**
 * 
 */
package com.youtube.rest.inventory;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.DBAccessor;

/**
 * @author Asanka Siriwardena
 *
 */
@Path("/v1/inventory/")
public class V1_inventory {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllCustomers() throws SQLException{
		
		DBAccessor accessor = new DBAccessor();
		JSONArray customerArr =  accessor.getAllCustomers();
		
		return Response.ok(customerArr.toString()).build();
	}
	
}
