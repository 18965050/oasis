<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<div class="panel-group" id="panelMenu">
	
	<shiro:hasRole name="admin">
	<div class="panel panel-default">
		<div class="panel-heading">
			 <a class="panel-title" data-toggle="collapse" data-parent="#panelMenu" href="#menuCompany">保险公司</a>
		</div>
		<div id="menuCompany" class="panel-collapse in">
			<div class="panel-body "><a id="companyList" href="/membercenter/company/list" >列表</a></div>
			<div class="panel-body"><a id="companyAdd" href="/membercenter/company/input">新增</a></div>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			 <a class="panel-title" data-toggle="collapse" data-parent="#panelMenu" href="#menuTag">标签&属性</a>
		</div>
		<div id="menuTag" class="panel-collapse in">
			<div class="panel-body "><a id="prodTag" href="/membercenter/tag/list" >标签</a></div>
			<div class="panel-body"><a id="prodOption" href="/membercenter/attribute/list">属性</a></div>		
		</div>
	</div>	

	<div class="panel panel-default">
		<div class="panel-heading">
			 <a class="panel-title" data-toggle="collapse" data-parent="#panelMenu" href="#menuProduct">保险产品</a>
		</div>
		<div id="menuProduct" class="panel-collapse in">
			<div class="panel-body "><a id="prodList" href="/membercenter/product/list" >列表</a></div>
			<div class="panel-body"><a id="prodAdd" href="/membercenter/product/input">新增</a></div>
		</div>
	</div>
	</shiro:hasRole>
	
	<div class="panel panel-default">
		<div class="panel-heading">
			 <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panelMenu" href="#menuComment">评论</a>
		</div>
		<div id="menuComment" class="panel-collapse collapse">
			<div class="panel-body"><a id="prodList" href="/membercenter/comment/list" >列表</a></div>
		</div>
	</div>
</div>