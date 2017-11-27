package cn.xyz.chaos.examples.demo.provider;

import java.util.Collection;

import cn.xyz.chaos.examples.demo.provider.dto.ProdSkuDTO;

/**
 * ProdSkuProvider <br/>
 * 产品SKU服务
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年8月13日 下午4:34:39
 * @author lcg
 */
public interface ProdSkuProvider {

    /**
     * 获取所有带有选择项的SKU
     *
     * @return
     */
    Collection<ProdSkuDTO> getAllSkusWithOpts();

}
