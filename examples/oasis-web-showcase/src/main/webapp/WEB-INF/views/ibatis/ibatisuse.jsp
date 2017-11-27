<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>

<chaos:override name="title">Ibatis - 代码使用</chaos:override>

<chaos:override name="javascript">
	<script>
    __menu = "#menu-ibatis-use";
  </script>
</chaos:override>

<chaos:override name="content">
	<div class="row clearfix">
		<h1>Ibatis 使用</h1>
		<hr>
		<p>使用方式与车险平台框架类似，主要是DAO通过继承IbatisDAO&lt;Entity, Criteria&gt;</p>
		<p>约定：自动生成的sqlmap.xml一般情况下不允许修改，自定义sqlmap新建一个文件保存，这样便于库表修改后可以重新自动生成sqlmap.xml仅需要修改自定义的sqlmap即可</p>
		<p>自定义SQL使用@SqlExecutor注解：</p>
		<ul>
			<li>自定义的sqlmap的namespace为DAO全名</li>
			<li>方法名称必须以select、update、insert、delete开头</li>
			<li>方法名称与自定义的statementName一致</li>
		</ul>
		<hr>
		<h2>More……</h2>
		<ul>
			<li>IbatisDAOPlus&lt;Entity, Criteria, DTO&gt;</li>
			<li>IbatisDAOPlusPlus&lt;Entity, Criteria&gt;</li>
		</ul>
	</div>
</chaos:override>
<jsp:include page="../__template.jsp" />
