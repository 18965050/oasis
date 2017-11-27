package cn.xyz.chaos.examples.demo.repository.dao;

import cn.xyz.chaos.examples.demo.repository.mapper.UserInfoMapper;

import cn.xyz.chaos.orm.mybatis.MyBatisRepository;

@MyBatisRepository
public interface UserInfoDAO extends UserInfoMapper {
}