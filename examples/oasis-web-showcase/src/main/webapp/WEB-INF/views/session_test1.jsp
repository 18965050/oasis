<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>
<html>
<head>

</head>
<body>

内容 应用：<c:out value="${appNum}" />  参数：<c:out value="${var}" /> <br/>

<c:set var="_s_var" value="${var}" scope="session"/>

<%-- 测试 --%>

include1: <c:import url="http://localhost/session/test2;JSESSIONID=5afdf373-b1cf-4423-a607-bbd8e96577d0"/><br/>

include2: <c:import url="http://localhost/session/test2;JSESSIONID=5afdf373-b1cf-4423-a607-bbd8e96577d0"/><br/>

include3: <c:import url="http://localhost/session/test2;JSESSIONID=5afdf373-b1cf-4423-a607-bbd8e96577d0"/><br/>

<%--include4: <c:import url="http://localhost/session/test2;JSESSIONID=5afdf373-b1cf-4423-a607-bbd8e96577d0"/><br/>--%>

尾一 应用：<c:out value="${appNum}" />  参数 <c:out value="${_s_var}" /> <br/>

</body>
</html>