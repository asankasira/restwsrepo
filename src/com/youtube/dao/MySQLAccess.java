/**
 * 
 */
package com.youtube.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;


/**
 * @author Asanka Siriwardena
 *
 */
public class MySQLAccess {
	
	private static final String DB_URL = "jdbc:mysql://localhost/test";
	
	private static Connection conn = null;
	private final static Logger logger = Logger.getLogger(MySQLAccess.class);

	public static Connection getConnection() throws  SQLException{
		
		logger.info("Connecting to mysql database...");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.error("MySQL Driver class not found", e);
		}
		conn = DriverManager.getConnection(DB_URL, "root", "");
		
		return conn;
	}
}
