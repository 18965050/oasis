/**
 * 菜单
 * Created by lcg on 2014/9/25.
 */
define(function(require, exports, module){
    function Menu(menu){
        this.menu = menu;
    }
    module.exports = Menu;

    Menu.prototype.active = function() {
        var $menu = $(this.menu);
        $menu.addClass("active");
        $menu.parent().parent().removeClass("collapse");
        $menu.parent().parent().addClass("in");
    };

});
