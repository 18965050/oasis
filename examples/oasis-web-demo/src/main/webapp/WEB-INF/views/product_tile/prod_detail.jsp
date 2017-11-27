<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<div class="row clearfix">
	<div class="col-md-3 column">
	<a> <chaos:img type="product" id="${product.comId}-${product.prodId}" alt="${product.prodAlias }" width="100%" height="100%" /></a>
	</div>
	<div class="col-md-9 column">
		<div class="jumbotron">
			<h1>
				${product.prodName}
			</h1>
			<input type="hidden" id="prodId" value="${product.prodId}"/>
			<input type="hidden" id="currentUserName" value="<shiro:principal property="name"/>"/>
			<p>
				适用人群： 境外旅游、出差、培训、探亲访友人群均适用;产品特色： 保障全面，24小时医疗救援，特含手机、手提电脑保障，境外游旅行必备，全球可保。
			</p>

		</div>
	</div>
</div>