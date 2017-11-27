## 0.5.4
1. 将原先io-all项目中的基础类放入chaos-commons中, 包括`cn.xyz.chaos.common.json.IOObjectMapper.java`, `cn.xyz.chaos.common.orika.IoConfigurableMapper`和`CustomDateTypeHandler.CustomDateTypeHandler`
2. 添加`cn.xyz.chaos.common.log.FilePropertyDefiner.java`, 用于日志配置文件可随不同环境配置
3. 废弃`cn.xyz.chaos.mvc.web.api.ApiBadRequestExceptionResolver.java`和`cn.xyz.chaos.mvc.web.api.ApiHandlerExceptionResolver.java`, 新增`cn.xyz.chaos.mvc.web.api.ApiGenericHandlerExceptionResolver`,用于API异步ajax请求的通用错误处理
4. 去掉jedis pipeline中不必要的exec方法执行
5. 修改`cn.xyz.chaos.mvc.shiro.session.SessionInternalAttrConfig`中属性patterns为excludePatterns, 避免理解上的歧义

## 0.5.6
1. 提供跨域处理Filter(`cn.xyz.chaos.common.cors.CorsFilter`)

## 0.5.8
1. 修正跨域处理Filter对于请求头(Request Header)中存在跨域参数则不需要在响应头(Response Header)中再次添加此参数的bug
2. 修正shiro过滤链(`cn.xyz.chaos.mvc.shiro.CustomShiroFilterFactoryBean#ShiroSessionCleanWarpFilter`)由于业务处理抛出异常而导致共享会话不能写回redis的bug
