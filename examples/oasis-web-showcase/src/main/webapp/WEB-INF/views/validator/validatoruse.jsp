<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>

<chaos:override name="title">Validator - validator使用</chaos:override>
<chaos:override name="content">
	<div class="row clearfix">
		<h1>一、待校验表单</h1>
		<div class="col-md-6 column">
			<c:if test="${!empty _errors}">
				<div class="alert alert-dismissable alert-warning">
					<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
					<c:forEach items="${_errors}" var="err">
						<div>
							<strong><c:out value="${err.key}" /></strong>
							<c:out value="${err.value}" />
						</div>
					</c:forEach>
				</div>
			</c:if>
			<form class="form-horizontal" role="form" action="${ctx}/validator/validator/validatorsubmit" method="post">
				<div class="form-group">
					<label for="inputLogUsername" class="col-sm-4 control-label">登录名</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="inputLogUsername" name="logUsername" value="${user.logUsername}">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword" class="col-sm-4 control-label">密码</label>
					<div class="col-sm-8">
						<input type="password" class="form-control" id="inputPassword" name="logPassword" value="${user.logPassword}">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword2" class="col-sm-4 control-label">确认密码</label>
					<div class="col-sm-8">
						<input type="password" class="form-control" id="inputPassword2" name="logPassword2" value="${user.logPassword2}">
					</div>
				</div>
				<div class="form-group">
					<label for="inputMobile" class="col-sm-4 control-label">手机</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="inputMobile" name="mobile" <c:if test="${not empty user.mobile}"> value="${user.mobile}" </c:if>>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPhone" class="col-sm-4 control-label">电话</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="inputPhone" name="phone" <c:if test="${not empty user.phone}"> value="${user.phone}" </c:if>>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-8">
						<button type="submit" class="btn btn-default">注册</button>
					</div>
				</div>
			</form>
		</div>
		<div class="col-md-6 column">
			<h4>表单对应的UserDTO的定义</h4>
			<img src="${ctx}/assets/img/validator/v4.png" alt="">
		</div>
	</div>
	<div class="row clearfix">
		<h1>二、配置验证信息</h1>
		<p>在src\main\resources\validator\下新建UserDTO.val.xml配置文件</p>
		<p>
			<img src="${ctx}/assets/img/validator/v5.png" alt="">
		</p>
		<p>示例校验需求：1、登录名必填，2、密码与确认密码必须相同，3、手机与电话至少填写一个</p>
		<p>其中atleastOne字段为表单无关字段,这里仅为作为mobile、phone必填一个的验证使用，当然atleast也可以配置在mobile或phone的验证中</p>
	</div>
	<div class="row clearfix">
		<h1>三、代码中使用@Validator注解</h1>
		<pre>
			<code class="java">
				@RequestMapping(value = "validatorsubmit", method = RequestMethod.POST)
				public String validatorSubmit(@ModelAttribute("user") @Validator UserDTO userDTO, BindingResult result) {
					return "validator/validatoruse";
				}
			</code>
		</pre>
		<h4>关于cn.xyz.chaos.validator.Validator注解</h4>
		<p>
			1、默认使用<strong>方法名</strong>称作为验证组名称，此处等价于@Validator("validatorSubmit")
		</p>
		<p>2、验证组可同时定义多个，如：@Validator({"g1", "g2"})</p>
	</div>
	<div class="row clearfix">
		<h1>四、页面中显示错误信息</h1>
		<h4>从request中获取_errors属性为一个map结构（key为字段名称，value为错误信息内容）</h4>
	</div>
</chaos:override>
<chaos:override name="javascript">
	<script>
		__menu = "#menu-validator-use";
	</script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/highlight.js/8.2/highlight.min.js" type="text/javascript" />
	<script>
		$(document).ready(function() {
			$('pre code').each(function(i, block) {
				hljs.configure({
					tabReplace : '  '
				});
				hljs.highlightBlock(block);
			});
		});
	</script>
</chaos:override>
<jsp:include page="../__template.jsp" />
