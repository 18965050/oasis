<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>

<chaos:override name="title">Security - Shiro集成</chaos:override>

<chaos:override name="javascript">
  <script>
    __menu = "#menu-shiro-integration";
  </script>
</chaos:override>

<chaos:override name="content">
<div class="row clearfix">

<div id="cnblogs_post_body"><h1>综述</h1>
<hr>
<p style="margin-left: 30px;"><br>系统使用Shiro作为安全框架。shiro配置文件为resources/shiro/shiro-spring.xml。</p>

<div style="margin-left: 30px;">对于新的系统，在集成的过程中，大部分文件保持默认配置即可。</div>
<div style="margin-left: 30px;">集成过程中可能需要实现的代码，系统做了示例，全部放在showcase/authc下。</div>
<div style="text-align: left; margin-left: 30px;"><img
    src="${ctx}/assets/img/security/181736407687432.jpg" alt=""></div>
<div style="text-align: left; margin-left: 30px;"><em><span style="font-size: 12px; background-color: #c0c0c0;"><span
    style="font-size: medium;"><span style="line-height: normal;">(图1-1)</span></span></span></em></div>
<div style="margin-left: 30px;">对于需要注意的和改动的部分，我们结合上图在下面一一说明。</div>
<div>&nbsp;</div>
<div>
<h1>Entity</h1>
<hr>
<p style="margin-left: 30px;">&nbsp;在 图1-1 中，authc/entity 中有两个类。</p>

<p style="margin-left: 30px;"><strong>AccInfo&nbsp;</strong>类代表账号信息。在应用中通过工具类（AuthUtils）获得当前用户/账户则会得到AccInfo的一个实例。</p>

<p style="margin-left: 30px;"><img src="${ctx}/assets/img/security/181851061758624.jpg" alt=""></p>

<p style="margin-left: 30px;"><em>(图2-1)</em></p>

<p style="margin-left: 30px;">AccInfo实例化的过程后续会将会讲到。</p>

<p style="margin-left: 30px;"><strong>KeyUsernamePwdToken&nbsp;</strong>类在系统中是凭证的概念，在认证的过程中用到。showcase中该类在 Shiro
  的UsernamePasswordToken 的基础上扩展，额外提供了一个“key”
  field用于类似"mickey"形式的认证，间接实现了AuthenticationToken。实际使用中可以根据需要实现AuthenticationToken接口。</p>

<p>&nbsp;</p>

<h1>认证流程</h1>
<hr>
<ul>
<li>
  <h3>Shiro的认证流程</h3>


  在shiro 的配置文件applicationContext-shiro.xml 中，有个“shiroFilter”的bean的配置。<br>

  <div class="cnblogs_code">
<pre><span style="color: #008080;"> 1</span> <span style="color: #008000;">&lt;!--</span><span style="color: #008000;"> Security 过滤器链配置 </span><span
    style="color: #008000;">--&gt;</span>
<span style="color: #008080;"> 2</span>   <span style="color: #0000ff;">&lt;</span><span
      style="color: #800000;">bean </span><span style="color: #ff0000;">id</span><span style="color: #0000ff;">="shiroFilter"</span><span
      style="color: #ff0000;"> class</span><span style="color: #0000ff;">="cn.xyz.chaos.mvc.shiro.CookieAuthShiroFilterFactoryBean"</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 3</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="shiroFilter"</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 4</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">bean </span><span
      style="color: #ff0000;">class</span><span style="color: #0000ff;">="cn.xyz.chaos.examples.demo.authc.CookieAuthShiroFilter"</span><span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;"> 5</span>     <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">property</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 6</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="securityManager"</span><span
      style="color: #ff0000;"> ref</span><span style="color: #0000ff;">="securityManager"</span> <span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;"> 7</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="loginUrl"</span><span style="color: #ff0000;"> value</span><span
      style="color: #0000ff;">="/auth/auth/login"</span> <span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;"> 8</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="successUrl"</span><span
      style="color: #ff0000;"> value</span><span
      style="color: #0000ff;">="/membercenter/membercenter/index"</span> <span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;"> 9</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="unauthorizedUrl"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="/error/403.jsp"</span><span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">10</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="filters"</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">11</span>       <span style="color: #0000ff;">&lt;</span><span
      style="color: #800000;">map</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">12</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">entry </span><span
      style="color: #ff0000;">key</span><span style="color: #0000ff;">="logout"</span><span style="color: #ff0000;"> value-ref</span><span
      style="color: #0000ff;">="logoutFilter"</span> <span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">13</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">entry </span><span
      style="color: #ff0000;">key</span><span style="color: #0000ff;">="authc"</span><span style="color: #ff0000;"> value-ref</span><span
      style="color: #0000ff;">="keyFormAuthenticationFilter"</span> <span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">14</span>       <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">map</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">15</span>     <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">property</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">16</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="filterChainDefinitions"</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">17</span>       <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">value</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">18</span> <span style="color: #000000;">          /favoicon.ico = anon
