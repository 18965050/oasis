<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>

<chaos:override name="title">Mybatis - Mybatis使用</chaos:override>

<chaos:override name="javascript">
  <script>
    __menu = "#menu-mybatis-use";
  </script>
</chaos:override>

<chaos:override name="content">
  <div class="row clearfix">

    <div id="cnblogs_post_body"><h1>Mybatis 使用</h1>
      <hr>
      <p>这篇文章本已经写完了，结果电脑崩溃了……气坏了。再简要提一下吧（o(╯□╰)o实在是很难重写）。</p>

      <p>Mybatis的使用形式和原汁原味的没差别，和大家习惯的Ibatis出入也不大，网上有大篇大篇的文章，这儿也没兴趣再赘述一次了，也可以看一看<a
          href="http://mybatis.github.io/mybatis-3/" target="_blank">官方文档</a>。<br>下面就演示一下Mybatis不用API的方式和Ibatis使用形式上的不同吧。
      </p>

      <p style="margin-left: 30px;">&nbsp;先看sql：</p>

      <div class="cnblogs_code">
<pre><span style="color: #008080;"> 1</span> <span style="color: #0000ff;">&lt;?</span><span style="color: #ff00ff;">xml version="1.0" encoding="UTF-8" </span><span
    style="color: #0000ff;">?&gt;</span>
<span style="color: #008080;"> 2</span> <span style="color: #0000ff;">&lt;!</span><span style="color: #ff00ff;">DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" </span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 3</span> <span style="color: #0000ff;">&lt;</span><span
      style="color: #800000;">mapper </span><span style="color: #ff0000;">namespace</span><span style="color: #0000ff;">="cn.xyz.chaos.examples.demo.repository.dao.AppraiseDAO"</span> <span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 4</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">select </span><span
      style="color: #ff0000;">id</span><span style="color: #0000ff;">="getProdRelated"</span><span
      style="color: #ff0000;"> resultMap</span><span style="color: #0000ff;">="cn.xyz.chaos.examples.demo.repository.mapper.AppraiseMapper.BaseResultMap"</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 5</span>     <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">select</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 6</span>
<span style="color: #008080;"> 7</span>   <span style="color: #0000ff;">&lt;</span><span
      style="color: #800000;">select </span><span style="color: #ff0000;">id</span><span style="color: #0000ff;">="selectBySelectiveWithRowbounds"</span><span
      style="color: #ff0000;"> parameterType</span><span style="color: #0000ff;">="map"</span><span
      style="color: #ff0000;"> resultType</span><span style="color: #0000ff;">="cn.xyz.chaos.examples.demo.entity.Comment"</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 8</span> <span style="color: #000000;">       select
</span><span style="color: #008080;"> 9</span> <span style="color: #000000;">       c.appraise_id appraiseId,
</span><span style="color: #008080;">10</span> <span style="color: #000000;">       c.user_id userId,
</span><span style="color: #008080;">11</span> <span style="color: #000000;">       u.email userName,
</span><span style="color: #008080;">12</span> <span style="color: #000000;">       c.prod_id prodId,
</span><span style="color: #008080;">13</span> <span style="color: #000000;">       p.prod_name prodName,
</span><span style="color: #008080;">14</span> <span style="color: #000000;">       c.content,
</span><span style="color: #008080;">15</span> <span style="color: #000000;">       c.score,
</span><span style="color: #008080;">16</span> <span style="color: #000000;">       c.add_time addTime
</span><span style="color: #008080;">17</span> <span style="color: #000000;">       from prod_info p, user_info u, appraise c
</span><span style="color: #008080;">18</span> <span style="color: #000000;">       where
</span><span style="color: #008080;">19</span> <span style="color: #000000;">       c.prod_id=p.prod_id and c.user_id=u.user_id
</span><span style="color: #008080;">20</span>        <span style="color: #0000ff;">&lt;</span><span
      style="color: #800000;">if </span><span style="color: #ff0000;">test</span><span style="color: #0000ff;">="appraise.userId !=null"</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">21</span> <span style="color: #000000;">           and c.user_id=# {appraise.userId}
