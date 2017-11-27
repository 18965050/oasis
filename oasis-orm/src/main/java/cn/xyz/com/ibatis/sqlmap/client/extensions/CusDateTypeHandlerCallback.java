package cn.xyz.com.ibatis.sqlmap.client.extensions;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * @author sunwei TODO To change the template for this generated type comment go to Window - Preferences - Java - Code
 *         Style - Code Templates
 */
public class CusDateTypeHandlerCallback implements TypeHandlerCallback {
    public Object getResult(ResultGetter getter) throws SQLException {
        final Timestamp timeStamp = getter.getTimestamp();
        Date date = null;
        if (timeStamp != null) {
            date = new Date(timeStamp.getTime());
        }
        return date;
    }

    public void setParameter(ParameterSetter setter, Object parameter) throws SQLException {
        if (null == parameter) {
            setter.setNull(Types.TIMESTAMP);
        } else {
            if (parameter instanceof Date) {
                setter.setTimestamp(new Timestamp(((Date) parameter).getTime()));
            } else {
                throw new SQLException("parameter type is invalid!");
            }
        }
    }

    public Object valueOf(String s) {
        return s;
    }
}
