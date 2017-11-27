$(document).ready(function(){
	//分页
	$('#tagPaginator').jqPagination({
		current_page : $("#currentPage").val(),
		max_page : $("#totalPage").val(),
		paged : function(page) {
			$("#currentPage").val(page);
			$("#tagForm").submit();
		}
	});
	
		//查询按钮事件
	$("#btnQuery").on("click",function(){
		$("#currentPage").val(1);
		$("#tagForm").submit();
	});
	
	var dlgOpt={
		autoOpen: false,
		height: "auto",
		width: 350,
		title:"",
		modal: true		
	};
	
	$("#btnRtn").on("click",function(){
		$("#editDialog").dialog(dlgOpt).dialog("close");
	});
	
	$("#btnAdd").on("click",function(){
		$("#editSkuId").text("");
		$("#editSkuName").val("");
		$("#editSkuName").removeAttr("readonly");
		$("#editSkuId").parent().parent().css("display","none");
		dlgOpt.title="新增标签";
		
	if($("#btnSubmit").css("display")=="none"){
		$("#btnSubmit").css("display","inline-block");
	}
		
		$("#editDialog").dialog(dlgOpt).dialog("open");
	});
	
	$("#update,#delete, #detail").on("click",function(){
		if($(this).text()=="修改"){
			$.ajax({
				url:"/membercenter/tag/input",
				type:"get",
				data:{
					skuId:$(this).attr("data")
				},
				success:function(msg){
					var obj=$.parseJSON(msg);
					$("#editSkuId").text(obj.id);
					$("#editSkuName").val(obj.name);
					$("#editSkuName").removeAttr("readonly");
					$("#editSkuId").parent().parent().css("display","block");
					dlgOpt.title="修改标签";		
						if($("#btnSubmit").css("display")=="none"){
							$("#btnSubmit").css("display","inline-block");
						}
					$("#editDialog").dialog(dlgOpt).dialog("open");
				}
			});		
		}else if ($(this).text()=="删除"){
			if(window.confirm("你真的确认要删除吗?")){
				window.location.href="/membercenter/tag/delete/"+$(this).attr("data");
			}		
		}else if ($(this).text()=="详情"){
			$.ajax({
				url:"/membercenter/tag/input",
				type:"get",
				data:{
					skuId:$(this).attr("data")
				},
				success:function(msg){
					var obj=$.parseJSON(msg);
					$("#editSkuId").text(obj.id);
					$("#editSkuName").val(obj.name);
					$("#editSkuName").attr("readonly","readonly");
					$("#editSkuId").parent().parent().css("display","block");
					dlgOpt.title="查看标签";
					if($("#btnSubmit").css("display")=="inline-block"){
						$("#btnSubmit").css("display","none");
					}
					$("#editDialog").dialog(dlgOpt).dialog("open");
				}
			});			
		}
		
	});
	
	$("#editForm").validate({
		rules:{
			editSkuName:"required"
		},
		submitHandler: function(form){
			$.ajax({
				url:"/membercenter/tag/update",
				type:"post",
				data:{
					skuId:$("#editSkuId").text(),
					skuName:$("#editSkuName").val()
				},
				success:function(msg){
					if(msg=="success"){
						window.location.href="/membercenter/tag/list";
					}else{
						$("#errDiv").css("display","inline-block");
					}
				}
			});			
		}
	});		
	
	
});