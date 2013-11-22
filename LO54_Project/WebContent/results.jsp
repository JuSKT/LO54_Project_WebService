<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
						<input type="checkbox" class="coursesessions" name="coursesessions" value="${cs.id}">
					</li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</c:forEach>