package cn.xyz.chaos.orm.ibatis.h2.dao;

import org.springframework.stereotype.Repository;

import cn.xyz.chaos.orm.ibatis.IbatisDAO;
import cn.xyz.chaos.orm.ibatis.h2.entity.SsUser;
import cn.xyz.chaos.orm.ibatis.h2.entity.SsUserCriteria;

@Repository
public class SsUserDAO extends IbatisDAO<SsUser, SsUserCriteria> {
}