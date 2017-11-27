<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>

<chaos:override name="title">产品列表</chaos:override>

<chaos:override name="css">
  <link href="${ctx}/assets/third-party/jqPagination/css/jqpagination.css" type="text/css" rel="stylesheet" />
  <link href="${ctx}/assets/css/prodlist_list.css" type="text/css" rel="stylesheet" />
</chaos:override>

<chaos:override name="javascript">
  <script src="${ctx}/assets/third-party/jqPagination/js/jquery.jqpagination.min.js"></script>
  <script>
     seajs.use('{ctx}/prodlist_list');
  </script>
</chaos:override>

<chaos:override name="context">
  <div class="row">
    <div id="leftContent" class="col-md-9">
      <div id="pageTitle" class="page-header">
        <div class="row">
          <div class="col-md-6">
            <h1>旅游险</h1>
          </div>
          <div id="pageSize" class="pull-right col-md-3">
            共有<span class="label label-primary">${totalCount }</span>款保险产品
          </div>
        </div>
      </div>
      <div id="skuSel" class="panel panel-default">
        <div class="hidden">
          <form id="skuForm" action="${ctx }/prodlist/list">
            <c:forEach items="${skuOptIds }" var="optId">
              <input id="__sku_value_${optId }" name="skuOptIds" value="${optId }" />
            </c:forEach>
          </form>
        </div>
        <div class="panel-body">
          <c:if test="${not empty selectedSku }">
            <div id="selSkuRow" class="row">
              <div class="selSkuNam"><span class="label label-success">筛选</span></div>
              <div class="col-md-10">
                <ul class="list-unstyled list-inline">
                  <c:forEach items="${selectedSku }" var="sku">
                    <li class="crumbAttr"><span data-tip="${sku.selectedOpt.skuOptionId }">${sku.skuName }:${sku.selectedOpt.optionName }</span></li>
                  </c:forEach>
                </ul>
              </div>
            </div>
          </c:if>
          <ul class="list-unstyled">
            <c:forEach items="${skus }" var="sku">
              <li>
                <div class="skuRow">
                   <div class="skuName">
                    ${sku.skuName }：
                   </div>
                   <div class="skuValueDiv">
                    <ul class="list-unstyled list-inline">
                      <c:forEach items="${sku.skuOptions }" var="opt">
                        <li><span data-tip="${opt.skuOptionId }">${opt.optionName }</span></li>
                      </c:forEach>
                    </ul>
                   </div>
                </div>
              </li>
            </c:forEach>
          </ul>
        </div>
      </div>
      <div id="prodList">
        <div >
            <ul class="list-group">
                <c:forEach var="prod" items="${data }" >
                    <li class="list-group-item">
                        <div class="row prodRow">
                            <div class="col-md-3 prodImgDiv">
                                
                                	<chaos:img type="product" id="${prod.comId}-${prod.prodId}" alt="${prod.prodAlias }" width="80%" height="80%" cssClass="img-responsive" />
                                
                            </div>
                            <div class="col-md-9">
                                <p class="lead"><a href="${ctx}/proddetail/detail/${prod.prodId}"><strong>${prod.prodAlias }</strong></a></p>
                                <div class="prodInfo">
                                    适用人群： 境外旅游、出差、培训、探亲访友人群均适用;产品特色： 保障全面，24小时医疗救援，特含手机、手提电脑保障，境外游旅行必备，全球可保。<br />
                                    限购数量:${prod.limitQuantity }
                                </div>
                                <div class="prodFeature">
                                    <ul class="list-unstyled list-inline">
                                        <li><span class="glyphicon glyphicon-globe"></span></li>
                                        <li><span class="glyphicon glyphicon-heart"></span></li>
                                        <li><span class="glyphicon glyphicon-leaf"></span></li>
                                        <li><span class="glyphicon glyphicon-thumbs-up"></span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div >
            <div class="pagination pull-right">
                <a href="#" class="first" data-action="first">&laquo;</a>
                <a href="#" class="previous" data-action="previous">&lsaquo;</a>
                <input type="text" readonly="readonly" data-current-page="${page}" data-max-page="${totalPages}" />
                <a href="#" class="next" data-action="next">&rsaquo;</a>
                <a href="#" class="last" data-action="last">&raquo;</a>
            </div>
        </div>
      </div>
      
      
    </div>
    
    <div id="rightContent" class="col-md-3">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title">相关评论</h3>
        </div>
        <div class="panel-body">
          <ul class="list-unstyled ">
            <c:forEach var="appraise" items="${appraises}" >
              <li>
                  <a href="${ctx}/proddetail/detail/${appraise.prodId}" >
                  ${appraise.content}
                  </a>
              </li>
            </c:forEach>
          </ul>
        </div>
      </div>
    </div>
  </div>
</chaos:override>
<jsp:include page="__template.jsp"/>