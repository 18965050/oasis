$(document).ready(function() {
			$("#btnRtn").on("click", function() {
						window.location.href = "/membercenter/product/list";
					});

			if ($("#addTime").length > 0) {
				$('#addTime').datetimepicker({
							timeFormat : "HH:mm:ss",
							dateFormat : "yy-mm-dd"
						});
			};
			
			if ($("#updateTime").length > 0) {
				$('#updateTime').datetimepicker({
							timeFormat : "HH:mm:ss",
							dateFormat : "yy-mm-dd"
						});
			};
			
			$("#fileUpload").uploadify({
				swf: "/assets/third-party/uploadify/uploadify.swf",
				uploader: "/fileupload/upload",
				fileObjName:"fileUpload",
				buttonText:"选择图片",
				fileTypeExts:"*.jpg;",
				fileTypeDesc:"jpg文件(*.jpg)",
				cancelImg:"/assets/third-party/uploadify/img/uploadify-cancel.png",
				fileSizeLimit: "300KB",
				multi:false,
				height:30,
				width:120,
				onUploadSuccess: function(file,data,response){
					var obj=jQuery.parseJSON(data);
					$("#picLoc").val(obj.fileName);
				}
			});
			
			
			$("#inputForm").validate({
				rules:{
					prodName:{
						required:true,
						maxlength:50
					},
					prodAlias:{
						required:true,
						maxlength:50
					},
					limitQuantity:{
						required:true,
						digits:true
					},
					weight:{
						required:true,
						digits:true
					}
				}
			});			
			
		});