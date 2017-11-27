package cn.xyz.chaos.examples.demo.provider.dto;

import java.io.Serializable;
import java.util.Date;

public class AppraiseDTO implements Serializable {
    private Integer appraiseId;

    private Integer userId;

    private Integer prodId;

    private String content;

    private Integer score;

    private Date addTime;

    private ProdInfoDTO prodInfoDTO;

    public ProdInfoDTO getProdInfoDTO() {
        return prodInfoDTO;
    }

    public void setProdInfoDTO(ProdInfoDTO prodInfoDTO) {
        this.prodInfoDTO = prodInfoDTO;
    }

    public Integer getAppraiseId() {
        return appraiseId;
    }

    public void setAppraiseId(Integer appraiseId) {
        this.appraiseId = appraiseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
