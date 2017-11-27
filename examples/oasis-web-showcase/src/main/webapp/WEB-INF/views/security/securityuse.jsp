<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>

<chaos:override name="title">Security - Shiro使用</chaos:override>

<chaos:override name="javascript">
  <script>
    __menu = "#menu-shiro-use";
  </script>
</chaos:override>

<chaos:override name="content">
<div class="row clearfix">

<div id="cnblogs_post_body">

<h1>综述</h1>
<hr>
<p>Shiro作为一个Security框架，其作用通俗来讲就是控制资源访问。<br>从此处Shiro的使用的角度来看，我们可以狭隘的定义Shiro可控的资源为"url"、"method"和"页面内容"。</p>
<p>针对上面的三点，下面来分别讲解Shiro中常用的三类授权的方法：Filter配置、注解和标签。当然，除此之外还有更灵活的传统API方式，这点也会补充。</p>
<h1>Filter配置</h1>
<hr>
<p>&nbsp;在《Shiro集成》中 #认证流程 一节中讲到过filterChainDefinitions的配置。我们再来看一下：</p>
<div class="cnblogs_code">
<pre><span style="color: #008080;"> 1</span> <span style="color: #008000;">&lt;!--</span><span style="color: #008000;"> Security 过滤器链配置 </span><span style="color: #008000;">--&gt;</span>
<span style="color: #008080;"> 2</span>   <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">bean </span><span style="color: #ff0000;">id</span><span style="color: #0000ff;">="shiroFilter"</span><span style="color: #ff0000;"> class</span><span style="color: #0000ff;">="cn.xyz.chaos.mvc.shiro.CookieAuthShiroFilterFactoryBean"</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 3</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span style="color: #ff0000;">name</span><span style="color: #0000ff;">="shiroFilter"</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 4</span>       <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">bean </span><span style="color: #ff0000;">class</span><span style="color: #0000ff;">="cn.xyz.chaos.examples.showcase.authc.CookieAuthShiroFilter"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;"> 5</span>     <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">property</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 6</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span style="color: #ff0000;">name</span><span style="color: #0000ff;">="securityManager"</span><span style="color: #ff0000;"> ref</span><span style="color: #0000ff;">="securityManager"</span> <span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;"> 7</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span style="color: #ff0000;">name</span><span style="color: #0000ff;">="loginUrl"</span><span style="color: #ff0000;"> value</span><span style="color: #0000ff;">="${authLoginUrl}"</span> <span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;"> 8</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span style="color: #ff0000;">name</span><span style="color: #0000ff;">="successUrl"</span><span style="color: #ff0000;"> value</span><span style="color: #0000ff;">="${successUrl}"</span> <span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;"> 9</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span style="color: #ff0000;">name</span><span style="color: #0000ff;">="unauthorizedUrl"</span><span style="color: #ff0000;"> value</span><span style="color: #0000ff;">="${unauthorizedUrl}"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">10</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span style="color: #ff0000;">name</span><span style="color: #0000ff;">="filters"</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">11</span>       <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">map</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">12</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">entry </span><span style="color: #ff0000;">key</span><span style="color: #0000ff;">="logout"</span><span style="color: #ff0000;"> value-ref</span><span style="color: #0000ff;">="logoutFilter"</span> <span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">13</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">entry </span><span style="color: #ff0000;">key</span><span style="color: #0000ff;">="authc"</span><span style="color: #ff0000;"> value-ref</span><span style="color: #0000ff;">="keyFormAuthenticationFilter"</span> <span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">14</span>       <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">map</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">15</span>     <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">property</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">16</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span style="color: #ff0000;">name</span><span style="color: #0000ff;">="filterChainDefinitions"</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">17</span>       <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">value</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">18</span> <span style="color: #000000;">        /favoicon.ico = anon
</span><span style="color: #008080;">19</span> <span style="color: #000000;">        /auth/auth/login = authc
</span><span style="color: #008080;">20</span> <span style="color: #000000;">        /logout = logout
</span><span style="color: #008080;">21</span> <span style="color: #000000;">        /membercenter/** =authc
</span><span style="color: #008080;">22</span> <span style="color: #000000;">        /** = anon
</span><span style="color: #008080;">23</span>       <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">value</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">24</span>     <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">property</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">25</span>   <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">bean</span><span style="color: #0000ff;">&gt;</span></pre>
</div>
<p>上面 property "filterChainDefinitions"就是我们此节要关注的Filter配置。<br>此处的Filter虽是Shiro内部的Filter流程，但意义和形式与容器的Filter类似。在通过Shiro内部的Filter之后便会继续执行容器的Filter。</p>
<p>Shiro验证URL时,URL匹配成功便不再继续匹配查找(所以要注意配置文件中的URL顺序,尤其在使用通配符时) 故filterChainDefinitions的配置顺序为自上而下,以最上面的为准。</p>
<p>当运行一个Web应用程序时,Shiro将会创建一些有用的默认Filter实例,并自动地在将它们置为可用 <br>  自动地可用的默认的Filter实例是被DefaultFilter枚举类定义的,枚举的名称字段就是可供配置的名称：</p>
<div class="cnblogs_code">
<pre><span style="color: #008080;"> 1</span> <span style="color: #000000;">  anon---------------org.apache.shiro.web.filter.authc.AnonymousFilter
</span><span style="color: #008080;"> 2</span> <span style="color: #000000;">  authc--------------org.apache.shiro.web.filter.authc.FormAuthenticationFilter
</span><span style="color: #008080;"> 3</span> <span style="color: #000000;">  authcBasic---------org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
</span><span style="color: #008080;"> 4</span> <span style="color: #000000;">  logout-------------org.apache.shiro.web.filter.authc.LogoutFilter
</span><span style="color: #008080;"> 5</span> <span style="color: #000000;">  noSessionCreation--org.apache.shiro.web.filter.session.NoSessionCreationFilter
</span><span style="color: #008080;"> 6</span> <span style="color: #000000;">  perms--------------org.apache.shiro.web.filter.authz.PermissionAuthorizationFilter
</span><span style="color: #008080;"> 7</span> <span style="color: #000000;">  port---------------org.apache.shiro.web.filter.authz.PortFilter
</span><span style="color: #008080;"> 8</span> <span style="color: #000000;">  rest---------------org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
</span><span style="color: #008080;"> 9</span> <span style="color: #000000;">  roles--------------org.apache.shiro.web.filter.authz.RolesAuthorizationFilter
</span><span style="color: #008080;">10</span> <span style="color: #000000;">  ssl----------------org.apache.shiro.web.filter.authz.SslFilter
</span><span style="color: #008080;">11</span>   user---------------org.apache.shiro.web.filter.authz.UserFilter </pre>
</div>
<p>&nbsp;</p>
<p>当然，这些Filter可以像示例中的一样，添加或覆盖（重名即覆盖）。</p>
<p>通常可将这些过滤器分为两组 ：</p>
<ul>
  <li>anon,authc,authcBasic,user是第一组认证过滤器</li>
  <li>perms,port,rest,roles,ssl是第二组授权过滤器</li>
</ul>
<p>在这儿插入一个概念介绍，user和authc的不同：</p>
<div class="cnblogs_code">
  <pre>当应用开启了rememberMe时,用户下次访问（如登陆超时后，关闭浏览器后等）时可以是一个user,但绝不会是authc,因为authc是需要重新认证的（假设有登陆超时时间或为Session生命周期的认证）。user表示用户不一定已通过认证,只要曾被Shiro记住过登录状态的用户就可以正常发起请求（意思就是，我们推断你应该是谁）,比如rememberMe 。说白了,以前的一个用户登录时开启了rememberMe,然后他关闭浏览器,下次再访问时他就是一个user,而不会authc。想要是authc，你必须重新认证一下，以确保，你的确是谁谁谁。</pre>
</div>
<p>&nbsp;</p>
<p>这个概念在余下的几种认证方式中都存在，可供用户使用。</p>
<p>好，下面继续讲Filter。<br>先举几个例子：</p>
<div class="cnblogs_code">
<pre>/admin=authc,roles[admin]      表示用户必需已通过认证,并拥有admin角色才可以正常发起<span style="color: #000000; font-weight: bold;">'</span><span style="color: #000000; font-weight: bold;">/admin</span><span style="color: #000000; font-weight: bold;">'</span><span style="color: #000000;">请求
</span>/edit=authc,perms[admin:edit]  表示用户必需已通过认证,并拥有admin:edit权限才可以正常发起<span style="color: #000000; font-weight: bold;">'</span><span style="color: #000000; font-weight: bold;">/edit</span><span style="color: #000000; font-weight: bold;">'</span><span style="color: #000000;">请求
</span>/home=user                     表示用户不一定需要已经通过认证,只需要曾经被Shiro记住过登录状态就可以正常发起<span style="color: #000000; font-weight: bold;">'</span><span style="color: #000000; font-weight: bold;">/home</span><span style="color: #000000; font-weight: bold;">'</span>请求 </pre>
</div>
<p>&nbsp;</p>
<p>用起来大概就是这样，各默认过滤器常用方法如下(注意URL Pattern里用到的是两颗星,这样才能实现任意层次的全匹配)：</p>
<div class="cnblogs_code">
<pre>  /admins/**=anon             无参,表示可匿名使用,<span style="color: #000000;">可以理解为匿名用户或游客
  </span>/admins/user/**=authc       无参,<span style="color: #000000;">表示需认证才能使用
  </span>/admins/user/**=authcBasic  无参,<span style="color: #000000;">表示httpBasic认证
  </span>/admins/user/**=user        无参,表示必须存在用户,<span style="color: #000000;">当登入操作时不做检查
  </span>/admins/user/**=ssl         无参,表示安全的URL请求,<span style="color: #000000;">协议为https
  </span>/admins/user/**=perms[user:add:*<span style="color: #000000;">]
      参数可写多个</span>,多参时必须加上引号,且参数之间用逗号分割,如/admins/user/**=perms[<span style="color: #000000; font-weight: bold;">"</span><span style="color: #000000; font-weight: bold;">user:add:*,user:modify:*</span><span style="color: #000000; font-weight: bold;">"</span><span style="color: #000000;">]
      当有多个参数时必须每个参数都通过才算通过</span>,<span style="color: #000000;">相当于isPermitedAll()方法
  </span>/admins/user/**=port[<span style="color: #800000;">8081</span><span style="color: #000000;">]
      当请求的URL端口不是8081时</span>,跳转到schemal://serverName:<span style="color: #800000;">8081</span>?<span style="color: #000000;">queryString
      其中schmal是协议http或https等</span>,serverName是你访问的Host,8081是Port端口,queryString是你访问的URL里的?<span style="color: #000000;">后面的参数
  </span>/admins/user/**=<span style="color: #000000;">rest[user]
      根据请求的方法</span>,相当于/admins/user/**=perms[user:method],其中method为post,get,<span style="color: #000000;">delete等
  </span>/admins/user/**=<span style="color: #000000;">roles[admin]
      参数可写多个</span>,多个时必须加上引号,且参数之间用逗号分割,如/admins/user/**=roles[<span style="color: #000000; font-weight: bold;">"</span><span style="color: #000000; font-weight: bold;">admin,guest</span><span style="color: #000000; font-weight: bold;">"</span><span style="color: #000000;">]
      当有多个参数时必须每个参数都通过才算通过</span>,相当于hasAllRoles()方法 </pre>
</div>
<h1>注解配置</h1>
<hr>
<p>注解一般用在方法上，在Spring mvc中，对应每个Controller的方法，其实也相当于URL的更进一步了。<br>按照一般的使用来讲，用户访问的URL控制在Filter中已经配置，那这儿的控制更相当于后台校验一样，只有恶意的用户或异常情况，才回让不符合条件的用户请求访问到这儿来，那自然是不需要给请求"好脸色"看了。<br>所以，如果请求不符合解配置的话一般都是抛出对应的认证/授权异常，由框架统一处理。</p>
<p>注解的使用很简单，就不需要多讲了，可用的注解如下：</p>
<p><strong>@ RequiresAuthentication</strong>&nbsp;<br>可以用户类/属性/方法，用于表明当前用户需是经过认证的用户。&nbsp;</p>
<div id="" class="dp-highlighter">
  <div class="bar">
    <div class="tools">
      <div class="cnblogs_code">
<pre><span style="color: #008080;">1</span> <span style="color: #000000;">@RequiresAuthentication
</span><span style="color: #008080;">2</span> <span style="color: #0000ff;">public</span> <span style="color: #0000ff;">void</span><span style="color: #000000;"> updateAccount(Account userAccount) {
</span><span style="color: #008080;">3</span>     <span style="color: #008000;">//</span><span style="color: #008000;">this method will only be invoked by a
</span><span style="color: #008080;">4</span>     <span style="color: #008000;">//</span><span style="color: #008000;">Subject that is guaranteed authenticated</span>
<span style="color: #008080;">5</span> <span style="color: #000000;">    ...
</span><span style="color: #008080;">6</span> }</pre>
      </div>
    </div>
  </div>
</div>
<p><strong>@ RequiresGuest</strong>&nbsp;<br>表明该用户需为”guest”用户&nbsp;<br><br><strong>@ RequiresPermissions</strong>&nbsp;<br>当前用户需拥有制定权限&nbsp;</p>
<div class="cnblogs_code">
<pre><span style="color: #008080;">1</span> @RequiresPermissions("account:create"<span style="color: #000000;">)
</span><span style="color: #008080;">2</span> <span style="color: #0000ff;">public</span> <span style="color: #0000ff;">void</span><span style="color: #000000;"> createAccount(Account account) {
</span><span style="color: #008080;">3</span>     <span style="color: #008000;">//</span><span style="color: #008000;">this method will only be invoked by a Subject
</span><span style="color: #008080;">4</span>     <span style="color: #008000;">//</span><span style="color: #008000;">that is permitted to create an account</span>
<span style="color: #008080;">5</span> <span style="color: #000000;">    ...
</span><span style="color: #008080;">6</span> }</pre>
</div>
<p><strong>@RequiresRoles</strong>&nbsp;<br>当前用户需拥有制定角色&nbsp;<br><br><strong>@ RequiresUser</strong>&nbsp;<br>当前用户需为已认证用户或已记住用户&nbsp;</p>
<p>&nbsp;</p>
<h1>标签</h1>
<hr>
<p>很多情况下，一个URL可以由不同身份的用户访问，method的注解配置也可以通过，但是，页面上展示的内容却根据不同的用户类型而不同。如页面上对未登录的用户显示一句话，提示用户登录。这样的需求很常见，但是作为对View的控制，如果在后台判断好，设置不同的内容前台展示，或是后台判断然后塞个标志位前台再判断一把这样多少有些别扭而且麻烦。所以，Shiro提供了一些方便的标签供我们使用。</p>
<p>下面一一介绍Shiro的标签：&nbsp;</p>
<p><br><strong>guest标签&nbsp;</strong><br>验证当前用户是否为“访客”，即未认证（包含未记住）的用户&nbsp;</p>
<div id="" class="dp-highlighter">
  <div class="bar">
    <div class="tools">
      <div class="cnblogs_code">
<pre><span style="color: #008080;">1</span> <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">shiro:guest</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">2</span>     Hi there!  Please <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">a </span><span style="color: #ff0000;">href</span><span style="color: #0000ff;">="login.jsp"</span><span style="color: #0000ff;">&gt;</span>Login<span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">a</span><span style="color: #0000ff;">&gt;</span> or <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">a </span><span style="color: #ff0000;">href</span><span style="color: #0000ff;">="signup.jsp"</span><span style="color: #0000ff;">&gt;</span>Signup<span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">a</span><span style="color: #0000ff;">&gt;</span><span style="color: #000000;"> today!
</span><span style="color: #008080;">3</span> <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">shiro:guest</span><span style="color: #0000ff;">&gt;</span></pre>
      </div>
    </div>
  </div>
</div>
<p><strong>user标签&nbsp;</strong><br>认证通过或已记住的用户&nbsp;</p>
<div id="" class="dp-highlighter">
  <div class="bar">
    <div class="tools">
      <div class="cnblogs_code">
<pre><span style="color: #008080;">1</span> <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">shiro:user</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">2</span>     Welcome back John!  Not John? Click <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">a </span><span style="color: #ff0000;">href</span><span style="color: #0000ff;">="login.jsp"</span><span style="color: #0000ff;">&gt;</span>here<span style="color: #0000ff;">&lt;</span><span style="color: #800000;">a</span><span style="color: #0000ff;">&gt;</span><span style="color: #000000;"> to login.
</span><span style="color: #008080;">3</span> <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">shiro:user</span><span style="color: #0000ff;">&gt;</span></pre>
      </div>
    </div>
  </div>
</div>
<p><strong>authenticated标签&nbsp;</strong><br>已认证通过的用户。不包含已记住的用户，这是与user标签的区别所在。&nbsp;</p>
<div id="" class="dp-highlighter">
  <div class="bar">
    <div class="tools">
      <div class="cnblogs_code">
<pre><span style="color: #008080;">1</span> <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">shiro:authenticated</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">2</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">a </span><span style="color: #ff0000;">href</span><span style="color: #0000ff;">="updateAccount.jsp"</span><span style="color: #0000ff;">&gt;</span>Update your contact information<span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">a</span><span style="color: #0000ff;">&gt;</span><span style="color: #000000;">.
</span><span style="color: #008080;">3</span> <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">shiro:authenticated</span><span style="color: #0000ff;">&gt;</span></pre>
      </div>
    </div>
  </div>
</div>
<p><strong>notAuthenticated标签&nbsp;</strong><br>未认证通过用户，与authenticated标签相对应。与guest标签的区别是，该标签包含已记住用户。&nbsp;</p>
<div id="" class="dp-highlighter">
  <div class="bar">
    <div class="tools">
      <div class="cnblogs_code">
<pre><span style="color: #008080;">1</span> <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">shiro:notAuthenticated</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">2</span>     Please <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">a </span><span style="color: #ff0000;">href</span><span style="color: #0000ff;">="login.jsp"</span><span style="color: #0000ff;">&gt;</span>login<span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">a</span><span style="color: #0000ff;">&gt;</span><span style="color: #000000;"> in order to update your credit card information.
</span><span style="color: #008080;">3</span> <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">shiro:notAuthenticated</span><span style="color: #0000ff;">&gt;</span></pre>
      </div>
    </div>
  </div>
</div>
<p><strong>principal 标签&nbsp;</strong><br>输出当前用户信息，通常为登录帐号信息&nbsp;shiro:principal 所代表的对象就是我们在认证的时候生成的AccInfo了。</p>
<div id="" class="dp-highlighter">
  <div class="bar">
    <div class="tools">
      <div class="cnblogs_code">
        <pre><span style="color: #008080;">1</span> Hello, <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">shiro:principal </span><span style="color: #ff0000;">property</span><span style="color: #0000ff;">="name"</span><span style="color: #0000ff;">/&gt;</span>, how are you today?</pre>
      </div>
    </div>
  </div>
</div>
<p><strong>hasRole标签</strong>&nbsp;<br>验证当前用户是否属于该角色&nbsp;</p>
<div id="" class="dp-highlighter">
  <div class="bar">
    <div class="tools">
      <div class="cnblogs_code">
<pre><span style="color: #008080;">1</span> <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">shiro:hasRole </span><span style="color: #ff0000;">name</span><span style="color: #0000ff;">="administrator"</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">2</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">a </span><span style="color: #ff0000;">href</span><span style="color: #0000ff;">="admin.jsp"</span><span style="color: #0000ff;">&gt;</span>Administer the system<span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">a</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">3</span> <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">shiro:hasRole</span><span style="color: #0000ff;">&gt;</span></pre>
      </div>
    </div>
  </div>
</div>
<p><strong>lacksRole标签&nbsp;</strong><br>与hasRole标签逻辑相反，当用户不属于该角色时验证通过&nbsp;</p>
<div id="" class="dp-highlighter">
  <div class="bar">
    <div class="tools">
      <div class="cnblogs_code">
<pre><span style="color: #008080;">1</span> <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">shiro:lacksRole </span><span style="color: #ff0000;">name</span><span style="color: #0000ff;">="administrator"</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">2</span> <span style="color: #000000;">    Sorry, you are not allowed to administer the system.
</span><span style="color: #008080;">3</span> <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">shiro:lacksRole</span><span style="color: #0000ff;">&gt;</span></pre>
      </div>
    </div>
  </div>
</div>
<p><strong>hasAnyRole标签&nbsp;</strong><br>验证当前用户是否属于以下任意一个角色。&nbsp;</p>
<div id="" class="dp-highlighter">
  <div class="bar">
    <div class="tools">
      <div class="cnblogs_code">
<pre><span style="color: #008080;">1</span> <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">shiro:hasAnyRoles </span><span style="color: #ff0000;">name</span><span style="color: #0000ff;">="developer, project manager, administrator"</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">2</span> <span style="color: #000000;">    You are either a developer, project manager, or administrator.
</span><span style="color: #008080;">3</span> <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">shiro:lacksRole</span><span style="color: #0000ff;">&gt;</span></pre>
      </div>
    </div>
  </div>
</div>
<p><strong>hasPermission标签&nbsp;</strong><br>验证当前用户是否拥有指定权限&nbsp;</p>
<div id="" class="dp-highlighter">
  <div class="bar">
    <div class="tools">
      <div class="cnblogs_code">
<pre><span style="color: #008080;">1</span> <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">shiro:hasPermission </span><span style="color: #ff0000;">name</span><span style="color: #0000ff;">="user:create"</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">2</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">a </span><span style="color: #ff0000;">href</span><span style="color: #0000ff;">="createUser.jsp"</span><span style="color: #0000ff;">&gt;</span>Create a new User<span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">a</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">3</span> <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">shiro:hasPermission</span><span style="color: #0000ff;">&gt;</span></pre>
      </div>
    </div>
  </div>
</div>
<p><strong>lacksPermission标签&nbsp;</strong><br>与hasPermission标签逻辑相反，当前用户没有指定权限时，验证通过&nbsp;</p>
<div class="cnblogs_code">
<pre><span style="color: #008080;">1</span>   <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">shiro:lacksPermission </span><span style="color: #ff0000;">name</span><span style="color: #0000ff;">="user:create"</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">2</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">a </span><span style="color: #ff0000;">href</span><span style="color: #0000ff;">="apply.jsp"</span><span style="color: #0000ff;">&gt;</span>申请权限<span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">a</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">3</span>   <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">shiro:lacksPermission</span><span style="color: #0000ff;">&gt;</span></pre>
</div>
<h1>api</h1>
<hr>
<p>API 也是最灵活的了，可以用在绝大多数你想用的地方，随心所欲组合为各种样子。当然，越灵活的东西，用着就越费神。大部分场景Shiro都已考虑到了，所以，如果可以的话先用上面几个，满足不了再自己改造。</p>
<div class="dp-highlighter"><strong>1.基于传统角色授权实现</strong>&nbsp;<br>当需要验证用户是否拥有某个角色时，可以调用Subject 实例的hasRole*方法验证。&nbsp;<br>
<div id="" class="dp-highlighter">
  <div class="bar">
    <div class="tools">
      <div class="cnblogs_code">
<pre>Subject currentUser =<span style="color: #000000;"> SecurityUtils.getSubject();
</span><span style="color: #0000ff;">if</span> (currentUser.hasRole("administrator"<span style="color: #000000;">)) {
    </span><span style="color: #008000;">//</span><span style="color: #008000;">show the admin button</span>
} <span style="color: #0000ff;">else</span><span style="color: #000000;"> {
    </span><span style="color: #008000;">//</span><span style="color: #008000;">don't show the button?  Grey it out?</span>
}</pre>
      </div>
    </div>
  </div>
</div>
相关验证方法如下：&nbsp;<br>
<table class="bbcode">
  <tbody>
  <tr>
    <td>Subject方法</td>
    <td>描述</td>



  </tr>
  <tr>
    <td>hasRole(String roleName)</td>
    <td>当用户拥有指定角色时，返回true</td>



  </tr>
  <tr>
    <td>hasRoles(List&lt;String&gt; roleNames)</td>
    <td>按照列表顺序返回相应的一个boolean值数组</td>



  </tr>
  <tr>
    <td>hasAllRoles(Collection&lt;String&gt; roleNames)</td>
    <td>如果用户拥有所有指定角色时，返回true</td>



  </tr>



  </tbody>



</table>
<ul>
  <li><strong>断言支持</strong>&nbsp;</li>


</ul>


Shiro还支持以断言的方式进行授权验证。断言成功，不返回任何值，程序继续执行；断言失败时，将抛出异常信息。使用断言，可以使我们的代码更加简洁。&nbsp;<br>
<div id="" class="dp-highlighter">
  <div class="bar">
    <div class="tools">
      <div class="cnblogs_code">
<pre><span style="color: #008080;">1</span> Subject currentUser =<span style="color: #000000;"> SecurityUtils.getSubject();
</span><span style="color: #008080;">2</span> <span style="color: #008000;">//</span><span style="color: #008000;">guarantee that the current user is a bank teller and
</span><span style="color: #008080;">3</span> <span style="color: #008000;">//</span><span style="color: #008000;">therefore allowed to open the account:</span>
<span style="color: #008080;">4</span> currentUser.checkRole("bankTeller"<span style="color: #000000;">);
</span><span style="color: #008080;">5</span> openBankAccount();</pre>
      </div>
    </div>
  </div>
</div>
断言的相关方法：&nbsp;<br>
<table class="bbcode">
  <tbody>
  <tr>
    <td>Subject方法</td>
    <td>描述</td>



  </tr>
  <tr>
    <td>checkRole(String roleName)</td>
    <td>断言用户是否拥有指定角色</td>



  </tr>
  <tr>
    <td>checkRoles(Collection&lt;String&gt; roleNames)</td>
    <td>断言用户是否拥有所有指定角色</td>



  </tr>
  <tr>
    <td>checkRoles(String... roleNames)</td>
    <td>对上一方法的方法重载</td>



  </tr>



  </tbody>



</table>



<br><strong>2基于权限授权实现</strong>&nbsp;<br>相比传统角色模式，基于权限的角色模式耦合性要更低些，它不会因角色的改变而对源代码进行修改，因此，基于权限的角色模式是更好的访问控制方式。&nbsp;<br>它的代码实现有以下几种实现方式：&nbsp;
<ul>
  <li><strong>基于权限对象的实现</strong>&nbsp;</li>


</ul>


创建org.apache.shiro.authz.Permission的实例，将该实例对象作为参数传递给Subject.isPermitted（）进行验证。&nbsp;<br>
<div id="" class="dp-highlighter">
  <div class="bar">
    <div class="tools">
      <div class="cnblogs_code">
<pre><span style="color: #008080;"> 1</span> Permission printPermission = <span style="color: #0000ff;">new</span> PrinterPermission("laserjet4400n", "print"<span style="color: #000000;">);
</span><span style="color: #008080;"> 2</span> Subject currentUser =<span style="color: #000000;"> SecurityUtils.getSubject();
</span><span style="color: #008080;"> 3</span> <span style="color: #0000ff;">if</span><span style="color: #000000;"> (currentUser.isPermitted(printPermission)) {
</span><span style="color: #008080;"> 4</span>     <span style="color: #008000;">//</span><span style="color: #008000;">show the Print button</span>
<span style="color: #008080;"> 5</span> } <span style="color: #0000ff;">else</span><span style="color: #000000;"> {
</span><span style="color: #008080;"> 6</span>     <span style="color: #008000;">//</span><span style="color: #008000;">don't show the button?  Grey it out?</span>
<span style="color: #008080;"> 7</span> <span style="color: #000000;">}
</span><span style="color: #008080;"> 8</span> Permission printPermission = <span style="color: #0000ff;">new</span> PrinterPermission("laserjet4400n", "print"<span style="color: #000000;">);
</span><span style="color: #008080;"> 9</span> Subject currentUser =<span style="color: #000000;"> SecurityUtils.getSubject();
</span><span style="color: #008080;">10</span> <span style="color: #0000ff;">if</span><span style="color: #000000;"> (currentUser.isPermitted(printPermission)) {
</span><span style="color: #008080;">11</span>     <span style="color: #008000;">//</span><span style="color: #008000;">show the Print button</span>
<span style="color: #008080;">12</span> } <span style="color: #0000ff;">else</span><span style="color: #000000;"> {
</span><span style="color: #008080;">13</span>     <span style="color: #008000;">//</span><span style="color: #008000;">don't show the button?  Grey it out?</span>
<span style="color: #008080;">14</span> }</pre>
      </div>
    </div>
  </div>
</div>
相关方法如下：&nbsp;<br>
<table class="bbcode">
  <tbody>
  <tr>
    <td>Subject方法</td>
    <td>描述</td>



  </tr>
  <tr>
    <td>isPermitted(Permission p)</td>
    <td>Subject拥有制定权限时，返回treu</td>



  </tr>
  <tr>
    <td>isPermitted(List&lt;Permission&gt; perms)</td>
    <td>返回对应权限的boolean数组</td>



  </tr>
  <tr>
    <td>isPermittedAll(Collection&lt;Permission&gt; perms)</td>
    <td>Subject拥有所有制定权限时，返回true</td>



  </tr>



  </tbody>



</table>
<ul>
  <li><strong>基于字符串的实现</strong>&nbsp;</li>


</ul>


相比笨重的基于对象的实现方式，基于字符串的实现便显得更加简洁。&nbsp;<br>
<div id="" class="dp-highlighter">
  <div class="bar">
    <div class="tools">
      <div class="cnblogs_code">
<pre><span style="color: #008080;">1</span> Subject currentUser =<span style="color: #000000;"> SecurityUtils.getSubject();
</span><span style="color: #008080;">2</span> <span style="color: #0000ff;">if</span> (currentUser.isPermitted("printer:print:laserjet4400n"<span style="color: #000000;">)) {
</span><span style="color: #008080;">3</span>     <span style="color: #008000;">//</span><span style="color: #008000;">show the Print button</span>
<span style="color: #008080;">4</span> } <span style="color: #0000ff;">else</span><span style="color: #000000;"> {
</span><span style="color: #008080;">5</span>     <span style="color: #008000;">//</span><span style="color: #008000;">don't show the button?  Grey it out?</span>
<span style="color: #008080;">6</span> }</pre>
      </div>
    </div>
  </div>
</div>
使用冒号分隔的权限表达式是org.apache.shiro.authz.permission.WildcardPermission 默认支持的实现方式。&nbsp;<br>这里分别代表了 资源类型：操作：资源ID&nbsp;<br><br>类似基于对象的实现相关方法，基于字符串的实现相关方法：&nbsp;<br>isPermitted(String perm)、isPermitted(String... perms)、isPermittedAll(String... perms)&nbsp;<br>
<ul>
  <li><strong>基于权限对象的断言实现</strong>&nbsp;</li>


</ul>
<div id="" class="dp-highlighter">
  <div class="bar">
    <div class="tools">
      <div class="cnblogs_code">
<pre><span style="color: #008080;">1</span> Subject currentUser =<span style="color: #000000;"> SecurityUtils.getSubject();
</span><span style="color: #008080;">2</span> <span style="color: #008000;">//</span><span style="color: #008000;">guarantee that the current user is permitted
</span><span style="color: #008080;">3</span> <span style="color: #008000;">//</span><span style="color: #008000;">to open a bank account:</span>
<span style="color: #008080;">4</span> Permission p = <span style="color: #0000ff;">new</span> AccountPermission("open"<span style="color: #000000;">);
</span><span style="color: #008080;">5</span> <span style="color: #000000;">currentUser.checkPermission(p);
</span><span style="color: #008080;">6</span> openBankAccount();</pre>
      </div>
      <p>&nbsp;</p>
    </div>
  </div>
</div>
<ul>
  <li><strong>基于字符串的断言实现</strong>&nbsp;</li>
</ul>
<div id="" class="dp-highlighter">
  <div class="bar">
    <div class="tools">
      <div class="cnblogs_code">
<pre><span style="color: #008080;">1</span> Subject currentUser =<span style="color: #000000;"> SecurityUtils.getSubject();
</span><span style="color: #008080;">2</span> <span style="color: #008000;">//</span><span style="color: #008000;">guarantee that the current user is permitted
</span><span style="color: #008080;">3</span> <span style="color: #008000;">//</span><span style="color: #008000;">to open a bank account:</span>
<span style="color: #008080;">4</span> currentUser.checkPermission("account:open"<span style="color: #000000;">);
</span><span style="color: #008080;">5</span> openBankAccount();</pre>
      </div>
    </div>
  </div>
</div>
断言实现的相关方法&nbsp;<br>
<table class="bbcode">
  <tbody>
  <tr>
    <td>Subject方法</td>
    <td>说明</td>



  </tr>
  <tr>
    <td>checkPermission(Permission p)</td>
    <td>断言用户是否拥有制定权限</td>



  </tr>
  <tr>
    <td>checkPermission(String perm)</td>
    <td>断言用户是否拥有制定权限</td>



  </tr>
  <tr>
    <td>checkPermissions(Collection&lt;Permission&gt; perms)</td>
    <td>断言用户是否拥有所有指定权限</td>



  </tr>
  <tr>
    <td>checkPermissions(String... perms)</td>
    <td>断言用户是否拥有所有指定权限<br><br></td>



  </tr>



  </tbody>



</table>


</div>
<div class="dp-highlighter">从上面可以看到，权限的对象用起来比较麻烦，还是字符串简单点。但不管怎么说，API比上面几种都麻烦点。</div>


</div>

</div>

</chaos:override>
<jsp:include page="../__template.jsp"/>
