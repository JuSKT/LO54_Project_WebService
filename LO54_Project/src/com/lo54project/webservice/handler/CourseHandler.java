package com.lo54project.webservice.handler;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lo54project.webservice.model.Course;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

public class CourseHandler extends Handler {
        
		public CourseHandler(String url) {
			super(url);
		}
        
        public  List<Course> parseCourses() throws JsonProcessingException, UniformInterfaceException, ClientHandlerException, IOException, ParseException{
                
                List<Course> course = new ArrayList<Course>();
                JsonNode rootNode = mapper.readTree(service.path("rest").path("courses").accept(MediaType.APPLICATION_JSON).get(String.class));
                ObjectMapper  m = new ObjectMapper();
                m.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                if(rootNode.path("course").isArray()){
		            for(JsonNode n : rootNode.path("course")) {
		        		course.add( m.readValue(n.toString(), Course.class));
		            }
                }else{
	        		course.add( m.readValue(rootNode.path("course").toString(), Course.class));
                }
                
                return course;
        }
        
        public  Course parseCourseById(String id) throws JsonProcessingException, UniformInterfaceException, ClientHandlerException, IOException, ParseException{
            
            Course course = new Course();
            
            JsonNode rootNode = mapper.readTree(service.path("rest").path("courses").path(id).accept(MediaType.APPLICATION_JSON).get(String.class));
//            for(JsonNode n : rootNode.path("course")) {
                    course = new Course(rootNode.path("code").asText(), rootNode.path("title").asText());
//            }
            
            return course;
    }

}