package cn.xyz.com.ibatis.sqlmap.engine.execution.dialect;

/**
 * @author badqiu
 */
public class MySQLDialect extends Dialect {

    @Override
    public boolean supportsLimitOffset() {
        return true;
    }

    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
        if (offset > 0) {
            return sql + " limit ?, ?";
        } else {
            return sql + " limit ?";
        }
    }
}
