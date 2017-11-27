<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<form class="form-horizontal" style="width:60%;" action="/membercenter/product/list"  >
	<div class="form-group">
		 <label for="prodId" class="col-sm-3 control-label">id:</label>
		<div class="col-sm-9">
			<label class="form-control" id="prodId">${product.prodId }</label>
		</div>
	</div>
	
	<div class="form-group">
		 <label for="comName" class="col-sm-3 control-label">保险公司:</label>
		<div class="col-sm-9">
			<label class="form-control" id="comName">${product.comName }</label>
		</div>
	</div>	
	
	<div class="form-group">
		 <label for="prodName" class="col-sm-3 control-label">名称:</label>
		<div class="col-sm-9">
			<label class="form-control" id="prodName">${product.prodName }</label>
		</div>
	</div>
	
	<div class="form-group">
		 <label for="prodAlias" class="col-sm-3 control-label">别名:</label>
		<div class="col-sm-9">
			<label class="form-control" id="prodAlias">${product.prodAlias }</label>
		</div>
	</div>	
	
	<div class="form-group">
		 <label for="picView" class="col-sm-3 control-label">图片:</label>
		<div class="col-sm-9">
			<a id="picView" class="form-control"  href="#"> 查看</a>
			<div id="picDialog" title="产品图片" style="display: none">
				<img alt="${product.prodName}" src="${product.picLoc}" />
			</div>
		</div>
	</div>		
	
	<div class="form-group">
		 <label for="limitQuantity" class="col-sm-3 control-label">限购数量:</label>
		<div class="col-sm-9">
			<label class="form-control" id="limitQuantity">${product.limitQuantity }</label>
		</div>
	</div>	
	
	<div class="form-group">
		 <label for="weight" class="col-sm-3 control-label">权重:</label>
		<div class="col-sm-9">
			<label class="form-control" id="weight">${product.weight }</label>
		</div>
	</div>		
	
	<div class="form-group">
		 <label for="status" class="col-sm-3 control-label">状态:</label>
		<div class="col-sm-9">
			<label class="form-control" id="status">
				<c:if test="${product.status==0 }">N/A</c:if>
				<c:if test="${product.status==1 }">有效</c:if>
				<c:if test="${product.status==2 }">无效</c:if>
			</label>
		</div>
	</div>	

	<div class="form-group">
		 <label for="attrView" class="col-sm-3 control-label">绑定属性:</label>
		<div class="col-sm-9">
			<a id="attrView" class="form-control"  href="#"> 查看</a>
		</div>
	</div>

	<div class="form-group">
		 <label for="addTime" class="col-sm-3 control-label">添加时间:</label>
		<div class="col-sm-9">
			<label class="form-control" id="addTime"><fmt:formatDate value="${product.addTime}" pattern="yy-MM-dd HH:mm:ss" /></label>
		</div>
	</div>
	
	<div class="form-group">
		 <label for="updateTime" class="col-sm-3 control-label">修改时间:</label>
		<div class="col-sm-9">
			<label class="form-control" id="updateTime"><fmt:formatDate value="${product.updateTime}" pattern="yy-MM-dd HH:mm:ss" /></label>
		</div>
	</div>	

	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-9">
			 <button type="submit" class="btn btn-default">返回列表页</button>
		</div>
	</div>
</form>

<div class="container" id="attrDialog" style="display: none">
	<div class="row clearfix">
	<c:forEach var="entry" items="${attrList}" >
		<div class="col-md-4  column">
			${entry.attrName}
		</div>	
	</c:forEach>										
	</div>		
</div>