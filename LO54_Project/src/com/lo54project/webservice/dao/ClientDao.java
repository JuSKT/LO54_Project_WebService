package com.lo54project.webservice.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lo54project.webservice.hibernate.util.HibernateUtil;
import com.lo54project.webservice.model.Client;
import com.lo54project.webservice.model.CourseSession;

public enum ClientDao implements DaoInterface {
	instance;
	
	private Map<Integer, Client> contentProvider = new HashMap<Integer, Client>();
	
	@SuppressWarnings("unchecked")
	private ClientDao(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        List<Client> clients = new ArrayList<Client>();
        clients = session.createCriteria(Client.class).setFetchMode("crss", FetchMode.JOIN).list();
        
//        clients.toString();
        
        for (Client c : clients) {
//        	session.merge(c);
        	contentProvider.put(c.getId(), c);
		}
        
        session.close();
	}

	public void createClientAndSetCourseSession(Client cli) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
        session.persist(cli); //pas besoin de .save(), hibernate le fait dans le getId
        contentProvider.put(cli.getId(), cli);
        
        session.getTransaction().commit();
        session.close();
	}
	
	public Map<Integer, Client> getModel(){
		return contentProvider;
	}

	@Override
	public <T> void create(T cli) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
        session.persist((Client) cli);
        contentProvider.put(((Client) cli).getId(), (Client) cli);
        
        session.getTransaction().commit();
        session.close();
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
