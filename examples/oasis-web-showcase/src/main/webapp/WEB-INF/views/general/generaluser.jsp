<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>

<chaos:override name="title">综合演示 - 用户管理</chaos:override>

<chaos:override name="javascript">
  <script>
    __menu = "#menu-acc-admin";
  </script>
</chaos:override>

<chaos:override name="content">
  <div class="row clearfix">

    <iframe frameborder="0" src="${ctx}/acc/user/list" width="100%" height="1200"></iframe>

  </div>

</chaos:override>
<jsp:include page="../__template.jsp"/>
