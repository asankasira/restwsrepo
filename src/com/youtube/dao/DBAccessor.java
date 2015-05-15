/**
 * 
 */
package com.youtube.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.custom.ContactInfoDTO;
import com.youtube.util.JSONUtil;

/**
 * This utility class will convert database table to a JSON array.
 * 
 * @author Asanka Siriwardena
 *
 */
public class DBAccessor {
	
	private final static Logger logger = Logger.getLogger(DBAccessor.class);
	public List<String> getEmployees() {
		
		Connection conn = null;
		PreparedStatement pst = null;
		List<String> emplist = new ArrayList<>();
		
		String sql = "select name, salary, city from employee";
		
		try {
						
			conn = MySQLAccess.getTestConnection();
			logger.info("<-------------  Inside DB getEmployee method ----------> ");
			
			pst = conn.prepareStatement(sql);		
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				
				emplist.add(rs.getString("name"));
			}
			
			rs.close();
			pst.close();
			
		} catch (SQLException e) {
			logger.error("Error in establish DB connection ", e);
		} finally{
			
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException se2) {/* nothing we can do */}
			
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {	}// end finally try
		}
		
		return emplist;
	}
	
	public JSONArray getAllCustomers() throws SQLException  {
				
		String sql = "select * from customers";	
		JSONArray arr = new JSONArray();

	    //try with jdbc resources	
		try (Connection conn =  MySQLAccess.getTestConnection();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			//set prepared statement parameters if required..
			
			try(ResultSet rs = pst.executeQuery()){  //nested try for ResultSet
				arr = new JSONUtil().toJSONArray(rs);
			}
			
		}
		
		/** catch clause is omitted as this throws SQLException */
		
		return arr;
	}
	
	public JSONArray queryAllEmployees() throws SQLException  {
		
		String sql = "select name, salary, city, region from employee";	
		JSONArray arr = new JSONArray();

	    //try with jdbc resources	
		try (Connection conn =  MySQLAccess.getTestConnection();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			//set prepared statement parameters if required..
			
			try(ResultSet rs = pst.executeQuery()){  //nested try for ResultSet
				arr = new JSONUtil().toJSONArray(rs);
			}
			
		}
		
		/** catch clause is omitted as this throws SQLException */
		
		return arr;
	}
	
	public JSONArray queryPaymentsOnCustomer(Integer customerNumber) throws SQLException  {
		
		String sql = "select checknumber, paymentdate, amount from payments where customerNumber = ?";	
		JSONArray arr = new JSONArray();

	    //try with jdbc resources	
		try (Connection conn =  MySQLAccess.getClassicModelConnection();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, customerNumber);
			
			try(ResultSet rs = pst.executeQuery()){  //nested try for ResultSet
				arr = new JSONUtil().toJSONArray(rs);
			}
			
		}
		
		return arr;
	}
	
    public JSONArray queryOrderDetailWithOrderNo(Integer orderNo) throws SQLException  {
		
		String sql = "select productcode, quantityordered, priceeach from orderdetails where orderNumber = ?";	
		JSONArray arr = new JSONArray();

	    //try with jdbc resources	
		try (Connection conn =  MySQLAccess.getClassicModelConnection();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, orderNo);
			
			try(ResultSet rs = pst.executeQuery()){  //nested try for ResultSet
				arr = new JSONUtil().toJSONArray(rs);
			}
			
		}
		
		return arr;
	}
	
    public JSONArray queryOrderDetailWithOrderNoAndOrderQty(Integer orderNo, Integer orderQty) throws SQLException  {
		
		String sql = "select productcode, quantityordered, priceeach from orderdetails where orderNumber = ? and quantityOrdered = ?";	
		JSONArray arr = new JSONArray();

	    //try with jdbc resources	
		try (Connection conn =  MySQLAccess.getClassicModelConnection();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, orderNo);
			pst.setInt(2, orderQty);
			
			try(ResultSet rs = pst.executeQuery()){  //nested try for ResultSet
				arr = new JSONUtil().toJSONArray(rs);
			}
			
		}
		
		return arr;
	}
	
    public boolean insertContactPerson(ContactInfoDTO infoDTO) throws SQLException{
    	
    	int row_count = 0;
    	String sql = "insert into contacts(firstname, lastname, telephone, email) values (?, ?, ?, ?)";
    	
    	logger.info("<------------- insertContactPerson method - Begin ----------> ");
    	
		try(Connection conn = MySQLAccess.getTestConnection();
    			PreparedStatement pst = conn.prepareStatement(sql)){
    		
    		pst.setString(1, infoDTO.getFirstName());
    		pst.setString(2, infoDTO.getLastName());
    		pst.setString(3, infoDTO.getTelephone());
    		pst.setString(4, infoDTO.getEmail());
    		
    		row_count = pst.executeUpdate();
    	}
    	
    	return (row_count > 0);
    }
}
