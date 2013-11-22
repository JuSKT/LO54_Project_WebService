<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="./static/css/css.css" />

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="./static/js/jquery.cookie.js"></script>
<script>

function global(){
///////// Jquery for design ///////////////

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
	
///////// End Jquery for design ////////////

///////// Jquery for Filter ///////////////

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
	
	
	// detection bottom page
	$(function(){
		$(window).scroll(function() {

			if ($(this).height() + $(this).scrollTop() == $(document).height()) {
                alert('Scrolled to Page Bottom');
            }
		});
	});
	
	function filterCourseSessions(){
		// ask web service 
		// getJson
		// parse and display
	};

///////// End Jquery for Filter ///////////////

///////// Jquery for the choice and registration for the courseSessions ///////////////

	//gestion of the courseSessions coockie
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
	
	
	// rechecked everyThing if the page is refresh
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
	};
	
	// unchecked evrything
	function emptyCheck(){
		$.removeCookie('courseSessionsList');
		$(".coursesessions").each(function(){
			$(this).prop('checked', false);
		});
	};
	
	//action on click of uncheck all button 
	$(function() 
	{
		$("#unCheckAll").click(function(){
			emptyCheck();
		});
	});
	
	
	// call checkifRefresh after loading the page
	$(function() 
	{
		checkIfRefresh();
	});
	
	
	//dowload the form page
	$(function()
	{
		$("#goToForm").click(function(){
			$( "#sub-content" ).html("<img src='./static/images/ajax-loader.gif' alt='Loading' height='100' width='100'>");
			$.get( "./registerForm", function( data ) {
				$( "#sub-content" ).html( data );
				global();
			});
		});	
	});
	
	
	//add click event to the return button
	$(function()
	{
		$("#returnToIndex").click(function(){
			$( "#sub-content" ).html("<img src='./static/images/ajax-loader.gif' alt='Loading' height='100' width='100'>");
			$.post("./",function(data){
				$("#sub-content").html(data);
				global();
			});
		});
	});
	
	
	//send the form
	$(function()
	{
		$("#registerForm").submit(function(){
			var form = $(this).serialize();
			var courseSessionList = JSON.parse($.cookie('courseSessionsList'));
			for(var c in courseSessionList){
				form+="&id="+courseSessionList[c];
			}
			$.post("./rest/clients",form,function(data){
				if(data=="true"){
					emptyCheck();
					$("#returnToIndex").click();
				}
					
			}).fail(function(){
				alert("Problem on Server, Can you try again, please ?");
			});
			return false;
		});
	});
	
	
}

$(function(){
	global();
});
	
///////// End Jquery for the choice and registration for the courseSessions ///////////////

</script>