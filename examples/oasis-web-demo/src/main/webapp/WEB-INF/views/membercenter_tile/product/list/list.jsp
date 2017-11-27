<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<form class="form-inline"  id="productForm" method="post" action="/membercenter/product/list">
  <div class="form-group">
  	<label  for="prodName">名称:</label>
    <input type="text" class="form-control" id="prodName" name="prodName" value="${queryDTO.prodName }">
  </div>
  
  <div class="form-group" style="width:80px"></div> 
  
  <div class="form-group">
  	<label  for="comId">保险公司:</label>
    <select class="form-control" name="comId">
    	<option value=""></option>
    	<c:forEach var="entry" items="${company}">
    		<option value="${entry.key}" <c:if test="${queryDTO.comId eq entry.key }">selected="selected" </c:if>  >${entry.value}</option>
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
			<th>保险公司</th>
			<th>名称</th>
			<th>别名</th>
			<th>状态</th>
			<th>权重</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

	 <c:forEach items="${productList}" var="product" varStatus="status">
	 <c:if test="${status.index %2 ==0 }"><tr class="info"></c:if>
		<c:if test="${status.index %2 !=0}"><tr class="active"></c:if>
	
			<td><c:out value="${product.prodId}"/></td>
			<td><c:out value="${product.comName}"/></td>
			<td><c:out value="${product.prodName}"/></td>
			<td><c:out value="${product.prodAlias}"/></td>
			<td>
				<c:if test="${product.status == 0}"><c:out value="N/A" /></c:if>
				<c:if test="${product.status == 1}"><c:out value="有效" /></c:if>
				<c:if test="${product.status == 2}"><c:out value="无效" /></c:if>
			</td>
			<td><c:out value="${product.weight}"/></td>
			<td>
				<a id="bind" url="/membercenter/product/bind/${product.prodId}" href="#">绑定属性</a>
				<a id="update" url="/membercenter/product/input/${product.prodId}" href="#">修改</a> 
				<a id="delete" url="/membercenter/product/delete/${product.prodId}" href="#">删除</a> 
				<a id="detail" url="/membercenter/product/detail/${product.prodId}" href="#">详情</a> 
			</td>
		</tr>		
	</c:forEach>
	
	</tbody>
</table>

<div class="col-md-4 col-md-offset-8">
	<div id="productPaginator" class="pagination">
	    <a href="#" class="first" data-action="first">&laquo;</a>
	    <a href="#" class="previous" data-action="previous">&lsaquo;</a>
	    <input type="text" readonly="readonly" data-max-page="40" />
	    <a href="#" class="next" data-action="next">&rsaquo;</a>
	    <a href="#" class="last" data-action="last">&raquo;</a>
	</div>
</div>


