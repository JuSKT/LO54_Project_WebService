package com.lo54project.webservice.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import com.lo54project.webservice.dao.CourseSessionDao;
import com.lo54project.webservice.model.CourseSession;

/** Class which will map the resource to the URL course_sessions */
@Path("/coursesessions")
public class CourseSessionResource 
{
	// Allows to insert contextual objects into the class
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	/** Return the list of course_sessions
	 * 
	 * @return courseSession
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<CourseSession> getCourseSessions() 
	{
		List<CourseSession> courseSession = new ArrayList<CourseSession>();
	    courseSession.addAll(CourseSessionDao.instance.getModel().values());
	    return courseSession; 
	}
	  
	/** Return the chosen course_session
	 * 
	 * @param id
	 * @return cs
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("id/{coursesession}")
	public CourseSession getCourseSessionById(@PathParam("coursesession") String id) 
	{
		CourseSession cs = CourseSessionDao.instance.getModel().get(Integer.valueOf(id));
		
		if(cs==null)
		{
			throw new RuntimeException("Get: Course session with " + id +  " not found");
		}
		
		return cs;
	}
	
	/** Return the list of course_sessions limited
	 * 
	 * @param lMin, lMax
	 * @return courseSession
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("limit/{lMin}/{lMax}")
	public List<CourseSession> getCourseSessionsWithLimit(@PathParam("lMin") String lMin,@PathParam("lMax") String lMax) throws SQLException, ParseException 
	{
		List<CourseSession> courseSession = new ArrayList<CourseSession>();
	    courseSession.addAll(CourseSessionDao.instance.getCourseSessionsWithLimit(lMin, lMax).values());
	    return courseSession; 
	}
	
	/** Return the chosen course_session by course_code
	 * 
	 * @param course_code
	 * @return cs
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("course_code/{coursesession}")
	public List<CourseSession> getCourseSessionByCourseCode(@PathParam("coursesession") String course_code) throws SQLException, ParseException 
	{
		List<CourseSession> courseSession = new ArrayList<CourseSession>();
		courseSession.addAll(CourseSessionDao.instance.getCourseSessionByCourseCode(course_code).values());
	    return courseSession;
	}
	
	/** Return the list of course_sessions filtred
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("filtre/{name}/{date}/{location}")
	public List<CourseSession> getCourseSessionFiltered(@PathParam("name") String name, @PathParam("date") String date ,@PathParam("location") String location) throws SQLException, ParseException 
	{
		List<CourseSession> courseSession = new ArrayList<CourseSession>();
	    courseSession.addAll(CourseSessionDao.instance.getCourseSessionFiltered(name, date, location).values());
	    return courseSession; 
	}
	
	/** Return the list of course_sessions filtred
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<CourseSession> getCourseSessionFilteredV2(@FormParam("name") String name, @FormParam("date") String date ,@FormParam("location") String location, @Context HttpServletResponse servletResponse) throws SQLException, ParseException 
	{
		List<CourseSession> courseSession = new ArrayList<CourseSession>();
	    courseSession.addAll(CourseSessionDao.instance.getCourseSessionFiltered(name, date, location).values());
	    return courseSession; 
	}
	
	
	/** Return the chosen course_session
	 * 
	 * @param location
	 * @return courseSession
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("location/{coursesession}")
	public List<CourseSession> getCourseSessionByLocation(@PathParam("coursesession") String location) 
	{
		List<CourseSession> courseSession = new ArrayList<CourseSession>();
		for (Entry<Integer, CourseSession> en : CourseSessionDao.instance.getModel().entrySet()) {
			if(en.getValue().getLoc().getCity().contains(location)){
				courseSession.add(en.getValue());
			}
		}
		
		return courseSession;
	}
	
	/** Return the number of course_sessions
	 * 
	 * @return count
	 */
	@GET
	@Path("count")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCount() 
	{
		int count = CourseSessionDao.instance.getModel().size();
		return String.valueOf(count);
	}
	
	/** Put a course_session into response and return it 
	 * 
	 * @param course_session
	 * @return c
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putCourseSession(JAXBElement<CourseSession> course_session) 
	{
		CourseSession c = course_session.getValue();
	    return putAndGetResponse(c);
	}
	  
	/** Put a course_session into response and return it
	 * 
	 * @param c
	 * @return res
	 */
	private Response putAndGetResponse(CourseSession c) 
	{
		Response res;
		
		if(CourseSessionDao.instance.getModel().containsKey(c.getId())) 
		{
			res = Response.noContent().build();
	    } 
		else 
		{
			res = Response.created(uriInfo.getAbsolutePath()).build();
	    }
		
		CourseSessionDao.instance.getModel().put(c.getId(), c);
	    return res;
	}
}
