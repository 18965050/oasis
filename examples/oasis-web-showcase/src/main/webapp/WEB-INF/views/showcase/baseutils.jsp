<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>

<chaos:override name="content">
	<div class="row">
	    <div class="well">
	      <h3>Jakarta Commons和Google Guava均是我们在项目开发中经常使用到的工具类.它们历经了多个版本,使用上已经相当的成熟和稳定,
	      对于代码简化,快速开发实现,可靠使用起到了至关重要的辅助作用 </h3>
	    </div>
 	</div>
 	
  <h2>Jakarta Commons</h2>
  <ul>
   <p><h4>Jakarta Commons发展历史久远,分支也很多.其中有些组件模块是从其他开源框架中提取出来, 也有些组件模块在不断发展过程中脱离了Commons,
   形成新的框架.其重要的组件模块包括: </h4></p>
    <li>基础模块包(commons-lang)</li>
    <li>Bean操作包(commons-bean)</li>
    <li>集合操作包(commons-collection)</li>
    <li>文件上传包(commons-fileupload)</li>
    <li>io操作包(commons-io)</li>
    <li>配置文件处理包(commons-configuration)</li>
    <li>命令行解析包(commons-cli)</li>
    <li>xml解析包(commons-digester)</li>
  </ul> 
  
  <h2>Google Guava</h2>
  <ul>
   <p><h4>相较于Jakarta Commons的发展历程,Google Guava较为年轻,但其发展的势头却很猛. Google Guava API 采用Fluent Style方式,
                          使得代码编写十分的简洁. 其重要的组件模块包括: </h4></p>
    <li>基础包(com.google.common.base)</li>
    <li>集合包(com.google.common.collect)</li>
    <li>缓存包(com.google.common.cache)</li>
    <li>并发包(com.google.common.util.concurrent)</li>
    <li>事件总线包(com.google.common.eventbus)</li>
    <li>io包(com.google.common.io)</li>
    <li>原生类型操作包(com.google.common.primitives)</li>
  </ul>  
  
	<div class="row">
	    <div class="well">
	      <h4>为了方面大家对基础工具包更好的理解和掌握, 特编写了<a href="${ctx}/assets/docs/baseutils/Jakarta Commons和Google Guava使用说明文档.docx">Jakarta Commons和Google Guava使用说明文档.docx</a>. 文档中所有的示例
	      均可在 <b style="color:purple;">chaos-common-test</b>项目中找到并进行单元测试 </h4>
	    </div>
 	</div>  
  
  	
</chaos:override>

<jsp:include page="${ctx}/WEB-INF/views/__template.jsp"/>