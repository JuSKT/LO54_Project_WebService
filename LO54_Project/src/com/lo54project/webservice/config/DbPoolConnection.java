package com.lo54project.webservice.config;

import java.sql.Connection;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DbPoolConnection {
	
	private String url = "jdbc:mysql://localhost:3306/lo54_project";
	private String user = "root";
	private String pass = "";
	
	private BoneCP connectionPool = null;
	
	public DbPoolConnection(){
		//Load of driver
		try {
		    Class.forName( "com.mysql.jdbc.Driver" );
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		BoneCPConfig config = new BoneCPConfig();
		config.setJdbcUrl( url );
		config.setUsername( user );
		config.setPassword( pass );
		     
		config.setMinConnectionsPerPartition( 5 );
		config.setMaxConnectionsPerPartition( 10 );
		config.setPartitionCount( 2 );
		
		try {
			connectionPool = new BoneCP( config );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException{
		return connectionPool.getConnection();
	}
	
//	public Connection getConnection() throws SQLException{
//		return DriverManager.getConnection( url, user, pass );
//	}
}
