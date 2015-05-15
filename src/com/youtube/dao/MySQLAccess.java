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
	
	private static final String DB_URL_TEST = "jdbc:mysql://localhost/test";
	private static final String DB_URL_CLASSIMODEL= "jdbc:mysql://localhost/classicmodels";
	
	private static Connection testConn = null;
	private static Connection clsModelConn = null;
	private final static Logger logger = Logger.getLogger(MySQLAccess.class);

	protected static Connection getTestConnection() throws  SQLException{
		
		loadMySQLDriver();
		if(testConn == null || testConn.isClosed()){
			testConn = DriverManager.getConnection(DB_URL_TEST, "root", "");
		}
		
		return testConn;
	}

	
    protected static Connection getClassicModelConnection() throws  SQLException{
		
		loadMySQLDriver();
		if(clsModelConn == null || clsModelConn.isClosed()){
			clsModelConn = DriverManager.getConnection(DB_URL_CLASSIMODEL, "root", "");
		}
		
		return clsModelConn;
	}
	
	protected static void loadMySQLDriver() {
		logger.info("Connecting to mysql database...");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			logger.debug("Loading MySQL Driver class ....");
		} catch (ClassNotFoundException e) {
			logger.error("MySQL Driver class not found", e);
		}
	}
}
