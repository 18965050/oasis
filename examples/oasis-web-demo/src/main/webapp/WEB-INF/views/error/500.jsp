<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ page isErrorPage="true" %>

<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%	
	//设置返回码200，避免浏览器自带的错误页面
	response.setStatus(200);
	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(exception.getMessage(), exception);
%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>500 - 系统内部错误</title>
	<link href="${ctx}/assets/third-party/bootstrap/css/bootstrap.css"
	rel="stylesheet" />
	<link href="${ctx}/assets/css/common/common.css" type="text/css" rel="stylesheet" />	
</head>

<body>
	<!-- 头部 -->
<jsp:include page="${ctx}/WEB-INF/views/fragments/common/header.jsp"></jsp:include>

	
	<div class="container" style="padding: 100px;">
		<div class="row clearfix">
			<div>
				<img alt="系统内部错误" src="${ctx }/assets/images/common/500.jpg">
			</div>
		</div>
	</div>
	
<!-- 尾部 -->
<jsp:include page="/WEB-INF/views/fragments/common/footer.jsp"></jsp:include>	
</body>
</html>



