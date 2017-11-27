package cn.xyz.chaos.examples.demo.provider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xyz.chaos.examples.demo.entity.Attribute;
import cn.xyz.chaos.examples.demo.entity.criteria.ProdSkuLinkCriteria;
import cn.xyz.chaos.examples.demo.provider.AttributeProvider;
import cn.xyz.chaos.examples.demo.provider.dto.AttributeDTO;
import cn.xyz.chaos.examples.demo.repository.dao.ProdSkuLinkDAO;
import cn.xyz.chaos.examples.demo.repository.dao.ProdSkuOptionDAO;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

@Repository
public class AttributeProviderImpl implements AttributeProvider {

    @Autowired
    private ProdSkuOptionDAO attributeDAO;

    @Autowired
    private ProdSkuLinkDAO prodAttrDAO;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public Map<String, Object> pageList(AttributeDTO queryDTO) {
        Map<String, Object> map = new HashMap<String, Object>();

        PageBounds pageBounds = new PageBounds(queryDTO.getLimit(), queryDTO.getPageIndex());

        Attribute queryInfo = this.dozerMapper.map(queryDTO, Attribute.class, "attrDTO2Attr");

        PageList<Attribute> attrList = this.attributeDAO.selectBySelectiveWithRowbounds(queryInfo, pageBounds);

        map.put("currentPage", attrList.getPaginator().getPage());
        map.put("totalPage", attrList.getPaginator().getTotalPages());

        List<AttributeDTO> attrDTOList = new ArrayList<AttributeDTO>();
        if (!CollectionUtils.isEmpty(attrList)) {
            for (Attribute attr : attrList) {
                attrDTOList.add(this.dozerMapper.map(attr, AttributeDTO.class, "attr2attrDTO"));
            }
        }
        map.put("attrList", attrDTOList);
        return map;
    }

    @Override
    public AttributeDTO get(Integer id) {
        return this.dozerMapper.map(this.attributeDAO.selectById(id), AttributeDTO.class, "attr2attrDTO");
    }

    @Override
    public void add(AttributeDTO attrDTO) {
        this.attributeDAO.insert(this.dozerMapper.map(attrDTO, Attribute.class, "attrDTO2Attr"));
    }

    @Override
    public void update(AttributeDTO attrDTO) {
        this.attributeDAO.updateByPrimaryKeySelective(this.dozerMapper.map(attrDTO, Attribute.class, "attrDTO2Attr"));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // (1)解除产品和标签属性绑定关系
        ProdSkuLinkCriteria prodAttrCriteria = new ProdSkuLinkCriteria();
        ProdSkuLinkCriteria.Criteria criteria = prodAttrCriteria.or();
        criteria.andSkuOptionIdEqualTo(id);
        this.prodAttrDAO.deleteByCriteria(prodAttrCriteria);
        // (2) 删除属性
        this.attributeDAO.deleteByPrimaryKey(id);
    }

    @Override
    public List<AttributeDTO> getByTagId(Integer tagId) {
        List<Attribute> attrList = this.attributeDAO.selectByTagId(tagId);
        List<AttributeDTO> attrDTOList = new ArrayList<AttributeDTO>();
        if (!CollectionUtils.isEmpty(attrList)) {
            for (Attribute attr : attrList) {
                attrDTOList.add(this.dozerMapper.map(attr, AttributeDTO.class, "attr2attrDTO"));
            }
        }
        return attrDTOList;
    }
}
