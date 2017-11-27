<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jsp" %>

<chaos:override name="title">Easylist&Paging - 使用</chaos:override>

<chaos:override name="javascript">
  <script>
    __menu = "#menu-easylist-use";
  </script>
</chaos:override>

<chaos:override name="content">

<div id="cnblogs_post_body"><h1>Easylist&amp;Paging使用</h1>
<hr>
<p>Easylist 和 paging插件整合在一起，可以同时使用也可以用其中一部分。</p>

<p>下面介绍两种插件同时使用的情形，如果只使用一部分，只需少了其中一部分即可。</p>

<p>我们以一个简单的用户列表为例。</p>

<p>User实体如下：<br><img src="${ctx}/assets/img/easylist/241023380454794.jpg" alt=""><br><br>扩展实体（User是代码生成的，为了和自己扩展的字段分开，故多了一个扩展的Model
  ）如下：</p>

<div class="cnblogs_code">
<pre><span style="color: #008080;"> 1</span> <span style="color: #0000ff;">package</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.entity.model;
</span><span style="color: #008080;"> 2</span>
<span style="color: #008080;"> 3</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.entity.User;
</span><span style="color: #008080;"> 4</span>
<span style="color: #008080;"> 5</span> <span style="color: #008000;">/**</span>
<span style="color: #008080;"> 6</span> <span style="color: #008000;"> * UserModel &lt;br/&gt;
</span><span style="color: #008080;"> 7</span> <span style="color: #008000;"> * User的扩展POJO
</span><span style="color: #008080;"> 8</span> <span style="color: #008000;"> *
</span><span style="color: #008080;"> 9</span> <span style="color: #008000;"> * </span><span style="color: #808080;">@version</span><span
      style="color: #008000;"> 1.0.0 &lt;br/&gt;
</span><span style="color: #008080;">10</span> <span style="color: #008000;"> *          创建时间：2014年7月29日 下午2:21:58
</span><span style="color: #008080;">11</span> <span style="color: #008000;"> * </span><span style="color: #808080;">@author</span><span
      style="color: #008000;"> lcg
</span><span style="color: #008080;">12</span>  <span style="color: #008000;">*/</span>
<span style="color: #008080;">13</span> <span style="color: #0000ff;">public</span> <span
      style="color: #0000ff;">class</span> UserModel <span style="color: #0000ff;">extends</span><span
      style="color: #000000;"> User {
</span><span style="color: #008080;">14</span>
<span style="color: #008080;">15</span>     <span style="color: #0000ff;">private</span><span style="color: #000000;"> String createUser;
</span><span style="color: #008080;">16</span>
<span style="color: #008080;">17</span>     <span style="color: #0000ff;">private</span><span style="color: #000000;"> String updateUser;
</span><span style="color: #008080;">18</span>
<span style="color: #008080;">19</span>     <span style="color: #008000;">/**</span>
<span style="color: #008080;">20</span> <span style="color: #008000;">     * </span><span style="color: #808080;">@return</span><span
      style="color: #008000;"> the createUser
</span><span style="color: #008080;">21</span>      <span style="color: #008000;">*/</span>
<span style="color: #008080;">22</span>     <span style="color: #0000ff;">public</span><span style="color: #000000;"> String getCreateUser() {
</span><span style="color: #008080;">23</span>         <span style="color: #0000ff;">return</span><span
      style="color: #000000;"> createUser;
</span><span style="color: #008080;">24</span> <span style="color: #000000;">    }
</span><span style="color: #008080;">25</span>
<span style="color: #008080;">26</span>     <span style="color: #008000;">/**</span>
<span style="color: #008080;">27</span> <span style="color: #008000;">     * </span><span
      style="color: #808080;">@param</span><span style="color: #008000;"> createUser the createUser to set
</span><span style="color: #008080;">28</span>      <span style="color: #008000;">*/</span>
<span style="color: #008080;">29</span>     <span style="color: #0000ff;">public</span> <span style="color: #0000ff;">void</span><span
      style="color: #000000;"> setCreateUser(String createUser) {
</span><span style="color: #008080;">30</span>         <span style="color: #0000ff;">this</span>.createUser =<span
      style="color: #000000;"> createUser;
</span><span style="color: #008080;">31</span> <span style="color: #000000;">    }
</span><span style="color: #008080;">32</span>
<span style="color: #008080;">33</span>     <span style="color: #008000;">/**</span>
<span style="color: #008080;">34</span> <span style="color: #008000;">     * </span><span style="color: #808080;">@return</span><span
      style="color: #008000;"> the updateUser
</span><span style="color: #008080;">35</span>      <span style="color: #008000;">*/</span>
<span style="color: #008080;">36</span>     <span style="color: #0000ff;">public</span><span style="color: #000000;"> String getUpdateUser() {
</span><span style="color: #008080;">37</span>         <span style="color: #0000ff;">return</span><span
      style="color: #000000;"> updateUser;
</span><span style="color: #008080;">38</span> <span style="color: #000000;">    }
</span><span style="color: #008080;">39</span>
<span style="color: #008080;">40</span>     <span style="color: #008000;">/**</span>
<span style="color: #008080;">41</span> <span style="color: #008000;">     * </span><span
      style="color: #808080;">@param</span><span style="color: #008000;"> updateUser the updateUser to set
</span><span style="color: #008080;">42</span>      <span style="color: #008000;">*/</span>
<span style="color: #008080;">43</span>     <span style="color: #0000ff;">public</span> <span style="color: #0000ff;">void</span><span
      style="color: #000000;"> setUpdateUser(String updateUser) {
</span><span style="color: #008080;">44</span>         <span style="color: #0000ff;">this</span>.updateUser =<span
      style="color: #000000;"> updateUser;
</span><span style="color: #008080;">45</span> <span style="color: #000000;">    }
</span><span style="color: #008080;">46</span>
<span style="color: #008080;">47</span> }</pre>
</div>
<p>sql Mapper：</p>

