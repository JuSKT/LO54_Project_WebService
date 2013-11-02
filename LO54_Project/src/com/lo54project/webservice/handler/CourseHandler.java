package com.lo54project.webservice.handler;

import java.io.IOException;
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

public class CourseHandler 
{
	public List<Course> parseCourses() throws JsonProcessingException, UniformInterfaceException, ClientHandlerException, IOException
	{	
		List<Course> courses = new ArrayList<Course>();
		
		ObjectMapper mapper = new ObjectMapper(); 
		ClientConfig cConfig = new DefaultClientConfig();
		com.sun.jersey.api.client.Client client = com.sun.jersey.api.client.Client.create(cConfig);
		WebResource service = client.resource(UriBuilder.fromUri("http://localhost:8080/LO54_Project").build());

		JsonNode rootNode = mapper.readTree(service.path("rest").path("courses").accept(MediaType.APPLICATION_JSON).get(String.class));
		
		for(JsonNode n : rootNode.path("course")) 
		{
			courses.add(new Course(n.path("code").textValue(), n.path("title").textValue()));
		}
		
		return courses;
	}
}
