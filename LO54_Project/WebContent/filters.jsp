<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    
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

				<select style="width:220px;" name='location' onChange='changeByLocation(this);'>
				<option value='0'>Select a city ...</option>
					<c:forEach var="location" items="${requestScope.locations}">
						<option value="${location.id}">${location.city}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>
</center>