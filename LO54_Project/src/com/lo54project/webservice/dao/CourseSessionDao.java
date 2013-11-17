package com.lo54project.webservice.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lo54project.webservice.hibernate.util.HibernateUtil;
import com.lo54project.webservice.model.Course;
import com.lo54project.webservice.model.CourseSession;
import com.lo54project.webservice.model.Location;
import com.lo54project.webservice.util.DbPoolConnection;

public enum CourseSessionDao {
	instance;

	private Map<Integer, CourseSession> contentProvider = new HashMap<Integer, CourseSession>();

	@SuppressWarnings("unchecked")
	private CourseSessionDao() {
		// Connection connection = null;
		// try
		// {
		// connection = DbPoolConnection.getConnection();
		//
		// Statement statement = connection.createStatement();
		// ResultSet resultat = statement.executeQuery(
		// "SELECT * FROM Course_session;" );
		//
		// while ( resultat.next() )
		// {
		// int id = resultat.getInt( "id" );
		// String start = resultat.getString( "start" );
		// String end = resultat.getString( "end" );
		// String course_code = resultat.getString("course_code");
		// int location_id = resultat.getInt("location_id");
		//
		// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//
		// CourseSession cs = new CourseSession(id, formatter.parse(start),
		// formatter.parse(end));
		//
		// cs.setCrs(CourseDao.instance.getCourse(course_code));
		// cs.setLoc(LocationDao.instance.getLocation(location_id));
		//
		// contentProvider.put(id, cs);
		// }
		// }
		// catch ( SQLException e )
		// {
		// e.printStackTrace();
		// }
		// catch (ParseException e)
		// {
		// e.printStackTrace();
		// }
		// finally
		// {
		// if ( connection != null )
		// try {
		// /* Close connection */
		// connection.close();
		// } catch ( SQLException ignore ) {}
		// }

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<CourseSession> coursesessions = new ArrayList<CourseSession>();
		coursesessions = session.createCriteria(CourseSession.class).list();

		for (CourseSession cs : coursesessions) {
			contentProvider.put(cs.getId(), cs);
		}

		session.close();

	}

	public CourseSession getCourseSession(Integer id_course_session)
			throws SQLException, ParseException {
		// Connection connection = null;
		CourseSession cd = new CourseSession();
		// try {
		// connection = DbPoolConnection.getConnection();
		//
		// Statement statement = connection.createStatement();
		// ResultSet resultat = statement.executeQuery(
		// "SELECT * FROM Course_session WHERE id ="+ id_course_session );
		//
		// while ( resultat.next() ) {
		// int id = resultat.getInt( "id" );
		// String start = resultat.getString( "start" );
		// String end = resultat.getString( "end" );
		// String course_code = resultat.getString( "course_code" );
		// int location_id = resultat.getInt( "location_id" );
		//
		// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		//
		// cd = new CourseSession(id, formatter.parse(start),
		// formatter.parse(end));
		//
		// //GET LOCATION
		// cd.setLoc(LocationDao.instance.getLocation(location_id));
		// //GET COURSE
		// cd.setCrs(CourseDao.instance.getCourse(course_code));
		// }
		// } catch ( SQLException e ) {
		// e.printStackTrace();
		// } catch (ParseException e) {
		// e.printStackTrace();
		// } finally {
		// if ( connection != null )
		// try {
		// /* Close connection */
		// connection.close();
		// } catch ( SQLException ignore ) {}
		// }

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		cd = (CourseSession) session
				.get(CourseSession.class, id_course_session);

		session.close();

		return cd;
	}
	
	@SuppressWarnings("unchecked")
	public Map<Integer, CourseSession> getCourseSessionsWithLimit(String lMin, String lMax) 
			throws SQLException, ParseException {
		
		contentProvider.clear();
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        List<CourseSession> coursesessions = new ArrayList<CourseSession>();
        coursesessions = session.createCriteria(CourseSession.class)
        				.addOrder(Order.asc("crs"))
        				.setFirstResult(Integer.parseInt(lMin))
        				.setMaxResults(Integer.parseInt(lMax))
        				.list();

        for (CourseSession cs : coursesessions) {
        	contentProvider.put(cs.getId(), cs);
		}
        
        session.close();

		return contentProvider;
	}
	

	@SuppressWarnings("unchecked")
	public Map<Integer, CourseSession> getCourseSessionByCourseCode(
			String course_code) throws SQLException, ParseException {
//		Connection connection = null;
		Map<Integer, CourseSession> sessions = new HashMap<Integer, CourseSession>();

//		try {
//			connection = DbPoolConnection.getConnection();
//
//			Statement statement = connection.createStatement();
//			ResultSet resultat = statement
//					.executeQuery("SELECT * FROM Course_session WHERE course_code = \""
//							+ course_code + "\"");
//
//			while (resultat.next()) {
//				int id = resultat.getInt("id");
//				String start = resultat.getString("start");
//				String end = resultat.getString("end");
//
//				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//
//				sessions.put(id, new CourseSession(id, formatter.parse(start),
//						formatter.parse(end)));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} finally {
//			if (connection != null) {
//				try {
//					/* Close connection */
//					connection.close();
//				} catch (SQLException ignore) {
//				}
//			}
//		}
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        List<CourseSession> coursesessions = new ArrayList<CourseSession>();
        coursesessions = session.createCriteria(CourseSession.class).add(Restrictions.eq("course_code", course_code)).list();
        
        for (CourseSession cs : coursesessions) {
        	contentProvider.put(cs.getId(), cs);
		}
        
        session.close();

		return sessions;
	}

	public Map<Integer, CourseSession> getModel() {
		return contentProvider;
	}
}
