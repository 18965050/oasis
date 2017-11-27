package cn.xyz.chaos.examples.demo.provider.dto;

import java.util.Date;

public class ProductDTO extends BaseDTO {

    private Integer prodId;

    private Integer comId;

    private String comName;

    private String prodName;

    private String prodAlias;

    private Integer limitQuantity;

    private Integer weight;

    private Integer status;

    private Integer adderId;

    private Integer updaterId;

    private Date addTime;

    private Date updateTime;

    // 图片路径
    private String picLoc;

    private byte[] prodPic;

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdAlias() {
        return prodAlias;
    }

    public void setProdAlias(String prodAlias) {
        this.prodAlias = prodAlias;
    }

    public Integer getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(Integer limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAdderId() {
        return adderId;
    }

    public void setAdderId(Integer adderId) {
        this.adderId = adderId;
    }

    public Integer getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public byte[] getProdPic() {
        return prodPic;
    }

    public void setProdPic(byte[] prodPic) {
        this.prodPic = prodPic;
    }

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
