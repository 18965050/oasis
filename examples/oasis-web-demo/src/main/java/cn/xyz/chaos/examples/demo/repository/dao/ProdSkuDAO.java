package cn.xyz.chaos.examples.demo.repository.dao;

import java.util.List;

import cn.xyz.chaos.orm.mybatis.MyBatisRepository;

import cn.xyz.chaos.examples.demo.entity.ProdSku;
import cn.xyz.chaos.examples.demo.entity.model.ProdSkuModel;
import cn.xyz.chaos.examples.demo.repository.mapper.ProdSkuMapper;

@MyBatisRepository
public interface ProdSkuDAO extends ProdSkuMapper {

    /**
     * 查询所有的SKU和选择项
     *
     * @return
     */
    List<ProdSkuModel> selectSkusAndOpts();

    public List<ProdSku> selectAll();
}
