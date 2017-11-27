package cn.xyz.chaos.examples.demo.web.dvo;

import cn.xyz.chaos.examples.demo.provider.dto.AppraiseDTO;

/**
 * cn.xyz.chaos.examples.demo.web.dvo
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2014/8/28 17:09.
 */
public class AppraiseDVO extends AppraiseDTO {
    private ProdInfoDVO prodInfo;

    public ProdInfoDVO getProdInfo() {
        return prodInfo;
    }

    public void setProdInfo(ProdInfoDVO prodInfo) {
        this.prodInfo = prodInfo;
    }
}
