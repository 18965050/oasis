<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/taglibs.jsp"%>


<div class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
          class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${ctx }/">Chaos-Web-Demo</a>
    </div>
    <div class="collapse navbar-collapse pull-right">
      <ul class="nav navbar-nav">
      <shiro:authenticated>
      	<li><a href="javascript:void(0)">你好:<shiro:principal property="name"/></a></li>
      </shiro:authenticated>

        <li><a href="${ctx}/membercenter/membercenter/index">会员中心</a></li>
        <shiro:authenticated>
          <li>
			<div class="dropdown" style="margin-top: 12px">
			  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
			    收藏夹
			    <span class="caret"></span>
			  </button> 
			 <ul class="dropdown-menu" role="menu" id="dropMenu" aria-labelledby="dropdownMenu1">
			  </ul>  
			</div> 
			        
          </li>
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