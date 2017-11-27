package cn.xyz.chaos.orm.ibatis.mysql.dao;

import org.springframework.stereotype.Repository;

import cn.xyz.chaos.orm.ibatis.IbatisDAO;
import cn.xyz.chaos.orm.ibatis.mysql.entity.SsUser;
import cn.xyz.chaos.orm.ibatis.mysql.entity.SsUserCriteria;

@Repository
public class SsUserDAO extends IbatisDAO<SsUser, SsUserCriteria> {
}