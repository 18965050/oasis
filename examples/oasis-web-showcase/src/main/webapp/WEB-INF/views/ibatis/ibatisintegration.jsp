<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>

<chaos:override name="title">Ibatis - 集成</chaos:override>

<chaos:override name="javascript">
	<script>
    __menu = "#menu-ibatis-integration";
  </script>
</chaos:override>

<chaos:override name="content">
	<div class="row clearfix">
		<h1>概述</h1>
		<p>Ibatis的集成很简单，用的也就是官方的一套，但做了特殊处理，主要是修改原来的逻辑分页为物理分页。</p>
		<p>Ibatis集成相关的配置有两处，都放在一个目录里。</p>
		<p>src\main\resources\ibatis\ibatis-spring.xml</p>
		<p>src\main\resources\ibatis\ibatis-config.xml</p>
		<img src="${ctx}/assets/img/ibatis/i1.png" alt=""> <img src="${ctx}/assets/img/ibatis/i2.png" alt="">
		<p>ibatis-config.xml 文件是ibatis的配置。大概没什么需要我们在集成的时候特别注意的。我在上面加了注释，看起来应该还很清晰明了的。</p>
		<p>也可以在ibatis-spring.xml配置中
	</div>
</chaos:override>
<jsp:include page="../__template.jsp" />
