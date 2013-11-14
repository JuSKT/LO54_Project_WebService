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
import org.hibernate.criterion.Order;

import com.lo54project.webservice.hibernate.util.HibernateUtil;
import com.lo54project.webservice.model.Client;
import com.lo54project.webservice.model.Course;
import com.lo54project.webservice.util.DbPoolConnection;

public enum CourseDao {
instance;
	
	private Map<String, Course> contentProvider = new HashMap<String, Course>();
	
	private CourseDao(){
		
//		Connection connection = null;
//		try {
//		    connection = DbPoolConnection.getConnection();
//		 
//		    Statement statement = connection.createStatement();
//		    ResultSet resultat = statement.executeQuery( "SELECT * FROM Course;" );
//		    
//		    while ( resultat.next() ) {
//		    	String code = resultat.getString( "code" );
//		        String title = resultat.getString( "title" );
//		        
//		        contentProvider.put(code, new Course(code, title));
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

        List<Course> courses = new ArrayList<Course>();
        courses = session.createCriteria(Course.class).list();//session.createQuery("from Client").list();
        
        for (Course c : courses) {
        	contentProvider.put(c.getCode(), c);
		}
        
        session.close();
		
	}
	
	public Course getCourse(String course_code) {
//		Connection connection = null;
		Course c = new Course();
//		try {
//		    connection = DbPoolConnection.getConnection();
//		 
//		    Statement statement = connection.createStatement();
//		    ResultSet resultat = statement.executeQuery( "SELECT * FROM Course WHERE code = \""+ course_code +"\"");
// 
//		    while ( resultat.next() ) {
//		    	
//		    	String code = resultat.getString( "code" );
//		        String title = resultat.getString( "title" );
//		        
//		        c = new Course(code, title);
//		        
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
        
		c = (Course) session.get(Course.class, course_code);
		
		session.close();
		
		return c;
	}
	
	public Map<String, Course> getModel(){
		return contentProvider;
	}
}
