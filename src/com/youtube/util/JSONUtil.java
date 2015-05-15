/**
 * 
 */
package com.youtube.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * @author Asanka Siriwardena
 *
 */
public class JSONUtil {

	private static final Logger log = Logger.getLogger(JSONUtil.class);
	
	public JSONArray toJSONArray(ResultSet rs) {
		
		JSONArray jsArr = new JSONArray();
		
			try {
				ResultSetMetaData rsmeta = rs.getMetaData();
				
				while(rs.next()){
					
					int numOfCol = rsmeta.getColumnCount();
					
					JSONObject object = new JSONObject();
					
					for (int i = 1; i <= numOfCol; i++) {
						
						String column_name = rsmeta.getColumnName(i);
						
						if(rsmeta.getColumnType(i) == Types.INTEGER){
							object.put(column_name, rs.getInt(column_name));
							log.debug("INTEGER ---> to JSON");
						}
						
						if(rsmeta.getColumnType(i) == Types.DOUBLE){
							object.put(column_name, rs.getDouble(column_name));
							log.debug("DOUBLE ---> to JSON");
						}
						
						if(rsmeta.getColumnType(i) == Types.DATE){
							object.put(column_name, rs.getDate(column_name));
							log.debug("DATE ---> to JSON");
						}
						
						if(rsmeta.getColumnType(i) == Types.CHAR){
							object.put(column_name, rs.getString(column_name));
							log.debug("CHAR ---> to JSON");
						}
						
						if(rsmeta.getColumnType(i) == Types.VARCHAR){
							object.put(column_name, rs.getString(column_name));
							log.debug("VARCHAR ---> to JSON");
						}
					}
					
					jsArr.put(object);
				}
			} catch (SQLException | JSONException e) { //catch multiple exceptions
				
				log.error("Error converting to JSON Array", e);
			}
			
		return jsArr;
	}
}
