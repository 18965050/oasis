$(document).ready(function() {
			//tab项高亮
			$("#navTab a").each(function(i) {
						var text = $(this).text();
						if ($("#title").val().indexOf(text) >= 0) {
							$(this).parent().css("display","block");
						} else {
							$(this).parent().css("display","none");
						}
					});

		});
