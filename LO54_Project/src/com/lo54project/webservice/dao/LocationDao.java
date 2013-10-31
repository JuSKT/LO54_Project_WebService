package com.lo54project.webservice.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.lo54project.webservice.config.DbPoolConnection;
import com.lo54project.webservice.model.Location;

public enum LocationDao {
	instance;
	
	private Map<Integer, Location> contentProvider = new HashMap<Integer, Location>();
	
	private LocationDao(){
		
		Connection connection = null;
		try {
		    connection = DbPoolConnection.getConnection();
		 
		    Statement statement = connection.createStatement();
		    ResultSet resultat = statement.executeQuery( "SELECT * FROM Location;" );
		    
		    while ( resultat.next() ) {
		        int id = resultat.getInt( "id" );
		        String city = resultat.getString( "city" );
		        
		        contentProvider.put(id, new Location(id, city));
		    }
		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
		    if ( connection != null )
		        try {
		            /* Close connection */
		        	connection.close();
		        } catch ( SQLException ignore ) {}
		}
	}

	public Location getLocation(int location_id) {
		Connection connection = null;
		Location loc = new Location();
		try {
		    connection = DbPoolConnection.getConnection();
		 
		    Statement statement = connection.createStatement();
		    ResultSet resultat = statement.executeQuery( "SELECT * FROM Loation WHERE id ="+ location_id );
		    		    
		    while ( resultat.next() ) {
		        int id = resultat.getInt( "id" );
		        String city = resultat.getString( "city" );
		        
		        loc = new Location(id, city);
		    }
		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
		    if ( connection != null )
		        try {
		            /* Close connection */
		        	connection.close();
		        } catch ( SQLException ignore ) {}
		}
	    
		return loc;
	}
	
	public Map<Integer, Location> getModel(){
		return contentProvider;
	}
}
