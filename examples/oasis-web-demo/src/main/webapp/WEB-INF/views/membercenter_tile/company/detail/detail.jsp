<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<form class="form-horizontal" style="width:60%;" action="/membercenter/company/list"  >
	<div class="form-group">
		 <label for="comId" class="col-sm-3 control-label">id:</label>
		<div class="col-sm-9">
			<label class="form-control" id="comId">${company.comId }</label>
		</div>
	</div>
	
	<div class="form-group">
		 <label for="comName" class="col-sm-3 control-label">名称:</label>
		<div class="col-sm-9">
			<label class="form-control" id="comName">${company.comName }</label>
		</div>
	</div>
	
	<div class="form-group">
		 <label for="comName" class="col-sm-3 control-label">状态:</label>
		<div class="col-sm-9">
			<label class="form-control" id="status">
				<c:if test="${company.status==0 }">N/A</c:if>
				<c:if test="${company.status==1 }">有效</c:if>
				<c:if test="${company.status==2 }">无效</c:if>
			</label>
		</div>
	</div>	

	<div class="form-group">
		 <label for="addTime" class="col-sm-3 control-label">添加时间:</label>
		<div class="col-sm-9">
			<label class="form-control" id="addTime"><fmt:formatDate value="${company.addTime}" pattern="yy-MM-dd HH:mm:ss" /></label>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-9">
			 <button type="submit" class="btn btn-default">返回列表页</button>
		</div>
	</div>
</form>