package cn.xyz.chaos.examples.demo.provider.dto;

public class AttributeDTO extends BaseDTO {

    private Integer skuOptionId;

    private Integer skuId;

    private String skuName;

    private String optionName;

    private Integer weight;

    public Integer getSkuOptionId() {
        return skuOptionId;
    }

    public void setSkuOptionId(Integer skuOptionId) {
        this.skuOptionId = skuOptionId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

}
