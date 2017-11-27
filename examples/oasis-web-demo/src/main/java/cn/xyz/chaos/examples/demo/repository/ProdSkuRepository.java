package cn.xyz.chaos.examples.demo.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xyz.chaos.examples.demo.entity.model.ProdSkuModel;
import cn.xyz.chaos.examples.demo.provider.dto.ProdSkuDTO;
import cn.xyz.chaos.examples.demo.provider.dto.ProdSkuOptionDTO;
import cn.xyz.chaos.examples.demo.repository.dao.ProdSkuDAO;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * ProdSkuRepository <br/>
 * 产品SKU仓储
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年8月13日 下午4:39:58
 * @author lcg
 */
@Service
public class ProdSkuRepository {

    @Autowired
    private ProdSkuDAO prodSkuDAO;

    public Collection<ProdSkuDTO> getAllSkusWithOpts() {
        List<ProdSkuModel> skuModels = prodSkuDAO.selectSkusAndOpts();
        return convertToDTOs(skuModels);
    }

    /**
     * @param skuModels
     * @return
     */
    private Collection<ProdSkuDTO> convertToDTOs(Collection<ProdSkuModel> skuModels) {
        if (CollectionUtils.isEmpty(skuModels)) {
            return Collections.emptyList();
        }
        Map<Integer, ProdSkuDTO> skus = Maps.newHashMap();

        for (ProdSkuModel prodSkuModel : skuModels) {
            Integer skuId = prodSkuModel.getSkuId();
            ProdSkuDTO prodSkuDTO = skus.get(skuId);
            if (null == prodSkuDTO) {
                prodSkuDTO = new ProdSkuDTO();
                prodSkuDTO.setSkuId(skuId);
                prodSkuDTO.setSkuName(prodSkuModel.getSkuName());
                prodSkuDTO.setSkuOptions(Lists.<ProdSkuOptionDTO> newArrayList());
                skus.put(skuId, prodSkuDTO);
            }
            ProdSkuOptionDTO skuOptionDTO = new ProdSkuOptionDTO();
            skuOptionDTO.setSkuId(skuId);
            skuOptionDTO.setSkuOptionId(prodSkuModel.getSkuOptionId());
            skuOptionDTO.setOptionName(prodSkuModel.getOptionName());
            skuOptionDTO.setWeight(prodSkuModel.getWeight());

            prodSkuDTO.getSkuOptions().add(skuOptionDTO);
        }

        return skus.values();
    }

}
