#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/taglibs.jsp" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${symbol_dollar}{ctx}/assets/sea-modules/bui/css/bs3/dpl-min.css" type="text/css" rel="stylesheet" />
<link href="${symbol_dollar}{ctx}/assets/sea-modules/bui/css/bs3/bui-min.css" type="text/css" rel="stylesheet" />

<link href="${symbol_dollar}{ctx}/assets/css/admin/main.css" type="text/css" rel="stylesheet" />
<title>管理首页</title>
</head>
<body>
  <div class="header">
    <div class="dl-title">
      <span class="">管理后台</span>
    </div>
    <div class="dl-log">
      欢迎您，<span class="dl-log-user"><shiro:principal property="name"></shiro:principal></span> <a href="${symbol_dollar}{ctx }/logout"
        title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
  </div>
  <div class="content">
    <div class="dl-main-nav">
      <ul id="J_Nav"  class="nav-list ks-clear">
        <li class="nav-item dl-selected"><div class="nav-item-inner nav-storage">管理中心首页</div></li>
        <li class="nav-item"><div class="nav-item-inner nav-inventory">账号管理</div></li>
      </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">
    </ul>
   </div>
  <script src="${symbol_dollar}{ctx}/assets/sea-modules/sea.js" type="text/javascript" id="seajsnode" ></script>
  <script src="${symbol_dollar}{ctx}/assets/sea-modules/seajs-config.js" type="text/javascript" ></script>
  <script src="${symbol_dollar}{ctx}/assets/third-party/jquery/jquery-1.11.1.min.js" type="text/javascript" ></script>
  <script>
     seajs.use('{ctx}/admin/index');
  </script>
</body>
</html>