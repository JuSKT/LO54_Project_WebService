<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<aside id="top-aside" style="text-align:left;">
<input type="button" id="returnToIndex" value="Go back" />
</aside>
<section id="main">
	<div>
		<ul>
			<c:forEach var="cs" items="${requestScope.courseSessions}">
				<li>
					<b>${cs.crs.code} - ${cs.crs.title}</b>
					<fmt:formatDate pattern="MM/dd/yyyy 'at' HH:mm" value="${cs.start}" />
					to
					<fmt:formatDate pattern="MM/dd/yyyy 'at' HH:mm" value="${cs.end}" />
					in 
					<b>${cs.loc.city}</b>
					<input type="checkbox" class="coursesessions" name="coursesessions" value="${cs.id}">
				</li>
			</c:forEach>
		</ul>
	</div>
	<div>
		<form action="./register" method="Post">
			Firstname:<input type="text" name="fn" /><br/>
			Lastname:<input type="text" name="ln" /><br/>
			Address:<input type="text" name="addr" /><br/>
			Phone Number:<input type="text" name="phone" /><br/>
			Email:<input type="text" name="email" /><br/>
			<input type="submit" value="register"/>
		</form>
	</div>
</section>