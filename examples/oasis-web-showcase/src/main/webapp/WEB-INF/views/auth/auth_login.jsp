<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>
<chaos:override name="title">
  登陆
</chaos:override>

<chaos:override name="content">
  <form class="form-signin" role="form" action="${ctx}/auth/auth/login" method="post">
    <h2 class="form-signin-heading">请登录</h2>
    <c:if test="${not empty loginErrorMsg}">
      <div class="alert alert-error controls input-large">
        <button class="close" data-dismiss="alert">×</button>
        <c:out value="${loginErrorMsg }" />
      </div>
    </c:if>
    <input class="form-control" id="username" name="username"  placeholder="邮箱" required autofocus>
    <input type="password" id="password" name="password" class="form-control" placeholder="密码" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
  </form>
</chaos:override>

<jsp:include page="__template.jsp"/>