#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>

<chaos:override name="title">恭喜你，项目成功运行！</chaos:override>

<chaos:override name="content">
  <div class="row">
    <div class="well">
      <h1>恭喜你！</h1>

      <p style="padding: 15px;">这是通过archetype生成的新项目！</p>
    </div>
  </div>
  <h2>接下来做什么?</h2>

  <h3>增删改！</h3>
  <p>
    项目中保留了认证涉及的部分代码。你可以根据你的需要进行任意的增删改！
  </p>
  <p>
    你可以测试下注册和登陆的功能，我们默认用的是嵌入式的H2，并且会在项目运行时初始化数据库。<br/>
    如果你正式使用的话，请确保你配置了正确的数据源。
  </p>
  <p>
    这边还有个小后台，里面只有第二个标签的用户信息管理有效。你可以结合URL体会下新项目的路径映射要求。
    <a href="${symbol_dollar}{ctx}/admin/admin/index">后台地址：${symbol_dollar}{ctx}/admin/admin/index</a>
  </p>

</chaos:override>
<jsp:include page="__template.jsp"/>
