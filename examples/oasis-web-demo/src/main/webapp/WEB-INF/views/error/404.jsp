<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>

<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>404 - 页面不存在</title>
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
				<img alt="页面不存在" src="${ctx }/assets/images/common/404.jpg">
			</div>
		</div>
	</div>
	
<!-- 尾部 -->
<jsp:include page="/WEB-INF/views/fragments/common/footer.jsp"></jsp:include>	
</body>
</html>
