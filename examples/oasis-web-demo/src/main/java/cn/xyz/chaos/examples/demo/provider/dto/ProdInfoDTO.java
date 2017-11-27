package cn.xyz.chaos.examples.demo.provider.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * ProdInfoDTO <br/>
 * 产品信息DTO
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年8月13日 下午1:57:36
 * @author lcg
 */
public class ProdInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer prodId;

    private Integer comId;

    private String prodName;

    private String prodAlias;

    private Integer limitQuantity;

    private Integer weight;

    private Integer status;

    private Integer adderId;

    private Integer updaterId;

    private Date addTime;

    private Date updateTime;

    private byte[] prodPic;

    /**
     * @return the prodId
     */
    public Integer getProdId() {
        return prodId;
    }

    /**
     * @param prodId the prodId to set
     */
    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    /**
     * @return the comId
     */
    public Integer getComId() {
        return comId;
    }

    /**
     * @param comId the comId to set
     */
    public void setComId(Integer comId) {
        this.comId = comId;
    }

    /**
     * @return the prodName
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * @param prodName the prodName to set
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    /**
     * @return the prodAlias
     */
    public String getProdAlias() {
        return prodAlias;
    }

    /**
     * @param prodAlias the prodAlias to set
     */
    public void setProdAlias(String prodAlias) {
        this.prodAlias = prodAlias;
    }

    /**
     * @return the limitQuantity
     */
    public Integer getLimitQuantity() {
        return limitQuantity;
    }

    /**
     * @param limitQuantity the limitQuantity to set
     */
    public void setLimitQuantity(Integer limitQuantity) {
        this.limitQuantity = limitQuantity;
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

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the adderId
     */
    public Integer getAdderId() {
        return adderId;
    }

    /**
     * @param adderId the adderId to set
     */
    public void setAdderId(Integer adderId) {
        this.adderId = adderId;
    }

    /**
     * @return the updaterId
     */
    public Integer getUpdaterId() {
        return updaterId;
    }

    /**
     * @param updaterId the updaterId to set
     */
    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

    /**
     * @return the addTime
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime the addTime to set
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the prodPic
     */
    public byte[] getProdPic() {
        return prodPic;
    }

    /**
     * @param prodPic the prodPic to set
     */
    public void setProdPic(byte[] prodPic) {
        this.prodPic = prodPic;
    }

}
