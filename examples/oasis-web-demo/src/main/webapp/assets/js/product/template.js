$(document).ready(function(){
	$("#btnCollect").on("click",function(){
		$.ajax({
				url:"/proddetail/addFavorite",
				type:"get",
				data:{
					prodId:$("#prodId").val(),
					userId:$("#userId").val()
				},
				success:function(msg){
					var obj=$.parseJSON(msg);
					if(obj.retCode=="1"){
						alert(obj.msg);
					}else{
						$("#dropMenu").append("<li ><a tabindex='-1' href='/proddetail/detail/"+$("#prodId").val()+"'>"+$("h1").text()+"</a></li>");
					}
				}
			});			
	});
	
	$("#aDetail, #aComment").on("click",function(){
		if($(this).attr("id")=="aDetail"){
			$(this).parent().addClass("active");
			$("#aComment").parent().removeClass("active");
			$("#tblDetail").show();
			$("#divComment").hide();
		}else{
			$(this).parent().addClass("active");
			$("#aDetail").parent().removeClass("active");
			$("#tblDetail").hide();
			$("#divComment").show();	
		}
	});
	
	var dlgOpt={
		autoOpen: false,
		height: "auto",
		width: 400,
		title:"",
		modal: true		
	};
	
	$("#btnComment").on("click",function(){
		$.ajax({
				url:"/proddetail/commentinput",
				type:"get",
				success:function(msg){
					if(msg=="login"){
						dlgOpt.title="登录";
						$("#divLogin").dialog(dlgOpt).dialog("open");
					}else{
						dlgOpt.title="评价";
						$("#divMyComment").dialog(dlgOpt).dialog("open");
					}
				}
			});			
	});
	
	$("#btnRtn").on("click",function(){
		$("#divMyComment").dialog("close");
	});
	
	$("#btnSubmit").on("click",function(){
		$.ajax({
				url:"/proddetail/addcomment",
				type:"post",
				data:{
					prodId:$("#prodId").val(),
					userId:$("#userId").val(),
					content:$("#content").val(),
					score:$("#score").val()
				},
				success:function(msg){
					if(msg=="fail"){
						$("#errDiv").show();
					}else{
						$("#divMyComment").dialog("close");
						$("#divComment").prepend("<blockquote><p>"+$("#content").val()+"</p><small>"+$("#currentUserName").val()+"</small></blockquote>");
					}
				}
			});		
	});
	
});