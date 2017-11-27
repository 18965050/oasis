package cn.xyz.chaos.examples.demo.provider.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xyz.chaos.examples.demo.provider.ProdInfoProvider;
import cn.xyz.chaos.examples.demo.provider.dto.ProdInfoDTO;
import cn.xyz.chaos.examples.demo.repository.ProdInfoRepository;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

/**
 * ProdInfoProviderImpl <br/>
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年8月13日 下午3:16:44
 * @author lcg
 */
@Service
public class ProdInfoProviderImpl implements ProdInfoProvider {

    @Autowired
    private ProdInfoRepository prodInfoRepository;

    @Override
    public PageList<ProdInfoDTO> list(List<Integer> skuOptIds, PageBounds pageBounds) {
        return prodInfoRepository.list(skuOptIds, pageBounds);
    }

}
