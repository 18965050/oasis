<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>

<chaos:override name="title">Window - Window集成</chaos:override>
<chaos:override name="javascript">
	<script>
    __menu = "#menu-window-integration";
	</script>
</chaos:override>
<chaos:override name="content">
	<div class="row clearfix">
		<h1>Window组件</h1>
		<p>复杂的页面基本上会汇聚很多业务模块的信息，这些被分解为不同的模块，我们称之为”window”。每个独立的window均可独立执行，这样一个完整的页面就可以分解为N多个独立的window。</p>
		<h4>组件提供这些特性</h4>
		<ul>
			<li>能够将一个页面分为多个窗口；</li>
			<li>开发者使用一个主控制器，在主控制器中不断通过WindowAsync.invoke方法，将请求并发转发给多个窗口；</li>
			<li>每个窗口有单独的控制器处理逻辑、可以返回独立视图就像一个web请求一样；</li>
			<li>框架能够处理并发转发、并发逻辑处理、并发渲染，并最后统一返回把html输出给浏览器；</li>
		</ul>
		<h1>集成</h1>
		<img src="${ctx}/assets/img/window/w1.png" alt="">
	</div>
</chaos:override>
<jsp:include page="../__template.jsp" />
