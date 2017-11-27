package cn.xyz.chaos.examples.demo.repository;

import cn.xyz.chaos.examples.demo.entity.Appraise;
import cn.xyz.chaos.examples.demo.entity.criteria.AppraiseCriteria;
import cn.xyz.chaos.examples.demo.provider.dto.AppraiseDTO;
import cn.xyz.chaos.examples.demo.repository.dao.AppraiseDAO;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * cn.xyz.chaos.examples.demo.repository
 *
 * @author lcg Created by lcg on 2014/8/28 10:40.
 * @version 1.0.0
 */
@Service
public class AppraiseRepository {
    @Autowired
    private AppraiseDAO appraiseDAO;

    /**
     * 获取前 appraiseNum 条产品相关的评论
     * @param prodIds 产品ID列表
     * @param appraiseNum 评论数量
     * @return
     */
    public List<AppraiseDTO> getProdRelated(List<Integer> prodIds, int appraiseNum) {
        if (CollectionUtils.isEmpty(prodIds) || appraiseNum < 1) {
            return Collections.emptyList();
        }
        PageBounds pageBounds = new PageBounds(appraiseNum);

        AppraiseCriteria criteria = new AppraiseCriteria();
        criteria.or().andProdIdIn(prodIds);
        List<Appraise> appraises = appraiseDAO.selectByCriteriaWithRowbounds(criteria, pageBounds);
        return convert(appraises);
    }

    /**
     * 转换 POJO 为 DTO
     * @param pojo
     * @return
     */
    public AppraiseDTO convert(Appraise pojo) {
        if (pojo == null) {
            return null;
        }
        AppraiseDTO dto = new AppraiseDTO();
        BeanUtils.copyProperties(pojo, dto);
        return dto;
    }

    /**
     * POJOs to DTOs
     * @param pojos
     * @return
     */
    public List<AppraiseDTO> convert(List<Appraise> pojos) {
        if (CollectionUtils.isEmpty(pojos)) {
            return Collections.emptyList();
        }
        ArrayList<AppraiseDTO> dtos = Lists.newArrayList();
        for (Appraise pojo : pojos) {
            dtos.add(convert(pojo));
        }
        return dtos;
    }

}
