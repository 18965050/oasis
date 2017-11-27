<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<chaos:override name="css">
</chaos:override>

<chaos:override name="title">
	<c:set var="title" value="属性详情"></c:set>
	<c:out value="${title}"></c:out>
	<input type="hidden" id="title" value="${title }"/>
</chaos:override>

<chaos:override name="body">
	<form class="form-horizontal" style="width:60%;" action="/membercenter/attribute/list"  >
		<div class="form-group">
			 <label for="skuOptionId" class="col-sm-3 control-label">id:</label>
			<div class="col-sm-9">
				<label class="form-control" id="skuOptionId">${attr.skuOptionId }</label>
			</div>
		</div>
		
		<div class="form-group">
			 <label for="skuName" class="col-sm-3 control-label">标签:</label>
			<div class="col-sm-9">
				<label class="form-control" id="skuName">${attr.skuName }</label>
			</div>
		</div>
		
		<div class="form-group">
			 <label for="optionName" class="col-sm-3 control-label">名称:</label>
			<div class="col-sm-9">
				<label class="form-control" id="optionName">${attr.optionName }</label>
			</div>
		</div>	
		
		<div class="form-group">
			 <label for="weight" class="col-sm-3 control-label">权重:</label>
			<div class="col-sm-9">
				<label class="form-control" id="weight">${attr.weight}</label>
			</div>
		</div>	
	
	
		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-9">
				 <button type="submit" class="btn btn-default">返回列表页</button>
			</div>
		</div>
	</form>
</chaos:override>

<chaos:override name="js">
</chaos:override>

<jsp:include page="${ctx}/WEB-INF/views/membercenter/template.jsp"></jsp:include>