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
    
<center>
	<table style="width: 990px;">
		<tr>
			<td style="width: 320px;">
				Filter by <b>words</b> : <input style="width:190px;" type="text" name="word" placeholder="Type words .."/>
			</td>
			
			<td style="width: 10px;text-align:center;">|</td>
			
			<td style="width: 320px;">
				By <b>date</b> : <input type="text" style="width:242px;" name="date" id="datepicker" placeholder="Select a date .."/>
			</td>
			
			<td style="width: 10px;text-align:center;">|</td>

			<td style="width: 320px;">
				By <b>location</b> :  

				<%
					ObjectMapper mapper = new ObjectMapper(); 
					ClientConfig cConfig = new DefaultClientConfig();
					Client client = Client.create(cConfig);
					WebResource service = client.resource(UriBuilder.fromUri("http://localhost:8080/LO54_Project").build());
					
					JsonNode rootNode = mapper.readTree(service.path("rest").path("locations").accept(MediaType.APPLICATION_JSON).get(String.class));
					
					out.println("<select style=\"width:220px;\" name='location'>");
					out.println("<option value='0'>Select a city ..</option>");
					
					for(JsonNode n : rootNode.path("location")) 
					{
						out.println("<option value='"+n.path("id").textValue()+"'>"+n.path("city").textValue()+"</option>");
					}
					
					out.println("</select>");
				%>
			</td>
		</tr>
	</table>
</center>