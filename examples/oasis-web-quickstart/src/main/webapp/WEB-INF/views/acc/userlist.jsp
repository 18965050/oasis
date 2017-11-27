<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx}/assets/sea-modules/bui/css/bs3/dpl-min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/assets/sea-modules/bui/css/bs3/bui-min.css" type="text/css" rel="stylesheet" />

<link href="${ctx}/assets/css/admin/main.css" type="text/css" rel="stylesheet" />
<title>管理首页</title>
</head>
<body>

  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span1" style="height: 1px"></div>
      <div id="context" class="span22">
      
        <div class="row-fluid">
          <h1 class="page-header">用户信息管理</h1>
          <form id="J_Form" class="form-horizontal">
            <div class="row-fluid">
              <div class="control-group span6">
                <label for="id" class="control-label">ID：</label>
                <div class="controls">
                  <input name="id" id="id" type="text" class="input-normal control-text" data-tip="{text:'请输入用户ID'}" data-rules="{number : true}">
                </div>
              </div>
              <div class="control-group span6">
                <label for="name" class="control-label">用户名：</label>
                <div class="controls">
                  <input name="name" id="name" type="text" class="input-normal control-text" data-tip="{text:'请输入用户名'}">
                </div>
              </div>
              <div class="control-group span6">
                <label for="nickName" class="control-label">昵称：</label>
                <div class="controls">
                  <input name="nickName" id="nickName" type="text" class="input-normal control-text" data-tip="{text:'请输入昵称'}">
                </div>
              </div>
              <div class="control-group span6">
                <label for="email" class="control-label">邮箱：</label>
                <div class="controls">
                  <input name="email" id="email" type="text" class="input-normal control-text" data-tip="{text:'请输入邮箱'}">
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span6">
                <label for="mobile" class="control-label">手机：</label>
                <div class="controls">
                  <input name="mobile" id="mobile" type="text" class="input-normal control-text" data-tip="{text:'请输入手机'}">
                </div>
              </div>
              <div class="control-group span6">
                <label class="control-label">状态：</label>
                <div class="controls">
                  <select name="status">
                    <option value="">所有</option>
                    <option value="1">未激活</option>
                    <option value="2">正常</option>
                    <option value="3">禁用</option>
                    <option value="4">已删除</option>
                  </select>
                </div>
              </div>
              <div class="control-group span10">
                <label for="createTimeBegin" class="control-label">创建日期：</label>
                <div class="controls bui-form-group" data-rules="{dateRange : true}">
                  <input id="createTimeBegin" name="betweenCreateTime[0]" data-tip="{text : '起始日期'}"
                    class="input-normal calendar calendar-time" type="text"><label>&nbsp;-&nbsp;</label> <input
                    name="betweenCreateTime[1]" data-tip="{text : '结束日期'}" class="input-normal calendar calendar-time"
                    type="text">
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-actions span5 offset3">
                <button type="submit" class="button button-primary">搜索</button>
                <button id="resetBtn" type="reset" class="button">清空</button>
              </div>
            </div>
          </form>

        </div>
        
        <hr/>
        
        <div class="row-fluid">
          <div id="grid"></div>
        </div>
        
        <div id="hideContent" class="hide">
        
          <div id="editContent">
            <form class="form-horizontal">
              <div class="row-fluid">
                <div class="control-group span24">
                  <label for="edit_name" class="control-label">用户名：</label>
                  <div class="controls">
                    <input name="name" id="edit_name" type="text" class="input-normal control-text" data-tip="{text:'请输入用户名'}">
                  </div>
                </div>
              </div>
              <div class="row-fluid">
                <div class="control-group span24">
                  <label for="edit_nickName" class="control-label">昵称：</label>
                  <div class="controls">
                    <input name="nickName" id="edit_nickName" type="text" class="input-normal control-text" data-tip="{text:'请输入昵称'}">
                  </div>
                </div>
              </div>
              <div class="row-fluid">
                <div class="control-group span24">
                  <label for="edit_email" class="control-label">邮箱：</label>
                  <div class="controls">
                    <input name="email" id="edit_email" type="text" class="input-normal control-text" data-tip="{text:'请输入邮箱'}">
                  </div>
                </div>
              </div>
              <div class="row-fluid">
                <div class="control-group span24">
                  <label for="edit_mobile" class="control-label">手机：</label>
                  <div class="controls">
                    <input name="mobile" id="edit_mobile" type="text" class="input-normal control-text" data-tip="{text:'请输入手机'}">
                  </div>
                </div>
              </div>
            </form>
          </div>
          
        </div>
        
        
        
      </div>
    </div>
  </div>


  <script src="${ctx}/assets/sea-modules/sea.js" type="text/javascript" id="seajsnode" ></script>
  <script src="${ctx}/assets/sea-modules/seajs-config.js" type="text/javascript" ></script>
  <script src="${ctx}/assets/third-party/jquery/jquery-1.11.1.min.js" type="text/javascript" ></script>
  <script>
     seajs.use('{ctx}/acc/user_list');
  </script>
</body>
</html>