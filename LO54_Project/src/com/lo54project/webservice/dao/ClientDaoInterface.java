package com.lo54project.webservice.dao;

import java.util.Map;

import com.lo54project.webservice.model.Client;

public interface ClientDaoInterface {
	
	public Map<?, ?> getModel();
	public <T> void create(T o);
	public <T> void remove(T o);
	public <T> void update(T o);
	
}
