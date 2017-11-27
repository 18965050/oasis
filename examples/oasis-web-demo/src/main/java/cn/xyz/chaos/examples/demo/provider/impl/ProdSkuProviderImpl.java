package cn.xyz.chaos.examples.demo.provider.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xyz.chaos.examples.demo.provider.ProdSkuProvider;
import cn.xyz.chaos.examples.demo.provider.dto.ProdSkuDTO;
import cn.xyz.chaos.examples.demo.repository.ProdSkuRepository;

/**
 * ProdSkuProviderImpl <br/>
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年8月13日 下午4:35:15
 * @author lcg
 */
@Service
public class ProdSkuProviderImpl implements ProdSkuProvider {

    @Autowired
    private ProdSkuRepository prodSkuRepository;

    @Override
    public Collection<ProdSkuDTO> getAllSkusWithOpts() {
        return prodSkuRepository.getAllSkusWithOpts();
    }

}
