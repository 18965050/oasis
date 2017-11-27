<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>

<chaos:override name="content">
	<div class="row">
	    <div class="well">
	      <h3>日志规范</h3>
	      <p>典型用法示例如下:</p>
	      <pre>
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	
	private Logger	log	= LoggerFactory.getLogger(getClass());
	log.info("hello world!");
	      </pre>
	    </div>
 	</div>
 	
  <h2>日志级别</h2>
  	<ul>
   		<p><h4>Slf4j Logger API 明确规定了五种日志级别,适用场景分别如下:</h4></p>
 	    <li><b style="color: gray">TRACE</b>: 用于记录流水跟踪日志. 比如请求响应跟踪,方法栈调用跟踪等等. 不允许应用开发中使用此级别</li>
      	<li><b style="color: orange">DEBUG</b>: 用于代码调试或问题定位. 比如正式环境问题定位时使用</li>
      	<li><b style="color: green">INFO</b>: 关键流程步骤记录. 比如会员注册,支付流程中的关键步骤记录</li>
      	<li><b style="color: purple">WARN</b>: 用于警告使用方式不正确但不至于应用出错或宕机. 比如配置文件未正确而使用了默认值</li>
      	<li><b style="color: red">ERROR</b>: 用于异常捕获日志打印. 要求必须打印堆栈信息</li>
   </ul>

  <h2>日志规范</h2>
  	<ul>
   		请参见 <a href="${ctx}/assets/docs/log/日志规范-V1.0.docx">日志使用规范</a>
   </ul>
 
  <h2>配置文件模板</h2>
  	<ul>
  		<h4>logback</h4>
   		<li><a href="${ctx}/assets/docs/log/logback-template.xml">logback配置文件模板</a> </li>
   		<li><a href="${ctx}/assets/docs/log/logback.xsd">logback schema文件</a>(由于logback配置极为灵活,没有统一的schema或DTD文件进行约束.
   		这是我从网上找到的一份schema约束文件,方便logback配置文件编写) </li>
   		<h4>log4j</h4>
   		<li><a href="${ctx}/assets/docs/log/log4j-template.xml">log4j配置文件模板</a> </li>
   </ul>  
  	
</chaos:override>

<jsp:include page="${ctx}/WEB-INF/views/__template.jsp"/>