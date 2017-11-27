(function($) {
	// 保险公司验证
	jQuery.validator.addMethod("isCompany", function(value, element) {
				var tel = /公司$/;
				return this.optional(element) || (tel.test(value));
			}, "保险公司名称需以'公司'结尾");
})(jQuery);