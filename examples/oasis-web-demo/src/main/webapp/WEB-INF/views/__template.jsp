<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" >
<meta name="renderer" content="webkit">
<!-- title -->
<title>
  <chaos:block name="title"></chaos:block>
</title>
<!-- 公用CSS -->
<link href="${ctx}/assets/third-party/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/assets/css/common/common.css" type="text/css" rel="stylesheet" />
<!-- css -->
<chaos:block name="css"></chaos:block>
</head>
<body>
<!-- 头部 -->
<jsp:include page="/WEB-INF/views/fragments/common/header.jsp"></jsp:include>
<!-- 主要内容 -->
<div id="mainContent" class="container">
  <chaos:block name="context"/>
</div>
<!-- 尾部 -->
<jsp:include page="/WEB-INF/views/fragments/common/footer.jsp"></jsp:include>
<!-- 公用 javascript -->
<script src="${ctx}/assets/sea-modules/sea.js" type="text/javascript" id="seajsnode" ></script>
<script src="${ctx}/assets/sea-modules/seajs-config.js" type="text/javascript" ></script>
<script src="${ctx}/assets/third-party/jquery/jquery-1.11.1.min.js" type="text/javascript" ></script>
<script src="${ctx}/assets/third-party/bootstrap/js/bootstrap.min.js" type="text/javascript" ></script>
<script src="${ctx}/assets/js/header.js" charset="UTF-8"></script>
<!-- javascript -->
<chaos:block name="javascript"></chaos:block>
</body>
</html>