</span><span style="color: #008080;">19</span> <span style="color: #000000;">        /auth/auth/login = authc
</span><span style="color: #008080;">20</span> <span style="color: #000000;">        /logout = logout
</span><span style="color: #008080;">21</span> <span style="color: #000000;">        /account/** = authc
</span><span style="color: #008080;">22</span> <span style="color: #000000;">        /admin/** = authc
</span><span style="color: #008080;">23</span> <span style="color: #000000;">        /membercenter/** =authc
</span><span style="color: #008080;">24</span>       <span style="color: #0000ff;">&lt;/</span><span
      style="color: #800000;">value</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">25</span>     <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">property</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">26</span>   <span style="color: #0000ff;">&lt;/</span><span
      style="color: #800000;">bean</span><span style="color: #0000ff;">&gt;</span></pre>
  </div>
  <p>其中 property "filterChainDefinitions" 定义了shiro内部的过滤器链。<br><span style="line-height: 1.5;">在过滤器链的的第二行 "/auth/auth/login = authc" 定义了登陆请求提交的链接。</span><span
      style="line-height: 1.5;">“/auth/auth/login”为链接地址，与property “loginUrl"的值相同。authc代表使用的过滤器。在该处配置中，通过定义property "filters" override了Shiro的内置过滤器logout和authc。<br><br></span>
  </p>
  <ul>
    <li><strong>创建Token</strong><br>authc对应的filter keyFormAuthenticationFilter中Overide FormAuthenticationFilter的
      createToken方法，以从登陆请求中提取表单参数创建我们自定义的token。<br>

      <div class="cnblogs_code">

<pre><span style="color: #000000;">    @Override
    </span><span style="color: #0000ff;">protected</span><span style="color: #000000;"> AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username </span>=<span style="color: #000000;"> getUsername(request);
        String password </span>=<span style="color: #000000;"> getPassword(request);
        </span><span style="color: #0000ff;">boolean</span> rememberMe =<span style="color: #000000;"> isRememberMe(request);
        String key </span>=<span style="color: #000000;"> getKey(request);
        String host </span>=<span style="color: #000000;"> getHost(request);
        </span><span style="color: #0000ff;">return</span> <span style="color: #0000ff;">new</span><span
    style="color: #000000;"> KeyUsernamePwdToken(username, password, rememberMe, host, key);
    } </span></pre>

      </div>
    </li>
    <li><strong>根据token获取系统中对应的认证信息AuthorizationInfo（用户认证数据）&nbsp;<br></strong>DemoRealm扩展Shiro的AuthorizingRealm，实现doGetAuthenticationInfo方法：<br>

      <div class="cnblogs_code">

<pre><span style="color: #008080;">1</span> <span style="color: #000000;">    @Override
</span><span style="color: #008080;">2</span>     <span style="color: #0000ff;">protected</span> AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) <span
    style="color: #0000ff;">throws</span><span style="color: #000000;"> AuthenticationException {
</span><span style="color: #008080;">3</span>         KeyUsernamePwdToken loginToken =<span style="color: #000000;"> (KeyUsernamePwdToken) token;
</span><span style="color: #008080;">4</span>         <span style="color: #0000ff;">return</span><span
    style="color: #000000;"> authService.getAuthInfo(loginToken, getName());
</span><span style="color: #008080;">5</span>     }</pre>

      </div>
      <p>方法的入参即为我们上面创建的token。&nbsp;<br>showcase中将创建AuthenticationInfo的过程委托给AuthService了。AuthService和我们平常的Service没什么区别。
      </p>

      <div class="cnblogs_code">

<pre><span style="color: #008080;"> 1</span> <span style="color: #0000ff;">package</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.web.service;
</span><span style="color: #008080;"> 2</span>
<span style="color: #008080;"> 3</span> <span style="color: #0000ff;">import</span> org.apache.shiro.authc.*<span
      style="color: #000000;">;
</span><span style="color: #008080;"> 4</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> org.springframework.beans.factory.annotation.Autowired;
</span><span style="color: #008080;"> 5</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> org.springframework.stereotype.Service;
</span><span style="color: #008080;"> 6</span>
<span style="color: #008080;"> 7</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.authc.entity.AccInfo;
</span><span style="color: #008080;"> 8</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.authc.entity.KeyUsernamePwdToken;
</span><span style="color: #008080;"> 9</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.entity.Account;
</span><span style="color: #008080;">10</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.provider.AccountProvider;
</span><span style="color: #008080;">11</span>
<span style="color: #008080;">12</span> <span style="color: #008000;">/**</span>
<span style="color: #008080;">13</span> <span style="color: #008000;"> * AuthService &lt;br/&gt;
</span><span style="color: #008080;">14</span> <span style="color: #008000;"> * 认证前台服务
</span><span style="color: #008080;">15</span> <span style="color: #008000;"> *
</span><span style="color: #008080;">16</span> <span style="color: #008000;"> * </span><span style="color: #808080;">@version</span><span
      style="color: #008000;"> 1.0.0 &lt;br/&gt;
</span><span style="color: #008080;">17</span> <span style="color: #008000;"> *          创建时间：2014年6月25日 下午8:08:36
</span><span style="color: #008080;">18</span> <span style="color: #008000;"> * </span><span style="color: #808080;">@author</span><span
      style="color: #008000;"> lcg
</span><span style="color: #008080;">19</span>  <span style="color: #008000;">*/</span>
<span style="color: #008080;">20</span> <span style="color: #000000;">@Service
</span><span style="color: #008080;">21</span> <span style="color: #0000ff;">public</span> <span
      style="color: #0000ff;">class</span><span style="color: #000000;"> AuthService {
</span><span style="color: #008080;">22</span>
<span style="color: #008080;">23</span> <span style="color: #000000;">    @Autowired
</span><span style="color: #008080;">24</span>     <span style="color: #0000ff;">private</span><span
      style="color: #000000;"> AccountProvider    accountProvider;
</span><span style="color: #008080;">25</span>
<span style="color: #008080;">26</span>     <span style="color: #0000ff;">public</span> AuthenticationInfo getAuthInfo(KeyUsernamePwdToken token, String realmName) <span
      style="color: #0000ff;">throws</span><span style="color: #000000;"> AuthenticationException {
</span><span style="color: #008080;">27</span>         <span style="color: #008000;">//</span><span
      style="color: #008000;"> 实际情况可以根据邮箱、用户名、手机号等
</span><span style="color: #008080;">28</span>         <span style="color: #008000;">//</span><span
      style="color: #008000;"> 甚至验证也在这一步做了，总之，获得一个匹配的账号</span>
<span style="color: #008080;">29</span>         Account account =<span style="color: #000000;"> accountProvider.getByEmail(token.getUsername());
</span><span style="color: #008080;">30</span>         <span style="color: #0000ff;">if</span> (<span
      style="color: #0000ff;">null</span> ==<span style="color: #000000;"> account) {
</span><span style="color: #008080;">31</span>             <span style="color: #0000ff;">throw</span> <span
      style="color: #0000ff;">new</span> UnknownAccountException("账号不存在"<span style="color: #000000;">);
</span><span style="color: #008080;">32</span> <span style="color: #000000;">        }
</span><span style="color: #008080;">33</span>         <span style="color: #0000ff;">if</span> (account.getStatus() != 1) {<span
      style="color: #008000;">//</span><span style="color: #008000;"> 只是个示例</span>
<span style="color: #008080;">34</span>             <span style="color: #0000ff;">throw</span> <span
      style="color: #0000ff;">new</span> DisabledAccountException("账号被冻结/账号类型错误……"<span style="color: #000000;">);
</span><span style="color: #008080;">35</span> <span style="color: #000000;">        }
</span><span style="color: #008080;">36</span>         AccInfo userInfo = <span style="color: #0000ff;">new</span><span
      style="color: #000000;"> AccInfo(account);
</span><span style="color: #008080;">37</span>         <span style="color: #0000ff;">return</span> <span
      style="color: #0000ff;">new</span><span style="color: #000000;"> SimpleAuthenticationInfo(userInfo, account.getPassword(), realmName);
</span><span style="color: #008080;">38</span> <span style="color: #000000;">    }
</span><span style="color: #008080;">39</span>
<span style="color: #008080;">40</span> }</pre>

      </div>
      <p>&nbsp;</p>
    </li>
    <li><strong>凭证匹配</strong>&nbsp;<br>KeyUsernamePwdCredentialsMatcher 实现 Shiro 的&nbsp;CredentialsMatcher。在登陆的流程中会根据CredentialsMatcher.doCredentialsMatch方法的返回值来判断凭证的正确或错误。<br>

      <div class="cnblogs_code">

