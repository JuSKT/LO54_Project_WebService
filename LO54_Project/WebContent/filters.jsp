<%@page import="com.fasterxml.jackson.databind.JsonNode"%>
<%@page import="com.fasterxml.jackson.core.type.TypeReference"%>
<%@page import="java.util.List"%>
<%@page import="javax.ws.rs.core.UriBuilder"%>
<%@page import="javax.ws.rs.core.MediaType"%>
<%@page import="com.lo54project.webservice.model.Location"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<center>
	<table style="width: 990px;">
		<tr>
			<td style="width: 320px;">
				Filter by <b>words</b> : <input style="width:190px;" type="text" name="word" placeholder="Type words .." onKeyUp='changeByWords(this);'/>
			</td>
			
			<td style="width: 10px;text-align:center;">|</td>
			
			<td style="width: 320px;">
				By <b>date</b> : <input type="text" style="width:242px;" name="date" id="datepicker" placeholder="Select a date .." onChange='changeByDate(this);'/>
			</td>
			
			<td style="width: 10px;text-align:center;">|</td>

			<td style="width: 320px;">
				By <b>location</b> :  

				<%
					List<Location> locations = (List<Location>)request.getAttribute("locations");
					
					out.println("<select style=\"width:220px;\" name='location' onChange='changeByLocation(this);'>");
					out.println("<option value='0'>Select a city ..</option>");
					
					for(Location loc : locations) 
					{
						out.println("<option value='"+ loc.getId() +"'>"+ loc.getCity() +"</option>");
					}
					
					out.println("</select>");
				%>
			</td>
		</tr>
	</table>
</center>