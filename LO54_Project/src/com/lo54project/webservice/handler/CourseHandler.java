package com.lo54project.webservice.handler;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lo54project.webservice.model.Course;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class CourseHandler {
        
        private static ObjectMapper mapper = new ObjectMapper(); 
        private static ClientConfig cConfig = new DefaultClientConfig();
        private static com.sun.jersey.api.client.Client client = com.sun.jersey.api.client.Client.create(cConfig);
        private static WebResource service = client.resource(UriBuilder.fromUri("http://localhost:8080/LO54_Project").build());
        
        public static List<Course> parseCourses() throws JsonProcessingException, UniformInterfaceException, ClientHandlerException, IOException, ParseException{
                
                List<Course> course = new ArrayList<Course>();
                
                JsonNode rootNode = mapper.readTree(service.path("rest").path("courses").accept(MediaType.APPLICATION_JSON).get(String.class));
                for(JsonNode n : rootNode.path("course")) {
                        course.add(new Course(n.path("code").asText(), n.path("title").asText()));
                }
                
                return course;
        }
        
        public static Course parseCourseById(String id) throws JsonProcessingException, UniformInterfaceException, ClientHandlerException, IOException, ParseException{
            
            Course course = new Course();
            
            JsonNode rootNode = mapper.readTree(service.path("rest").path("courses").path(id).accept(MediaType.APPLICATION_JSON).get(String.class));
//            for(JsonNode n : rootNode.path("course")) {
                    course = new Course(rootNode.path("code").asText(), rootNode.path("title").asText());
//            }
            
            return course;
    }

}