<pre><span style="color: #008080;"> 1</span> <span style="color: #0000ff;">package</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.authc;
</span><span style="color: #008080;"> 2</span>
<span style="color: #008080;"> 3</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> org.apache.shiro.authc.AuthenticationInfo;
</span><span style="color: #008080;"> 4</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> org.apache.shiro.authc.AuthenticationToken;
</span><span style="color: #008080;"> 5</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> org.apache.shiro.authc.IncorrectCredentialsException;
</span><span style="color: #008080;"> 6</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> org.apache.shiro.authc.credential.CredentialsMatcher;
</span><span style="color: #008080;"> 7</span>
<span style="color: #008080;"> 8</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.authc.entity.KeyUsernamePwdToken;
</span><span style="color: #008080;"> 9</span>
<span style="color: #008080;">10</span> <span style="color: #008000;">/**</span>
<span style="color: #008080;">11</span> <span style="color: #008000;"> * KeyUsernamePwdCredentialsMatcher &lt;br/&gt;
</span><span style="color: #008080;">12</span> <span style="color: #008000;"> * KEY username password 证书匹配
</span><span style="color: #008080;">13</span> <span style="color: #008000;"> *
</span><span style="color: #008080;">14</span> <span style="color: #008000;"> * </span><span style="color: #808080;">@version</span><span
      style="color: #008000;"> 1.0.0 &lt;br/&gt;
</span><span style="color: #008080;">15</span> <span style="color: #008000;"> *          创建时间：2014年6月27日 下午2:55:49
</span><span style="color: #008080;">16</span> <span style="color: #008000;"> * </span><span style="color: #808080;">@author</span><span
      style="color: #008000;"> lcg
</span><span style="color: #008080;">17</span>  <span style="color: #008000;">*/</span>
<span style="color: #008080;">18</span> <span style="color: #0000ff;">public</span> <span
      style="color: #0000ff;">class</span> KeyUsernamePwdCredentialsMatcher <span
      style="color: #0000ff;">implements</span><span style="color: #000000;"> CredentialsMatcher {
</span><span style="color: #008080;">19</span>
<span style="color: #008080;">20</span> <span style="color: #000000;">    @Override
</span><span style="color: #008080;">21</span>     <span style="color: #0000ff;">public</span> <span
      style="color: #0000ff;">boolean</span><span style="color: #000000;"> doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
</span><span style="color: #008080;">22</span>         KeyUsernamePwdToken t =<span style="color: #000000;"> (KeyUsernamePwdToken) token;
</span><span style="color: #008080;">23</span>         <span style="color: #0000ff;">if</span><span
      style="color: #000000;"> (t.isNeedNoMatch()) {
</span><span style="color: #008080;">24</span>             <span style="color: #0000ff;">return</span> <span
      style="color: #0000ff;">true</span><span style="color: #000000;">;
</span><span style="color: #008080;">25</span> <span style="color: #000000;">        }
</span><span style="color: #008080;">26</span>         String realPwd =<span style="color: #000000;"> info.getCredentials().toString();
</span><span style="color: #008080;">27</span>         String pwd = String.valueOf((<span
      style="color: #0000ff;">char</span><span style="color: #000000;">[]) t.getCredentials());
</span><span style="color: #008080;">28</span>         <span style="color: #0000ff;">if</span><span
      style="color: #000000;"> (realPwd.equals(pwd)) {
</span><span style="color: #008080;">29</span>             <span style="color: #0000ff;">return</span> <span
      style="color: #0000ff;">true</span><span style="color: #000000;">;
</span><span style="color: #008080;">30</span> <span style="color: #000000;">        }
</span><span style="color: #008080;">31</span>         <span style="color: #0000ff;">throw</span> <span
      style="color: #0000ff;">new</span> IncorrectCredentialsException("证书错误（KEY/密码）与用户名不一致"<span
      style="color: #000000;">);
</span><span style="color: #008080;">32</span> <span style="color: #000000;">    }
</span><span style="color: #008080;">33</span>
<span style="color: #008080;">34</span> }</pre>

      </div>
    </li>
    <li><strong><strong>错误/异常流程和</strong>登陆结果</strong>&nbsp;<br>认证的过程中可能有用户不存在、用户被禁用、密码错误等等引起无法正常登陆的情况。<br>当出现异常流程的时候，可以通过抛出AuthenticationException异常及其扩展异常进入异常处理流程。<br>在authc对应的filter
      keyFormAuthenticationFilter中，Override&nbsp;setFailureAttribute方法可以定义一些友好的异常信息。<br>

      <div class="cnblogs_code">

