package com.lo54project.webservice.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lo54project.webservice.model.Location;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class LocationHandler extends Handler {

        public static List<Location> parseLocations() throws JsonProcessingException, UniformInterfaceException, ClientHandlerException, IOException{
                
                List<Location> locations = new ArrayList<Location>();

                JsonNode rootNode = mapper.readTree(service.path("rest").path("locations").accept(MediaType.APPLICATION_JSON).get(String.class));
                for(JsonNode n : rootNode.path("location")) {
                        locations.add(new Location(n.path("id").asInt(), n.path("city").textValue()));
                }
                
                return locations;
        }
        
        public static Location parseLocationById(String id) throws JsonProcessingException, UniformInterfaceException, ClientHandlerException, IOException{
            
            Location location = new Location();

            JsonNode rootNode = mapper.readTree(service.path("rest").path("locations").path(id).accept(MediaType.APPLICATION_JSON).get(String.class));
//            for(JsonNode n : rootNode.path("location")) {
                    location = new Location(rootNode.path("id").asInt(), rootNode.path("city").textValue());
//            }
            
            return location;
    }
}