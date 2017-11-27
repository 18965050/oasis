<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>

应用：<c:out value="${appNum}" /> 开始：参数：<c:out value="${_s_var}" /> <br/>

<c:set var="_s_var" value="${_s_var}${appNum}" scope="session"/>

应用：<c:out value="${appNum}" /> 修改后 参数：<c:out value="${_s_var}" /> <br/>
