<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<aside id="top-aside">
	<jsp:include page="filters.jsp"/>
</aside>
<section id="main">
	<div style="padding-left:10px;padding-top:5px;">
		<span>After checked all the courses go to the register form: </span>
		<input type="button" id="goToForm" value="Register here" />
		<span>or if you prefer unchecked everything</span>
		<input type="button" id="unCheckAll" value="Uncheck all here" />.
	</div>
	<div id="accordion">
		<jsp:include page="results.jsp"/>
	</div>
</section>