<pre><span style="color: #008080;"> 1</span> <span style="color: #000000;">    @Override
</span><span style="color: #008080;"> 2</span>     <span style="color: #0000ff;">protected</span> <span
    style="color: #0000ff;">void</span><span style="color: #000000;"> setFailureAttribute(ServletRequest request, AuthenticationException ae) {
</span><span style="color: #008080;"> 3</span>         String errorMessage = <span
    style="color: #0000ff;">null</span><span style="color: #000000;">;
</span><span style="color: #008080;"> 4</span>         <span style="color: #0000ff;">if</span> (ae <span
    style="color: #0000ff;">instanceof</span><span style="color: #000000;"> ConcurrentAccessException) {
</span><span style="color: #008080;"> 5</span>             <span style="color: #008000;">//</span><span
    style="color: #008000;"> 并发访问（同账号多处登陆）</span>
<span style="color: #008080;"> 6</span>         } <span style="color: #0000ff;">else</span> <span
      style="color: #0000ff;">if</span> (ae <span style="color: #0000ff;">instanceof</span><span
      style="color: #000000;"> LockedAccountException) {
</span><span style="color: #008080;"> 7</span>             <span style="color: #008000;">//</span><span
      style="color: #008000;"> 锁定（DisabledAccountException的一种）</span>
<span style="color: #008080;"> 8</span>         } <span style="color: #0000ff;">else</span> <span
      style="color: #0000ff;">if</span> (ae <span style="color: #0000ff;">instanceof</span> UnknownAccountException || ae <span
      style="color: #0000ff;">instanceof</span><span style="color: #000000;"> DisabledAccountException
</span><span style="color: #008080;"> 9</span>                 || ae <span
      style="color: #0000ff;">instanceof</span><span style="color: #000000;"> IncorrectCredentialsException) {
</span><span style="color: #008080;">10</span>             <span style="color: #008000;">//</span><span
      style="color: #008000;"> 已定义错误消息</span>
<span style="color: #008080;">11</span>             errorMessage =<span style="color: #000000;"> ae.getMessage();
</span><span style="color: #008080;">12</span>         } <span style="color: #0000ff;">else</span><span
      style="color: #000000;"> {
</span><span style="color: #008080;">13</span>             <span style="color: #008000;">//</span><span
      style="color: #008000;"> 非已确定的账号异常，可能是服务器异常</span>
<span style="color: #008080;">14</span>             errorMessage = "服务器异常，请稍后再试！"<span style="color: #000000;">;
</span><span style="color: #008080;">15</span> <span style="color: #000000;">        }
</span><span style="color: #008080;">16</span> <span style="color: #000000;">        request.setAttribute(getFailureKeyAttribute(), errorMessage);
</span><span style="color: #008080;">17</span>     }</pre>

      </div>
      <p>该方法的调用是在onLoginFailure中。<br>onLoginFailure和onLoginSuccess是根据不同的认证结果进行后去处理的两个分支。</p>

      <div class="cnblogs_code">

