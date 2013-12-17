package com.lo54project.webservice.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lo54project.webservice.hibernate.util.HibernateUtil;
import com.lo54project.webservice.model.CourseSession;

public enum CourseSessionDao implements DaoInterface {
	instance;

	private Map<Integer, CourseSession> contentProvider = new HashMap<Integer, CourseSession>();

	private CourseSessionDao() {
		
	}

	public CourseSession getCourseSession(Integer id_course_session)
			throws SQLException, ParseException {
		CourseSession cd = new CourseSession();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

//		cd = (CourseSession) session.get(CourseSession.class, id_course_session);
		
		cd = (CourseSession) session.createCriteria(CourseSession.class)
				.add(Restrictions.idEq(id_course_session))
				.setFetchMode("loc", FetchMode.JOIN)
				.uniqueResult();

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
        				.setFetchMode("crs", FetchMode.JOIN)
        				.setFetchMode("loc", FetchMode.JOIN)
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
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        List<CourseSession> coursesessions = new ArrayList<CourseSession>();
        coursesessions = session.createCriteria(CourseSession.class)
        		.setFetchMode("crs", FetchMode.JOIN)
        		.setFetchMode("loc", FetchMode.JOIN)
        		.add(Restrictions.eq("crs.code", course_code))
        		.list();
        
        for (CourseSession cs : coursesessions) {
        	contentProvider.put(cs.getId(), cs);
		}
        
        session.close();

        return contentProvider;
	}
	
	@SuppressWarnings("unchecked")
	public Map<Integer, CourseSession> getCourseSessionFiltered(String name, String date, String location)
			throws SQLException, ParseException {
		
		contentProvider.clear();
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	    SimpleDateFormat formatterBdd = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;    
        if(!date.equals("")){
        	try {       	 
        		newDate = formatter.parse(date);  
        		formatterBdd.format(newDate);    	
        	} catch (ParseException e) {
        		e.printStackTrace();
        	}
        }
        
        Criteria criteria = session.createCriteria(CourseSession.class)
        		.setFetchMode("crs", FetchMode.JOIN)
        		.setFetchMode("loc", FetchMode.JOIN)
        		.createAlias("crs", "c");   
        if(!name.equals("")){
        	//criteria.add(Restrictions.eq("c.code", name));
        	criteria.add(Restrictions.like("c.title", "%" + name + "%"));
        }
        if(newDate != null){
        	criteria.add(Restrictions.eq("start", newDate));
        }
        if(!location.equals("0")){
        	criteria.add(Restrictions.eq("id", Integer.parseInt(location)));
        }      
        List<CourseSession> coursesessions = criteria.list();
             
		for (CourseSession cs : coursesessions) {
        	contentProvider.put(cs.getId(), cs);
		}        
		criteria.setMaxResults(15);
        session.close();
		return contentProvider;
	}

	public Map<Integer, CourseSession> getModel() {
		loadModel();
		return contentProvider;
	}

	@Override
	public <T> void create(T o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void remove(T o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void update(T o) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadModel() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<CourseSession> coursesessions = new ArrayList<CourseSession>();
		coursesessions = session.createCriteria(CourseSession.class)
				.setFetchMode("crs", FetchMode.JOIN)
				.setFetchMode("loc", FetchMode.JOIN)
				.list();

		for (CourseSession cs : coursesessions) {
			contentProvider.put(cs.getId(), cs);
		}

		session.close();
	}
}
