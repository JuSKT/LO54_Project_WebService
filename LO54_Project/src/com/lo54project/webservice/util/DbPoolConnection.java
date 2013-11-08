package com.lo54project.webservice.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DbPoolConnection {

	//Config  
	private static String url = "jdbc:mysql://localhost:3306/lo54_project";
	private static String user = "root";
	private static String pass = "";
	
	private static BoneCP connectionPool = null;
	
	private DbPoolConnection(){}
	
	public static Connection getConnection() throws SQLException{
		if(connectionPool == null){
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
		return connectionPool.getConnection();
	}
}
