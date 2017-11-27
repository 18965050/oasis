<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>

<chaos:override name="title">Validator - validator扩展</chaos:override>

<chaos:override name="css">
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/highlight.js/8.2/styles/default.min.css">
</chaos:override>

<chaos:override name="content">
	<h1>Validator扩展</h1>
	<div class="row clearfix">
		<h2>一、校验规则扩展</h2>
		<p>用于对字段规则的校验约束，如：requied、notnull、regex等</p>
		<h4>方法一：通过继承AbstractValidator类扩展</h4>
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
		<h4>方法二：通过扩展已有验证器</h4>
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
	<div class="row clearfix">
		<h2>二、校验执行器扩展</h2>
		<p>用于分组校验是对字段执行校验，目前实现了字段校验与条件判断（field、script）</p>
		<h4>步骤一：通过继承cn.xyz.chaos.validator.action.ActionValidator类扩展</h4>
		<p>1、继承ActionValidator类实现验证器扩展，如：FieldActionValidator</p>
		<pre>
			<code class="java">
public class FieldActionValidator implements ActionValidator<FieldAction> {
	@Override
	public List<EasyFieldError> validator(EasyValidatorUtilities utilities, ValidContext validContext,
			FieldAction action, ActionValidatorChain chain) {
		List<Valid> valids = action.getValids();
		for (Valid valid : valids) {
			AbstractValidator validator = utilities.getContext().getValidator(valid.getName());
			if (validator != null) {
				validContext.setValid(valid);
				// 验证值
				Object object = BeanUtils.getValue(validContext.getTarget(), action.getProperty());
				if (!validator.isValid(object, validContext)) {
					EasyFieldError error = new EasyFieldError(action.getProperty(), valid.getMsg(), StringUtils.split(
							valid.getArgs(), StringUtils.COMMA));
					validContext.getErrors().add(error);
					return Arrays.asList(error);
				}
			}
		}
		return null;
	}

	@Override
	public boolean supports(Action action) {
		return action instanceof Field;
	}

}
			</code>
		</pre>
		<p>2、在src\main\resources\validator\validator.xml中配置此FieldActionValidator，并配置相关参数</p>
		<pre>
			<code class="xml">
			&lt;validators&gt;
			  &lt;action-validator class="cn.xyz.chaos.validator.action.FieldActionValidator" /&gt;
			&lt;/validators&gt;
			</code>
		</pre>
		<h4>步骤二：扩展xml校验执行器配置解析器</h4>
		<p>1、继承cn.xyz.chaos.validator.action.xml.ActionResolver类实现验证器扩展，如：FieldActionResolver</p>
		<p>如下图：(mail扩展了regex校验器)</p>
		<pre>
			<code class="xml">
			&lt;validators&gt;
			  &lt;action-resolver class="cn.xyz.chaos.validator.action.xml.FieldActionResolver"/&gt;
			&lt;/validators&gt;
			</code>
		</pre>
	</div>

</chaos:override>
<chaos:override name="javascript">
	<script>__menu = "#menu-validator-extend";</script>
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
