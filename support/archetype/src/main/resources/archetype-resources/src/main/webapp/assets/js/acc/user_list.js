/**
 * 用户列表页面js
 */
define(['bui/grid', 'bui/data', 'bui/form','bui/common','bui/overlay'], function(require){
    var Form = require('bui/form');
    var Grid = require('bui/grid');
    var Data = require('bui/data');
    var Store = Data.Store;
    
    // Ajax请求地址
    var ajaxUri = {
        "list" : {method : 'get', url : __ctx + '/acc/user/listjson'},
        "add" : {method : 'post', url : __ctx + '/acc/user/addjson'}
    }
    
    // 搜索表单
    var searchForm = new Form.HForm({
        srcNode : '#J_Form',
        defaultChildCfg : {
            validEvent : 'blur' // 移除时进行验证
        }
    }).render();
    
    // 触发筛选搜索
    searchForm.on('beforesubmit', function(){
        var params = this.serializeToObject();
        store.load(params);
        return false;
    });
    
    
    // 列表项
    var columns = [ {
        title : 'id',
        dataIndex : 'id',
        fixed : true,
        sortable : false,
        width : 30
    } ,{
        title : '用户名',
        dataIndex : 'name',
        sortable : false,
        width : '10%'
    },{
        title : '昵称',
        dataIndex : 'nickName',
        sortable : false,
        width : '10%'
    },{
        title : '邮箱',
        dataIndex : 'email',
        sortable : false,
        width : '15%'
    },{
        title : '手机',
        dataIndex : 'mobile',
        sortable : false,
        width : '12%'
    },{
        title : '状态',
        dataIndex : 'status',
        sortable : false,
        width : '5%',
        visible : false
    },{
        title : '微博',
        dataIndex : 'weiboUrl',
        sortable : false,
        width : '10%',
        visible : false
    },{
        title : '豆瓣',
        dataIndex : 'doubanUrl',
        sortable : false,
        width : '10%',
        visible : false
    },{
        title : '签名',
        dataIndex : 'signature',
        sortable : false,
        width : '10%',
        visible : false
    },{
        title : '创建时间',
        dataIndex : 'createTime',
        renderer : function(value){
            return BUI.Date.format(value,"yyyy-mm-dd HH:MM:ss", false);
        },
        sortable : false,
        width : 135
    },{
        title : '创建人',
        dataIndex : 'createUser',
        sortable : false,
        width : '10%'
    },{
        title : '更新时间',
        dataIndex : 'updateTime',
        renderer : function(value){
            return BUI.Date.format(value,"yyyy-mm-dd HH:MM:ss", false);
        },
        sortable : false,
        width : 135
    },{
        title : '最后操作人',
        dataIndex : 'updateUser',
        sortable : false,
        width : '10%',
        visible : false
    },{
        title : '操作',
        dataIndex : '',
        sortable : false,
        renderer : function(value,obj,index){
            var inner = '<span class="grid-command btn-edit">编辑</span>';
            return inner;
        },
        width : 45,
        visible : false
    }];
    
    // 数据源
    var store = new Store({
        autoSync : true,
        autoLoad : true,
        root : 'data',
        totalProperty : 'count',
        pageSize : 10,
        remoteSort : true,
        proxy : {
            url : ajaxUri['list'].url,
            method : ajaxUri['list'].method,
            pageStart : 1,
            limitParam : 'limit',
            pageIndexParam : 'pageIndex'
        },
        sortInfo : {
            field : 'create_time',
            direction : 'DESC' // 升序ASC，降序DESC
        },
        matchfunction:function(obj1, obj2){
            if(obj1 && obj2 && obj1.id == obj2.id){
                return true;
            }
            return false;
        }
    });
    
    // 编辑器
    var editing = new Grid.Plugins.DialogEditing({
        contentId : 'editContent', //设置隐藏的Dialog内容
        triggerCls : 'btn-edit', //触发显示Dialog的样式
        editor : {
          title : '用户信息编辑',
          width : 600,
          success : onAddBtnClick
        }
      });
    
    // 表格
    var grid = new Grid.Grid({
        render : '#grid',
        columns : columns,
        width : '100%',
        forceFit : false,
        loadMask : true,
        store : store,
        plugins : [editing/*,Grid.Plugins.CheckSelection*/],
        emptyDataTpl : '<div class="centered"><img alt="Crying" src="http://img03.taobaocdn.com/tps/i3/T1amCdXhXqXXXXXXXX-60-67.png"><h2>查询的数据不存在</h2></div>',
        bbar : {
            pagingBar : true
        },
        tbar:{ //添加、删除
            items : [{
              btnCls : 'button button-small',
              text : '<i class="icon-plus"></i>添加',
              listeners : {
                'click' : addFunction
              }
            }/*,
            {
              btnCls : 'button button-small',
              text : '<i class="icon-remove"></i>删除',
              listeners : {
                'click' : delFunction
              }
            }*/]
        }
    });
    grid.render();
    
    /**
     * 编辑框点击确定按钮
     */
    function onAddBtnClick(){
        var edtor = this,
        form = edtor.get('form'),
        record = edtor.get('record'),
        editType = editing.get('editType');
        
        var editUri = ajaxUri[editType];
        if(!editUri){
            console.log("none Url");
            return;
        }
        var url = editUri.url;
        var method = editUri.method;
        
        form.valid();
        if(form.isValid()){
            form.ajaxSubmit({
                url : url,
                method : method,
                success : function(data){
                    if(!data.result == "success"){
                        console.log(data);
                    }else {
                        var id = data.id;
                        var idField = new BUI.Form.Field({value : id, name : 'id'});
                        form.addChild(idField);
                        edtor.accept();
                    }
                },
                error : function(data){
                    console.error("Ajax请求出错 ",data);
                    edtor.close();
                }
                
            });
        }
        
    }
    
   //添加记录
    function addFunction(){
      var newData = {status : 1};
      editing.add(newData, 0); //添加记录后，直接编辑
    }
    
    //删除选中的记录
    function delFunction(){
      var selections = grid.getSelection();
      store.remove(selections);
    } 
    
    
    
    
});