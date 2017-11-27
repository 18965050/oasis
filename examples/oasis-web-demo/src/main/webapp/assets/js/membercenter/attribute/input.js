$(document).ready(function(){
	$("#btnRtn").on("click", function() {
				window.location.href = "/membercenter/attribute/list";
			});
					
		var dlgOpt={
		autoOpen: false,
		height: "auto",
		width: 550,
		title:"选择标签",
		modal: true,
		buttons:{
			"确定":function(){
				$("#skuId").val($("input[type='radio']:checked").val());
				$("#selDialog").dialog("close");
			},
			"取消":function(){
				$("#selDialog").dialog("close");
			}
		}		
		};	
		
	$("#tagSel").on("click",function(){
		$("#selDialog").dialog(dlgOpt).dialog("open");
	});	
	
	$("#inputForm").validate({
		rules:{
			skuId:{
				required:true,
				digits:true
			},
			optionName:{
				required:true,
				maxlength:30
			},
			weight:{
				required:true,
				digits:true
			}
		},
		ignore: ""
	});	
	
	
});