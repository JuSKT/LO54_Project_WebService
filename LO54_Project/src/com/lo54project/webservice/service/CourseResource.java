package com.lo54project.webservice.service;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.lo54project.webservice.dao.CourseDao;
import com.lo54project.webservice.model.Course;

// Will map the resource to the URL courses
@Path("/courses")
public class CourseResource 
{
	// Allows to insert contextual objects into the class
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the list of courses
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Course> getCourses() 
	{
		List<Course> courses = new ArrayList<Course>();
		courses.addAll(CourseDao.instance.getModel().values());
	    return courses; 
	}
	
	// Return the chosen course
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{course}")
	public Course getCourse(@PathParam("course") String code) 
	{
		Course course = CourseDao.instance.getCourse(code);
		
		if(course.getCode()==null)
		{
			throw new RuntimeException("Get: Course with " + code +  " not found");	  
		}
		
		return course;
	}
	   
	// Return the number of courses
	@GET
	@Path("count")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCount() 
	{
		int count = CourseDao.instance.getModel().size();
		return String.valueOf(count);
	}
	  
	// Put a course into response and return it 
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putCourse(JAXBElement<Course> course) 
	{
		Course c = course.getValue();
	    return putAndGetResponse(c);
	}
	  
	// Put a client into response and return it
	private Response putAndGetResponse(Course c) 
	{
		Response res;
		
		if(CourseDao.instance.getModel().containsKey(c.getCode())) 
		{
			res = Response.noContent().build();
	    } 
		else 
		{
			res = Response.created(uriInfo.getAbsolutePath()).build();
	    }
		
		CourseDao.instance.getModel().put(c.getCode(), c);
	    return res;
	}
}