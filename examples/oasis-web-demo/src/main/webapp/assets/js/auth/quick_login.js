$(document).ready(function(){
	$("#btnLoginRtn").on("click",function(){
		$("#divLogin").dialog(dlgOpt).dialog("close");
	});
	
	$("#btnLoginSubmit").on("click",function(){
		$.ajax({
				url:"/auth/auth/qlogin",
				type:"post",
				data:{
					email:$("#userName").val(),
					password:$("#password").val()
				},
				success:function(msg){
					var obj=$.parseJSON(msg);
					if(obj.retCode==0){
						$("#divLogin").dialog("close");
						$("#userId").val(obj.userId);
					}else{
						$("#errLoginDiv").show();
					}
				}
			});			
	});
	
});