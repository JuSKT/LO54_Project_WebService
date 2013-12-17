package com.lo54project.webservice.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lo54project.webservice.hibernate.util.HibernateUtil;
import com.lo54project.webservice.model.Location;

public enum LocationDao implements DaoInterface {
	instance;
	
	private Map<Integer, Location> contentProvider = new HashMap<Integer, Location>();
	
	private LocationDao(){
		
	}

	public Location getLocation(int location_id) {
		Location loc = new Location();
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        
        loc = (Location) session.get(Location.class, location_id);
		
		session.close();
		
		return loc;
	}
	
	public Map<Integer, Location> getModel(){
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

        List<Location> locations = new ArrayList<Location>();
        locations = session.createCriteria(Location.class).list();
        
        for (Location l : locations) {
        	contentProvider.put(l.getId(), l);
		}
        
        session.close();
	}
}