<div class="cnblogs_code">
<pre><span style="color: #008080;"> 1</span> <span style="color: #0000ff;">&lt;?</span><span style="color: #ff00ff;">xml version="1.0" encoding="UTF-8" </span><span
    style="color: #0000ff;">?&gt;</span>
<span style="color: #008080;"> 2</span> <span style="color: #0000ff;">&lt;!</span><span style="color: #ff00ff;">DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" </span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 3</span> <span style="color: #0000ff;">&lt;</span><span
      style="color: #800000;">mapper </span><span style="color: #ff0000;">namespace</span><span style="color: #0000ff;">="cn.xyz.chaos.examples.showcase.repository.dao.UserDAO"</span> <span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 4</span>   <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">resultMap </span><span
      style="color: #ff0000;">id</span><span style="color: #0000ff;">="BaseModelResult"</span><span
      style="color: #ff0000;"> type</span><span style="color: #0000ff;">="cn.xyz.chaos.examples.showcase.entity.model.UserModel"</span> <span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;"> 5</span>     <span style="color: #008000;">&lt;!--</span>
<span style="color: #008080;"> 6</span> <span style="color: #008000;">      WARNING - @mbggenerated
</span><span style="color: #008080;"> 7</span> <span style="color: #008000;">      This element is automatically generated by MyBatis Generator, do not modify.
</span><span style="color: #008080;"> 8</span> <span style="color: #008000;">      This element was generated on Mon Jul 28 13:47:12 CST 2014.
</span><span style="color: #008080;"> 9</span>     <span style="color: #008000;">--&gt;</span>
<span style="color: #008080;">10</span>     <span style="color: #0000ff;">&lt;</span><span
      style="color: #800000;">id </span><span style="color: #ff0000;">column</span><span
      style="color: #0000ff;">="ID"</span><span style="color: #ff0000;"> property</span><span style="color: #0000ff;">="id"</span><span
      style="color: #ff0000;"> jdbcType</span><span style="color: #0000ff;">="DECIMAL"</span> <span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">11</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">result </span><span
      style="color: #ff0000;">column</span><span style="color: #0000ff;">="NAME"</span><span style="color: #ff0000;"> property</span><span
      style="color: #0000ff;">="name"</span><span style="color: #ff0000;"> jdbcType</span><span style="color: #0000ff;">="VARCHAR"</span> <span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">12</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">result </span><span
      style="color: #ff0000;">column</span><span style="color: #0000ff;">="NICK_NAME"</span><span
      style="color: #ff0000;"> property</span><span style="color: #0000ff;">="nickName"</span><span
      style="color: #ff0000;"> jdbcType</span><span style="color: #0000ff;">="VARCHAR"</span> <span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">13</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">result </span><span
      style="color: #ff0000;">column</span><span style="color: #0000ff;">="EMAIL"</span><span style="color: #ff0000;"> property</span><span
      style="color: #0000ff;">="email"</span><span style="color: #ff0000;"> jdbcType</span><span
      style="color: #0000ff;">="VARCHAR"</span> <span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">14</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">result </span><span
      style="color: #ff0000;">column</span><span style="color: #0000ff;">="MOBILE"</span><span style="color: #ff0000;"> property</span><span
      style="color: #0000ff;">="mobile"</span><span style="color: #ff0000;"> jdbcType</span><span
      style="color: #0000ff;">="VARCHAR"</span> <span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">15</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">result </span><span
      style="color: #ff0000;">column</span><span style="color: #0000ff;">="STATUS"</span><span style="color: #ff0000;"> property</span><span
      style="color: #0000ff;">="status"</span><span style="color: #ff0000;"> jdbcType</span><span
      style="color: #0000ff;">="DECIMAL"</span> <span style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">16</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">result </span><span
      style="color: #ff0000;">column</span><span style="color: #0000ff;">="WEIBO_URL"</span><span
      style="color: #ff0000;"> property</span><span style="color: #0000ff;">="weiboUrl"</span><span
      style="color: #ff0000;"> jdbcType</span><span style="color: #0000ff;">="VARCHAR"</span> <span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">17</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">result </span><span
      style="color: #ff0000;">column</span><span style="color: #0000ff;">="DOUBAN_URL"</span><span
      style="color: #ff0000;"> property</span><span style="color: #0000ff;">="doubanUrl"</span><span
      style="color: #ff0000;"> jdbcType</span><span style="color: #0000ff;">="VARCHAR"</span> <span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">18</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">result </span><span
      style="color: #ff0000;">column</span><span style="color: #0000ff;">="SIGNATURE"</span><span
      style="color: #ff0000;"> property</span><span style="color: #0000ff;">="signature"</span><span
      style="color: #ff0000;"> jdbcType</span><span style="color: #0000ff;">="VARCHAR"</span> <span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">19</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">result </span><span
      style="color: #ff0000;">column</span><span style="color: #0000ff;">="CREATE_TIME"</span><span
      style="color: #ff0000;"> property</span><span style="color: #0000ff;">="createTime"</span><span
      style="color: #ff0000;"> jdbcType</span><span style="color: #0000ff;">="DATE"</span> <span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">20</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">result </span><span
      style="color: #ff0000;">column</span><span style="color: #0000ff;">="CREATE_USER_ID"</span><span
      style="color: #ff0000;"> property</span><span style="color: #0000ff;">="createUserId"</span><span
      style="color: #ff0000;"> jdbcType</span><span style="color: #0000ff;">="DECIMAL"</span> <span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">21</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">result </span><span
      style="color: #ff0000;">column</span><span style="color: #0000ff;">="CREATE_USER"</span><span
      style="color: #ff0000;"> property</span><span style="color: #0000ff;">="createUser"</span><span
      style="color: #ff0000;"> jdbcType</span><span style="color: #0000ff;">="VARCHAR"</span> <span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">22</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">result </span><span
      style="color: #ff0000;">column</span><span style="color: #0000ff;">="UPDATE_TIME"</span><span
      style="color: #ff0000;"> property</span><span style="color: #0000ff;">="updateTime"</span><span
      style="color: #ff0000;"> jdbcType</span><span style="color: #0000ff;">="DATE"</span> <span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">23</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">result </span><span
      style="color: #ff0000;">column</span><span style="color: #0000ff;">="UPDATE_USER_ID"</span><span
      style="color: #ff0000;"> property</span><span style="color: #0000ff;">="updateUserId"</span><span
      style="color: #ff0000;"> jdbcType</span><span style="color: #0000ff;">="DECIMAL"</span> <span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">24</span>     <span style="color: #0000ff;">&lt;</span><span style="color: #800000;">result </span><span
      style="color: #ff0000;">column</span><span style="color: #0000ff;">="UPDATE_USER"</span><span
      style="color: #ff0000;"> property</span><span style="color: #0000ff;">="updateUser"</span><span
      style="color: #ff0000;"> jdbcType</span><span style="color: #0000ff;">="VARCHAR"</span> <span
      style="color: #0000ff;">/&gt;</span>
