<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<form class="form-inline"  id="commentForm" method="post" action="/membercenter/comment/list">

  <div class="form-group">
  	<label  for="userId">用户:</label>
    <select class="form-control" name="userId">
    	<option value=""></option>
    	<c:forEach var="entry" items="${user }">
    		<option value="${entry.key}" <c:if test="${queryDTO.userId eq entry.key }">selected="selected" </c:if>  >${entry.value}</option>
    	</c:forEach>
    </select> 
  </div>
  
  <div class="form-group" style="width:80px"></div>
  
  <div class="form-group">
  	<label  for="prodId">产品:</label>
    <select class="form-control" name="prodId">
    	<option value=""></option>
    	<c:forEach var="entry" items="${product }">
    		<option value="${entry.key}" <c:if test="${queryDTO.prodId eq entry.key }">selected="selected" </c:if>  >${entry.value}</option>
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
			<th>用户</th>
			<th>产品</th>
			<th>评论</th>
			<th>得分</th>
			<th>添加时间</th>
		</tr>
	</thead>
	<tbody>

	 <c:forEach items="${commentList}" var="comment" varStatus="status">
	 <c:if test="${status.index %2 ==0 }"><tr class="info"></c:if>
		<c:if test="${status.index %2 !=0}"><tr class="active"></c:if>
	
			<td><c:out value="${comment.appraiseId}"/></td>
			<td><c:out value="${comment.userName}"/></td>
			<td><c:out value="${comment.prodName}"/></td>
			<td><c:out value="${comment.content}"/></td>
			<td><c:out value="${comment.score}"/></td>
			<td><fmt:formatDate value="${comment.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>		
	</c:forEach>
	
	</tbody>
</table>

<div class="col-md-4 col-md-offset-8">
	<div id="commentPaginator" class="pagination">
	    <a href="#" class="first" data-action="first">&laquo;</a>
	    <a href="#" class="previous" data-action="previous">&lsaquo;</a>
	    <input type="text" readonly="readonly" data-max-page="40" />
	    <a href="#" class="next" data-action="next">&rsaquo;</a>
	    <a href="#" class="last" data-action="last">&raquo;</a>
	</div>
</div>


