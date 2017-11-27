<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<form class="form-inline"  id="companyForm" method="post" action="/membercenter/company/list">
  <div class="form-group">
  	<label  for="name">名称:</label>
    <input type="text" class="form-control" id="name" name="comName" value="${queryDTO.comName }">
  </div>
  
  <div class="form-group" style="width:80px"></div>
  
  <div class="form-group">
  	<label  for="name">状态:</label>
    <select class="form-control" name="status">
    	<option value=""></option>
    	<c:forEach var="entry" items="${companyStatus }">
    		<option value="${entry.key}" <c:if test="${queryDTO.status eq entry.key }">selected="selected" </c:if>  >${entry.value}</option>
    	</c:forEach>
    </select> 
  </div>
  
  <div class="form-group" style="width:120px"></div>
  <button type="button" id="btnQuery" class="btn btn-default">查询</button>
  
  <input type="hidden" id="currentPage" name="pageIndex" value="${currentPage }" />
  <input type="hidden" id="totalPage" value="${totalPage }" />
</form>

<div style="height:30px;"></div>

<table class="table">
	<thead>
		<tr>
			<th>id</th>
			<th>名称</th>
			<th>状态</th>
			<th>添加时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

	 <c:forEach items="${companyList}" var="company" varStatus="status">
	 <c:if test="${status.index %2 ==0 }"><tr class="info"></c:if>
		<c:if test="${status.index %2 !=0}"><tr class="active"></c:if>
	
			<td><c:out value="${company.comId}"/></td>
			<td><c:out value="${company.comName}"/></td>
			<td>
				<c:if test="${company.status == 0}"><c:out value="N/A" /></c:if>
				<c:if test="${company.status == 1}"><c:out value="有效" /></c:if>
				<c:if test="${company.status == 2}"><c:out value="无效" /></c:if>
			</td>
			<td><fmt:formatDate value="${company.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td>
				<a id="update" url="/membercenter/company/input/${company.comId}" href="#">修改</a> 
				<a id="delete" url="/membercenter/company/delete/${company.comId}" href="#">删除</a> 
				<a id="detail" url="/membercenter/company/detail/${company.comId}" href="#">详情</a> 
			</td>
		</tr>		
	</c:forEach>
	
	</tbody>
</table>

<div class="col-md-4 col-md-offset-8">
	<div id="companyPaginator" class="pagination">
	    <a href="#" class="first" data-action="first">&laquo;</a>
	    <a href="#" class="previous" data-action="previous">&lsaquo;</a>
	    <input type="text" readonly="readonly" data-max-page="40" />
	    <a href="#" class="next" data-action="next">&rsaquo;</a>
	    <a href="#" class="last" data-action="last">&raquo;</a>
	</div>
</div>


