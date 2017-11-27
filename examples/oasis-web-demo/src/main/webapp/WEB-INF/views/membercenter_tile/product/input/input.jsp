<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<c:if test="${not empty _errors || not empty product.errors && fn:length(product.errors)>0}">
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
				<c:forEach var="entry" items="${product.errors}">
					<li>${entry.value}</li>
				</c:forEach>
			</ul>
		</div>
		
	</div>
</c:if>


<form id="inputForm" class="form-horizontal" style="width:60%;" action="/membercenter/product/update" method="post" >
	
	<c:if test="${not empty product.prodId }">
		<div class="form-group">
			 <label for="prodId" class="col-sm-3 control-label">id:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="prodId" name="prodId" readonly="readonly" value="${product.prodId}"></input>
			</div>
		</div>		
	</c:if>
	
	<div class="form-group">
		 <label for="comId" class="col-sm-3 control-label">保险公司:</label>
		<div class="col-sm-9">
			<select class="form-control" id="comId" name="comId">
				<c:forEach var="entry" items="${company}">
    				<option value="${entry.key}" <c:if test="${product.comId eq entry.key }">selected="selected" </c:if>  >${entry.value}</option>
    			</c:forEach>
			</select>
		</div>
	</div>	
	
	<div class="form-group">
		 <label for="prodName" class="col-sm-3 control-label">名称:</label>
		<div class="col-sm-9">
			<input class="form-control" id="prodName" name="prodName" type="text" value="${product.prodName }"></input>
		</div>
	</div>
	
	<div class="form-group">
		 <label for="prodAlias" class="col-sm-3 control-label">别名:</label>
		<div class="col-sm-9">
			<input class="form-control" id="prodAlias" name="prodAlias" type="text" value="${product.prodAlias }"></input>
		</div>
	</div>	
	
	<div class="form-group">
		 <label for="fileUpload" class="col-sm-3 control-label">图片:</label>
		<div class="col-sm-9">
			<input class="form-control" id="fileUpload" type="file" ></input>
			<input type="hidden" id=picLoc name="picLoc" value="${product.picLoc}"/> 
		</div>
	</div>	
	
	<div class="form-group">
		 <label for="limitQuantity" class="col-sm-3 control-label">限购数量:</label>
		<div class="col-sm-9">
			<input class="form-control" id="limitQuantity" name="limitQuantity" type="text" value="${product.limitQuantity}"></input>
		</div>
	</div>		
	
	<div class="form-group">
		 <label for="weight" class="col-sm-3 control-label">权重:</label>
		<div class="col-sm-9">
			<input class="form-control" id="weight" name="weight" type="text" value="${product.weight}"></input>
		</div>
	</div>		
	
	<div class="form-group">
		 <label for="status" class="col-sm-3 control-label">状态:</label>
		<div class="col-sm-9">
			<select class="form-control" id="status" name="status">
				<c:forEach var="entry" items="${productStatus }">
    				<option value="${entry.key}" <c:if test="${product.status eq entry.key }">selected="selected" </c:if>  >${entry.value}</option>
    			</c:forEach>
			</select>
		</div>
	</div>	
	
	<c:if test="${not empty product.prodId }">
		<div class="form-group">
			 <label for="addTime" class="col-sm-3 control-label">添加时间:</label>
			<div class="col-sm-9">
				<input class="form-control" id="addTime" name="addTime" type="text" value="<fmt:formatDate value='${product.addTime}' pattern='yyyy-MM-dd HH:mm:ss' />"></input>
			</div>
		</div>		
	</c:if>
	
	<c:if test="${not empty product.prodId }">
		<div class="form-group">
			 <label for="updateTime" class="col-sm-3 control-label">修改时间:</label>
			<div class="col-sm-9">
				<input class="form-control" id="updateTime" name="updateTime" type="text" value="<fmt:formatDate value='${product.updateTime}' pattern='yyyy-MM-dd HH:mm:ss' />"></input>
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