</span><span style="color: #008080;">22</span>        <span style="color: #0000ff;">&lt;/</span><span
      style="color: #800000;">if</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">23</span>        <span style="color: #0000ff;">&lt;</span><span
      style="color: #800000;">if </span><span style="color: #ff0000;">test</span><span style="color: #0000ff;">="appraise.prodId !=null"</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">24</span> <span style="color: #000000;">           and c.prod_id=# {appraise.prodId}
</span><span style="color: #008080;">25</span>        <span style="color: #0000ff;">&lt;/</span><span
      style="color: #800000;">if</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">26</span>   <span style="color: #0000ff;">&lt;/</span><span
      style="color: #800000;">select</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">27</span>
<span style="color: #008080;">28</span>   <span style="color: #0000ff;">&lt;</span><span
      style="color: #800000;">select </span><span style="color: #ff0000;">id</span><span style="color: #0000ff;">="selectByProdId"</span><span
      style="color: #ff0000;"> parameterType</span><span style="color: #0000ff;">="int"</span><span
      style="color: #ff0000;"> resultType</span><span style="color: #0000ff;">="cn.xyz.chaos.examples.demo.entity.Comment"</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">29</span> <span style="color: #000000;">       select
</span><span style="color: #008080;">30</span> <span style="color: #000000;">       a.appraise_id appraiseId,
</span><span style="color: #008080;">31</span> <span style="color: #000000;">       a.user_id userId,
</span><span style="color: #008080;">32</span> <span style="color: #000000;">       u.email userName,
</span><span style="color: #008080;">33</span> <span style="color: #000000;">       a.prod_id prodId,
</span><span style="color: #008080;">34</span> <span style="color: #000000;">       p.prod_name prodName,
</span><span style="color: #008080;">35</span> <span style="color: #000000;">       a.content,
</span><span style="color: #008080;">36</span> <span style="color: #000000;">       a.score,
</span><span style="color: #008080;">37</span> <span style="color: #000000;">       a.add_time addTime
</span><span style="color: #008080;">38</span> <span style="color: #000000;">       from appraise a,prod_info p,user_info u
</span><span style="color: #008080;">39</span> <span style="color: #000000;">       where
</span><span style="color: #008080;">40</span> <span style="color: #000000;">       a.prod_id=p.prod_id and a.user_id=u.user_id and a.prod_id=# {prodId}
</span><span style="color: #008080;">41</span>   <span style="color: #0000ff;">&lt;/</span><span
      style="color: #800000;">select</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">42</span>
<span style="color: #008080;">43</span> <span style="color: #0000ff;">&lt;/</span><span
      style="color: #800000;">mapper</span><span style="color: #0000ff;">&gt;</span></pre>
      </div>
      <p style="margin-left: 30px;">
        这个就是Mybatis的Mapper，和Ibatis相差不大，里面具体的表啊类啊什么的不用纠结，只是个示例。<br>接下来就是怎么在代码中执行sql并且拿到结果。<br>在我们项目中放dao的地方（这个根据项目的Mybatis集成的配置了）写一个接口：
      </p>

      <div class="cnblogs_code">
<pre><span style="color: #008080;"> 1</span> <span style="color: #0000ff;">package</span><span style="color: #000000;"> cn.xyz.chaos.examples.demo.repository.dao;
</span><span style="color: #008080;"> 2</span>
<span style="color: #008080;"> 3</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> java.util.List;
</span><span style="color: #008080;"> 4</span>
<span style="color: #008080;"> 5</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> org.apache.ibatis.annotations.Param;
</span><span style="color: #008080;"> 6</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.orm.mybatis.MyBatisRepository;
</span><span style="color: #008080;"> 7</span>
<span style="color: #008080;"> 8</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.examples.demo.entity.Appraise;
</span><span style="color: #008080;"> 9</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.examples.demo.entity.Comment;
</span><span style="color: #008080;">10</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.examples.demo.repository.mapper.AppraiseMapper;
</span><span style="color: #008080;">11</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
</span><span style="color: #008080;">12</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;
</span><span style="color: #008080;">13</span>
<span style="color: #008080;">14</span> <span style="color: #000000;">@MyBatisRepository
</span><span style="color: #008080;">15</span> <span style="color: #0000ff;">public</span> <span
      style="color: #0000ff;">interface</span> AppraiseDAO <span style="color: #0000ff;">extends</span><span
      style="color: #000000;"> AppraiseMapper {
</span><span style="color: #008080;">16</span>
<span
    style="color: #008080;">17</span>     PageList&lt;Comment&gt; selectBySelectiveWithRowbounds(@Param("appraise"<span
      style="color: #000000;">) Appraise appraise, PageBounds pageBounds);
</span><span style="color: #008080;">18</span>
<span style="color: #008080;">19</span>     List&lt;Comment&gt;<span style="color: #000000;"> selectByProdId(Integer prodId);
</span><span style="color: #008080;">20</span> }</pre>
      </div>
      <p>&nbsp;</p>

      <p style="margin-left: 30px;">这个接口以及方法的签名要和xml中的对应。<br>之所以有extends
        一个Mapper是因为这个Mapper是用代码生成工具生成的。我们想把自己写的部分和生成的部分分开维护又有同样的使用效果所以就用了extends。实际上没差别。<br>到此，其实就完成了。只需两步。接口并不需要实现类，可以直接注入到任意bean中供调用，就像下面这样：
      </p>

      <div class="cnblogs_code">
