package com.lo54project.webservice.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BddConnection {
	
	private String url = "jdbc:mysql://localhost:3306/lo54_project";
	private String user = "root";
	private String pass = "";
	
	public BddConnection(){
		//Load of driver
		try {
		    Class.forName( "com.mysql.jdbc.Driver" );
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection( url, user, pass );
	}
}
