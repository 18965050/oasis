<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>

<chaos:override name="title">Mybatis - 代码生成</chaos:override>

<chaos:override name="javascript">
  <script>
    __menu = "#menu-mybatis-generator";
  </script>
</chaos:override>

<chaos:override name="content">
  <div class="row clearfix">

    <div id="cnblogs_post_body"><h1>Mybatis 代码生成</h1>
      <hr>
      <p>Mybatis代码生成的配置文件可以放在任何位置，是否在项目中都是没关系的。</p>

      <p>Mybatis代码生成的方式和Ibatis差不多，为了通用，没用eclipse专用的插件。示例的配置如下：</p>

      <div class="cnblogs_code">
<pre><span style="color: #008080;"> 1</span> <span style="color: #0000ff;">&lt;?</span><span style="color: #ff00ff;">xml version="1.0" encoding="UTF-8" </span><span
    style="color: #0000ff;">?&gt;</span>
<span style="color: #008080;"> 2</span> <span style="color: #0000ff;">&lt;!</span><span style="color: #ff00ff;">DOCTYPE generatorConfiguration PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN" "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd" </span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 3</span> <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">generatorConfiguration</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 4</span>   <span style="color: #008000;">&lt;!--</span><span style="color: #008000;"> 驱动路径 </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;"> 5</span>   <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">classPathEntry </span><span
      style="color: #ff0000;">location</span><span style="color: #0000ff;">="D:\documents and settings\lvchenggang\.m2\repository\mysql\mysql-connector-java\5.1.30\mysql-connector-java-5.1.30.jar"</span> <span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;"> 6</span>
<span style="color: #008080;"> 7</span>   <span style="color: #0000ff;">&lt;</span><span
      style="color: #800000;">context </span><span style="color: #ff0000;">id</span><span style="color: #0000ff;">="Mysql_17_77"</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 8</span>
<span style="color: #008080;"> 9</span>       <span style="color: #008000;">&lt;!--</span><span style="color: #008000;"> 自定义使用方式支持，包括DAO，扩展XML，注解等 </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">10</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">plugin </span><span
      style="color: #ff0000;">type</span><span style="color: #0000ff;">="cn.xyz.chaos.orm.generator.plugins.CustomizeDaoSupportPlugin"</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">11</span>       <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="mapperAnnotation"</span><span
      style="color: #ff0000;"> value</span><span
      style="color: #0000ff;">="cn.xyz.chaos.orm.mybatis.MyBatisRepository"</span><span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">12</span>       <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="daoSuffix"</span><span style="color: #ff0000;"> value</span><span
      style="color: #0000ff;">="DAO"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">13</span>       <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="targetProject"</span><span
      style="color: #ff0000;"> value</span><span
      style="color: #0000ff;">="E:/git/chaos/chaos-web-demo/src/main/java"</span><span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">14</span>       <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="targetPackage"</span><span
      style="color: #ff0000;"> value</span><span
      style="color: #0000ff;">="cn.xyz.chaos.examples.demo.repository.dao"</span><span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">15</span>       <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="xmlTargetProject"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="E:/git/chaos/chaos-web-demo/src/main/resources"</span><span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">16</span>       <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="xmlTargetPackage"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="context/mybatis/dao"</span><span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">17</span>     <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">plugin</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">18</span>
<span style="color: #008080;">19</span>     <span style="color: #008000;">&lt;!--</span><span style="color: #008000;"> 自动生成sql添加rowBounds支持 </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">20</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">plugin </span><span
      style="color: #ff0000;">type</span><span
      style="color: #0000ff;">="org.mybatis.generator.plugins.RowBoundsPlugin"</span> <span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">21</span>
