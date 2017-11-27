<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<chaos:override name="css">
	<link href="${ctx}/assets/third-party/jquery-ui/jquery-ui.css"
		rel="stylesheet" />	
	<link href="${ctx}/assets/third-party/jqPagination/css/jqpagination.css"
		rel="stylesheet" />
</chaos:override>

<chaos:override name="title">
	<c:set var="title" value="标签列表"></c:set>
	<c:out value="${title}"></c:out>
	<input type="hidden" id="title" value="${title }"/>
</chaos:override>

<chaos:override name="body">
	<form class="form-inline"  id="tagForm" method="post" action="/membercenter/tag/list">
	  <div class="form-group">
	  	<label  for="skuName">名称:</label>
	    <input type="text" class="form-control" id="skuName" name="skuName" value="${queryDTO.skuName }">
	  </div>
	
	  
	  <div class="form-group" style="width:120px"></div>
	  <button type="button" id="btnQuery" class="btn btn-default">查询</button>
	  
	  <input type="hidden" id="currentPage" name="pageIndex" value="${currentPage }" />
	  <input type="hidden" id="totalPage" value="${totalPage }" />
	  
	  <div class="form-group" style="width:80px"></div>
	  <button type="button" id="btnAdd" class="btn btn-primary">新增标签</button>
	  
	</form>
	
	
	<div id="editDialog" style="display: none">
<form class="form-horizontal" id="editForm" action="/membercenter/tag/update" method="post">
	<div id="errDiv" class="panel panel-warning col-sm-offset-3" style="display:none;">
		<div class="panel-heading">
			<h3 class="panel-title">
				出错啦!
			</h3>
		</div>
		<div class="panel-body">
			<ul>
				<li>添加/修改数据失败</li>
			</ul>
		</div>
	</div>

	<div class="form-group" style="display:none;">
		 <label for="editSkuId" class="col-sm-3 control-label" >id:</label>
		<div class="col-sm-9" >
			<label class="form-control" id="editSkuId" name="editSkuId" ></label>
		</div>
	</div>


	<div class="form-group">
		 <label for="editSkuName" class="col-sm-3 control-label" >名称:</label>
		<div class="col-sm-9" >
			<input class="form-control" id="editSkuName"  name="editSkuName" type="text"></input>
		</div>
	</div>	
	
	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-9">
			 <button type="submit" id="btnSubmit" class="btn btn-default" style="display:inline-block">提交</button>
			 &nbsp;&nbsp;&nbsp;
			 <button type="button" id="btnRtn" class="btn btn-default">返回</button>
		</div>
	</div>	
	
	
</form>					
</div>


<div style="height:30px;"></div>

<table class="table">
	<thead>
		<tr>
			<th width="30%">id</th>
			<th width="40%">名称</th>
			<th width="30%">操作</th>
		</tr>
	</thead>
	<tbody>

	 <c:forEach items="${tagList}" var="tag" varStatus="status">
	 <c:if test="${status.index %2 ==0 }"><tr class="info"></c:if>
		<c:if test="${status.index %2 !=0}"><tr class="active"></c:if>
	
			<td><c:out value="${tag.skuId}"/></td>
			<td><c:out value="${tag.skuName}"/></td>
			<td>
				<a id="update" data="${tag.skuId}" href="#">修改</a> 
				<a id="delete" data="${tag.skuId}" href="#">删除</a> 
				<a id="detail" data="${tag.skuId}" href="#">详情</a> 
			</td>
		</tr>		
	</c:forEach>
	
	</tbody>
</table>

<div class="col-md-4 col-md-offset-8">
	<div id="tagPaginator" class="pagination">
	    <a href="#" class="first" data-action="first">&laquo;</a>
	    <a href="#" class="previous" data-action="previous">&lsaquo;</a>
	    <input type="text" readonly="readonly" data-max-page="40" />
	    <a href="#" class="next" data-action="next">&rsaquo;</a>
	    <a href="#" class="last" data-action="last">&raquo;</a>
	</div>
</div>
</chaos:override>

<chaos:override name="js">
	<script src="${ctx}/assets/third-party/jquery-ui/jquery-ui.js"
			charset="UTF-8"></script>	
	<script
		src="${ctx}/assets/third-party/jqPagination/js/jquery.jqpagination.js"
		charset="UTF-8"></script>
	<script src="${ctx}/assets/third-party/jquery-validation-1.13.0/jquery.validate.js"
			charset="UTF-8"></script>
	<script src="${ctx}/assets/third-party/jquery-validation-1.13.0/localization/messages_zh.js"
			charset="UTF-8"></script>	
	<script src="${ctx}/assets/js/addon-validate.js"
			charset="UTF-8"></script>		
	<script src="${ctx}/assets/js/membercenter/tag/list.js"
		charset="UTF-8"></script>
</chaos:override>

<jsp:include page="${ctx}/WEB-INF/views/membercenter/template.jsp"></jsp:include>