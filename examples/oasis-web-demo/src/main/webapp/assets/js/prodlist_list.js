/**
 * 产品列表页面js
 */
define(function (require) {

    $("#skuSel").on("click", ".skuValueDiv li span", function () {
        var _this = $(this);
        var optId = _this.attr('data-tip')
        var skuForm = $("#skuForm");
        skuForm.append('<input id="__sku_value_' + optId + '" name="skuOptIds" value="' + optId + '" />')
        skuForm.submit();
    });

    $("#skuSel").on("click", "#selSkuRow .crumbAttr span", function () {
        var _this = $(this);
        var optId = _this.attr('data-tip')
        var skuInputId = '#__sku_value_' + optId;
        var skuInput = $(skuInputId);
        var skuForm = $("#skuForm");
        skuInput.remove();
        skuForm.submit();
    });

    $('.pagination').jqPagination({
        paged:function(page) {
            var skuForm = $("#skuForm");
            skuForm.append('<input id="__page_value" name="page" value="' + page + '" />');
            skuForm.submit();
        }
    });

});