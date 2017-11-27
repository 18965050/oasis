package cn.xyz.chaos.examples.demo.web.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xyz.chaos.examples.demo.provider.AppraiseProvider;
import cn.xyz.chaos.examples.demo.provider.ProdInfoProvider;
import cn.xyz.chaos.examples.demo.provider.ProdSkuProvider;
import cn.xyz.chaos.examples.demo.provider.dto.*;
import cn.xyz.chaos.examples.demo.web.svo.ProdlistSVO;
import cn.xyz.chaos.orm.mybatis.easylist.list.EasyListHelper;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.Paginator;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * ProdlistService <br/>
 * TODO
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年8月13日 上午11:33:25
 * @author lcg
 */
@Service
public class ProdlistService {

    @Autowired
    private ProdInfoProvider prodInfoProvider;

    @Autowired
    private ProdSkuProvider prodSkuProvider;

    @Autowired
    private AppraiseProvider appraiseProvider;

    /**
     * 列表View所需要的数据
     *
     * @param svo
     * @return
     */
    public Map<String, Object> list(ProdlistSVO svo) {
        Map<String, Object> rsObjs = Maps.newHashMap();
        // 查询SKU
        Collection<ProdSkuDTO> allSkusWithOpts = prodSkuProvider.getAllSkusWithOpts();
        HashSet<ProdSkuDTO> selectedSku = Sets.newHashSet();
        // 分离已选择的sku
        separateSelectedSku(allSkusWithOpts, svo.getSkuOptIds(), selectedSku);
        // 查询产品
        PageBounds pageBounds = EasyListHelper.getPageBounds(svo, svo.getLimit(), svo.getPage());
        PageList<ProdInfoDTO> prodInfos = prodInfoProvider.list(svo.getSkuOptIds(), pageBounds);
        // 查询产品对应的评论
        List<Integer> prodIdList = Lists.newArrayList();
        for (ProdInfoDTO dto : prodInfos) {
            prodIdList.add(dto.getProdId());
        }
        int appraiseNum = 5;// 评论最大数量
        List<AppraiseDTO> relatedAppraise = appraiseProvider.getProdRelated(prodIdList, appraiseNum);
        assembleProdInfoInfoAppraise(relatedAppraise, prodInfos);
        // 最终结果
        Paginator paginator = prodInfos.getPaginator();

        rsObjs.put("skuOptIds", svo.getSkuOptIds());
        rsObjs.put("data", prodInfos);
        rsObjs.put("limit", paginator.getLimit());
        rsObjs.put("page", paginator.getPage());
        rsObjs.put("totalCount", paginator.getTotalCount());
        rsObjs.put("totalPages", paginator.getTotalPages());
        rsObjs.put("result", "success");
        rsObjs.put("skus", allSkusWithOpts);
        rsObjs.put("selectedSku", selectedSku);
        rsObjs.put("appraises", relatedAppraise);

        return rsObjs;
    }

    /**
     * 将SKUS中有选项被选中的分离到selectedSku
     *
     * @param skus
     * @param skuOptIds
     * @param selectedSku
     */
    private void separateSelectedSku(Collection<ProdSkuDTO> skus, List<Integer> skuOptIds,
            Collection<ProdSkuDTO> selectedSku) {
        if (CollectionUtils.isEmpty(skuOptIds) || CollectionUtils.isEmpty(skus)) {
            return;
        }
        Iterator<ProdSkuDTO> iterator = skus.iterator();
        while (iterator.hasNext()) {
            ProdSkuDTO prodSkuDTO = iterator.next();
            boolean need = true;
            // 如果skuOptIds里面包含了该sku下的opt则此sku不需要展示
            for (ProdSkuOptionDTO optionDTO : prodSkuDTO.getSkuOptions()) {
                if (skuOptIds.contains(optionDTO.getSkuOptionId())) {
                    need = false;
                    ProdSkuDTO selected = new ProdSkuDTO();
                    selected.setSkuId(prodSkuDTO.getSkuId());
                    selected.setSkuName(prodSkuDTO.getSkuName());
                    selected.setSelectedOpt(optionDTO);
                    selectedSku.add(selected);
                    break;
                }
            }
            if (!need) {
                iterator.remove();
            }
        }
    }

    private void assembleProdInfoInfoAppraise(List<AppraiseDTO> appraiseDTOs, List<ProdInfoDTO> productDTOs) {
        if (CollectionUtils.isNotEmpty(appraiseDTOs) && CollectionUtils.isNotEmpty(productDTOs)) {
            Map<Integer, ProdInfoDTO> prodMap = Maps.newHashMap();
            for (ProdInfoDTO productDTO : productDTOs) {
                prodMap.put(productDTO.getProdId(), productDTO);
            }
            for (AppraiseDTO appraiseDTO : appraiseDTOs) {
                appraiseDTO.setProdInfoDTO(prodMap.get(appraiseDTO.getProdId()));
            }
        }
    }

}
