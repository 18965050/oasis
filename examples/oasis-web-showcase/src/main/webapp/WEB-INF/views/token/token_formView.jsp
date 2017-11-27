<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>

<chaos:override name="title">Overview - Chaos-Web-Showcase</chaos:override>

<chaos:override name="content">
  <div class="row">
    <div class="well">
      <h1>TOKEN 测试</h1>

      <p style="padding: 15px;">直接看代码</p>
    </div>
  </div>
  <div class="row">
    <div class="col-lg-12">
      <form role="form" method="post" action="${ctx}/token/token/form">
        <input name="_form_unique_id" type="hidden" value="${_newToken}"/>
        <input name="test" type="text">
        <button type="submit">提交</button>
      </form>
    </div>
  </div>

</chaos:override>
<jsp:include page="../__template.jsp"/>
