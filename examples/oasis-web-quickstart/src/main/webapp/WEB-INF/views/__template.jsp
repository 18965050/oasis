<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
  <meta name="renderer" content="webkit">
  <!-- title -->
  <title>
    <chaos:block name="title"></chaos:block>
  </title>
  <!-- 公用CSS -->
  <link href="${ctx}/assets/third-party/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
  <link href="${ctx}/assets/css/common/common.css" type="text/css" rel="stylesheet"/>
  <!-- css -->
  <chaos:block name="css"></chaos:block>
</head>
<body>
<!-- 头部 -->
<jsp:include page="/WEB-INF/views/fragments/common/header.jsp"></jsp:include>
<!-- 主要内容 -->
<div id="mainContent" class="container">
  <div class="row">
    <div id="rightContent" class="col-md-12">
      <chaos:block name="content"/>
      <input id="reqURI" type="hidden" value="${reqURI}">
    </div>
  </div>
</div>
<!-- 尾部 -->
<jsp:include page="/WEB-INF/views/fragments/common/footer.jsp"></jsp:include>
<!-- 公用 javascript -->
<script src="${ctx}/assets/sea-modules/sea.js" type="text/javascript" id="seajsnode"></script>
<script src="${ctx}/assets/sea-modules/seajs-config.js" type="text/javascript"></script>
<script src="${ctx}/assets/third-party/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/third-party/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/js/template.js" type="text/javascript"></script>
<!-- javascript -->
<chaos:block name="javascript"></chaos:block>
<script>
  seajs.use('{ctx}/common/common', function (Menu) {
    if(typeof __menu != "undefined" && __menu) {
      var menu = new Menu(__menu);
      menu.active();
    }
  });
</script>
</body>
</html>