<span style="color: #008080;">25</span>   <span style="color: #0000ff;">&lt;/</span><span style="color: #800000;">resultMap</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">26</span>
<span style="color: #008080;">27</span>   <span style="color: #0000ff;">&lt;</span><span
      style="color: #800000;">select </span><span style="color: #ff0000;">id</span><span
      style="color: #0000ff;">="list"</span><span style="color: #ff0000;"> parameterType</span><span
      style="color: #0000ff;">="map"</span><span style="color: #ff0000;"> resultMap</span><span style="color: #0000ff;">="BaseModelResult"</span><span
      style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">28</span> <span style="color: #000000;">    select au.*, au2.name as create_user, au3.name as update_user
</span><span style="color: #008080;">29</span> <span style="color: #000000;">    from acc_user au
</span><span style="color: #008080;">30</span> <span style="color: #000000;">      left join acc_user au2 on au.create_user_id = au2.id
</span><span style="color: #008080;">31</span> <span style="color: #000000;">      left join acc_user au3 on au.update_user_id = au3.id
</span><span style="color: #008080;">32</span>     where au.id <span style="color: #0000ff;">&lt;![CDATA[</span><span
      style="color: #808080;"> &lt;&gt; </span><span style="color: #0000ff;">]]&gt;</span><span style="color: #000000;"> 998
