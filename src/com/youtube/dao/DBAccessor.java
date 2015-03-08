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

/**
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
						
			conn = MySQLAccess.getConnection();
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
}
