package com.lo54project.webservice.dao;

import java.util.Map;

public interface DaoInterface {
	
	public void loadModel();
	public Map<?, ?> getModel();
	public <T> void create(T o);
	public <T> void remove(T o);
	public <T> void update(T o);
	
}