</span><span style="color: #008080;">33</span>   <span style="color: #0000ff;">&lt;/</span><span
      style="color: #800000;">select</span><span style="color: #0000ff;">&gt;</span>
<span style="color: #008080;">34</span> <span style="color: #0000ff;">&lt;/</span><span
      style="color: #800000;">mapper</span><span style="color: #0000ff;">&gt;</span></pre>
</div>
<p><span style="line-height: 1.5;">这个sql和普通查询一样。后面的Where部分是为了测试，具体后面讲。</span></p>

<p>DAO：</p>

<div class="cnblogs_code">
<pre><span style="color: #008080;"> 1</span> <span style="color: #0000ff;">package</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.repository.dao;
</span><span style="color: #008080;"> 2</span>
<span style="color: #008080;"> 3</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> org.apache.ibatis.session.RowBounds;
</span><span style="color: #008080;"> 4</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.orm.mybatis.MyBatisRepository;
</span><span style="color: #008080;"> 5</span>
<span style="color: #008080;"> 6</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.entity.model.UserModel;
</span><span style="color: #008080;"> 7</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.repository.mapper.UserMapper;
</span><span style="color: #008080;"> 8</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;
</span><span style="color: #008080;"> 9</span>
<span style="color: #008080;">10</span> <span style="color: #000000;">@MyBatisRepository
</span><span style="color: #008080;">11</span> <span style="color: #0000ff;">public</span> <span
      style="color: #0000ff;">interface</span> UserDAO <span style="color: #0000ff;">extends</span><span
      style="color: #000000;"> UserMapper {
</span><span style="color: #008080;">12</span>
<span style="color: #008080;">13</span>     PageList&lt;UserModel&gt;<span style="color: #000000;"> list(RowBounds pageBounds);
</span><span style="color: #008080;">14</span>
<span style="color: #008080;">15</span> }</pre>
</div>
<p>&nbsp;</p>

<p>需要注意的是想要使用easyList和Paging的特性，则在DAO中查询方法的最后一个参数必须为org.apache.ibatis.session.RowBounds
  类型。也就是说，有这个参数则会尝试easyList和分页，无的话则和普通的sql执行没区别。<br>而返回类型cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList
  只是在ArrayList的基础上扩展了一个cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.Paginator 类型的field。这个字段存放查询后的分页信息。<br><br>为方便起见，下面直接Service：
</p>

