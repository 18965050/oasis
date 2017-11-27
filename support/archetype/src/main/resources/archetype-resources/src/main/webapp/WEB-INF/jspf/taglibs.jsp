#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="chaos" uri="http://www.xyz.cn/tags/chaos" %>
<c:set var="ctx" value="${symbol_dollar}{pageContext.request.contextPath }" />
<c:set var="reqURI" value="${symbol_dollar}{pageContext.request.requestURI }" />