<pre><span style="color: #008080;"> 1</span> <span style="color: #000000;">    @Override
</span><span style="color: #008080;"> 2</span>     <span style="color: #0000ff;">protected</span> <span
    style="color: #0000ff;">boolean</span><span style="color: #000000;"> onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
</span><span style="color: #008080;"> 3</span>             ServletResponse response) <span style="color: #0000ff;">throws</span><span
    style="color: #000000;"> Exception {
</span><span style="color: #008080;"> 4</span>         <span style="color: #0000ff;">if</span><span
    style="color: #000000;"> (isAjaxRequest(request)) {
</span><span style="color: #008080;"> 5</span>             <span style="color: #008000;">//</span><span
    style="color: #008000;"> TODO AJAX 请求处理</span>
<span style="color: #008080;"> 6</span> <span style="color: #000000;">        }
</span><span style="color: #008080;"> 7</span> <span style="color: #000000;">        AuthUtils.setLoginCookie(AuthUtils.getCurrentUser());
</span><span style="color: #008080;"> 8</span>         <span style="color: #0000ff;">return</span> <span
      style="color: #0000ff;">super</span><span style="color: #000000;">.onLoginSuccess(token, subject, request, response);
</span><span style="color: #008080;"> 9</span> <span style="color: #000000;">    }
</span><span style="color: #008080;">10</span>
<span style="color: #008080;">11</span> <span style="color: #000000;">    @Override
</span><span style="color: #008080;">12</span>     <span style="color: #0000ff;">protected</span> <span
      style="color: #0000ff;">boolean</span><span style="color: #000000;"> onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
