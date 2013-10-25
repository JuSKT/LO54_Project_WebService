package com.lo54project.webservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.lo54project.webservice.dao.CourseSessionDao;
import com.lo54project.webservice.model.CourseSession;

@Path("/coursesessions")
public class CourseSessionResource {
	 // Allows to insert contextual objects into the class, 
	  // e.g. ServletContext, Request, Response, UriInfo
	  @Context
	  UriInfo uriInfo;
	  @Context
	  Request request;
	  
	  // Return the list of course_sessions for applications
	  @GET
	  @Produces({MediaType.APPLICATION_JSON})
	  public List<CourseSession> getCourseSessions() {
	    List<CourseSession> courseSession = new ArrayList<CourseSession>();
	    courseSession.addAll(CourseSessionDao.instance.getModel().values());
	    return courseSession; 
	  }
	  
	  // Defines that the next path parameter after course sessions is
	  // treated as a parameter and passed to the ClientResources
	  // Allows to type http://localhost:8080/de.vogella.jersey.todo/rest/todos/1
	  // 1 will be treaded as parameter course session and passed to CourseSessionResource
	  @GET
	  @Produces({MediaType.APPLICATION_JSON})
	  @Path("{coursesession}")
	  public CourseSession getCourseSession(@PathParam("coursesession") String id) {
		  CourseSession cs = CourseSessionDao.instance.getModel().get(Integer.valueOf(id));
		  if(cs==null){
			  throw new RuntimeException("Get: Course session with " + id +  " not found");
		  }
		  return cs;
	  }

}
