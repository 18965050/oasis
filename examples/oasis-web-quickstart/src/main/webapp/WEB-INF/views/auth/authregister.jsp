<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>
<chaos:override name="title">
  注册
</chaos:override>

<chaos:override name="javascript">
  <script src="${ctx}/assets/third-party/jquery-validation-1.13.0/jquery.validate.min.js" type="text/javascript" ></script>
  <script>
  	seajs.use('{ctx}/auth/auth_register');
  </script>
</chaos:override>

<chaos:override name="content">
  <form id="form" class="form-horizontal form-signin" role="form" action="${ctx}/auth/auth/registerpost" method="post">
    <h2 class="form-signin-heading">请注册</h2>
    <c:if test="${'fail' eq result}">
      <div class="alert alert-error controls input-large">
        <button class="close" data-dismiss="alert">×</button>
        <c:out value="${msg }" />
        <c:if test="${error != null}">
            服务端校验错误，请不要禁用javascript
        </c:if>
      </div>
    </c:if>
    <div class="form-group">
      <!-- <label for="email" class="col-sm-4 control-label">邮箱</label> -->
      <div class="col-sm-12">
        <input type="email" class="form-control" id="email" name="email" placeholder="Email">
      </div>
    </div>
    <div class="form-group">
      <!-- <label for="password" class="col-sm-4 control-label">密码</label> -->
      <div class="col-sm-12">
        <input type="password" id="password" name="password" class="form-control" placeholder="密码">
      </div>
    </div>
    <div class="form-group">
    <!--   <label for="rptPassword" class="col-sm-4 control-label">重复密码</label> -->
      <div class="col-sm-12">
        <input type="password" id="rptPassword" name="rptPassword" class="form-control" placeholder="重复密码">
      </div>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
  </form>
</chaos:override>
<jsp:include page="__template.jsp"/>