package com.lo54project.webservice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.lo54project.webservice.model.Client;

public enum ClientDao {
	instance;
	
	private Map<Integer, Client> contentProvider = new HashMap<Integer, Client>();
	
	private ClientDao(){
		try {
		    Class.forName( "com.mysql.jdbc.Driver" );
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		/* Connexion à la base de données */
		String url = "jdbc:mysql://localhost:3306/lo54_project";
		String utilisateur = "root";
		String motDePasse = "";
		Connection connexion = null;
		try {
		    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		 
		    Statement statement = connexion.createStatement();
		    ResultSet resultat = statement.executeQuery( "SELECT * FROM Client;" );
		    
		    while ( resultat.next() ) {
		        int id = resultat.getInt( "id" );
		        String lastname = resultat.getString( "lastname" );
		        String firstname = resultat.getString( "firstname" );
		        String address = resultat.getString( "address" );
		        String phone = resultat.getString( "phone" );
		        String email = resultat.getString( "email" );
		        int session_id = resultat.getInt( "session_id" );
		        
		        contentProvider.put(id, new Client(id, lastname, firstname, address, phone, email));
		    }
		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
		    if ( connexion != null )
		        try {
		            /* Fermeture de la connexion */
		            connexion.close();
		        } catch ( SQLException ignore ) {}
		}
		
		
//		Client c = new Client(1, "COLLE", "Juju", "Le soleil, nova 2", "0845789865", "jc@lesoleil.espace");
//		contentProvider.put(c.getId(), c);
//		c = new Client(2, "COLLE", "Julien", "La lune, trou 2", "0845789865", "jc@lalune.espace");
//		contentProvider.put(c.getId(), c);
	}
	
	public Map<Integer, Client> getModel(){
		return contentProvider;
	}
}