<div class="cnblogs_code">
<pre><span style="color: #008080;"> 1</span> <span style="color: #0000ff;">package</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.web.service.acc;
</span><span style="color: #008080;"> 2</span>
<span style="color: #008080;"> 3</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> java.util.Date;
</span><span style="color: #008080;"> 4</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> java.util.Map;
</span><span style="color: #008080;"> 5</span>
<span style="color: #008080;"> 6</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> org.springframework.beans.factory.annotation.Autowired;
</span><span style="color: #008080;"> 7</span>
<span style="color: #008080;"> 8</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.entity.User;
</span><span style="color: #008080;"> 9</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.entity.model.UserModel;
</span><span style="color: #008080;">10</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.repository.dao.UserDAO;
</span><span style="color: #008080;">11</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.web.dvo.UserDVO;
</span><span style="color: #008080;">12</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.orm.mybatis.easylist.list.EasyListHelper;
</span><span style="color: #008080;">13</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
</span><span style="color: #008080;">14</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;
</span><span style="color: #008080;">15</span>
<span style="color: #008080;">16</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> com.google.common.collect.ImmutableMap;
</span><span style="color: #008080;">17</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> org.springframework.stereotype.Service;
</span><span style="color: #008080;">18</span>
<span style="color: #008080;">19</span> <span style="color: #008000;">/**</span>
<span style="color: #008080;">20</span> <span style="color: #008000;"> * UserService &lt;br/&gt;
</span><span style="color: #008080;">21</span> <span style="color: #008000;"> * 前端Service
</span><span style="color: #008080;">22</span> <span style="color: #008000;"> *
</span><span style="color: #008080;">23</span> <span style="color: #008000;"> * </span><span style="color: #808080;">@version</span><span
      style="color: #008000;"> 1.0.0 &lt;br/&gt;
</span><span style="color: #008080;">24</span> <span style="color: #008000;"> *          创建时间：2014年7月29日 下午2:31:05
</span><span style="color: #008080;">25</span> <span style="color: #008000;"> * </span><span style="color: #808080;">@author</span><span
      style="color: #008000;"> lcg
</span><span style="color: #008080;">26</span>  <span style="color: #008000;">*/</span>
<span style="color: #008080;">27</span> <span style="color: #000000;">@Service
</span><span style="color: #008080;">28</span> <span style="color: #0000ff;">public</span> <span
      style="color: #0000ff;">class</span><span style="color: #000000;"> UserService {
</span><span style="color: #008080;">29</span>
<span style="color: #008080;">30</span> <span style="color: #000000;">    @Autowired
</span><span style="color: #008080;">31</span>     <span style="color: #0000ff;">private</span><span
      style="color: #000000;"> UserDAO    userDAO;
</span><span style="color: #008080;">32</span>
<span style="color: #008080;">33</span>     <span style="color: #008000;">/**</span>
<span style="color: #008080;">34</span> <span style="color: #008000;">     * </span><span
      style="color: #808080;">@param</span><span style="color: #008000;"> userDVO
</span><span style="color: #008080;">35</span> <span style="color: #008000;">     * </span><span
      style="color: #808080;">@return</span>
<span style="color: #008080;">36</span>      <span style="color: #008000;">*/</span>
<span style="color: #008080;">37</span>     <span style="color: #0000ff;">public</span> Map&lt;String, Object&gt;<span
      style="color: #000000;"> list(UserDVO userDVO) {
</span><span style="color: #008080;">38</span>         PageBounds pageBounds =<span style="color: #000000;"> EasyListHelper.getPageBounds(userDVO, userDVO.getLimit(), userDVO.getPageIndex());
</span><span style="color: #008080;">39</span>         <span style="color: #008000;">//</span><span
      style="color: #008000;"> 通过下面这种方法，直接调用自动生成的查询语句，可以分页
</span><span style="color: #008080;">40</span>         <span style="color: #008000;">//</span><span
      style="color: #008000;"> UserCriteria userCriteria = new UserCriteria();
</span><span style="color: #008080;">41</span>         <span style="color: #008000;">//</span><span
      style="color: #008000;"> PageList&lt;User&gt; rsList = (PageList&lt;User&gt;) userDAO.selectByCriteriaWithRowbounds(userCriteria, pageBounds);
</span><span style="color: #008080;">42</span>
<span style="color: #008080;">43</span>         <span style="color: #008000;">//</span><span style="color: #008000;"> 通过这种组合的办法，也可以</span>
<span style="color: #008080;">44</span>         PageList&lt;UserModel&gt; rsList =<span style="color: #000000;"> userDAO.list(pageBounds);
</span><span style="color: #008080;">45</span>
<span style="color: #008080;">46</span>         <span style="color: #0000ff;">return</span> ImmutableMap.&lt;String, Object&gt; of("result", "success", "data", rsList, "count"<span
      style="color: #000000;">, rsList.getPaginator()
</span><span style="color: #008080;">47</span> <span style="color: #000000;">                .getTotalCount());
</span><span style="color: #008080;">48</span> <span style="color: #000000;">    }
</span><span style="color: #008080;">49</span>
<span style="color: #008080;">50</span> }</pre>
</div>
<p>&nbsp;</p>

