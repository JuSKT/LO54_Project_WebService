<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="./static/css/css.css" />

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<script>
	// Instanciation of the datepicker
	$(function() 
	{
    	$( "#datepicker" ).datepicker();
  	});
  
	// Instanciation of accordions
	$(function() 
	{
		$( "#accordion" ).accordion();
	});
	
	
	// Filter results by location
	function changeByLocation(id)
	{
		alert(id.value);
	}
	
	// Filter results by date
	function changeByDate(date)
	{
		alert(date.value);
	}
	
	
	// Filter results by words
	function changeByWords(words)
	{
		alert(words.value);
	}
</script>