<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<c:if test="${not empty _errors}">
<div class="panel panel-warning col-sm-offset-3" style="display:inline-block;">
	<div class="panel-heading">
		<h3 class="panel-title">
			出错啦!
		</h3>
	</div>
	<div class="panel-body">
		<ul>
			<c:forEach var="entry" items="${_errors }">
				<li>${entry.value}</li>
			</c:forEach>
			
		</ul>
	</div>
</div>

</c:if>


<form id="inputForm" class="form-horizontal" style="width:60%;" action="/membercenter/company/update" method="post" >
	
	<c:if test="${not empty company.comId }">
		<div class="form-group">
			 <label for="comId" class="col-sm-3 control-label">id:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="comId" name="comId" readonly="readonly" value="${company.comId }"></input>
			</div>
		</div>		
	</c:if>

	<div class="form-group">
		 <label for="comName" class="col-sm-3 control-label">名称:</label>
		<div class="col-sm-9">
			<input class="form-control" id="comName" name="comName" type="text" value="${company.comName }"></input>
		</div>
	</div>
	
	<div class="form-group">
		 <label for="comName" class="col-sm-3 control-label">状态:</label>
		<div class="col-sm-9">
			<select class="form-control" id="status" name="status">
				<c:forEach var="entry" items="${companyStatus }">
    				<option value="${entry.key}" <c:if test="${company.status eq entry.key }">selected="selected" </c:if>  >${entry.value}</option>
    			</c:forEach>
			</select>
		</div>
	</div>	
	
	<c:if test="${not empty company.addTime }">
		<div class="form-group">
			 <label for="addTime" class="col-sm-3 control-label">添加时间:</label>
			<div class="col-sm-9">
				<input class="form-control" id="addTime" name="addTime" type="text" value="<fmt:formatDate value='${company.addTime}' pattern='yyyy-MM-dd HH:mm:ss' />"></input>
			</div>
		</div>		
	</c:if>

	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-9">
			 <button type="submit" class="btn btn-default">提交</button>
			 &nbsp;&nbsp;&nbsp;
			 <button type="button" id="btnRtn" class="btn btn-default">返回</button>
		</div>
	</div>
</form>