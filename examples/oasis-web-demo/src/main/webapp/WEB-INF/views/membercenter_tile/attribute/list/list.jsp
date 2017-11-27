<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<form class="form-inline"  id="attrForm" method="post" action="/membercenter/attribute/list">
  <div class="form-group">
  	<label  for="optionName">名称:</label>
    <input type="text" class="form-control" id="optionName" name="optionName" value="${queryDTO.optionName }">
  </div>
  
  <div class="form-group" style="width:80px"></div> 
  
  <div class="form-group">
  	<label  for="comId">标签:</label>
    <select class="form-control" name="skuId">
    	<option value=""></option>
    	<c:forEach var="entry" items="${tag}">
    		<option value="${entry.key}" <c:if test="${queryDTO.skuId eq entry.key }">selected="selected" </c:if>  >${entry.value}</option>
    	</c:forEach>
    </select> 
  </div>
  
  <div class="form-group" style="width:120px"></div>
  <button type="button" id="btnQuery" class="btn btn-default">查询</button>
  
  <input type="hidden" id="currentPage" name="pageIndex" value="${currentPage }" />
  <input type="hidden" id="totalPage" value="${totalPage }" />
  
    <div class="form-group" style="width:80px"></div>
  <button type="button" id="btnAdd" class="btn btn-primary">新增属性</button>
</form>

<div style="height:30px;"></div>

<table class="table">
	<thead>
		<tr>
			<th>id</th>
			<th>标签</th>
			<th>名称</th>
			<th>权重</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

	 <c:forEach items="${attrList}" var="attr" varStatus="status">
	 <c:if test="${status.index %2 ==0 }"><tr class="info"></c:if>
		<c:if test="${status.index %2 !=0}"><tr class="active"></c:if>
	
			<td><c:out value="${attr.skuOptionId}"/></td>
			<td><c:out value="${attr.skuName}"/></td>
			<td><c:out value="${attr.optionName}"/></td>
			<td><c:out value="${attr.weight}"/></td>
			<td>
				<a id="update" url="/membercenter/attribute/input/${attr.skuOptionId}" href="#">修改</a> 
				<a id="delete" url="/membercenter/attribute/delete/${attr.skuOptionId}" href="#">删除</a> 
				<a id="detail" url="/membercenter/attribute/detail/${attr.skuOptionId}" href="#">详情</a> 
			</td>
		</tr>		
	</c:forEach>
	
	</tbody>
</table>

<div class="col-md-4 col-md-offset-8">
	<div id="attrPaginator" class="pagination">
	    <a href="#" class="first" data-action="first">&laquo;</a>
	    <a href="#" class="previous" data-action="previous">&lsaquo;</a>
	    <input type="text" readonly="readonly" data-max-page="40" />
	    <a href="#" class="next" data-action="next">&rsaquo;</a>
	    <a href="#" class="last" data-action="last">&raquo;</a>
	</div>
</div>


