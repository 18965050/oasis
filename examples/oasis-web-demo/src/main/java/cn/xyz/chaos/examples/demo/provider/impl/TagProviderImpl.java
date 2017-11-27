package cn.xyz.chaos.examples.demo.provider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xyz.chaos.examples.demo.entity.ProdSku;
import cn.xyz.chaos.examples.demo.entity.criteria.ProdSkuCriteria;
import cn.xyz.chaos.examples.demo.entity.criteria.ProdSkuOptionCriteria;
import cn.xyz.chaos.examples.demo.provider.TagProvider;
import cn.xyz.chaos.examples.demo.provider.dto.TagDTO;
import cn.xyz.chaos.examples.demo.repository.dao.ProdSkuDAO;
import cn.xyz.chaos.examples.demo.repository.dao.ProdSkuLinkDAO;
import cn.xyz.chaos.examples.demo.repository.dao.ProdSkuOptionDAO;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

@Repository
public class TagProviderImpl implements TagProvider {

    @Autowired
    private ProdSkuDAO prodSkuDAO;

    @Autowired
    private ProdSkuOptionDAO prodSkuOptionDAO;

    @Autowired
    private ProdSkuLinkDAO prodSkuLinkDAO;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public List<TagDTO> list() {
        List<ProdSku> prodSkuList = this.prodSkuDAO.selectAll();
        List<TagDTO> tagDTOList = new ArrayList<TagDTO>();
        if (!CollectionUtils.isEmpty(prodSkuList)) {
            for (ProdSku prodSku : prodSkuList) {
                tagDTOList.add(this.dozerMapper.map(prodSku, TagDTO.class, "prodSku2tagDTO"));
            }
        }
        return tagDTOList;
    }

    @Override
    public Map<String, Object> pageList(TagDTO queryDTO) {
        Map<String, Object> map = new HashMap<String, Object>();

        PageBounds pageBounds = new PageBounds(queryDTO.getLimit(), queryDTO.getPageIndex());

        ProdSku queryInfo = this.dozerMapper.map(queryDTO, ProdSku.class, "tagDTO2prodSku");

        ProdSkuCriteria prodSkuCriteria = new ProdSkuCriteria();
        ProdSkuCriteria.Criteria criteria = prodSkuCriteria.or();

        if (StringUtils.isNotEmpty(queryInfo.getSkuName())) {
            criteria.andSkuNameLike("%" + queryInfo.getSkuName() + "%");
        }

        List<ProdSku> prodSkuList = this.prodSkuDAO.selectByCriteriaWithRowbounds(prodSkuCriteria, pageBounds);

        PageList<ProdSku> pageList = (PageList<ProdSku>) prodSkuList;

        map.put("currentPage", pageList.getPaginator().getPage());
        map.put("totalPage", pageList.getPaginator().getTotalPages());

        List<TagDTO> tagDTOList = new ArrayList<TagDTO>();
        if (!CollectionUtils.isEmpty(prodSkuList)) {
            for (ProdSku tag : prodSkuList) {
                tagDTOList.add(this.dozerMapper.map(tag, TagDTO.class, "prodSku2tagDTO"));
            }
        }
        map.put("tagList", tagDTOList);
        return map;
    }

    @Override
    public void add(TagDTO tagDTO) {
        this.prodSkuDAO.insert(this.dozerMapper.map(tagDTO, ProdSku.class, "tagDTO2prodSku"));
    }

    @Override
    public void update(TagDTO tagDTO) {
        this.prodSkuDAO.updateByPrimaryKeySelective(this.dozerMapper.map(tagDTO, ProdSku.class, "tagDTO2prodSku"));
    }

    @Override
    public TagDTO get(Integer id) {
        return this.dozerMapper.map(prodSkuDAO.selectByPrimaryKey(id), TagDTO.class, "prodSku2tagDTO");
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // (1) 解除产品和标签属性绑定关系
        this.prodSkuLinkDAO.deleteByTagId(id);
        // (2) 删除标签属性
        ProdSkuOptionCriteria attrCriteria = new ProdSkuOptionCriteria();
        ProdSkuOptionCriteria.Criteria criteria = attrCriteria.or();
        criteria.andSkuIdEqualTo(id);
        this.prodSkuOptionDAO.deleteByCriteria(attrCriteria);
        // (3)删除标签
        this.prodSkuDAO.deleteByPrimaryKey(id);
    }
}
