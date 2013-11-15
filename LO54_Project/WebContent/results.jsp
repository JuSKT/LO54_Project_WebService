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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="accordion">
	<c:forEach var="course" items="${requestScope.courses}">
		<h3>${course.code} - ${course.title}</h3>
		<div>
			<ul>
				<c:forEach var="cs" items="${requestScope.coursesessions}">
					<c:if test="${cs.crs.code==course.code}">
						<li>
							<fmt:formatDate pattern="MM/dd/yyyy 'at' HH:mm" value="${cs.start}" />
							to
							<fmt:formatDate pattern="MM/dd/yyyy 'at' HH:mm" value="${cs.end}" />
							in 
							<b>${cs.loc.city}</b>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</c:forEach>
</div>