package cn.xyz.chaos.examples.demo.entity;

public class Product extends ProdInfo {
    // 保险公司名称
    private String comName;

    // 文件路径
    private String picLoc;

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getPicLoc() {
        return picLoc;
    }

    public void setPicLoc(String picLoc) {
        this.picLoc = picLoc;
    }
}
