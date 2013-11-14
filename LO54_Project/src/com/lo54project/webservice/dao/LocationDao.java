package com.lo54project.webservice.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lo54project.webservice.hibernate.util.HibernateUtil;
import com.lo54project.webservice.model.Client;
import com.lo54project.webservice.model.Course;
import com.lo54project.webservice.model.Location;
import com.lo54project.webservice.util.DbPoolConnection;

public enum LocationDao {
	instance;
	
	private Map<Integer, Location> contentProvider = new HashMap<Integer, Location>();
	
	@SuppressWarnings("unchecked")
	private LocationDao(){
		
//		Connection connection = null;
//		try {
//		    connection = DbPoolConnection.getConnection();
//		 
//		    Statement statement = connection.createStatement();
//		    ResultSet resultat = statement.executeQuery( "SELECT * FROM Location;" );
//		    
//		    while ( resultat.next() ) {
//		        int id = resultat.getInt( "id" );
//		        String city = resultat.getString( "city" );
//		        
//		        contentProvider.put(id, new Location(id, city));
//		    }
//		} catch ( SQLException e ) {
//			e.printStackTrace();
//		} finally {
//		    if ( connection != null )
//		        try {
//		            /* Close connection */
//		        	connection.close();
//		        } catch ( SQLException ignore ) {}
//		}
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        List<Location> locations = new ArrayList<Location>();
        locations = session.createCriteria(Location.class).list();
        
        for (Location l : locations) {
        	contentProvider.put(l.getId(), l);
		}
        
        session.close();
	}

	public Location getLocation(int location_id) {
//		Connection connection = null;
		Location loc = new Location();
//		try {
//		    connection = DbPoolConnection.getConnection();
//		 
//		    Statement statement = connection.createStatement();
//		    ResultSet resultat = statement.executeQuery( "SELECT * FROM Location WHERE id ="+ location_id );
//		    		    
//		    while ( resultat.next() ) {
//		        int id = resultat.getInt( "id" );
//		        String city = resultat.getString( "city" );
//		        
//		        loc = new Location(id, city);
//		    }
//		} catch ( SQLException e ) {
//			e.printStackTrace();
//		} finally {
//		    if ( connection != null )
//		        try {
//		            /* Close connection */
//		        	connection.close();
//		        } catch ( SQLException ignore ) {}
//		}
	    
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        
        loc = (Location) session.get(Location.class, location_id);
		
		session.close();
		
		return loc;
	}
	
	public Map<Integer, Location> getModel(){
		return contentProvider;
	}
}
