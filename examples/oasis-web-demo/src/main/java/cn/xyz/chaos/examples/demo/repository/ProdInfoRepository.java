package cn.xyz.chaos.examples.demo.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xyz.chaos.examples.demo.entity.ProdInfo;
import cn.xyz.chaos.examples.demo.provider.dto.ProdInfoDTO;
import cn.xyz.chaos.examples.demo.repository.dao.ProdInfoDAO;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

/**
 * ProdInfoRepository <br/>
 *
 * @author lcg
 * @version 1.0.0 <br/>
 *          创建时间：2014年8月13日 下午3:26:08
 */
@Service
public class ProdInfoRepository {

    @Autowired
    private ProdInfoDAO prodInfoDAO;

    /**
     * @param skuOptIds SKU选择项
     * @param pageBounds
     */
    public PageList<ProdInfoDTO> list(List<Integer> skuOptIds, PageBounds pageBounds) {
        PageList<ProdInfo> prodInfos = prodInfoDAO.listWithSKU(skuOptIds, pageBounds);
        return new PageList<ProdInfoDTO>(convert(prodInfos), prodInfos.getPaginator());
    }

    /**
     * 转换 POJO 为 DTO
     *
     * @param pojo
     * @return
     */
    public ProdInfoDTO convert(ProdInfo pojo) {
        if (pojo == null) {
            return null;
        }
        ProdInfoDTO dto = new ProdInfoDTO();
        BeanUtils.copyProperties(pojo, dto);
        return dto;
    }

    /**
     * POJOs to DTOs
     *
     * @param pojos
     * @return
     */
    public List<ProdInfoDTO> convert(List<ProdInfo> pojos) {
        if (CollectionUtils.isEmpty(pojos)) {
            return Collections.emptyList();
        }
        ArrayList<ProdInfoDTO> dtos = Lists.newArrayList();
        for (ProdInfo pojo : pojos) {
            dtos.add(convert(pojo));
        }
        return dtos;
    }

}
