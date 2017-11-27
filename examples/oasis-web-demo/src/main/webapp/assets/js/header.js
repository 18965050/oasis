$(document).ready(function(){
	$("#dropMenu").empty();
	if($("#userId").length>0){
		$.ajax({
			url:"/membercenter/favorite/get/"+$("#userId").val(),
			success:function(msg){
				var favoArr=$.parseJSON(msg);
				for(var i=0;i<favoArr.length;i++){
					$("#dropMenu").append("<li ><a tabindex='-1' href='/proddetail/detail/"+favoArr[i].prodId+"'>"+favoArr[i].prodName+"</a></li>");
				}
			}
		});		
	}
});