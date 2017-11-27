<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<c:if test="${!empty attr.errors && fn:length(attr.errors)>0}">
	<div class="panel panel-warning col-sm-offset-3" style="display:inline-block;">
		<div class="panel-heading">
			<h3 class="panel-title">
				出错啦!
			</h3>
		</div>
		<div class="panel-body">
			<ul>
				<c:forEach var="entry" items="${attr.errors}">
					<li>${entry.value}</li>
				</c:forEach>
			</ul>
		</div>
		
	</div>
</c:if>


<form id="inputForm" class="form-horizontal" style="width:60%;" action="/membercenter/attribute/update" method="post" >
	
	<c:if test="${not empty attr.skuOptionId }">
		<div class="form-group">
			 <label for="skuOptionId" class="col-sm-3 control-label">id:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="skuOptionId" name="skuOptionId" readonly="readonly" value="${attr.skuOptionId}"></input>
			</div>
		</div>		
	</c:if>
	
	<div class="form-group">
		 <label for="tagSel" class="col-sm-3 control-label">标签:</label>
		<div class="col-sm-9">
			<a class="form-control" id="tagSel" href="#">选择</a>
			<input type="hidden" id="skuId" name="skuId" value="${attr.skuId}"/>
		</div>
	</div>	
	
	<div class="form-group">
		 <label for="optionName" class="col-sm-3 control-label">名称:</label>
		<div class="col-sm-9">
			<input class="form-control" id="optionName" name="optionName" type="text" value="${attr.optionName}"></input>
		</div>
	</div>
	
	<div class="form-group">
		 <label for="weight" class="col-sm-3 control-label">权重:</label>
		<div class="col-sm-9">
			<input class="form-control" id="weight" name="weight" type="text" value="${attr.weight}"></input>
		</div>
	</div>		

	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-9">
			 <button type="submit" class="btn btn-default">提交</button>
			 &nbsp;&nbsp;&nbsp;
			 <button type="button" id="btnRtn" class="btn btn-default">返回</button>
		</div>
	</div>
</form>

<div class="container" id="selDialog" style="display: none">
	<div class="row clearfix">
	<c:forEach var="entry" items="${tag}" >
		<div class="col-md-4  column">
			<input type="radio" name="tagName" value="${entry.key}" <c:if test="${attr.skuId eq entry.key }">checked="checked"</c:if>>${entry.value}</input>
		</div>	
	</c:forEach>										
	</div>		
</div>
