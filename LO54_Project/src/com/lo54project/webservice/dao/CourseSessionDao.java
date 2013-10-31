package com.lo54project.webservice.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.lo54project.webservice.config.DbPoolConnection;
import com.lo54project.webservice.model.CourseSession;

public enum CourseSessionDao {
	instance;
	
	private Map<Integer, CourseSession> contentProvider = new HashMap<Integer, CourseSession>();
	
	private CourseSessionDao() {
		
		Connection connection = null;
		try {
		    connection = DbPoolConnection.getConnection();
		 
		    Statement statement = connection.createStatement();
		    ResultSet resultat = statement.executeQuery( "SELECT * FROM Course_session;" );
		    
		    while ( resultat.next() ) {
		        int id = resultat.getInt( "id" );
		        String start = resultat.getString( "start" );
		        String end = resultat.getString( "end" );
		        
//		        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		        
		        contentProvider.put(id, new CourseSession(id, formatter.parse(start), formatter.parse(end)));
		    }
		} catch ( SQLException e ) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
		    if ( connection != null )
		        try {
		            /* Close connection */
		        	connection.close();
		        } catch ( SQLException ignore ) {}
		}
	}

	public CourseSession getCourseSession(Integer id_course_session) throws SQLException, ParseException {
		Connection connection = null;
		CourseSession cd = new CourseSession();
		try {
		    connection = DbPoolConnection.getConnection();
		 
		    Statement statement = connection.createStatement();
		    ResultSet resultat = statement.executeQuery( "SELECT * FROM Course_session WHERE id ="+ id_course_session );
		    		    
		    while ( resultat.next() ) {
		        int id = resultat.getInt( "id" );
		        String start = resultat.getString( "start" );
		        String end = resultat.getString( "end" );
		        String course_code = resultat.getString( "course_code" );
		        int location_id = resultat.getInt( "location_id" );
		        
		        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		        
		        cd = new CourseSession(id, formatter.parse(start), formatter.parse(end));
		        
		        //GET LOCATION
		        cd.setLoc(LocationDao.instance.getLocation(location_id));
		        //GET COURSE
		        cd.setCrs(CourseDao.instance.getCourse(course_code));
		    }
		} catch ( SQLException e ) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
		    if ( connection != null )
		        try {
		            /* Close connection */
		        	connection.close();
		        } catch ( SQLException ignore ) {}
		}
	    
		return cd;
	}
	
	public Map<Integer, CourseSession> getModel(){
		return contentProvider;
	}
}
