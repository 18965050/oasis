<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jspf/taglibs.jsp" %>
<div id="leftMenu" class="col-md-3">

  <div class="panel-group" id="panelMenu">
    <div class="panel panel-default">
      <div class="panel-heading">
        <a class="panel-title" data-toggle="collapse" data-parent="#panelMenu" href="#baseUtil">基础公共工具</a>
      </div>
      <div id="baseUtil" class="panel-collapse collapse">
        <ul class="nav nav-pills nav-stacked" role="tablist">
          <li><a href="/showcase/baseutils">Jakarta Commons & Google Guava</a></li>
        </ul>
      </div>
    </div>

    <div class="panel panel-default">
      <div class="panel-heading">
        <a class="panel-title" data-toggle="collapse" data-parent="#panelMenu" href="#compoundShow">综合演示</a>
      </div>
      <div id="compoundShow" class="panel-collapse collapse">
        <ul class="nav nav-pills nav-stacked" role="tablist">
          <li id="menu-acc-admin"><a href="${ctx}/general/general/user">用户管理</a></li>
          <%--<li id="menu-role-admin"><a href="#">角色管理</a></li>
          <li id="menu-pms-admin"><a href="#">权限管理</a></li>--%>
          <li class="nav-divider"/>
          <li id="menu-demo"><a href="${ctx}/general/general/demo">Chaos-Web-Demo</a></li>
        </ul>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <a class="panel-title" data-toggle="collapse" data-parent="#panelMenu" href="#Window">Window</a>
      </div>
      <div id="Window" class="panel-collapse collapse">
        <ul class="nav nav-pills nav-stacked" role="tablist">
          <li id="menu-window-integration"><a href="${ctx}/window/window/windowintegration">Window集成</a></li>
          <li id="menu-window-use"><a href="${ctx}/window/window/windowuse">Window使用</a></li>
          <!-- <li id="menu-window-extend"><a href="${ctx}/window/window/windowextend">Window扩展</a></li>  -->
        </ul>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <a class="panel-title" data-toggle="collapse" data-parent="#panelMenu" href="#Validator">Validator</a>
      </div>
      <div id="Validator" class="panel-collapse collapse">
        <ul class="nav nav-pills nav-stacked" role="tablist">
          <li id="menu-validator-integration"><a href="${ctx}/validator/validator/validatorintegration">Validator集成</a></li>
          <li id="menu-validator-use"><a href="${ctx}/validator/validator/validatoruse">Validator使用</a></li>
          <li id="menu-validator-extend"><a href="${ctx}/validator/validator/validatorextend">Validator扩展</a></li>
        </ul>
      </div>
    </div>

    <div class="panel panel-default">
      <div class="panel-heading">
        <a class="panel-title" data-toggle="collapse" data-parent="#panelMenu" href="#Security">Security</a>
      </div>
      <div id="Security" class="panel-collapse collapse">
        <ul class="nav nav-pills nav-stacked" role="tablist">
          <li id="menu-shiro-integration"><a href="${ctx}/security/security/shirointegration">Shiro集成</a></li>
          <li id="menu-shiro-use"><a href="${ctx}/security/security/shirouse">Shiro使用</a></li>
        </ul>
      </div>
    </div>

    <div class="panel panel-default">
      <div class="panel-heading">
        <a class="panel-title" data-toggle="collapse" data-parent="#panelMenu" href="#Ibatis">Ibatis</a>
      </div>
      <div id="Ibatis" class="panel-collapse collapse">
        <ul class="nav nav-pills nav-stacked" role="tablist">
          <li id="menu-ibatis-integration"><a href="${ctx}/ibatis/ibatis/ibatisintegration">Ibatis集成</a></li>
          <li id="menu-ibatis-generator"><a href="${ctx}/ibatis/ibatis/ibatisgenerator">代码生成</a></li>
          <li id="menu-ibatis-use"><a href="${ctx}/ibatis/ibatis/ibatisuse">Ibatis使用</a></li>
        </ul>
      </div>
    </div>

    <div class="panel panel-default">
      <div class="panel-heading">
        <a class="panel-title" data-toggle="collapse" data-parent="#panelMenu" href="#Mybatis">Mybatis</a>
      </div>
      <div id="Mybatis" class="panel-collapse collapse">
        <ul class="nav nav-pills nav-stacked" role="tablist">
          <li id="menu-mybatis-integration"><a href="${ctx}/mybatis/mybatis/mybatisintegration">Mybatis集成</a></li>
          <li id="menu-mybatis-generator"><a href="${ctx}/mybatis/mybatis/mybatisgenerator">代码生成</a></li>
          <li id="menu-mybatis-use"><a href="${ctx}/mybatis/mybatis/mybatisuse">Mybatis使用</a></li>
        </ul>
      </div>
    </div>

    <div class="panel panel-default">
      <div class="panel-heading">
        <a class="panel-title" data-toggle="collapse" data-parent="#panelMenu" href="#Paging">Easylist & Paging</a>
      </div>
      <div id="Paging" class="panel-collapse collapse">
        <ul class="nav nav-pills nav-stacked" role="tablist">
          <li id="menu-easylist-integration"><a href="${ctx}/easylist/easylist/integration">Easylist&Paging集成</a></li>
          <li id="menu-easylist-use"><a href="${ctx}/easylist/easylist/use">Easylist&Paging使用</a></li>
        </ul>
      </div>
    </div>

	<div class="panel panel-default">
		<div class="panel-heading">
			 <a class="panel-title" data-toggle="collapse" data-parent="#panelMenu" href="#Logging">Logging</a>
		</div>
		<div id="Logging" class="panel-collapse collapse">
	        <ul class="nav nav-pills nav-stacked" role="tablist">
	          <li ><a href="/showcase/logusage">日志使用</a></li>
	          <li ><a href="/showcase/logspec">日志规范</a></li>
	        </ul>
		</div>
	</div>

  </div>
</div>
