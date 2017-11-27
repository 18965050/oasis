<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>

<chaos:override name="title">Window - Window集成</chaos:override>
<chaos:override name="javascript">
	<script>
    __menu = "#menu-window-devcoc";
	</script>
</chaos:override>
<chaos:override name="content">
	<div class="row clearfix">
		<h1>DevCocInterceptor</h1>
		<p>命名规范-约定大于配置(COC)-仅在开发阶段使用校验，用于规范Url与Controller与view之间的对应关系</p>
		<ul>
			<li>1、类必须以Controller结尾</li>
			<li>2、类所在的[包名+类名]与此类标注的[RequestMapping注解路径]必须一致</li>
			<li>3、类标注的[RequestMapping注解路径]以'/'开头且不以’/’结尾</li>
			<li>4、方法名与此方法标注的[RequestMapping注解路径]需以此开头</li>
			<li>5、方法标注的[RequestMapping注解路径]不能以'/'开头或结尾</li>
			<li>6、视图名称与包+类+方法对应，规则为[包/类名称_方法名称]</li>
		</ul>
	</div>
</chaos:override>
<jsp:include page="../__template.jsp" />
