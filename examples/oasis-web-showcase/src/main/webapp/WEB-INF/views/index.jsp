<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>

<chaos:override name="title">Overview - Chaos-Web-Showcase</chaos:override>

<chaos:override name="content">
  <div class="row">
    <div class="well">
      <h1>What is it?</h1>

      <p style="padding: 15px;">Chaos-Web-Showcase 是 Chaos的使用示例，针对Chaos项目中各个技术点的使用进行分别的展示和说明。</p>
    </div>
  </div>
  <h1>What is new?</h1>

  <h2>0.0.1 alpha</h2>
  <ul>
    <li>mvc 基础演示。</li>
    <li>Ajax + Json演示。</li>
    <li>jsp:layout 演示。</li>
    <li>window View 演示。</li>
    <li>Shiro Security 演示。</li>
    <li>Mybatis 及 对应代码生成 演示</li>
    <li>基于Mybatis的 简单列表和分页组件 演示</li>
    <li>日志规范及配置、使用 演示</li>
    <li>Apache commons + google guava等工具类 演示</li>
    <li>结合Shiro的常规权限模型综合演示</li>
    <li>使用Chaos实现的简单保险项目综合演示</li>
    <li>其他</li>
  </ul>

</chaos:override>
<jsp:include page="__template.jsp"/>