<span style="color: #008080;">22</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">plugin </span><span
      style="color: #ff0000;">type</span><span style="color: #0000ff;">="cn.xyz.chaos.orm.generator.plugins.AdjustAllExamplePlugin"</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">23</span>       <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="searchString"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="Example"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">24</span>       <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="replaceString"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="Criteria"</span><span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">25</span>       <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="targetProject"</span><span
      style="color: #ff0000;"> value</span><span
      style="color: #0000ff;">="E:/git/chaos/chaos-web-demo/src/main/java"</span><span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">26</span>       <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="targetPackage"</span><span
      style="color: #ff0000;"> value</span><span
      style="color: #0000ff;">="cn.xyz.chaos.examples.demo.entity.criteria"</span><span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">27</span>     <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">plugin</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">28</span>
<span style="color: #008080;">29</span>       <span style="color: #008000;">&lt;!--</span><span style="color: #008000;"> 数据库连接 </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">30</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">jdbcConnection </span><span
      style="color: #ff0000;">driverClass</span><span style="color: #0000ff;">="com.mysql.jdbc.Driver"</span><span
      style="color: #ff0000;"> connectionURL</span><span style="color: #0000ff;">="jdbc:mysql://192.168.17.77:3306/chaos_demo?characterEncoding=utf-8"</span><span
      style="color: #ff0000;"> userId</span><span style="color: #0000ff;">="chaos"</span><span style="color: #ff0000;"> password</span><span
      style="color: #0000ff;">="chaos"</span> <span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">31</span>
<span style="color: #008080;">32</span>     <span style="color: #008000;">&lt;!--</span><span style="color: #008000;"> 数据库类型解析为JavaType </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">33</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">javaTypeResolver </span><span
      style="color: #ff0000;">type</span><span style="color: #0000ff;">="cn.xyz.chaos.orm.generator.internal.types.NoShortJavaTypeResolver"</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">34</span>         <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="forceBigDecimals"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="false"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">35</span>     <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">javaTypeResolver</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">36</span>
<span style="color: #008080;">37</span>
<span style="color: #008080;">38</span>     <span style="color: #008000;">&lt;!--</span><span style="color: #008000;"> entity 生成配置 </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">39</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">javaModelGenerator </span><span
      style="color: #ff0000;">targetPackage</span><span
      style="color: #0000ff;">="cn.xyz.chaos.examples.demo.entity"</span><span
      style="color: #ff0000;"> targetProject</span><span style="color: #0000ff;">="E:/git/chaos/chaos-web-demo/src/main/java"</span> <span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">40</span>     <span style="color: #008000;">&lt;!--</span><span style="color: #008000;"> sqlMapper xml文件生成配置 </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">41</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">sqlMapGenerator </span><span
      style="color: #ff0000;">targetPackage</span><span style="color: #0000ff;">="context/mybatis/mapper"</span><span
      style="color: #ff0000;"> targetProject</span><span style="color: #0000ff;">="E:/git/chaos/chaos-web-demo/src/main/resources"</span> <span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">42</span>     <span style="color: #008000;">&lt;!--</span><span style="color: #008000;"> sqlMapper 接口生成配置 </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">43</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">javaClientGenerator </span><span
      style="color: #ff0000;">targetPackage</span><span style="color: #0000ff;">="cn.xyz.chaos.examples.demo.repository.mapper"</span><span
      style="color: #ff0000;"> targetProject</span><span style="color: #0000ff;">="E:/git/chaos/chaos-web-demo/src/main/java"</span><span
      style="color: #ff0000;"> type</span><span style="color: #0000ff;">="XMLMAPPER"</span> <span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">44</span>