<p>代码中注释的另一种用分页查询的方法和自己写sql效果是一样的，此处就只展示自己写sql的方式。<br>方法的返回值不用介意，其中&nbsp;rsList.getPaginator().getTotalCount()
  是获取分页查询结果的总数。<br>Paginator的方法如下：<br><img src="${ctx}/assets/img/easylist/241127283579541.jpg"
                                           alt=""><br>相信看方法的名字就能猜出来其作用。<br>接着说上面的代码，<br>PageBounds 也可以通过类似&nbsp;new
  PageBounds(10,1) 这样的初始化。而EasyListHelper方法的作用就是给pageBounds设置Where条件和排序条件。<br>这些条件自然是内部通过传入的参数userDVO提取。<br>来看看UserDVO：
</p>

<div class="cnblogs_code">
<pre><span style="color: #008080;"> 1</span> <span style="color: #0000ff;">package</span><span style="color: #000000;"> cn.xyz.chaos.examples.showcase.web.dvo;
</span><span style="color: #008080;"> 2</span>
<span style="color: #008080;"> 3</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> java.util.Date;
</span><span style="color: #008080;"> 4</span>
<span style="color: #008080;"> 5</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> org.springframework.format.annotation.DateTimeFormat;
</span><span style="color: #008080;"> 6</span>
<span style="color: #008080;"> 7</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.orm.mybatis.easylist.list.base.SearchType;
</span><span style="color: #008080;"> 8</span> <span style="color: #0000ff;">import</span><span style="color: #000000;"> cn.xyz.chaos.orm.mybatis.easylist.list.base.annotation.SearchItem;
</span><span style="color: #008080;"> 9</span>
<span style="color: #008080;">10</span> <span style="color: #008000;">/**</span>
<span style="color: #008080;">11</span> <span style="color: #008000;"> * UserDVO &lt;br/&gt;
</span><span style="color: #008080;">12</span> <span style="color: #008000;"> * User 页面值对象
</span><span style="color: #008080;">13</span> <span style="color: #008000;"> *
</span><span style="color: #008080;">14</span> <span style="color: #008000;"> * </span><span style="color: #808080;">@version</span><span
      style="color: #008000;"> 1.0.0 &lt;br/&gt;
</span><span style="color: #008080;">15</span> <span style="color: #008000;"> *          创建时间：2014年7月29日 下午2:15:52
</span><span style="color: #008080;">16</span> <span style="color: #008000;"> * </span><span style="color: #808080;">@author</span><span
      style="color: #008000;"> lcg
</span><span style="color: #008080;">17</span>  <span style="color: #008000;">*/</span>
<span style="color: #008080;">18</span> <span style="color: #0000ff;">public</span> <span
      style="color: #0000ff;">class</span><span style="color: #000000;"> UserDVO {
</span><span style="color: #008080;">19</span>
<span style="color: #008080;">20</span>     @SearchItem(value = "au.id", searchType =<span style="color: #000000;"> SearchType.NUMBER_EQUAL)
</span><span style="color: #008080;">21</span>     <span style="color: #0000ff;">private</span><span
      style="color: #000000;"> Long id;
</span><span style="color: #008080;">22</span>
<span style="color: #008080;">23</span>     @SearchItem(value = "au.name", searchType =<span style="color: #000000;"> SearchType.TEXT_LIKE)
</span><span style="color: #008080;">24</span>     <span style="color: #0000ff;">private</span><span
      style="color: #000000;"> String name;
</span><span style="color: #008080;">25</span>
<span style="color: #008080;">26</span>     @SearchItem(value = "au.nick_name", searchType =<span
      style="color: #000000;"> SearchType.TEXT_LIKE)
</span><span style="color: #008080;">27</span>     <span style="color: #0000ff;">private</span><span
      style="color: #000000;"> String nickName;
</span><span style="color: #008080;">28</span>
<span style="color: #008080;">29</span>     @SearchItem(value = "au.email", searchType =<span style="color: #000000;"> SearchType.TEXT_LIKE)
</span><span style="color: #008080;">30</span>     <span style="color: #0000ff;">private</span><span
      style="color: #000000;"> String email;
</span><span style="color: #008080;">31</span>
<span style="color: #008080;">32</span>     @SearchItem(value = "au.mobile", searchType =<span style="color: #000000;"> SearchType.TEXT_LIKE)
</span><span style="color: #008080;">33</span>     <span style="color: #0000ff;">private</span><span
      style="color: #000000;"> String mobile;
</span><span style="color: #008080;">34</span>
<span style="color: #008080;">35</span>     @SearchItem(value = "au.status", searchType =<span style="color: #000000;"> SearchType.NUMBER_EQUAL)
</span><span style="color: #008080;">36</span>     <span style="color: #0000ff;">private</span><span
      style="color: #000000;"> Integer status;
</span><span style="color: #008080;">37</span>
<span style="color: #008080;">38</span>     <span style="color: #0000ff;">private</span><span style="color: #000000;"> String weiboUrl;
</span><span style="color: #008080;">39</span>
<span style="color: #008080;">40</span>     <span style="color: #0000ff;">private</span><span style="color: #000000;"> String doubanUrl;
</span><span style="color: #008080;">41</span>
<span style="color: #008080;">42</span>     <span style="color: #0000ff;">private</span><span style="color: #000000;"> String signature;
</span><span style="color: #008080;">43</span>
<span style="color: #008080;">44</span>     <span style="color: #0000ff;">private</span><span style="color: #000000;"> Date createTime;
</span><span style="color: #008080;">45</span>
<span style="color: #008080;">46</span>     <span style="color: #0000ff;">private</span><span style="color: #000000;"> String createUser;
</span><span style="color: #008080;">47</span>
<span style="color: #008080;">48</span>     <span style="color: #0000ff;">private</span><span style="color: #000000;"> Date updateTime;
</span><span style="color: #008080;">49</span>
<span style="color: #008080;">50</span>     <span style="color: #0000ff;">private</span><span style="color: #000000;"> String updateUser;
</span><span style="color: #008080;">51</span>
<span style="color: #008080;">52</span>     @SearchItem(value = "au.create_time", searchType =<span
      style="color: #000000;"> SearchType.DATE_BETWEEN)
</span><span style="color: #008080;">53</span>     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss"<span
      style="color: #000000;">)
