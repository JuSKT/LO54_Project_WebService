<%@page import="com.fasterxml.jackson.databind.JsonNode"%>
<%@page import="com.fasterxml.jackson.core.type.TypeReference"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="javax.ws.rs.core.UriBuilder"%>
<%@page import="javax.ws.rs.core.MediaType"%>
<%@page import="com.sun.jersey.api.client.config.DefaultClientConfig"%>
<%@page import="com.sun.jersey.api.client.config.ClientConfig"%>
<%@page import="com.sun.jersey.api.client.Client"%>
<%@page import="com.sun.jersey.api.client.WebResource"%>
<%@page import="com.lo54project.webservice.model.Course"%>
<%@page import="com.lo54project.webservice.model.CourseSession"%>
<%@page import="com.lo54project.webservice.model.Location"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="accordion">

<%
	List<Location> locations = (List<Location>)request.getAttribute("locations");
	List<Course> courses = (List<Course>)request.getAttribute("courses");
	List<CourseSession> coursesessions = (List<CourseSession>)request.getAttribute("coursesessions");
	
	for(Course c : courses)
	{
		out.println("<h3>" + c.getCode() + " - " + c.getTitle() + "</h3>");
		out.println("<div><ul>");
		
		for(CourseSession cs : coursesessions)
		{
			if(cs.getCrs().getCode().equals(c.getCode()))
			{				
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm");
				String start = sdf.format(cs.getStart());
				String end = sdf.format(cs.getEnd());
				
				out.println("<li>" + start + " to " + end + " in <b>" + locations.get(cs.getLoc().getId()).getCity() + "</b></li>");
			}
		}
		
		out.println("</ul></div>");
	}
%>

</div>