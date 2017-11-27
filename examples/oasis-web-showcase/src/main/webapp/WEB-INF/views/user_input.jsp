<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<html>
<head>
<title>登录页</title>
</head>

<body>
	<h1>登录页</h1>
	<form:form id="loginForm" modelAttribute="user" action="${ctx}/user/create" method="POST">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<div class="control-group">
			<label for="username" class="control-label">名称:</label>
			<div class="controls">
			<!--
				<input type="text" id="username" name="username" value="${username}"
					class="input-medium required" />
					 -->
					<form:input path="username" />
			</div>
			<div><form:errors path="username" cssClass="error" /></div>
		</div>
		<div class="control-group">
			<label for="password" class="control-label">密码:</label>
			<div class="controls">
				<input type="password" id="password" name="password"
					class="input-medium required" />
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<label class="checkbox inline" for="rememberMe"> <input
					type="checkbox" id="rememberMe" name="rememberMe" /> 记住我
				</label> <input id="submit_btn" class="btn" type="submit" value="登录" />
			</div>
		</div>
	</form:form>
</body>
</html>
