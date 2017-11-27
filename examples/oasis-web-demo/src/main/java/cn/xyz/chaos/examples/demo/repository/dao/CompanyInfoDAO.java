package cn.xyz.chaos.examples.demo.repository.dao;

import java.util.List;

import cn.xyz.chaos.orm.mybatis.MyBatisRepository;

import cn.xyz.chaos.examples.demo.entity.CompanyInfo;
import cn.xyz.chaos.examples.demo.repository.mapper.CompanyInfoMapper;

@MyBatisRepository
public interface CompanyInfoDAO extends CompanyInfoMapper {

    public List<CompanyInfo> selectAll();
}
