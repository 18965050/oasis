<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jspf/taglibs.jsp" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
          class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${ctx }/">Chaos-Web-Showcase</a>
    </div>
    <div class="collapse navbar-collapse pull-right">
      <ul class="nav navbar-nav">
        <shiro:authenticated>
          <li><a href="javascript:void(0)">你好:<shiro:principal property="name"/></a></li>
          <input type="hidden" id="userId" value="<shiro:principal property='id'/>"/>
          <li><a href="${ctx }/logout">退出</a></li>
        </shiro:authenticated>
        <shiro:notAuthenticated>
          <li><a href="${ctx }/auth/auth/login">登陆</a></li>
          <li><a href="${ctx }/auth/auth/register">注册</a></li>
        </shiro:notAuthenticated>

      </ul>
    </div>
    <!--/.nav-collapse -->
  </div>
</div>