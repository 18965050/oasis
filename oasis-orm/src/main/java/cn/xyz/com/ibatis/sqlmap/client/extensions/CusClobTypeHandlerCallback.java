package cn.xyz.com.ibatis.sqlmap.client.extensions;

import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Types;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * @author sunwei TODO To change the template for this generated type comment go to Window - Preferences - Java - Code
 *         Style - Code Templates
 */
public class CusClobTypeHandlerCallback implements TypeHandlerCallback {
    public Object getResult(ResultGetter getter) throws SQLException {
        final Clob clob = getter.getClob();
        Clob newClob = null;
        if (clob != null) {
            newClob = new SerializableClob(clob);
        }
        return newClob;
    }

    public void setParameter(ParameterSetter setter, Object parameter) throws SQLException {
        if (parameter == null) {
            setter.setNull(Types.CLOB);
        } else {
            if (parameter instanceof SerializableClob) {
                parameter = ((SerializableClob) parameter).getWrappedClob();
            }
            if (parameter instanceof ClobImpl) {
                final Clob clob = (Clob) parameter;
                final Reader reader = clob.getCharacterStream();
                setter.setCharacterStream(reader, (int) clob.length());
            } else {
                setter.setClob((Clob) parameter);
            }
        }
    }

    public Object valueOf(String s) {
        return s;
    }
}
