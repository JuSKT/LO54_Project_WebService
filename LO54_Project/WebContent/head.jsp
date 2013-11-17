<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="./static/css/css.css" />

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="./static/js/jquery.cookie.js"></script>
<script>
	// Instanciation of the datepicker
	$(function() 
	{
    	$( "#filterByDate" ).datepicker();
  	});
  
	// Instanciation of accordions
	$(function() 
	{
		$( "#accordion" ).accordion();
	});
	
	$(function() 
	{
		$.cookie.json = true;
		$(".coursesessions").click(function(){
			
			var courseSessionList = new Array();
			
			if($(this).is(':checked')){
				
				if($.cookie('courseSessionsList')){
					courseSessionList = JSON.parse($.cookie('courseSessionsList'));
				}
				
				courseSessionList.push($(this).val());
				
			}else{
				
				courseSessionList = JSON.parse($.cookie('courseSessionsList'));
				var index = courseSessionList.indexOf($(this).val());
				if (index > -1) {
					courseSessionList.splice(index, 1);
				}
			}
			
			$.cookie('courseSessionsList',JSON.stringify(courseSessionList));
		});
	});
	
	// Filter results by words
	$(function() 
	{
		$("#filterByWords").keyup(function(){
			alert($(this).val());
		});
	});
	
	// Filter results by date
	$(function() 
	{
		$("#filterByDate").change(function(){
			alert($(this).val());
		});
	});
	
	
	// Filter results by location
	$(function() 
	{
		$("#filterByLocation").change(function(){
			alert($(this).val());
		});
	});
	
	function filterCourseSessions(){
		// ask web service 
		// getJson
		// parse and display
	};
	
	function checkIfRefresh(){
		$(".coursesessions").each(function(){
			if($.cookie('courseSessionsList')){
				var courseSessionList = JSON.parse($.cookie('courseSessionsList'));
				var index = courseSessionList.indexOf($(this).val());
				if (index > -1) {
					$(this).prop('checked', true);
				}
			}
		});
	}
	
	function emptyCheck(){
		$.removeCookie('courseSessionsList');
		$(".coursesessions").each(function(){
			$(this).prop('checked', false);
		});
	}
	
	$(function() 
	{
		$("#unCheckAll").click(function(){
			emptyCheck();
		});
	});
	
	
	$(function() 
	{
		checkIfRefresh();
	});
	

</script>