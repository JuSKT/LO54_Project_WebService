<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<aside id="top-aside" style="text-align:left;">
<input type="button" id="returnFromRegister" value="Go back" />
</aside>
<section id="main">
	<form action="./register" method="Post">
		Firstname:<input type="text" name="fn" /><br/>
		Lastname:<input type="text" name="ln" /><br/>
		Address:<input type="text" name="addr" /><br/>
		Phone Number:<input type="text" name="phone" /><br/>
		Email:<input type="text" name="email" /><br/>
		<input type="submit" value="register"/>
	</form>
</section>