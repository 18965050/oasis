<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@include file="/WEB-INF/jspf/taglibs.jsp"%>

<chaos:override name="css">
	<link href="${ctx}/assets/third-party/jquery-ui/jquery-ui.css"
		rel="stylesheet" />		
</chaos:override>

<chaos:override name="prodDetail">
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
</chaos:override>

<chaos:override name="prodOper">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<ul class="nav nav-tabs">
				<li class="active">
					<a id="aDetail" href="#">详情</a>
				</li>
				<li>
					<a id="aComment" href="#">评价</a>
				</li>
			</ul>
			<table id="tblDetail" class="table" style="display:inline-table">
				<thead>
					<tr>
						<th>属性</th>
						<th>说明</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>所属公司</td>
						<td>${product.comName }</td>
					</tr>			
					<tr class="active">
						<td>名称</td>
						<td>${product.prodName }</td>
					</tr>
					<tr class="success">
						<td>别名</td>
						<td>${product.prodAlias }</td>
					</tr>
					<tr class="warning">
						<td>状态</td>
						<td>
							<c:if test="${product.status eq 0 }">N/A</c:if>
							<c:if test="${product.status eq 1 }">有效</c:if>
							<c:if test="${product.status eq 2 }">无效</c:if>
						</td>
	
					</tr>
					<tr class="danger">
						<td>权重</td>
						<td>${product.weight }</td>
					</tr>
					<shiro:authenticated>
					<tr ><td colspan="2">	
						<p class="text-center"><button type="button" id="btnCollect" class="btn text-center btn-success btn-lg">收藏</button></p>
					</td></tr> 
					</shiro:authenticated>					
				</tbody>
			</table>
			
			<div id="divComment" style="display:none">
				<c:forEach var="comment" items="${commentList }">
				<blockquote>
					<p>${comment.content }</p> 
					<small>${comment.userName }</small>
				</blockquote>				
				</c:forEach>	
					<p class="text-center" style="margin-top: 20px;"><button type="button" id="btnComment" class="btn text-center btn-success btn-lg">我要评价</button></p>
						
				<jsp:include page="/WEB-INF/views/auth/quick_login.jsp" />
				
					<div id="divMyComment" style="display:none;">
						<form class="form-horizontal" id="commentForm" action="" method="post">
							<div id="errDiv" class="panel panel-warning col-sm-offset-3" style="display:none;">
								<div class="panel-heading">
									<h3 class="panel-title">
										出错啦!
									</h3>
								</div>
								<div class="panel-body">
									<ul>
										<li>评价失败,请确认您是否已评价</li>
									</ul>
								</div>
							</div>
						
							<div class="form-group">
								 <label for="content" class="col-sm-4 control-label" >您的评价:</label>
								<div class="col-sm-8" >
									<textarea id="content" name="content" rows="3" cols="29"></textarea>
								</div>
							</div>	
							
							<div class="form-group">
								 <label for="score" class="col-sm-4 control-label" >评分:</label>
								<div class="col-sm-8" >
									<input class="form-control" id="score"  name="score" placeholder="1~5分" type="text"></input>
								</div>
							</div>		
							
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-9">
									 <button type="button" id="btnSubmit" class="btn btn-default" style="display:inline-block">提交</button>
									 &nbsp;&nbsp;&nbsp;
									 <button type="button" id="btnRtn" class="btn btn-default">返回</button>
								</div>
							</div>	
						</form>	
					</div>			
				
			</div>		
			
		</div>
	</div>
</chaos:override>

<chaos:override name="js">
	<script src="${ctx}/assets/third-party/jquery-ui/jquery-ui.js"
			charset="UTF-8"></script>	
	<script src="${ctx}/assets/js/auth/quick_login.js"
			charset="UTF-8"></script>		
</chaos:override>

<jsp:include page="${ctx}/WEB-INF/views/proddetail/detail_template.jsp"></jsp:include>