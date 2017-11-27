package cn.xyz.chaos.examples.demo.provider.dto;

import java.io.Serializable;

/**
 * ProdSkuOptionDTO <br/>
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年8月13日 下午4:19:35
 * @author lcg
 */
public class ProdSkuOptionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer skuOptionId;

    private Integer skuId;

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
     * @return the skuId
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * @param skuId the skuId to set
     */
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
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
