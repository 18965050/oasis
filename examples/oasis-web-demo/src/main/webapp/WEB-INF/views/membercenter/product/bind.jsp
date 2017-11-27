<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<chaos:override name="css">
</chaos:override>

<chaos:override name="title">
	<c:set var="title" value="绑定属性"></c:set>
	<c:out value="${title}"></c:out>
	<input type="hidden" id="title" value="${title }"/>
</chaos:override>

<chaos:override name="body">
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<h3>${product.prodName}</h3>
				<input type="hidden" id="prodId" value="${product.prodId }" />
				<input type="hidden" id="prodAttr" value="${prodAttr}" />
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-3 column">
				<div id="tagDiv" class="list-group">
					<c:forEach var="tag" items="${tagList }">
						<a href="#" class="list-group-item" id="tagId" data="${tag.skuId}"> ${tag.skuName}</a>
					</c:forEach>
						
				</div>
			</div>
			<div class="col-md-6 column">
				<div id="attrDiv" class="list-group">
				</div>
			</div>
		</div>
		
			<div class="form-group">
				<div class="col-sm-offset-6 col-sm-3">
					 <button type="button" id="btnBind" class="btn btn-default">绑定</button>
					 &nbsp;&nbsp;&nbsp;
					 <button type="button" id="btnRtn" class="btn btn-default">返回</button>
				</div>
			</div>
	</div>		
</chaos:override>

<chaos:override name="js">
	<script src="${ctx}/assets/js/membercenter/product/bind.js"
			charset="UTF-8"></script>	
</chaos:override>

<jsp:include page="${ctx}/WEB-INF/views/membercenter/template.jsp"></jsp:include>