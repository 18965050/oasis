<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>

<chaos:override name="title">Validator - validator集成</chaos:override>
<chaos:override name="javascript">
	<script>
    __menu = "#menu-validator-integration";
	</script>
</chaos:override>
<chaos:override name="content">
	<div class="row clearfix">
		<h1>概述</h1>
		<p>validator的方案由三种备选：1、Hibernate Validator，2、Focus Validator，3、Dev（重新开发）</p>
		<table class="table table-bordered table-condensed" contenteditable="true">
			<thead>
				<tr>
					<th>方案</th>
					<th>优点</th>
					<th>缺点</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1、Hibernate Validator</td>
					<td>代码与配置完美的整合到Class中，不仅可以使用在Controller层，其他层也可以复用</td>
					<td>Class中代码包含大量注解，更主要是因为分组定义比较复杂，需要定义接口且并修改Class注解</td>
				</tr>
				<tr class="warning">
					<td>2、Focus Validator</td>
					<td>与前端验证控件集成，统一由配置文件定义</td>
					<td>前端的趋势是使用jQuery Validation</td>
				</tr>
				<tr class="success">
					<td>3、Dev(chaos-validator)</td>
					<td>使用方式与Focus Validator类似，与前端控件解绑</td>
					<td>工作量</td>
				</tr>
			</tbody>
		</table>
		<h1>validator与Spring集成配置</h1>
		<p>validator的集成很简单，用的也就是官方Spring
			Validator的一套（实现org.springframework.validation.Validator接口），validator集成相关的配置有在spring-mvc.xml里。</p>
		<p>如下图：</p>
		<p>
			<img src="${ctx}/assets/img/validator/v2.png" alt="">
		</p>
		<p>
			<img src="${ctx}/assets/img/validator/v1.png" alt="">
		</p>
		<p>配置在 spring-mvc.xml 这个配置文件，上面写了注释了，相信应该没什么疑问。</p>
		<h1>validator配置</h1>
		<hr>
		<p>validator.xml文件是校验器的配置，大概没什么需要我们在集成的时候特别注意的，红框是自定义扩展的校验器。</p>
		<div class="cnblogs_code">
			<img src="${ctx}/assets/img/validator/v3.png" alt="">
		</div>
		<h1>内置校验器</h1>
		<div>
			<table class="table table-bordered table-condensed" contenteditable="true">
				<thead>
					<tr>
						<th>内置校验器</th>
						<th>名称</th>
						<th>参数示例</th>
						<th>说明</th>
					</tr>
				</thead>
				<tbody>
					<tr class="warning">
						<td>RequiredValidator</td>
						<td>required</td>
						<td></td>
						<td>必填（notnull+notblank）</td>
					</tr>
					<tr class="warning">
						<td>NullValidator/NotNullValidator</td>
						<td>null/notnull</td>
						<td></td>
						<td>Null或非Null</td>
					</tr>
					<tr class="warning">
						<td>NotBlankValidator</td>
						<td>notblank</td>
						<td></td>
						<td>非空格字符</td>
					</tr>
					<tr class="success">
						<td>RegexValidator</td>
						<td>regex</td>
						<td>args="\\w+",mode="0x01"（见：Pattern中mode定义）</td>
						<td>正则表达式匹配</td>
					</tr>
					<tr class="warning">
						<td>LengthValidator</td>
						<td>length</td>
						<td>args="1,8"</td>
						<td>字符串长度校验，长度1到8之间,包含1和8</td>
					</tr>
					<tr class="warning">
						<td>MaxValidator/MinValidator</td>
						<td>max/min</td>
						<td>args="100"</td>
						<td>数字类型最大或最小</td>
					</tr>
					<tr class="warning">
						<td>SizeValidator</td>
						<td>size</td>
						<td>args="1,10"</td>
						<td>大小，支持：字符串、数组、Collection、Map</td>
					</tr>
					<tr class="success">
						<td>EqualValidator</td>
						<td>equal</td>
						<td>args="field1"</td>
						<td>比较校验器，支持Number、Date、String型比较，args为比较字段</td>
					</tr>
					<tr class="success">
						<td>CompareValidator</td>
						<td>notblank</td>
						<td>args="field1,field2,field3", mode="LT"</td>
						<td>多值比较校验器，可以定义在‘实际字段’或‘虚拟字段’中，支持Number、Date、String型比较，参数args为字段列表<br>
							提供比较模式（mode）有：相等（EQ）、不相等（NE）、小于（LT）、小于等于（LE）、大于（GT）、大于等于（GE）<br>
						</td>
					</tr>
					<tr class="success">
						<td>AtMostValidator/AtLeastValidator</td>
						<td>atmost/atleast</td>
						<td>args="field1,field2,field3",mode="2"</td>
						<td>至少或至多N个，可以定义在‘实际字段’或‘虚拟字段’中<br>即标识N=2,args参与的实际字段名称
						</td>
					</tr>
					<tr class="warning">
						<td>PastValidator/FutureValidator</td>
						<td>past/future</td>
						<td></td>
						<td>当前时间之后或之前</td>
					</tr>
					<tr class="success">
						<td>UrlValidator</td>
						<td>url</td>
						<td></td>
						<td>Url</td>
					</tr>
					<tr class="warning">
						<td>EmailValidator</td>
						<td>email</td>
						<td></td>
						<td>邮箱格式</td>
					</tr>
				</tbody>
			</table>
		</div>
		相关验证器配置在src\main\resources\validator\validator.xml中配置。 validator和Spring的集成唯一需要关心的可能就是新项目各种类型文件存放的目录了。
		<h1>TODO</h1>
		<p>譬如：选择'证件类型'与'证件号码'的组合校验，这里面涉及到多字段条件判断，目前还没有好的想法</p>
	</div>
</chaos:override>
<jsp:include page="../__template.jsp" />