</span><span style="color: #008080;">13</span> <span style="color: #000000;">            ServletResponse response) {
</span><span style="color: #008080;">14</span>         <span style="color: #0000ff;">if</span><span
      style="color: #000000;"> (isAjaxRequest(request)) {
</span><span style="color: #008080;">15</span>             <span style="color: #008000;">//</span><span
      style="color: #008000;"> TODO AJAX 请求处理</span>
<span style="color: #008080;">16</span> <span style="color: #000000;">        }
</span><span style="color: #008080;">17</span>         <span style="color: #0000ff;">return</span> <span
      style="color: #0000ff;">super</span><span style="color: #000000;">.onLoginFailure(token, e, request, response);
</span><span style="color: #008080;">18</span>     }</pre>

      </div>
      默认情况下，登陆成功会redirect 到登录前访问地址或“shiroFilter” bean配置中的successUrl。而登陆失败则Shiro只会在request中设置失败信息，不做额外处理（也就是应用自己处理）。
    </li>
    <li><strong>退出/注销</strong><br>和登陆请求配置类似，“/logout = logout” 配置的是退出请求。<br>访问该地址的用户将执行退出操作，清除登陆状态。默认情况退出后返回主页，也可在logoutFilter中配置“redirectUrl”来决定退出后跳转地址。
    </li>


  </ul>


</li>


</ul>
<ul>
  <li>
    <h3>自定义认证流程</h3>


    自定义认证流程是指自己开发代码处理登陆退出请求，在上面的“shiroFilter” bean配置中不需要额外的配置。<br>
    <ul>
      <li><strong>登陆</strong><br>凭证的创建可以自行从请求中提取，然后手动调用工具类的登陆方法(showcase 实现为
        AuthUtils.login(KeyUsernamePwdToken),参数为我们创建的token)。<br>该方法同样会使用Shiro认证流程中的获取用户认证信息和凭证匹配步骤，但过程中抛出的异常需要由我们自行处理。如果无异常，则表示登陆成功。
      </li>
      <li><strong>退出&nbsp;<br></strong>退出只要在处理流程中调用工具类的退出方法即可。(showcase 实现为 AuthUtils.logout() )</li>

    </ul>

  </li>

</ul>
<p style="margin-left: 30px;">&nbsp;</p>


</div>
</div>

</div>

</chaos:override>
<jsp:include page="../__template.jsp"/>
