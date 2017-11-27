<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<chaos:override name="title">
	<c:set var="title" value="新增属性"></c:set>
	<c:out value="${title}"></c:out>
	<input type="hidden" id="title" value="${title }"/>
</chaos:override>

<jsp:include page="${ctx}/WEB-INF/views/membercenter/attribute/input_template.jsp"></jsp:include>