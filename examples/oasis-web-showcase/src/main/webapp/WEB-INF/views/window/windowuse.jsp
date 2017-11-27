<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>
<chaos:override name="title">Window - Window使用</chaos:override>
<chaos:override name="javascript">
	<script>__menu = "#menu-window-use"</script>
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
<chaos:override name="content">
	<div class="row clearfix">
		<h1>一、创建控制器</h1>
		<h4>一个是WindowAsync主控，另外两个是窗口控制器</h4>
		<pre>
			<code class="java">
				@RequestMapping(value = "index")
				public String index(WindowAsync windowAsync) {
					Window w1 = new Window("w1", "/window/windowasync/w1");
					Window w2 = new Window("w2", "/window/windowasync/w2");
					windowAsync.invoke(w1, w2);
					return "window/windowasync_index";
				}

				@RequestMapping(value = "w1")
				public String w1(WindowAsync windowAsync) {
					return "window/windowasync_w1";
				}

				@RequestMapping(value = "w2")
				public String w2(HttpServletRequest request, HttpServletResponse response) {
					return "window/windowasync_w2";
				}
			</code>
		</pre>
	</div>
	<div class="row clearfix">
		<h1>二、页面</h1>
		<p>webapp/views下创建相应页面（.jsp/.vm）</p>
		<p>
			<img src="${ctx}/assets/img/window/w3.png" alt="">
		</p>
	</div>
	<div class="row clearfix">
		<h1>启动服务器</h1>
		<div>运行该webapp，访问<a href="http://127.0.0.1/window/windowasync/index" target="blank">http://127.0.0.1/window/windowasync/index</a>显示如下</div>
		<p>
			<img src="${ctx}/assets/img/window/w4.png" alt="">
		</p>
	</div>
</chaos:override>
<jsp:include page="../__template.jsp" />
