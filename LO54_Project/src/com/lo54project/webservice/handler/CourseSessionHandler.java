package com.lo54project.webservice.handler;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.lo54project.webservice.model.Course;
import com.lo54project.webservice.model.CourseSession;
import com.lo54project.webservice.model.Location;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

public class CourseSessionHandler extends Handler {
        
		public CourseSessionHandler(String url){
			super(url);
		}
	
		/**
		 * Parse all coursesessions
		 * 
		 * @return
		 * @throws JsonProcessingException
		 * @throws UniformInterfaceException
		 * @throws ClientHandlerException
		 * @throws IOException
		 * @throws ParseException
		 */
		public List<CourseSession> parseCourseSessions() throws JsonProcessingException, UniformInterfaceException, ClientHandlerException, IOException, ParseException{
                
                List<CourseSession> courseSession = new ArrayList<CourseSession>();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                
                JsonNode rootNode = mapper.readTree(service.path("rest").path("coursesessions").accept(MediaType.APPLICATION_JSON).get(String.class));
                for(JsonNode n : rootNode.path("courseSession")) {
                	CourseSession cs = new CourseSession(n.path("id").asInt(), formatter.parse(n.path("start").asText()), formatter.parse(n.path("end").asText()));
                	
                	cs.setCrs(new Course(n.path("crs").path("code").asText(), n.path("crs").path("title").asText()));
                	cs.setLoc(new Location(n.path("loc").path("id").asInt(),n.path("loc").path("city").asText()));
                	
                	courseSession.add(cs);
                }
                
                return courseSession;
        }
        
		/**
		 * Parse coursessions by location
		 * 
		 * @param location
		 * @return
		 * @throws JsonProcessingException
		 * @throws UniformInterfaceException
		 * @throws ClientHandlerException
		 * @throws IOException
		 * @throws ParseException
		 */
        public List<CourseSession> parseCourseSessionsByLocation(String location) throws JsonProcessingException, UniformInterfaceException, ClientHandlerException, IOException, ParseException{
                
                List<CourseSession> courseSession = new ArrayList<CourseSession>();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                
                LocationHandler lh = new LocationHandler(this.url);
                CourseHandler ch = new CourseHandler(this.url);
                
                JsonNode rootNode = mapper.readTree(service.path("rest").path("coursesessions").path("location").path(location).accept(MediaType.APPLICATION_JSON).get(String.class));
                for(JsonNode n : rootNode.path("courseSession")) {
                	CourseSession cs = new CourseSession(n.path("id").asInt(), formatter.parse(n.path("start").asText()), formatter.parse(n.path("end").asText()));
                	cs.setCrs(ch.parseCourseById(n.path("crs").path("code").asText()));
                	cs.setLoc(lh.parseLocationById(n.path("loc").path("id").asText()));
                	courseSession.add(cs);
                }
                
                return courseSession;
        }
        
        /**
         * Parse coursessions by ids
         * 
         * @param ids
         * @return
         * @throws JsonParseException
         * @throws JsonMappingException
         * @throws UniformInterfaceException
         * @throws ClientHandlerException
         * @throws IOException
         */
        public List<CourseSession> parseCourseSessionsById(int[] ids) throws JsonParseException, JsonMappingException, UniformInterfaceException, ClientHandlerException, IOException{
        	
        	List<CourseSession> courseSessions=new ArrayList<CourseSession>();
        	for(int i : ids){
        		courseSessions.add( mapper.readValue(service.path("rest").path("coursesessions").path("id").path(i+"").accept(MediaType.APPLICATION_JSON).get(String.class), CourseSession.class));
        	}
        	return courseSessions;
        }

}