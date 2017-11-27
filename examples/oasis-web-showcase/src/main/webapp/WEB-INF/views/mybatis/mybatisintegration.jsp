<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>

<chaos:override name="title">Mybatis - Mybatis集成</chaos:override>

<chaos:override name="javascript">
  <script>
    __menu = "#menu-mybatis-integration";
  </script>
</chaos:override>

<chaos:override name="content">
  <div class="row clearfix">

    <div id="cnblogs_post_body"><h1>概述</h1>
      <hr>
      <p>Mybatis的集成很简单，用的也就是官方的一套，没做什么特殊处理。所以，这节名字是概述，其实，除了概述也就没了。</p>

      <p>Mybatis集成相关的配置有两处，都放在一个目录里。<br>&nbsp;&nbsp;<img
          src="${ctx}/assets/img/mybatis/221402333264690.jpg" alt=""></p>

      <h1>Mybatis-Spring集成</h1>
      <hr>
      <p>看名字就知道，这节将的是mybatis-spring.xml 这个配置文件。Mybatis和Spring的集成唯一需要关心的可能就是新项目各种类型文件存放的目录了。</p>

      <div class="cnblogs_code">
<pre>  <span style="color: #008000;">&lt;!--</span><span style="color: #008000;"> sqlSession配置 </span><span
    style="color: #008000;">--&gt;</span>
  <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">bean </span><span
      style="color: #ff0000;">id</span><span style="color: #0000ff;">="sqlSessionFactory"</span><span
      style="color: #ff0000;"> class</span><span
      style="color: #0000ff;">="org.mybatis.spring.SqlSessionFactoryBean"</span><span
      style="color: #0000ff;">&gt;</span>
    <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="dataSource"</span><span
      style="color: #ff0000;"> ref</span><span style="color: #0000ff;">="dataSource"</span><span
      style="color: #0000ff;">/&gt;</span>
    <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> 自动扫描entity目录, 省掉Configuration.xml里的手工配置 </span><span
      style="color: #008000;">--&gt;</span>
    <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="typeAliasesPackage"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="cn.xyz.chaos.examples.showcase.entity"</span><span
      style="color: #0000ff;">/&gt;</span>
    <span style="color: #008000;">&lt;!--</span><span style="color: #008000;"> 指定MyBatis配置文件 </span><span
      style="color: #008000;">--&gt;</span>
    <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="configLocation"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="classpath:/mybatis/mybatis-config.xml"</span><span
      style="color: #0000ff;">/&gt;</span>
    <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="mapperLocations"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="classpath:/mybatis/xml/**/*.xml"</span><span
      style="color: #0000ff;">/&gt;</span>
  <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">bean</span><span
      style="color: #0000ff;">&gt;</span>
  <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> 扫描basePackage下所有以@MyBatisRepository标识的 接口 </span><span style="color: #008000;">--&gt;</span>
  <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">bean </span><span
      style="color: #ff0000;">class</span><span style="color: #0000ff;">="org.mybatis.spring.mapper.MapperScannerConfigurer"</span><span
      style="color: #0000ff;">&gt;</span>
    <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="basePackage"</span><span
      style="color: #ff0000;"> value</span><span
      style="color: #0000ff;">="cn.xyz.chaos.examples.showcase.repository"</span><span
      style="color: #0000ff;">/&gt;</span>
    <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="annotationClass"</span><span
      style="color: #ff0000;"> value</span><span
      style="color: #0000ff;">="cn.xyz.chaos.orm.mybatis.MyBatisRepository"</span><span
      style="color: #0000ff;">/&gt;</span>
  <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">bean</span><span
      style="color: #0000ff;">&gt;</span></pre>

      </div>
      <p>都在这两个bean的配置中，上面写了注释了，相信应该没什么疑问。</p>

      <h1>Mybatis配置</h1>
      <hr>
      <p>mybatis-config.xml 文件是Mybatis的配置。大概没什么需要我们在集成的时候特别注意的。我在上面加了注释，看起来应该还很清晰明了的。</p>

      <div class="cnblogs_code">

<pre><span style="color: #008080;"> 1</span> <span style="color: #0000ff;">&lt;?</span><span style="color: #ff00ff;">xml version="1.0" encoding="UTF-8"</span><span
    style="color: #0000ff;">?&gt;</span>
