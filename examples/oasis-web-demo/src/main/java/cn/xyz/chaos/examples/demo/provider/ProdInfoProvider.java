package cn.xyz.chaos.examples.demo.provider;

import java.util.List;

import cn.xyz.chaos.examples.demo.provider.dto.ProdInfoDTO;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

/**
 * ProdInfoProvider <br/>
 * 产品信息服务接口
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年8月13日 下午2:42:51
 * @author lcg
 */
public interface ProdInfoProvider {

    /**
     * 分页列表
     *
     * @param skuOptIds TODO
     * @param pageBounds
     * @return
     */
    PageList<ProdInfoDTO> list(List<Integer> skuOptIds, PageBounds pageBounds);

}
