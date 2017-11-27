<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>

<chaos:override name="content">
	<div class="row">
	    <div class="well">
	      <h3>
	      	日志记录对于应用开发中的流程跟踪, 问题定位等起着至关重要的工作.在slf4j面世前,Java日志组件可以说是纷繁复杂,使用方式也不统一. 
	      	我们推荐使用slf4j+logback和slf4j+log4j两种方式进行日志记录. 默认推荐使用slf4j+logback
	       </h3>
	    </div>
 	</div>
 	
  <h2>slf4j</h2>
  <ul>
   <p><h4>slf4j提供了统一的日志使用API接口,可适配诸如JCL,JUL,Log4j,Logback等具体的日志实现类</h4></p>
   </ul>


 	<h2>log4j</h2>  
  <ul>
   <p><h4>log4j作为老牌的日志框架,在已有的应用中还是被大量的使用.我们建议如果继续采用log4j作为日志组件,请将日志接口统一替换为slf4j 日志API</h4></p>
    <div class="well">
    Maven 配置
      <pre>
     	&lt;dependency&gt;
		&lt;groupId&gt;org.slf4j&lt;/groupId&gt;
		&lt;artifactId&gt;slf4j-api&lt;/artifactId&gt;
	&lt;/dependency&gt;

	&lt;dependency&gt;
		&lt;groupId&gt;org.slf4j&lt;/groupId&gt;
		&lt;artifactId&gt;slf4j-log4j12&lt;/artifactId&gt;
	&lt;/dependency&gt;
       </pre>
    </div>   
   </ul> 	 
  
  <h2>logback</h2>
  <ul>
   <p><h4>logback非常相似于log4j(毕竟作者是同一人). 相比于log4j,logback具有以下优势:</h4></p>
    <li>更快的实现: Logback的内核重写了，在一些关键执行路径上性能提升<i style="color:red">10</i>倍以上</li>
    <li>自然无缝的实现了slf4j API接口: 不同于log4j或其他的日志组件, 需要通过适配器进行适配</li>
    <li>自动重新加载配置文件: 可通过配置&lt;configuration&gt;标签的<i style="color:blue;">scan</i>和<i style="color:blue;">scanPeriod</i>属性实现配置文件变化的自动加载</li>
    <li>自动清除和压缩日志文件: 可根据日志文件<i style="color:blue;">大小,数量,时间</i>等条件进行日志的自动清理</li>
    <li>支持条件处理,比如&lt;if&gt;,&lt;then&gt;&lt;else&gt;标签等等,这对于我们通过一个配置文件区分不同的环境十分方便</li>
    <li>支持MDC(Mapped Diagnostic Contexts)和过滤器,使用MDC可以很方便的区分不同应用, 用户等等的上下文,MDC结合强大的过滤器可以方便,快捷的记录下某个上下文日志而忽略其他上下文日志</li>
    <li>logback-access,作为logback发布包的一部分,可与jetty或Tomcat进行无缝集成,提供了非常丰富而强大的通过HTTP访问日志的功能</li>
    
    <div class="well">
    Maven 配置
      <pre>
     	&lt;dependency&gt;
		&lt;groupId&gt;org.slf4j&lt;/groupId&gt;
		&lt;artifactId&gt;slf4j-api&lt;/artifactId&gt;
	&lt;/dependency&gt;

	&lt;dependency&gt;
		&lt;groupId&gt;ch.qos.logback&lt;/groupId&gt;
		&lt;artifactId&gt;logback-classic&lt;/artifactId&gt;
	&lt;/dependency&gt;
       </pre>
    </div>

  </ul>  
  
  	
</chaos:override>

<jsp:include page="${ctx}/WEB-INF/views/__template.jsp"/>