<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@include file="/WEB-INF/jspf/taglibs.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员中心</title>

<link href="${ctx}/assets/third-party/bootstrap/css/bootstrap.css"
	rel="stylesheet" />
	
<link href="${ctx}/assets/css/common/common.css" type="text/css" rel="stylesheet" />	
<chaos:block name="css"></chaos:block>


</head>
<body>
<!-- 头部 -->
<jsp:include page="${ctx}/WEB-INF/views/fragments/common/header.jsp"></jsp:include>
	
	<div class="container" style="padding: 20px;">
		<div class="row clearfix">
			<div class="col-md-3  column">
				<div style="height:42px; width: 100%"></div>
				<!-- left menu -->
				<jsp:include page="${ctx}/WEB-INF/views/membercenter/left_menu.jsp" />
			</div>
			<div class="col-md-9 column">
				<!-- top nav -->
				<jsp:include page="${ctx}/WEB-INF/views/membercenter/top_nav.jsp" />

				<h2 class="mark">
					<!-- title -->
					<chaos:block name="title"></chaos:block>
				</h2>
				<div style="height: 50px; width: 100%"></div>

				<!-- center body -->
				<chaos:block name="body"></chaos:block>

			</div>
		</div>
	</div>

<!-- 尾部 -->
<jsp:include page="/WEB-INF/views/fragments/common/footer.jsp" />

	<script src="${ctx}/assets/third-party/jquery/jquery-1.11.1.min.js"
		charset="UTF-8"></script>
	<script src="${ctx}/assets/third-party/bootstrap/js/bootstrap.js"
		charset="UTF-8"></script>
	<script src="${ctx}/assets/js/membercenter/template.js" charset="UTF-8"></script>
	<script src="${ctx}/assets/js/header.js" charset="UTF-8"></script>
	<chaos:block name="js"></chaos:block>
	
</body>

</html>
