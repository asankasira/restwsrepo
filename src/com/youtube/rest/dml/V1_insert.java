/**
 * 
 */
package com.youtube.rest.dml;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.youtube.dao.DBAccessor;
import com.youtube.dao.custom.ContactInfoDTO;

/**
 * @author Asanka Siriwardena
 *
 */
@Path("/v1/create/")
public class V1_insert {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addContactInfo(@Context HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{
		
	    String first = request.getParameter("firstname");
	    String last = request.getParameter("lastname");
	    String email = request.getParameter("email");
	    String telephone = request.getParameter("telephone");
	    
	    String statusMsg;
	    
	    ContactInfoDTO infoDTO = new ContactInfoDTO();
	    infoDTO.setFirstName(first);
	    infoDTO.setLastName(last);
	    infoDTO.setEmail(email);
	    infoDTO.setTelephone(telephone);
	    
/*		ObjectMapper mapper = new ObjectMapper();
		ContactEntry entry = mapper.readValue(incomingData, ContactEntry.class);
		System.out.println("----------------- Mapping success ---------" + entry.firstname);*/
	    
	    boolean isInsertSuccess = false;
		DBAccessor accessor = new DBAccessor();
		try {
			isInsertSuccess = accessor.insertContactPerson(infoDTO);
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(500).entity("Server Error while inserting data").build();
		}
		
		if(isInsertSuccess)
			statusMsg = "Contact information created successfully!";
		else
			statusMsg = "Data is not inserted";
		
		return Response.status(201).entity(statusMsg).build();
	}
	
	/**
	 * This method will allow you to update data in the PC_PARTS table.
	 * In this example we are using both PathParms and the message body (payload).
	 * 
	 * @param brand
	 * @param item_number
	 * @param incomingData
	 * @return
	 * @throws Exception
	 */
	@Path("/{brand}/{item_number}")
	@PUT
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateItem(String incomingData) 
								throws Exception {
		
		//System.out.println("incomingData: " + incomingData);
		//System.out.println("brand: " + brand);
		//System.out.println("item_number: " + item_number);
		
		int pk;
		int avail;
		int http_code = 200;
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
//		Schema308tube dao = new Schema308tube();
		
		try {
			
			JSONObject partsData = new JSONObject(incomingData); //we are using json objects to parse data
			pk = partsData.optInt("PC_PARTS_PK", 0);
			avail = partsData.optInt("PC_PARTS_AVAIL", 0);
			
			//call the correct sql method
//			http_code = dao.updatePC_PARTS(pk, avail);
			
			if(http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been updated successfully");
			} else {
				return Response.status(500).entity("Server was not able to process your request").build();
			}
			
			returnString = jsonArray.put(jsonObject).toString();
			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
}
