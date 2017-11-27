package cn.xyz.chaos.examples.demo.provider;

import java.util.List;
import java.util.Map;

import cn.xyz.chaos.examples.demo.provider.dto.ProdAttrLinkDTO;
import cn.xyz.chaos.examples.demo.provider.dto.ProductDTO;

public interface ProductProvider {

    Map<String, Object> pageList(ProductDTO queryDTO);

    List<ProductDTO> list();

    ProductDTO get(Integer id);

    void add(ProductDTO productDTO) throws Exception;

    void update(ProductDTO productDTO) throws Exception;

    void delete(Integer id);

    List<ProdAttrLinkDTO> getProdAttrs(Integer prodId);

    void bindAttr(Integer prodId, String prodAttrs);

}
