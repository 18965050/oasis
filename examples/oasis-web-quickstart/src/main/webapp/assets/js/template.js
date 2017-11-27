$(document).ready(function(){
	var reqURI=$("#reqURI").val();
	
	$("#panelMenu li").each(function(i){
		var child=$(this).children()[0];
		if(reqURI.indexOf($(child).attr("href"))>-1){
			$(this).addClass("active");
			$(this).parent().parent().removeClass("collapse");
			$(this).parent().parent().addClass("in");
		}
	});
});