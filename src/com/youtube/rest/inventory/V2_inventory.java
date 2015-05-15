/**
 * 
 */
package com.youtube.rest.inventory;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.DBAccessor;
import com.youtube.dao.MySQLAccess;

/**
 * @author Asanka Siriwardena
 *
 */
@Path("/v2/inventory/")
public class V2_inventory {
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllPayments(@QueryParam("custNo")  Integer customerNumber){
		
		String returnString = null;
		JSONArray array = new JSONArray();
		DBAccessor accessor = new DBAccessor();
		
		try {
			
			if(customerNumber == null){
				return Response.status(400).entity("Error: Please specify customer number for this search").build();
			}
			
			 array = accessor.queryPaymentsOnCustomer(customerNumber);
			 returnString = array.toString();
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	
	@Path("/{orderNo}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnOrderDetailWithOrderNo(@PathParam("orderNo") Integer orderNum){
		
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		
		DBAccessor accessor = new DBAccessor();
		
		try {
			
			if(orderNum == null){
				return Response.status(400).entity("Error: Please specify an Order Number for this search").build();
			}
			
			jsonArray = accessor.queryOrderDetailWithOrderNo(orderNum);
			returnString = jsonArray.toString();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}

	@Path("/{orderNo}/{orderQty}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnOrderDetailWithOrderNoAndOrderQty(@PathParam("orderNo") Integer orderNum,
			@PathParam("orderQty") Integer orderQty){
		
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		
		DBAccessor accessor = new DBAccessor();
		
		try {
			
			jsonArray = accessor.queryOrderDetailWithOrderNoAndOrderQty(orderNum, orderQty);
			returnString = jsonArray.toString();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
}
