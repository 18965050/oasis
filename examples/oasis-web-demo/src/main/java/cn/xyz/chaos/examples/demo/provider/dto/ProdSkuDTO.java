package cn.xyz.chaos.examples.demo.provider.dto;

import java.io.Serializable;
import java.util.List;

/**
 * ProdSkuDTO <br/>
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年8月13日 下午4:17:44
 * @author lcg
 */
public class ProdSkuDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer skuId;

    private String skuName;

    private List<ProdSkuOptionDTO> skuOptions;

    private ProdSkuOptionDTO selectedOpt;

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
     * @return the skuName
     */
    public String getSkuName() {
        return skuName;
    }

    /**
     * @param skuName the skuName to set
     */
    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    /**
     * @return the skuOptions
     */
    public List<ProdSkuOptionDTO> getSkuOptions() {
        return skuOptions;
    }

    /**
     * @param skuOptions the skuOptions to set
     */
    public void setSkuOptions(List<ProdSkuOptionDTO> skuOptions) {
        this.skuOptions = skuOptions;
    }

    /**
     * @return the selectedOpt
     */
    public ProdSkuOptionDTO getSelectedOpt() {
        return selectedOpt;
    }

    /**
     * @param selectedOpt the selectedOpt to set
     */
    public void setSelectedOpt(ProdSkuOptionDTO selectedOpt) {
        this.selectedOpt = selectedOpt;
    }

}
