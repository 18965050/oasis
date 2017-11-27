$(document).ready(function(){
	var prodAttrs=$("#prodAttr").val().split(",");
	
	$("#tagDiv a").on("click",function(){
		$.ajax({
			url:"/membercenter/attribute/gettagattrs",
			type:"get",
			data:{
				tagId:$(this).attr("data")
			},
			success:function(msg){
				var obj=$.parseJSON(msg);
				$("#attrDiv").empty();
				$.each(obj,function(i,n){
					if($.inArray(n.attrId,prodAttrs)!=-1){
						$("#attrDiv").append("<div class='list-group-item'><input type='checkbox' checked='checked' value='"+n.attrId+"'>"+n.attrName+"</input></div>");
					}else{
						$("#attrDiv").append("<div class='list-group-item'><input type='checkbox' value='"+n.attrId+"'>"+n.attrName+"</input></div>");
					}
				});	

			}
		});
	});
	
	$("#attrDiv").delegate("div input[type='checkbox']","click",function(){
		if(this.checked){
			if($.inArray(this.value,prodAttrs)==-1){
				prodAttrs.push(this.value);
			}
		}else{
			prodAttrs.splice($.inArray(this.value,prodAttrs),1);
		}
	});
	
	$("#btnBind").on("click",function(){
		$.post(
		"/membercenter/product/bindattr",
		{prodId: $("#prodId").val(),prodAttr:prodAttrs.toString()},
		function(msg){
			window.location.href=msg;
		}
		);
	});
	
	$("#btnRtn").on("click",function(){
		window.location.href="/membercenter/product/list";
	});
	
});