<pre><span style="color: #008080;"> 1</span> <span style="color: #000000;">    @Autowired
</span><span style="color: #008080;"> 2</span>     <span style="color: #0000ff;">private</span><span
    style="color: #000000;"> AppraiseDAO appraiseDAO;
</span><span style="color: #008080;"> 3</span>
<span style="color: #008080;"> 4</span>     <span style="color: #008000;">/**</span>
<span style="color: #008080;"> 5</span> <span style="color: #008000;">     * 获取前 appraiseNum 条产品相关的评论
</span><span style="color: #008080;"> 6</span> <span style="color: #008000;">     * </span><span
      style="color: #808080;">@param</span><span style="color: #008000;"> prodIds 产品ID列表
</span><span style="color: #008080;"> 7</span> <span style="color: #008000;">     * </span><span
      style="color: #808080;">@param</span><span style="color: #008000;"> appraiseNum 评论数量
</span><span style="color: #008080;"> 8</span> <span style="color: #008000;">     * </span><span
      style="color: #808080;">@return</span>
<span style="color: #008080;"> 9</span>      <span style="color: #008000;">*/</span>
<span style="color: #008080;">10</span>     <span style="color: #0000ff;">public</span> List&lt;AppraiseDTO&gt; getProdRelated(List&lt;Integer&gt; prodIds, <span
      style="color: #0000ff;">int</span><span style="color: #000000;"> appraiseNum) {
</span><span style="color: #008080;">11</span>         <span style="color: #0000ff;">if</span> (CollectionUtils.isEmpty(prodIds) || appraiseNum &lt; 1<span
      style="color: #000000;">) {
</span><span style="color: #008080;">12</span>             <span style="color: #0000ff;">return</span><span
      style="color: #000000;"> Collections.emptyList();
</span><span style="color: #008080;">13</span> <span style="color: #000000;">        }
</span><span style="color: #008080;">14</span>         PageBounds pageBounds = <span
      style="color: #0000ff;">new</span><span style="color: #000000;"> PageBounds(appraiseNum);
</span><span style="color: #008080;">15</span>
<span style="color: #008080;">16</span>         AppraiseCriteria criteria = <span
      style="color: #0000ff;">new</span><span style="color: #000000;"> AppraiseCriteria();
</span><span style="color: #008080;">17</span> <span style="color: #000000;">        criteria.or().andProdIdIn(prodIds);
</span><span style="color: #008080;">18</span>         List&lt;Appraise&gt; appraises =<span style="color: #000000;"> appraiseDAO.selectByCriteriaWithRowbounds(criteria, pageBounds);
</span><span style="color: #008080;">19</span>         <span style="color: #0000ff;">return</span><span
      style="color: #000000;"> convert(appraises);
</span><span style="color: #008080;">20</span>     }</pre>
      </div>
      <p style="margin-left: 30px;">所以很简单很方便。<br>这篇太打击积极性了，写了半天没了。想了想反正网上都有，因为保留原汁原味，没啥项目中特殊的地方，所以看来的都适用的。通过插件扩展的列表工具和分页就单独拿出去讲吧。
      </p>

      <p style="margin-left: 30px;"><br><br></p></div>

  </div>

</chaos:override>
<jsp:include page="../__template.jsp"/>
