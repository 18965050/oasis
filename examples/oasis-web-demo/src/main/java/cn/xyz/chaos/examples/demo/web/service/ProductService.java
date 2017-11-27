package cn.xyz.chaos.examples.demo.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;

import cn.xyz.chaos.examples.demo.provider.ProductProvider;
import cn.xyz.chaos.examples.demo.provider.TagProvider;
import cn.xyz.chaos.examples.demo.provider.dto.ProdAttrLinkDTO;
import cn.xyz.chaos.examples.demo.provider.dto.ProductDTO;

@Service
public class ProductService {

    @Autowired
    private ProductProvider productProvider;

    @Autowired
    private TagProvider tagProvider;

    public Map<String, Object> list(ProductDTO queryDTO) {
        return productProvider.pageList(queryDTO);
    }

    public void add(ProductDTO productDTO) {
        productDTO.setAddTime(new Date());
        productDTO.setUpdateTime(new Date());
        productDTO.setAdderId(0);
        productDTO.setUpdaterId(0);
        try {
            this.productProvider.add(productDTO);
        } catch (Exception e) {
            productDTO.addError("ADD_PRODUCT_ERROR", "添加产品失败");
        }

    }

    public void update(ProductDTO productDTO) {
        try {
            this.productProvider.update(productDTO);
        } catch (Exception e) {
            productDTO.addError("UPDATE_PRODUCT_ERROR", "修改产品失败");
        }

    }

    public ProductDTO get(Integer id) {
        return this.productProvider.get(id);
    }

    public void detail(Integer id, ModelMap model) {
        model.addAttribute("product", get(id));
        model.addAttribute("attrList", this.productProvider.getProdAttrs(id));
    }

    public void delete(Integer id) {
        this.productProvider.delete(id);
    }

    public void bind(Integer id, ModelMap model) {
        model.addAttribute("product", get(id));
        model.addAttribute("tagList", this.tagProvider.list());
        List<ProdAttrLinkDTO> prodAttrDTOList = this.productProvider.getProdAttrs(id);
        StringBuilder sb = new StringBuilder("");
        if (!CollectionUtils.isEmpty(prodAttrDTOList)) {
            for (int i = 0; i < prodAttrDTOList.size(); i++) {
                sb.append(prodAttrDTOList.get(i).getSkuOptionId());
                if (i != prodAttrDTOList.size() - 1) {
                    sb.append(",");
                }
            }
        }
        model.addAttribute("prodAttr", sb.toString());
    }

    public void bindAttr(int prodId, String prodAttr) {
        this.productProvider.bindAttr(prodId, prodAttr);
    }
}
