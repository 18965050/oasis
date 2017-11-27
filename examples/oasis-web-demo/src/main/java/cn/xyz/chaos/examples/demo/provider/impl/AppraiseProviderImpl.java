package cn.xyz.chaos.examples.demo.provider.impl;

import cn.xyz.chaos.examples.demo.provider.AppraiseProvider;
import cn.xyz.chaos.examples.demo.provider.dto.AppraiseDTO;
import cn.xyz.chaos.examples.demo.repository.AppraiseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * cn.xyz.chaos.examples.demo.provider.impl
 *
 * @author lcg Created by lcg on 2014/8/28 10:30.
 * @version 1.0.0
 */
@Service
public class AppraiseProviderImpl implements AppraiseProvider {

    @Autowired
    private AppraiseRepository appraiseRepository;

    @Override
    public List<AppraiseDTO> getProdRelated(List<Integer> prodIds, int appraiseNum) {
        return appraiseRepository.getProdRelated(prodIds, appraiseNum);
    }
}
