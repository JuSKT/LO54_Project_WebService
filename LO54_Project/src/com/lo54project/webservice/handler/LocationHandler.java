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

public class LocationHandler {
        
        private static ObjectMapper mapper = new ObjectMapper(); 
        private static ClientConfig cConfig = new DefaultClientConfig();
        private static com.sun.jersey.api.client.Client client = com.sun.jersey.api.client.Client.create(cConfig);
        private static WebResource service = client.resource(UriBuilder.fromUri("http://localhost:8080/LO54_Project").build());

        public static List<Location> parseLocations() throws JsonProcessingException, UniformInterfaceException, ClientHandlerException, IOException{
                
                List<Location> locations = new ArrayList<Location>();

                JsonNode rootNode = mapper.readTree(service.path("rest").path("locations").accept(MediaType.APPLICATION_JSON).get(String.class));
                for(JsonNode n : rootNode.path("location")) {
                        locations.add(new Location(n.path("id").asInt(), n.path("city").textValue()));
                }
                
                return locations;
        }
}