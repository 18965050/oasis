package cn.xyz.chaos.examples.demo.provider.dto;

public class ProdAttrLinkDTO extends BaseDTO {

    private Integer skuLinkId;

    private Integer prodId;

    private String prodName;

    private Integer skuOptionId;

    private String attrName;

    public Integer getSkuLinkId() {
        return skuLinkId;
    }

    public void setSkuLinkId(Integer skuLinkId) {
        this.skuLinkId = skuLinkId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getSkuOptionId() {
        return skuOptionId;
    }

    public void setSkuOptionId(Integer skuOptionId) {
        this.skuOptionId = skuOptionId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

}
