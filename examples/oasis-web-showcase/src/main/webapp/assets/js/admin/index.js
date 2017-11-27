/**
 * 管理首页JS
 */
define(['{ctx}/admin/main'],function(require){
    require('{ctx}/admin/main');
    var config = [{
        id:'index',
        menu:[{
            text:'业务概况',
            items:[
              {id:'welcome',text:'介绍',href:'/'}
            ]
          },{
              text:'系统概况',
              items:[
                {id:'cpu-info',text:'CPU使用情况',href:''},
                {id:'mem-info',text:'内存使用情况',href:''}
              ]
            }]
        },{
          id:'acc',
          menu:[{
              text:'账户管理',
              items:[
                {id:'acc_manage',text:'账号管理',href:''},
                {id:'user_manage',text:'用户信息管理',href:__ctx + '/acc/user/list'},
                {id:'role_manage',text:'角色管理',href:''},
                {id:'pms_manage',text:'权限管理',href:''}
              ]
            }]
        }];
    
    
    
    
    
    
    new PageUtil.MainPage({
      modulesConfig : config
    });
});