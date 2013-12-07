package com.lo54project.webservice.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

        List<Course> courses = new ArrayList<Course>();
        courses = session.createCriteria(Course.class).list();
        
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
