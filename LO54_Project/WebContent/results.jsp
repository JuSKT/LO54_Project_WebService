<%@page import="com.fasterxml.jackson.databind.JsonNode"%>
<%@page import="com.fasterxml.jackson.core.type.TypeReference"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.ws.rs.core.UriBuilder"%>
<%@page import="javax.ws.rs.core.MediaType"%>
<%@page import="com.sun.jersey.api.client.config.DefaultClientConfig"%>
<%@page import="com.sun.jersey.api.client.config.ClientConfig"%>
<%@page import="com.sun.jersey.api.client.Client"%>
<%@page import="com.sun.jersey.api.client.WebResource"%>
<%@page import="com.lo54project.webservice.model.Location"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="accordion">

<%
	ObjectMapper mapper = new ObjectMapper(); 
	ClientConfig cConfig = new DefaultClientConfig();
	Client client = Client.create(cConfig);
	WebResource service = client.resource(UriBuilder.fromUri("http://localhost:8080/LO54_Project").build());
	
	JsonNode rootNode = mapper.readTree(service.path("rest").path("courses").accept(MediaType.APPLICATION_JSON).get(String.class));
	
	for(JsonNode n : rootNode.path("course")) 
	{
		out.println("<h3>" + n.path("code").textValue() + " - " + n.path("title").textValue() + "</h3>");
		out.println("<div><p>List of Course Sessions<p></div>");
	}
%>

</div>