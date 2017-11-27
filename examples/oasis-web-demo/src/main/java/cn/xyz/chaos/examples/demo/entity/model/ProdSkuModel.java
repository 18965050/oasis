package cn.xyz.chaos.examples.demo.entity.model;

import cn.xyz.chaos.examples.demo.entity.ProdSku;

/**
 * ProdSkuModel <br/>
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年8月13日 下午4:29:22
 * @author lcg
 */
public class ProdSkuModel extends ProdSku {

    private Integer skuOptionId;

    private String optionName;

    private Integer weight;

    /**
     * @return the skuOptionId
     */
    public Integer getSkuOptionId() {
        return skuOptionId;
    }

    /**
     * @param skuOptionId the skuOptionId to set
     */
    public void setSkuOptionId(Integer skuOptionId) {
        this.skuOptionId = skuOptionId;
    }

    /**
     * @return the optionName
     */
    public String getOptionName() {
        return optionName;
    }

    /**
     * @param optionName the optionName to set
     */
    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    /**
     * @return the weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}
