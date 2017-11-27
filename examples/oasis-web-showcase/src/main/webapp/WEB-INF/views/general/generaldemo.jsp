<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>

<chaos:override name="title">综合演示 - 用户管理</chaos:override>

<chaos:override name="javascript">
  <script>
    __menu = "#menu-demo";
  </script>
</chaos:override>

<chaos:override name="content">
  <div class="row clearfix">
    <div id="cnblogs_post_body"><h1>Chaos-Web-Demo</h1>
      <hr>
      <p>chaos-web-demo项目通过一个简化版的保险项目展示了各个技术的实际运用。请诸位移步查看。</p>
    </div>
  </div>

</chaos:override>
<jsp:include page="../__template.jsp"/>
