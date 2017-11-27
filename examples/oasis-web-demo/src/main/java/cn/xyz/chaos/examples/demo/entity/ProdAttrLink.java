package cn.xyz.chaos.examples.demo.entity;

public class ProdAttrLink extends ProdSkuLink {

    private String prodName;

    private String attrName;

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
