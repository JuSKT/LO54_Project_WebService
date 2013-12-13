package com.lo54project.webservice.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.lo54project.webservice.model.Location;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

public class LocationHandler extends Handler {
	
		public LocationHandler(String url){
			super(url);
		}

        public List<Location> parseLocations() throws JsonProcessingException, UniformInterfaceException, ClientHandlerException, IOException{
                
                List<Location> locations = new ArrayList<Location>();

                JsonNode rootNode = this.mapper.readTree(this.service.path("rest").path("locations").accept(MediaType.APPLICATION_JSON).get(String.class));
                for(JsonNode n : rootNode.path("location")) {
                        locations.add(new Location(n.path("id").asInt(), n.path("city").textValue()));
                }
                
                return locations;
        }
        
        public Location parseLocationById(String id) throws JsonProcessingException, UniformInterfaceException, ClientHandlerException, IOException{
            
            Location location = new Location();

            JsonNode rootNode = this.mapper.readTree(this.service.path("rest").path("locations").path(id).accept(MediaType.APPLICATION_JSON).get(String.class));
//            for(JsonNode n : rootNode.path("location")) {
                    location = new Location(rootNode.path("id").asInt(), rootNode.path("city").textValue());
//            }
            
            return location;
    }
}