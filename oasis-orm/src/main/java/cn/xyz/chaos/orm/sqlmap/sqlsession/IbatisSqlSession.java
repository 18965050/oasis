package cn.xyz.chaos.orm.sqlmap.sqlsession;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

@SuppressWarnings("deprecation")
public class IbatisSqlSession implements SqlSession {
    private SqlMapClientTemplate sqlMapClientTemplate;

    public IbatisSqlSession(SqlMapClientTemplate sqlMapClientTemplate) {
        this.sqlMapClientTemplate = sqlMapClientTemplate;
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) sqlMapClientTemplate.queryForObject(statement, parameter);
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        return sqlMapClientTemplate.queryForList(statement, parameter);
    }

    @Override
    public int insert(String statement, Object parameter) {
        return sqlMapClientTemplate.insert(statement, parameter) == null ? 0 : 1;
    }

    @Override
    public int update(String statement, Object parameter) {
        return sqlMapClientTemplate.update(statement, parameter);
    }

    @Override
    public int delete(String statement, Object parameter) {
        return sqlMapClientTemplate.delete(statement, parameter);
    }

}
