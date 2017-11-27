$(document).ready(function(){
	//分页
	$('#commentPaginator').jqPagination({
		current_page : $("#currentPage").val(),
		max_page : $("#totalPage").val(),
		paged : function(page) {
			$("#currentPage").val(page);
			$("#commentForm").submit();
		}
	});
	
	//查询按钮事件
	$("#btnQuery").on("click",function(){
		$("#currentPage").val(1);
		$("#commentForm").submit();
	});
});