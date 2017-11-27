package cn.xyz.chaos.examples.demo.provider.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xyz.chaos.examples.demo.entity.ProdAttrLink;
import cn.xyz.chaos.examples.demo.entity.ProdInfo;
import cn.xyz.chaos.examples.demo.entity.ProdSkuLink;
import cn.xyz.chaos.examples.demo.entity.Product;
import cn.xyz.chaos.examples.demo.entity.criteria.ProdSkuLinkCriteria;
import cn.xyz.chaos.examples.demo.provider.ProductProvider;
import cn.xyz.chaos.examples.demo.provider.dto.ProdAttrLinkDTO;
import cn.xyz.chaos.examples.demo.provider.dto.ProductDTO;
import cn.xyz.chaos.examples.demo.repository.dao.ProdInfoDAO;
import cn.xyz.chaos.examples.demo.repository.dao.ProdSkuLinkDAO;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

@Repository
public class ProductProviderImpl implements ProductProvider {

    @Autowired
    private ProdInfoDAO prodInfoDAO;

    @Autowired
    private ProdSkuLinkDAO prodAttrDAO;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public Map<String, Object> pageList(ProductDTO queryDTO) {
        Map<String, Object> map = new HashMap<String, Object>();

        PageBounds pageBounds = new PageBounds(queryDTO.getLimit(), queryDTO.getPageIndex());

        ProdInfo queryInfo = dozerMapper.map(queryDTO, ProdInfo.class, "prodDTO2prodInfo");

        PageList<Product> productList = this.prodInfoDAO.selectBySelectiveWithRowbounds(queryInfo, pageBounds);

        map.put("currentPage", productList.getPaginator().getPage());
        map.put("totalPage", productList.getPaginator().getTotalPages());

        List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
        if (!CollectionUtils.isEmpty(productList)) {
            for (Product product : productList) {
                productDTOList.add(this.dozerMapper.map(product, ProductDTO.class, "prod2prodDTO"));
            }
        }
        map.put("productList", productDTOList);
        return map;
    }

    @Override
    public ProductDTO get(Integer id) {
        Product product = this.prodInfoDAO.selectById(id);
        // 生成图片文件
        String picLoc = "/cache/product/" + product.getComId() + "-" + product.getProdId() + ".jpg";
        File file = new File(System.getProperty("web.root") + "/WEB-INF" + picLoc);
        if (!file.exists() && product.getProdPic() != null && product.getProdPic().length > 0) {
            try {
                FileUtils.writeByteArrayToFile(file, product.getProdPic());
            } catch (IOException e) {
                // ignore
            }
        }
        product.setPicLoc(picLoc);
        return this.dozerMapper.map(product, ProductDTO.class, "prod2prodDTO");
    }

    @Override
    public void add(ProductDTO productDTO) throws Exception {
        String picLoc = productDTO.getPicLoc();
        if (StringUtils.isNotBlank(picLoc)) {
            productDTO.setProdPic(FileUtils.readFileToByteArray(new File(picLoc)));
        }
        this.prodInfoDAO.insert(this.dozerMapper.map(productDTO, ProdInfo.class, "prodDTO2prodInfo"));
    }

    @Override
    public void update(ProductDTO productDTO) throws Exception {
        String picLoc = productDTO.getPicLoc();
        if (StringUtils.isNotBlank(picLoc)) {
            productDTO.setProdPic(FileUtils.readFileToByteArray(new File(picLoc)));
        }
        this.prodInfoDAO
                .updateByPrimaryKeySelective(this.dozerMapper.map(productDTO, ProdInfo.class, "prodDTO2prodInfo"));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // (1)解除产品和标签属性绑定关系
        ProdSkuLinkCriteria prodAttrCriteria = new ProdSkuLinkCriteria();
        ProdSkuLinkCriteria.Criteria criteria = prodAttrCriteria.or();
        criteria.andProdIdEqualTo(id);
        this.prodAttrDAO.deleteByCriteria(prodAttrCriteria);
        // (2)删除产品
        this.prodInfoDAO.deleteByPrimaryKey(id);
    }

    @Override
    public List<ProdAttrLinkDTO> getProdAttrs(Integer prodId) {
        List<ProdAttrLink> prodAttrList = this.prodAttrDAO.getProdAttrs(prodId);
        List<ProdAttrLinkDTO> prodAttrDTOList = new ArrayList<ProdAttrLinkDTO>();
        if (!CollectionUtils.isEmpty(prodAttrList)) {
            for (ProdAttrLink prodAttr : prodAttrList) {
                prodAttrDTOList.add(this.dozerMapper.map(prodAttr, ProdAttrLinkDTO.class, "prodAttr2prodAttrDTO"));
            }
        }
        return prodAttrDTOList;
    }

    @Transactional
    @Override
    public void bindAttr(Integer prodId, String prodAttrs) {
        // (1) 先删除product和attribute之间的关联关系
        ProdSkuLinkCriteria prodAttrCriteria = new ProdSkuLinkCriteria();
        ProdSkuLinkCriteria.Criteria criteria = prodAttrCriteria.or();
        criteria.andProdIdEqualTo(prodId);
        this.prodAttrDAO.deleteByCriteria(prodAttrCriteria);
        // (2)添加product和attribute之间的关联关系
        if (StringUtils.isNotBlank(prodAttrs)) {
            List<String> attrList = Arrays.asList(prodAttrs.split(","));

            Collection<String> filteredList = Collections2.filter(attrList, new Predicate<String>() {
                @Override
                public boolean apply(String input) {
                    return StringUtils.isNotBlank(input);
                }
            });

            for (String attr : filteredList) {
                ProdSkuLink prodAttr = new ProdSkuLink();
                prodAttr.setProdId(prodId);
                prodAttr.setSkuOptionId(Integer.valueOf(attr));
                this.prodAttrDAO.insert(prodAttr);
            }
        }
    }

    @Override
    public List<ProductDTO> list() {
        List<ProdInfo> productList = this.prodInfoDAO.selectByCriteria(null);
        List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
        if (!CollectionUtils.isEmpty(productList)) {
            for (ProdInfo product : productList) {
                productDTOList.add(this.dozerMapper.map(product, ProductDTO.class, "prodInfo2prodDTO"));
            }
        }
        return productDTOList;
    }
}
