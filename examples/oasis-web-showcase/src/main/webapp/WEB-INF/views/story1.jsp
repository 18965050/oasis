<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>
<div>story2</div>
<div>${win1.content}</div>
<div>${win2.content}</div>
<div>${win3.content}</div>
<div>${win4.content}</div>
<rapid:override name="content"></rapid:override>
<rapid:block name="content">
	story1 content
</rapid:block>