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

public enum ClientDao implements DaoInterface {
	instance;
	
	private Map<Integer, Client> contentProvider = new HashMap<Integer, Client>();
	
	private ClientDao(){
		
	}
	
	public Map<Integer, Client> getModel(){
		loadModel();
		return contentProvider;
	}

	@Override
	public <T> void create(T cli) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
        session.save(((Client) cli));
        contentProvider.put(((Client)cli).getId(), (Client) cli);
        
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

	@SuppressWarnings("unchecked")
	@Override
	public void loadModel() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        List<Client> clients = new ArrayList<Client>();
        clients = session.createCriteria(Client.class)
        		.setFetchMode("crss", FetchMode.JOIN)
        		.setFetchMode("crss.crs", FetchMode.JOIN)
        		.setFetchMode("crss.loc", FetchMode.JOIN)
        		.list();
        
        for (Client c : clients) {
        	contentProvider.put(c.getId(), c);
		}
        
        session.close();
	}
	
	
}
