<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>

<chaos:override name="title">Easylist&Paging - 集成</chaos:override>

<chaos:override name="javascript">
  <script>
    __menu = "#menu-easylist-integration";
  </script>
</chaos:override>

<chaos:override name="content">

  <div id="cnblogs_post_body"><h1>Easylist&amp;Paging集成</h1>
    <hr>
    <p>easylist是从TM借鉴过来的列表工具组件，服务端代码用的。Paging就是分页插件了。</p>

    <p>经过一些改造或者说适配，用插件或工具类的形式和Mybatis的插件倒腾到一起了，集成和用起来都没多少额外的东西。这儿就简要介绍一下。</p>

    <p>先说分页插件。</p>

    <p>分页插件是Mybatis的插件，自然在Mybatis的配置文件mybatis-config.xml中：</p>

    <div class="cnblogs_code">
<pre>    &lt;!-- 分页插件 --&gt;
    &lt;plugins&gt;
      &lt;plugin interceptor="cn.xyz.chaos.orm.mybatis.easylist.paginator.OffsetLimitInterceptor"&gt;
       &lt;!--&lt;property name="dialectClass" value="cn.xyz.chaos.orm.mybatis.easylist.paginator.dialect.OracleDialect"/&gt;--&gt;
       &lt;property name="dialectClass" value="cn.xyz.chaos.orm.mybatis.easylist.paginator.dialect.H2Dialect"/&gt;
      &lt;/plugin&gt;
    &lt;/plugins&gt;</pre>
    </div>
    <p>property "dialectClass" 用户指定分页的方言支持类，注释里面是Oracle，orm项目还有其他类。都是从别处扒来的，H2、mysql、Oracle算是经过多次检验。</p>

    <p>上面的配置就算完成了，代码中的使用使用那节再细说。</p>

    <p>至于easyList插件，其实和分页插件绑定在了一起。在分页插件的流程中有对列表条件语句的自动组装，而分页也算是列表的一部分，故没有拆开，但是使用却可以分开说。<br>easyList没有额外的集成的配置，有工具类和注解供使用，这个同样在使用的那一节会介绍。
    </p>

    <p>&nbsp;</p></div>

</chaos:override>
<jsp:include page="../__template.jsp"/>
