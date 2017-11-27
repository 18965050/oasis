<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>

<chaos:override name="title">综合演示 - 用户管理</chaos:override>

<chaos:override name="javascript">
  <script>
    $(document).ready(function () {
      $("#menu-shiro-use").addClass("active");
    });
  </script>
</chaos:override>

<chaos:override name="content">
  <div class="row clearfix">



  </div>

</chaos:override>
<jsp:include page="../__template.jsp"/>