</span><span style="color: #008080;">54</span>     <span style="color: #0000ff;">private</span> Date[] betweenCreateTime = <span
      style="color: #0000ff;">new</span> Date[2<span style="color: #000000;">];
</span><span style="color: #008080;">55</span>
<span style="color: #008080;">56</span>     <span style="color: #008000;">/**</span>
<span style="color: #008080;">57</span> <span style="color: #008000;">     * 分页参数，第几页
</span><span style="color: #008080;">58</span>      <span style="color: #008000;">*/</span>
<span style="color: #008080;">59</span>     <span style="color: #0000ff;">private</span> <span style="color: #0000ff;">int</span> pageIndex = 0<span
      style="color: #000000;">;
</span><span style="color: #008080;">60</span>
<span style="color: #008080;">61</span>     <span style="color: #008000;">/**</span>
<span style="color: #008080;">62</span> <span style="color: #008000;">     * 分页参数，每页多少条
</span><span style="color: #008080;">63</span>      <span style="color: #008000;">*/</span>
<span style="color: #008080;">64</span>     <span style="color: #0000ff;">private</span> <span style="color: #0000ff;">int</span> limit = 10<span
      style="color: #000000;">;
</span><span style="color: #008080;">65</span>
<span style="color: #008080;">66</span> <span style="color: #000000;">       ……
</span><span style="color: #008080;">67</span>
<span style="color: #008080;">68</span> }</pre>
</div>
<p>这其实就是个POJO，特殊的是用了easyList的注解。限于篇幅，就不细细解释有哪些注解、怎样使用了。<br>以Long id为例，其注解@SearchItem(value = "au.id", searchType =
  SearchType.NUMBER_EQUAL)表示该字段是搜索字段。其中value的值表示对应sql中的查询字段，searchType表示匹配的模式。<br>id字段如果有值则会在sql中组合出 au.id =
  {id}的条件，如果无，则对sql没啥影响。<br>目前组合的条件和原sql的融合方式是：</p>
<ul>
  <li>如果原sql无where XXX则直接在最后面追加</li>
  <li>如果原sql有where，则会在第一个where后面追加条件</li>


</ul>
<p>这样就是整个使用easyList的流程。<br>如果不用EasyListHelper提取条件，则只是分页。<br>如果分页只是new PageBounds() ,而不设置单页数量和起始页码，则只是添加条件不分页。<br>如果不用Pagebounds/RowBounds做DAO的参数，则既不会有分页也不会有条件查询。
</p></div>

</chaos:override>
<jsp:include page="../__template.jsp"/>
