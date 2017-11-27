package cn.xyz.com.ibatis.sqlmap.engine.execution.dialect;

/**
 * @author badqiu
 */
public class OracleDialect extends Dialect {

    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public boolean supportsLimitOffset() {
        return true;
    }

    @Override
    public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
        sql = sql.trim();
        boolean isForUpdate = false;
        if (sql.toLowerCase().endsWith(" for update")) {
            sql = sql.substring(0, sql.length() - 11);
            isForUpdate = true;
        }

        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
        if (offset > 0) {
            pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
        } else {
            pagingSelect.append("select * from ( ");
        }
        pagingSelect.append(sql);
        if (offset > 0) {
            pagingSelect.append(" ) row_ where rownum <= ?) where rownum_ > ?");
        } else {
            pagingSelect.append(" ) where rownum <= ?");
        }

        if (isForUpdate) {
            pagingSelect.append(" for update");
        }

        return pagingSelect.toString();
    }

    @Override
    public Object[] getParameters(int offset, int limit) {
        if (offset > 0) {
            return new Object[] { offset + limit, offset };
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
