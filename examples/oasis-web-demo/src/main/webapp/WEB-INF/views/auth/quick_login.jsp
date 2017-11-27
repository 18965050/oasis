<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<input type="hidden" id="userId"  value="<shiro:principal property="id"/>" />

<div id="divLogin" style="display:none;">
	<form class="form-horizontal" id="loginForm" action="" method="post">
		<div id="errLoginDiv" class="panel panel-warning col-sm-offset-3" style="display:none;">
			<div class="panel-heading">
				<h3 class="panel-title">
					出错啦!
				</h3>
			</div>
			<div class="panel-body">
				<ul>
					<li>登录失败,请检查用户名/密码输入是否正确</li>
				</ul>
			</div>
		</div>
	
		<div class="form-group">
			 <label for="userName" class="col-sm-3 control-label" >用户名:</label>
			<div class="col-sm-9" >
				<input class="form-control" id="userName"  name="userName" placeholder="邮箱" type="text"></input>
			</div>
		</div>	
		
		<div class="form-group">
			 <label for="userName" class="col-sm-3 control-label" >密码:</label>
			<div class="col-sm-9" >
				<input class="form-control" id="password"  name="password" placeholder="密码" type="password"></input>
			</div>
		</div>		
		
		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-9">
				 <button type="button" id="btnLoginSubmit" class="btn btn-default" style="display:inline-block">提交</button>
				 &nbsp;&nbsp;&nbsp;
				 <button type="button" id="btnLoginRtn" class="btn btn-default">返回</button>
			</div>
		</div>	
	</form>	
</div>