<span style="color: #008080;">45</span>     <span style="color: #008000;">&lt;!--</span><span style="color: #008000;"> Table 映射配置 START  </span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">46</span>
<span style="color: #008080;">47</span>     <span style="color: #008000;">&lt;!--</span><span style="color: #008000;">&lt;table tableName="appraise" domainObjectName="Appraise"&gt;
</span><span style="color: #008080;">48</span> <span style="color: #008000;">      &lt;property name="ignoreQualifiersAtRuntime" value="true"/&gt;
</span><span style="color: #008080;">49</span> <span style="color: #008000;">      &lt;generatedKey column="appraise_id" sqlStatement="MySql" identity="true"/&gt;
</span><span style="color: #008080;">50</span> <span style="color: #008000;">    &lt;/table&gt;
</span><span style="color: #008080;">51</span> <span style="color: #008000;">    &lt;table tableName="company_info" domainObjectName="CompanyInfo"&gt;
</span><span style="color: #008080;">52</span> <span style="color: #008000;">      &lt;property name="ignoreQualifiersAtRuntime" value="true"/&gt;
</span><span style="color: #008080;">53</span> <span style="color: #008000;">    &lt;/table&gt;
</span><span style="color: #008080;">54</span> <span style="color: #008000;">    &lt;table tableName="favorite" domainObjectName="Favorite"&gt;
</span><span style="color: #008080;">55</span> <span style="color: #008000;">      &lt;property name="ignoreQualifiersAtRuntime" value="true"/&gt;
</span><span style="color: #008080;">56</span> <span style="color: #008000;">      &lt;generatedKey column="favorite_id" sqlStatement="MySql" identity="true"/&gt;
</span><span style="color: #008080;">57</span> <span style="color: #008000;">    &lt;/table&gt;
</span><span style="color: #008080;">58</span> <span style="color: #008000;">    &lt;table tableName="prod_info" domainObjectName="ProdInfo"&gt;
</span><span style="color: #008080;">59</span> <span style="color: #008000;">      &lt;property name="ignoreQualifiersAtRuntime" value="true"/&gt;
</span><span style="color: #008080;">60</span> <span style="color: #008000;">      &lt;generatedKey column="prod_id" sqlStatement="MySql" identity="true"/&gt;
</span><span style="color: #008080;">61</span> <span style="color: #008000;">    &lt;/table&gt;
</span><span style="color: #008080;">62</span> <span style="color: #008000;">    &lt;table tableName="prod_sku" domainObjectName="ProdSku"&gt;
</span><span style="color: #008080;">63</span> <span style="color: #008000;">      &lt;property name="ignoreQualifiersAtRuntime" value="true"/&gt;
</span><span style="color: #008080;">64</span> <span style="color: #008000;">      &lt;generatedKey column="sku_id" sqlStatement="MySql" identity="true"/&gt;
</span><span style="color: #008080;">65</span> <span style="color: #008000;">    &lt;/table&gt;
</span><span style="color: #008080;">66</span> <span style="color: #008000;">     &lt;table tableName="prod_sku_link" domainObjectName="ProdSkuLink"&gt;
</span><span style="color: #008080;">67</span> <span style="color: #008000;">      &lt;property name="ignoreQualifiersAtRuntime" value="true"/&gt;
</span><span style="color: #008080;">68</span> <span style="color: #008000;">      &lt;generatedKey column="sku_link_id" sqlStatement="MySql" identity="true"/&gt;
</span><span style="color: #008080;">69</span> <span style="color: #008000;">    &lt;/table&gt;
</span><span style="color: #008080;">70</span> <span style="color: #008000;">    &lt;table tableName="prod_sku_option" domainObjectName="ProdSkuOption"&gt;
</span><span style="color: #008080;">71</span> <span style="color: #008000;">      &lt;property name="ignoreQualifiersAtRuntime" value="true"/&gt;
</span><span style="color: #008080;">72</span> <span style="color: #008000;">      &lt;generatedKey column="sku_option_id" sqlStatement="MySql" identity="true"/&gt;
</span><span style="color: #008080;">73</span> <span style="color: #008000;">    &lt;/table&gt;
</span><span style="color: #008080;">74</span> <span style="color: #008000;">    &lt;table tableName="user_info" domainObjectName="UserInfo"&gt;
</span><span style="color: #008080;">75</span> <span style="color: #008000;">      &lt;property name="ignoreQualifiersAtRuntime" value="true"/&gt;
</span><span style="color: #008080;">76</span> <span style="color: #008000;">      &lt;generatedKey column="user_id" sqlStatement="MySql" identity="true"/&gt;
</span><span style="color: #008080;">77</span> <span style="color: #008000;">    &lt;/table&gt;</span><span
      style="color: #008000;">--&gt;</span>
