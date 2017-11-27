<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>

<chaos:override name="title">Window - Window扩展</chaos:override>
<chaos:override name="css">
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/highlight.js/8.2/styles/default.min.css">
</chaos:override>

<chaos:override name="content">
	<div class="row clearfix">
		<h1>Validator扩展</h1>
		<h3>方法一：通过继承AbstractValidator类扩展</h3>
		<p>1、继承AbstractValidator类实现验证器扩展XxxValidator</p>
		<pre>
			<code class="java">
			package cn.xyz.chaos.examples.showcase.web.controller.validator;

			import cn.xyz.chaos.validator.ValidContext;
			import cn.xyz.chaos.validator.validators.AbstractValidator;

			/**
			 * 自定义扩展校验器
			 *
			 * @author mfan
			 */
			public class XxxValidator extends AbstractValidator {

				@Override
				public boolean isValid(Object object, ValidContext validContext) {
					// TODO具体校验规则
					return false;
				}

			}
			</code>
		</pre>
		<p>2、在src\main\resources\validator\validator.xml中配置此XxxValidator，并配置相关参数</p>
		<pre>
			<code class="xml">
			&lt;validators&gt;
			  &lt;validator name="xxx" class="cn.xyz.chaos.examples.showcase.web.controller.validator.XxxValidator" /&gt;
			&lt;/validators&gt;
			</code>
		</pre>
		<h3>方法二：通过扩展已有验证器</h3>
		<p>1、在src\main\resources\validator\validator.xml中配置此扩展已有验证器，配置相关参数</p>
		<p>如下图：(mail扩展了regex校验器)</p>
		<pre>
			<code class="xml">
			&lt;validators&gt;
			  &lt;validator name="mail" extends="regex" args="\\w+@\\w+\\.\\w+" /&gt;
			&lt;/validators&gt;
			</code>
		</pre>
	</div>

</chaos:override>
<chaos:override name="javascript">
	<script>
		__menu = "#menu-window-extend";
	</script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/highlight.js/8.2/highlight.min.js" type="text/javascript" />
	<script>
		$(document).ready(function() {
			$('pre code').each(function(i, block) {
				hljs.configure({
					tabReplace : '  '
				});
				hljs.highlightBlock(block);
			});
		});
	</script>
</chaos:override>
<jsp:include page="../__template.jsp" />
