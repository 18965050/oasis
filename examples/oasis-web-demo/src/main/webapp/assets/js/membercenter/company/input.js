$(document).ready(function() {
			$("#btnRtn").on("click", function() {
						window.location.href = "/membercenter/company/list";
					});

			if ($("#addTime").length > 0) {
				$('#addTime').datetimepicker({
							timeFormat : "HH:mm:ss",
							dateFormat : "yy-mm-dd"
						});
			}
			
			$("#inputForm").validate({
				rules:{
					comName:{
						required:true,
						maxlength:50,
						isCompany:true
					},
					status:"required",
					addTime:"date"
				}
			});

		});