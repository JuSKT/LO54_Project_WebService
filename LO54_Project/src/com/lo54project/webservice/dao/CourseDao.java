package com.lo54project.webservice.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.lo54project.webservice.config.DbPoolConnection;
import com.lo54project.webservice.model.Course;

public enum CourseDao {
instance;
	
	private Map<String, Course> contentProvider = new HashMap<String, Course>();
	
	private CourseDao(){
		
		Connection connection = null;
		try {
			DbPoolConnection bc = new DbPoolConnection();
			
		    connection = bc.getConnection();
		 
		    Statement statement = connection.createStatement();
		    ResultSet resultat = statement.executeQuery( "SELECT * FROM Course;" );
		    
		    while ( resultat.next() ) {
		    	String code = resultat.getString( "code" );
		        String title = resultat.getString( "title" );
		        
		        contentProvider.put(code, new Course(code, title));
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
	
	public Course getCourse(String course_code) {
		Connection connection = null;
		Course c = new Course();
		try {
			DbPoolConnection bc = new DbPoolConnection();
			
		    connection = bc.getConnection();
		 
		    Statement statement = connection.createStatement();
		    ResultSet resultat = statement.executeQuery( "SELECT * FROM Course WHERE code = \""+ course_code +"\"");
 
		    while ( resultat.next() ) {
		    	
		    	String code = resultat.getString( "code" );
		        String title = resultat.getString( "title" );
		        
		        c = new Course(code, title);
		        
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
	    
		return c;
	}
	
	public Map<String, Course> getModel(){
		return contentProvider;
	}
}
