<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" >
<meta name="renderer" content="webkit">
<title>登录页</title>
<link href="${ctx}/assets/non-project/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/assets/css/auth_login.css" type="text/css" rel="stylesheet" />

<script src="${ctx}/assets/sea-modules/sea.js" type="text/javascript" id="seajsnode"></script>
<script src="${ctx}/assets/sea-modules/seajs-config.js" type="text/javascript"></script>
</head>

<body>
      <div class="container">

      <form class="form-signin" role="form" action="${ctx}/auth/login" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <c:if test="${not empty loginErrorMsg}">
          <div class="alert alert-error controls input-large">
            <!-- <button class="close" data-dismiss="alert">×</button> -->
            <c:out value="${loginErrorMsg }" />
          </div>
        </c:if>
        <input class="form-control" id="username" name="username"  placeholder="用户名" required autofocus>
        <input type="password" id="password" name="password" class="form-control" placeholder="密码" required>
<!--         <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div> -->
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      </form>

    </div>

</body>
</html>