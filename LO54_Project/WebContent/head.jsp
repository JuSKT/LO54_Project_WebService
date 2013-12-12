<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="./static/css/css.css" />

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="./static/js/jquery.cookie.js"></script>
<script>
var words="";
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
			if($(this).val()!=words){
				words=$(this).val();
				filterCourseSessions();
			}
		});
	});
	
	// Filter results by date
	$(function() 
	{
		$("#filterByDate").change(function(){
			filterCourseSessions();
		});
	});
	
	
	// Filter results by location
	$(function() 
	{
		$("#filterByLocation").change(function(){
			filterCourseSessions();
		});
	});
	
	
	// detection bottom page
	$(function(){
		$(window).scroll(function() {

			if ($(this).height() + $(this).scrollTop() == $(document).height()) {
               // alert('Scrolled to Page Bottom');
            }
		});
	});
	
	
	
	function filterCourseSessions(){
		var form = $("#filtersForm").serialize();
		$( "#accordion" ).html("<img src='./static/images/ajax-loader.gif' alt='Loading' height='100' width='100'>");
		$.post($("#filtersForm").attr("action"),form,function(data){	
				var html="<div id='accordion'>";
				if(data){
					var cs = data.course;
					if(typeof(cs.length) === "undefined"){
						html+="<h3>"+cs.code+" - "+cs.title+"</h3>";
						html+="<div><ul>";
						if(typeof(cs.courseSessions.length) === "undefined"){
							html+=getLiCS(cs.courseSessions.start,cs.courseSessions.end,cs.courseSessions.loc.city,cs.courseSessions.id);
							 
						}
						else{
							jQuery.each(cs.courseSessions, function(i, val) {
								html+=getLiCS(val.start,val.end,val.loc.city,val.id);
								 
							});
						}
						html+="</ul></div>";
					}
					else{
						jQuery.each(cs, function(i, val) {
							html+="<h3>"+val.code+" - "+val.title+"</h3>";
							html+="<div><ul>";
							if(typeof(val.courseSessions.length) === "undefined"){
								html+=getLiCS(val.courseSessions.start,val.courseSessions.end,val.courseSessions.loc.city,val.courseSessions.id);
							}
							else{
								jQuery.each(val.courseSessions, function(y, v) {
									html+=getLiCS(v.start,v.end,v.loc.city,v.id);
									 
								});
							}
							html+="</ul></div>";
						});
					}
				}
				html+="</div>";
				$("#accordion").remove();
				$("#main").append(html);
				$("#accordion").accordion();
				global();
		}).fail(function(){
			alert("Problem on Server, Can you,please, try again ?");
		});
	};
	
	function getLiCS(start,end,city,id){
		var d=start.substring(8,10);
		var m=start.substring(5,7);
		var y=start.substring(0,4);
		var h=start.substring(11,13);
		var min=start.substring(14,16);
		
		var html="<li>"
		+m+"/"+d+"/"+y+" at "+h+":"+min
		+" to ";
		d=end.substring(8,10);
		m=end.substring(5,7);
		y=end.substring(0,4);
		h=end.substring(11,13);
		min=end.substring(14,16);
		html+=m+"/"+d+"/"+y+" at "+h+":"+min
		+" in "
		+"<b>"+city+"</b>"
		+"<input type='checkbox' class='coursesessions' name='coursesessions' value='"+id+"'>"
		+"</li>";
		
		
		return html;
	}

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
		var courseSessionList = new Array();
		$("#goToForm").click(function(){
			
			if($.cookie('courseSessionsList')){
				courseSessionList = JSON.parse($.cookie('courseSessionsList'));
			}
			
			if(courseSessionList.length>0){
				
				$( "#sub-content" ).html("<img src='./static/images/ajax-loader.gif' alt='Loading' height='100' width='100'>");
				$.get( "./registerForm", function( data ) {
					$( "#sub-content" ).html( data );
					global();
				});
				
			}else{
				alert("Can You check something before sending the file");
			}
			
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
			if($.cookie('courseSessionsList')){
				courseSessionList = JSON.parse($.cookie('courseSessionsList'));
			}
			
			if(courseSessionList.length>0){

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
					alert("Problem on Server, Can you, please, try again ?");
				});
			
			}else{
				alert("Can You check something before sending the form.");
			}
			return false;
		});
	});
	
	
}

$(function(){
	global();
});
	
///////// End Jquery for the choice and registration for the courseSessions ///////////////

</script>