<span style="color: #008080;"> 2</span> <span style="color: #0000ff;">&lt;!</span><span style="color: #ff00ff;">DOCTYPE configuration
</span><span style="color: #008080;"> 3</span> <span style="color: #ff00ff;">  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
</span><span style="color: #008080;"> 4</span> <span style="color: #ff00ff;">  "http://mybatis.org/dtd/mybatis-3-config.dtd"</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 5</span> <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">configuration</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 6</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">settings</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 7</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> 启用缓存？ </span><span style="color: #008000;">--&gt;</span>
<span style="color: #008080;"> 8</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">setting </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="cacheEnabled"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="false"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;"> 9</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> 延迟加载？ </span><span style="color: #008000;">--&gt;</span>
<span style="color: #008080;">10</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">setting </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="lazyLoadingEnabled"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="false"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">11</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> 当启用时, 有延迟加载属性的对象 在被任意延迟加载的属性被访问时将会完全加载所有属性。否则, 每种属性将会按需要加载。 </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">12</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">setting </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="aggressiveLazyLoading"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="false"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">13</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> 是否允许从一条语句中返回多结果集(需要适合的驱动)。 </span><span style="color: #008000;">--&gt;</span>
<span style="color: #008080;">14</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">setting </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="multipleResultSetsEnabled"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="true"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">15</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> 使用column label 代替 column name ，依赖于驱动实现。请参阅驱动文档或测试不同模式的结果</span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">16</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">setting </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="useColumnLabel"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="true"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">17</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> Allows JDBC support for generated keys. A compatible driver is required. This setting forces generated keys to be used if set to true, as some drivers deny compatibility but still work (e.g. Derby). </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">18</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">setting </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="useGeneratedKeys"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="false"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">19</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> 指定 MyBatis 如何自动映射列到字段/ 属性。PARTIAL 只会自动映射简单, 没有嵌套的结果。FULL 会自动映射任 意复杂的结果(嵌套的或其他情况) 。 </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">20</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">setting </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="autoMappingBehavior"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="PARTIAL"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">21</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> 配置默认的执行器。SIMPLE 执行器没 有什么特别之处。REUSE 执行器重用 预处理语句。BATCH 执行器重用语句 和批量更新 </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">22</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">setting </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="defaultExecutorType"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="BATCH"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">23</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> 默认语句执行超时时间 </span><span style="color: #008000;">--&gt;</span>
<span style="color: #008080;">24</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">setting </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="defaultStatementTimeout"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="25"</span><span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">25</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> Allows using RowBounds on nested statements. </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">26</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">setting </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="safeRowBoundsEnabled"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="false"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">27</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> Enables automatic mapping from classic database column names A_COLUMN to camel case classic Java property names aColumn. </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">28</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">setting </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="mapUnderscoreToCamelCase"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="false"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">29</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> MyBatis uses local cache to prevent circular references and speed up repeated nested queries. By default (SESSION) all queries executed during a session are cached. If localCacheScope=STATEMENT local session will be used just for statement execution, no data will be shared between two different calls to the same SqlSession. </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">30</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">setting </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="localCacheScope"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="SESSION"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">31</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> Specifies the JDBC type for null values when no specific JDBC type was provided for the parameter. Some drivers require specifying the column JDBC type but others work with generic values like NULL, VARCHAR or OTHER. </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">32</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">setting </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="jdbcTypeForNull"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="OTHER"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">33</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> Specifies which Object's methods trigger a lazy load </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">34</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">setting </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="lazyLoadTriggerMethods"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="equals,clone,hashCode,toString"</span><span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">35</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> Specifies the language used by default for dynamic SQL generation. </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">36</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> &lt;setting name="defaultScriptingLanguage" value="org.apache.ibatis.scripting.xmltags.XMLDynamicLanguageDriver"/&gt; </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">37</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> 当结果集中含有Null值时是否执行映射对象的setter或者Map对象的put方法。此设置对于原始类型如int,boolean等无效。 </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">38</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">setting </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="callSettersOnNulls"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="false"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">39</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> Specifies the prefix string that MyBatis will add to the logger names. </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">40</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> &lt;setting name="logPrefix" value=""/&gt; </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">41</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> Specifies which logging implementation MyBatis should use. If this setting is not present logging implementation will be autodiscovered. </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">42</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> SLF4J | LOG4J | LOG4J2 | JDK_LOGGING | COMMONS_LOGGING | STDOUT_LOGGING | NO_LOGGING </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">43</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">setting </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="logImpl"</span><span style="color: #ff0000;"> value</span><span
      style="color: #0000ff;">="SLF4J"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">44</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> Specifies the proxy tool that MyBatis will use for creating lazy loading capable objects. </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">45</span>         <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;"> CGLIB | JAVASSIST </span><span style="color: #008000;">--&gt;</span>
<span style="color: #008080;">46</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">setting </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="proxyFactory"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="CGLIB"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">47</span>     <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">settings</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">48</span>     
<span style="color: #008080;">49</span>     <span style="color: #008000;">&lt;!--</span><span style="color: #008000;"> 别名 </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">50</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">typeAliases</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">51</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">package </span><span
      style="color: #ff0000;">name</span><span
      style="color: #0000ff;">="cn.xyz.chaos.examples.showcase.entity"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">52</span>     <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">typeAliases</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">53</span>   
<span style="color: #008080;">54</span>     <span style="color: #008000;">&lt;!--</span><span style="color: #008000;"> 分页插件 </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">55</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">plugins</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">56</span>       <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">plugin </span><span
      style="color: #ff0000;">interceptor</span><span style="color: #0000ff;">="cn.xyz.chaos.orm.mybatis.easylist.paginator.OffsetLimitInterceptor"</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">57</span>        <span style="color: #008000;">&lt;!--</span><span
      style="color: #008000;">&lt;property name="dialectClass" value="cn.xyz.chaos.orm.mybatis.easylist.paginator.dialect.OracleDialect"/&gt;</span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">58</span>        <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="dialectClass"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="cn.xyz.chaos.orm.mybatis.easylist.paginator.dialect.H2Dialect"</span><span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">59</span>       <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">plugin</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">60</span>     <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">plugins</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">61</span>     
<span style="color: #008080;">62</span> <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">configuration</span><span
      style="color: #0000ff;">&gt;</span></pre>

      </div>
      <p>&nbsp;</p></div>

  </div>

</chaos:override>
<jsp:include page="../__template.jsp"/>
