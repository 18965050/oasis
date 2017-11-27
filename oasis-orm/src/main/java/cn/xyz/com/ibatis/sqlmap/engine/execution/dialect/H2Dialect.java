package cn.xyz.com.ibatis.sqlmap.engine.execution.dialect;

/**
 * A dialect compatible with the H2 database.
 */
public class H2Dialect extends Dialect {

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
            return sql + " limit ? offset ?";
        } else {
            return sql + " limit ?";
        }
    }

    @Override
    public Object[] getParameters(int offset, int limit) {
        if (offset > 0) {
            return new Object[] { limit, offset };
        } else {
            return new Object[] { limit };
        }
    }

    @Override
    public String[] getPlaceholderNames(int offset, int limit) {
        if (offset > 0) {
            return new String[] { PLACEHOLDER_LIMIT, PLACEHOLDER_OFFSET };
        } else {
            return new String[] { PLACEHOLDER_LIMIT };
        }
    }
}
