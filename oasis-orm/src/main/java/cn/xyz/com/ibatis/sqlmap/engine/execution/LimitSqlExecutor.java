package cn.xyz.com.ibatis.sqlmap.engine.execution;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cn.xyz.com.ibatis.sqlmap.engine.execution.dialect.Dialect;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.mapping.parameter.ParameterMap;
import com.ibatis.sqlmap.engine.mapping.parameter.ParameterMapping;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.scope.StatementScope;
import com.ibatis.sqlmap.engine.type.IntegerTypeHandler;

/**
 * 结合dialect为ibatis提供使用数据库原生的分页 请查看:http://pengfeng.javaeye.com/blog/200772
 *
 * @author badqiu
 *
 */
public class LimitSqlExecutor extends SqlExecutor {

    private Dialect dialect;

    private boolean enableLimit = true;

    public Dialect getDialect() {
        return dialect;
    }

    public void setDialect(Dialect dialect) {
        if (dialect != null) {
            System.out.println("[iBATIS] set ibatis LimitSqlExecutor.dialect = " + dialect.getClass().getName());
        }
        this.dialect = dialect;
    }

    public boolean isEnableLimit() {
        return enableLimit;
    }

    public void setEnableLimit(boolean enableLimit) {
        this.enableLimit = enableLimit;
    }

    @Override
    public void executeQuery(StatementScope statementScope, Connection conn, String sql, Object[] parameters,
            int skipResults, int maxResults, RowHandlerCallback callback) throws SQLException {
        this._executeQuery(statementScope, conn, sql, parameters, skipResults, maxResults, callback, false);
    }

    @Override
    public void executeQueryProcedure(StatementScope statementScope, Connection conn, String sql, Object[] parameters,
            int skipResults, int maxResults, RowHandlerCallback callback) throws SQLException {
        this._executeQuery(statementScope, conn, sql, parameters, skipResults, maxResults, callback, true);
    }

    public void _executeQuery(StatementScope statementScope, Connection conn, String sql, Object[] parameters,
            int skipResults, int maxResults, RowHandlerCallback callback, boolean procedure) throws SQLException {
        if (supportsLimit() && (skipResults != NO_SKIPPED_RESULTS || maxResults != NO_MAXIMUM_RESULTS)) {
            sql = sql.trim();
            if (dialect.supportsLimitOffset()) {
                sql = dialect.getLimitString(sql, skipResults, maxResults);
                // 追加动态参数
                parameters = appendParameters(parameters, skipResults, maxResults);
                appendMapping(statementScope, skipResults, maxResults);
                skipResults = NO_SKIPPED_RESULTS;
            } else {
                sql = dialect.getLimitString(sql, 0, maxResults);
                // 追加动态参数
                parameters = appendParameters(parameters, 0, maxResults);
                appendMapping(statementScope, 0, maxResults);
            }
            maxResults = NO_MAXIMUM_RESULTS;
        }
        if (procedure) {
            super.executeQueryProcedure(statementScope, conn, sql, parameters, skipResults, maxResults, callback);
        } else {
            super.executeQuery(statementScope, conn, sql, parameters, skipResults, maxResults, callback);
        }
    }

    public boolean supportsLimit() {
        if (enableLimit && dialect != null) {
            return dialect.supportsLimit();
        }
        return false;
    }

    private Object[] appendParameters(Object[] parameters, int skipResults, int maxResults) {
        Object[] pars = dialect.getParameters(skipResults, maxResults);
        List<Object> list = new ArrayList<Object>(Arrays.asList(parameters));
        Collections.addAll(list, pars);
        return list.toArray(new Object[list.size()]);
    }

    private void appendMapping(StatementScope statementScope, int skipResults, int maxResults) {
        ParameterMap map = statementScope.getParameterMap();
        ParameterMapping[] mappings = map.getParameterMappings();
        List<ParameterMapping> mappingList = new ArrayList<ParameterMapping>(Arrays.asList(mappings));
        for (String name : dialect.getPlaceholderNames(skipResults, maxResults)) {
            mappingList.add(newParameterMapping(name));
        }
        map.setParameterMappingList(mappingList);
    }

    private ParameterMapping newParameterMapping(String propertyName) {
        ParameterMapping mapping = new ParameterMapping();
        mapping.setJavaType(Integer.class);
        mapping.setPropertyName(propertyName);
        mapping.setTypeHandler(new IntegerTypeHandler());
        return mapping;
    }

}
