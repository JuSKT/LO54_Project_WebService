package com.lo54project.webservice.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import com.lo54project.webservice.hibernate.util.HibernateUtil;
import com.lo54project.webservice.model.Course;

public enum CourseDao implements DaoInterface {
instance;
	
	private Map<String, Course> contentProvider = new HashMap<String, Course>();
	
	@SuppressWarnings("unchecked")
	private CourseDao(){	
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        List<Course> courses = session.createCriteria(Course.class)
        		.setFetchMode("courseSessions", FetchMode.JOIN)
				.setFetchMode("courseSessions.loc", FetchMode.JOIN)
				.list();
        
        for (Course c : courses) {
        	contentProvider.put(c.getCode(), c);
		}
        
        session.close();
	}
	
	public Course getCourse(String course_code) {
		Course c = new Course();
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        
//		c = (Course) session.get(Course.class, course_code);
		
		c = (Course) session.createCriteria(Course.class)
				.add(Restrictions.idEq(course_code))
				.uniqueResult();
		
		session.close();
		
		return c;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Course> getCourseCourseSessionFiltered(String name, String date, String location)
			throws SQLException, ParseException {
		
		contentProvider.clear();
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date newDate = null;    
        if(!date.equals("")){
        	try {       	 
        		newDate = formatter.parse(date);  
        	} catch (ParseException e) {
        		e.printStackTrace();
        	}
        }
        
        Criteria criteria = session.createCriteria(Course.class)
        						   .setFetchMode("courseSessions", FetchMode.JOIN)
        						   .setFetchMode("courseSessions.loc", FetchMode.JOIN);
        						   
        if(!name.equals("")){
        	//criteria.add(Restrictions.eq("c.code", name));
        	criteria.add(Restrictions.like("title", "%" + name + "%"));
        }
        if(newDate != null){
        	System.out.println(new java.sql.Date(newDate.getTime()));
        	criteria.createAlias("courseSessions", "cs",Criteria.INNER_JOIN);
        	
        	Calendar c = Calendar.getInstance(); 
        	c.setTime(newDate); 
        	c.add(Calendar.DATE, 1);
        	newDate = c.getTime();  

        	System.out.println(newDate);
        	criteria.add(Restrictions.between("cs.start", new java.sql.Date(newDate.getTime()),new java.sql.Date(c.getTime().getTime())));
        	//criteria.add(Restrictions.lt("cSession.start", newDate));
        }
        if(!location.equals("0")){
        	criteria.createAlias("courseSessions.loc", "loc",Criteria.INNER_JOIN)
        			.add(Restrictions.eq("loc.id", Integer.parseInt(location)));
        }      

        List<Course> courses = criteria.list();
        System.out.println(courses.size());
    
		for (Course c : courses) {
        	contentProvider.put(c.getCode(), c);
		}        
        session.close();
		return contentProvider;
	}
	
	public Map<String, Course> getModel(){
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
}
