package cn.xyz.chaos.examples.demo.provider;

import cn.xyz.chaos.examples.demo.provider.dto.AppraiseDTO;

import java.util.List;

/**
 * cn.xyz.chaos.examples.demo.provider
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2014/8/27 16:59.
 */
public interface AppraiseProvider {

    List<AppraiseDTO> getProdRelated(List<Integer> prodIds, int appraiseNum);

}
