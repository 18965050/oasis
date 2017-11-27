$(document).ready(function(){
	//分页
	$('#attrPaginator').jqPagination({
		current_page : $("#currentPage").val(),
		max_page : $("#totalPage").val(),
		paged : function(page) {
			$("#currentPage").val(page);
			$("#attrForm").submit();
		}
	});
	
	//查询按钮事件
	$("#btnQuery").on("click",function(){
		$("#currentPage").val(1);
		$("#attrForm").submit();
	});
	
	//新增属性
	$("#btnAdd").on("click",function(e){
		window.location.href="/membercenter/attribute/input";
	});
	
	$("#update,#delete,#detail").on("click",function(){
		if($(this).text()=="修改"){
			var url=$(this).attr("url").substring(0,$(this).attr("url").lastIndexOf("/"));
			var data=$(this).attr("url").substring($(this).attr("url").lastIndexOf("/")+1);
			window.location.href=url+"?skuOptionId="+data;
		}else if ($(this).text()=="删除"){
			if(window.confirm("你真的确认要删除吗?")){
				window.location.href=$(this).attr("url");
			}		
		}else if ($(this).text()=="详情"){
			window.location.href=$(this).attr("url");
		}
	});
	
});