<span style="color: #008080;">78</span>
<span style="color: #008080;">79</span>     <span style="color: #0000ff;">&lt;</span><span
      style="color: #800000;">table </span><span style="color: #ff0000;">tableName</span><span style="color: #0000ff;">="my_test"</span><span
      style="color: #ff0000;"> domainObjectName</span><span style="color: #0000ff;">="MyTest"</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">80</span>       <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">property </span><span
      style="color: #ff0000;">name</span><span style="color: #0000ff;">="ignoreQualifiersAtRuntime"</span><span
      style="color: #ff0000;"> value</span><span style="color: #0000ff;">="true"</span><span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">81</span>     <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">table</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">82</span>
<span style="color: #008080;">83</span>   <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">context</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">84</span>
<span style="color: #008080;">85</span> <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">generatorConfiguration</span><span
      style="color: #0000ff;">&gt;</span></pre>
      </div>
      <p>关于Mybatis generator具体可以看可以看<a href="http://mybatis.github.io/generator/" target="_blank">官方的文档</a>，我们项目里面需要留心的就是几个自定义扩展的targetProject和targetPackage配置，莫要生成错了地方。结合注释，不知道具体用途可以先生成一遍看看，对比生成的文件就更好理解点。<br>具体的执行的话只需一句话：
      </p>

      <div class="cnblogs_code">
<pre><span style="color: #008080;"> 1</span> <span style="color: #0000ff;">package</span><span style="color: #000000;"> cn.xyz.chaos.examples.demo;
</span><span style="color: #008080;"> 2</span>
<span style="color: #008080;"> 3</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.orm.generator.GeneratorLauncher;
</span><span style="color: #008080;"> 4</span>
<span style="color: #008080;"> 5</span> <span style="color: #008000;">/**</span>
<span style="color: #008080;"> 6</span> <span style="color: #008000;"> * Generator &lt;br/&gt;
</span><span style="color: #008080;"> 7</span> <span style="color: #008000;"> *
</span><span style="color: #008080;"> 8</span> <span style="color: #008000;"> * </span><span style="color: #808080;">@version</span><span
      style="color: #008000;"> 1.0.0 &lt;br/&gt;
</span><span style="color: #008080;"> 9</span> <span style="color: #008000;"> *          创建时间：2014年8月25日 上午9:53:18
</span><span style="color: #008080;">10</span> <span style="color: #008000;"> * </span><span style="color: #808080;">@author</span><span
      style="color: #008000;"> lcg
</span><span style="color: #008080;">11</span>  <span style="color: #008000;">*/</span>
<span style="color: #008080;">12</span> <span style="color: #0000ff;">public</span> <span
      style="color: #0000ff;">class</span><span style="color: #000000;"> Generator {
</span><span style="color: #008080;">13</span>     <span style="color: #0000ff;">public</span> <span
      style="color: #0000ff;">static</span> <span style="color: #0000ff;">void</span> main(String[] args) <span
      style="color: #0000ff;">throws</span><span style="color: #000000;"> Exception {
</span><span style="color: #008080;">14</span>         GeneratorLauncher.generate("E:/DevGit/chaos/chaos-web-demo/src/main/resources/mybatis/generatorConfig.xml"<span
      style="color: #000000;">);
</span><span style="color: #008080;">15</span> <span style="color: #000000;">    }
</span><span style="color: #008080;">16</span> }</pre>
      </div>
      <p>&nbsp;</p></div>

  </div>

</chaos:override>
<jsp:include page="../__template.jsp"/>
