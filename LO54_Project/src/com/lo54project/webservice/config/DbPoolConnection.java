package com.lo54project.webservice.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DbPoolConnection {
	
	private String url = "jdbc:mysql://localhost:3306/lo54_project";
	private String user = "root";
	private String pass = "";
	
	BoneCP connectionPool = null;
	
	public DbPoolConnection(){
		//Load of driver
		try {
		    Class.forName( "com.mysql.jdbc.Driver" );
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		BoneCPConfig config = new BoneCPConfig();   // cr�ation d'un objet BoneCPConfig
		config.setJdbcUrl( url );           // d�finition de l'URL JDBC
		config.setUsername( user );       // d�finition du nom d'utilisateur
		config.setPassword( pass );       // d�finition du mot de passe
		     
		config.setMinConnectionsPerPartition( 5 );  // d�finition du nombre min de connexions par partition
		config.setMaxConnectionsPerPartition( 10 ); // d�finition du nombre max de connexions par partition
		config.setPartitionCount( 2 );          // d�finition du nombre de partitions
		
		try {
			// cr�ation du pool � partir de l'objet BoneCPConfig
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
