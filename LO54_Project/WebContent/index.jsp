<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<jsp:include page="head.jsp"/>
		<title>LO54 Project</title>
	</head>
	
	<body>
		<div id="content">
			<header>
				<table>
					<tr>
						<td>
							<a href="./"><img src="./static/images/java_logo.png" width="70px"/></a>
						</td>
						<td>
							<h1>Find your course</h1>
						</td>
					</tr>
				</table>	
			</header>
			<div id="sub-content">
				<aside id="filters">
					<jsp:include page="filters.jsp"/>
				</aside>
				<section id="main">
					<jsp:include page="results.jsp"/>
				</section>
			</div>
		</div>
	</body>
</html>