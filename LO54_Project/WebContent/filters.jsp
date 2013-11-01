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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>Filters</h2>
By Word:<input type="text" name="word" placeholder="Search"/><br/>
By date:<input type="text" name="date" id="datepicker" placeholder="click"/><br/>
By Location: 
<%
ObjectMapper mapper = new ObjectMapper(); 
ClientConfig cConfig = new DefaultClientConfig();
Client client = Client.create(cConfig);
WebResource service = client.resource(UriBuilder.fromUri("http://localhost:8080/LO54_Project").build());

JsonNode rootNode = mapper.readTree(service.path("rest").path("locations").accept(MediaType.APPLICATION_JSON).get(String.class));
out.println("<select name='location'>");
out.println("<option value='0'>Choisissez une ville...</option>");
for(JsonNode n : rootNode.path("location")) {
	out.println("<option value='"+n.path("id").textValue()+"'>"+n.path("city").textValue()+"</option>");
}
out.println("</select>");
%>
