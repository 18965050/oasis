<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>

<chaos:override name="title">Ibatis - 代码生成</chaos:override>

<chaos:override name="javascript">
	<script>
    __menu = "#menu-ibatis-generator";
  </script>
</chaos:override>

<chaos:override name="content">
	<div class="row clearfix">
		<h1>Ibatis 代码生成</h1>
		<p>Ibatis代码生成的配置文件可以放在任何位置，是否在项目中都是没关系的。</p>
		<p>Ibatis代码生成的方式通过执行Java代码的main方法即可，为了通用，没用eclipse专用的插件。示例的配置如下：</p>
		<img src="${ctx}/assets/img/ibatis/i3.png" alt="">
	</div>

</chaos:override>
<jsp:include page="../__template.jsp" />
