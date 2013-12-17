package com.lo54project.webservice.handler;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.lo54project.webservice.model.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

public class ClientHandler extends Handler {
        
		public ClientHandler(String url){
			super(url);
		} 
	
		/**
		 * Parse all clients
		 * 
		 * @return
		 * @throws JsonProcessingException
		 * @throws UniformInterfaceException
		 * @throws ClientHandlerException
		 * @throws IOException
		 * @throws ParseException
		 */
        public  List<Client> parseClients() throws JsonProcessingException, UniformInterfaceException, ClientHandlerException, IOException, ParseException{
                
                List<Client> client = new ArrayList<Client>();
                
                JsonNode rootNode = this.mapper.readTree(this.service.path("rest").path("clients").accept(MediaType.APPLICATION_JSON).get(String.class));
                for(JsonNode n : rootNode.path("client")) {
                        client.add(new Client(n.path("id").asInt(), n.path("lastname").asText(), n.path("firstname").asText(), n.path("address").asText(), n.path("phone").asText(), n.path("email").asText()));
                }
                
                return client;
        }
        
        /**
         * Parse a client by the id
         * 
         * @param id
         * @return
         * @throws JsonProcessingException
         * @throws UniformInterfaceException
         * @throws ClientHandlerException
         * @throws IOException
         * @throws ParseException
         */
        public Client parseClientById(String id) throws JsonProcessingException, UniformInterfaceException, ClientHandlerException, IOException, ParseException{
            
            Client client = new Client();
            
            JsonNode rootNode = mapper.readTree(service.path("rest").path("clients").path(id).accept(MediaType.APPLICATION_JSON).get(String.class));
            client = new Client(rootNode.path("id").asInt(), rootNode.path("lastname").asText(), rootNode.path("firstname").asText(), rootNode.path("address").asText(), rootNode.path("phone").asText(), rootNode.path("email").asText());
            
            return client;
    }

}