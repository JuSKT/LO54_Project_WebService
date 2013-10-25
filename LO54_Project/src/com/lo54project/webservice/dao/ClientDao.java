package com.lo54project.webservice.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.lo54project.webservice.config.DbPoolConnection;
import com.lo54project.webservice.model.Client;

public enum ClientDao {
	instance;
	
	private Map<Integer, Client> contentProvider = new HashMap<Integer, Client>();
	
	private ClientDao(){
		
		Connection connection = null;
		try {
			DbPoolConnection bc = new DbPoolConnection();
			
		    connection = bc.getConnection();
		 
		    Statement statement = connection.createStatement();
		    ResultSet resultat = statement.executeQuery( "SELECT * FROM Client;" );
		    
		    while ( resultat.next() ) {
		        int id = resultat.getInt( "id" );
		        String lastname = resultat.getString( "lastname" );
		        String firstname = resultat.getString( "firstname" );
		        String address = resultat.getString( "address" );
		        String phone = resultat.getString( "phone" );
		        String email = resultat.getString( "email" );
		        //int session_id = resultat.getInt( "session_id" );
		        
		        contentProvider.put(id, new Client(id, lastname, firstname, address, phone, email));
		    }
		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
		    if ( connection != null )
		        try {
		            /* Close connection */
		        	connection.close();
		        } catch ( SQLException ignore ) {}
		}
	}

	public void createClientAndSetCourseSession(Client cli) {
		Connection connection = null;
		try {
			DbPoolConnection bc = new DbPoolConnection();
		    connection = bc.getConnection();
		 
		    Statement statement = connection.createStatement();

		    int id = statement.executeUpdate( "INSERT INTO Client (`lastname` ,`firstname` ,`address` ,`phone` ,`email` ,`session_id`) VALUES ('"+cli.getLastname()+"', '"+cli.getFirstname()+"', '"+cli.getAddress()+"', '"+cli.getPhone()+"', '"+cli.getEmail()+"', '"+cli.getCrss().getId()+"');", Statement.RETURN_GENERATED_KEYS);
		    cli.setId(id);
		    contentProvider.put(cli.getId(), cli);
		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
		    if ( connection != null )
		        try {
		            /* Close connection */
		        	connection.close();
		        } catch ( SQLException ignore ) {}
		}
	}
	
	public Map<Integer, Client> getModel(){
		return contentProvider;
	}
}
