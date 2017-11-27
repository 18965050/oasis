package cn.xyz.com.ibatis.sqlmap.client.extensions;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Types;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * @author sunwei TODO To change the template for this generated type comment go to Window - Preferences - Java - Code
 *         Style - Code Templates
 */
final public class CusBlobTypeHandlerCallback implements TypeHandlerCallback {
    /**
     *
     */
    public Object getResult(ResultGetter getter) throws SQLException {
        final Blob blob = getter.getBlob();
        Blob newBlob = null;
        if (blob != null) {
            newBlob = new SerializableBlob(blob);
        }
        return newBlob;
    }

    /**
     *
     */
    public void setParameter(ParameterSetter setter, Object parameter) throws SQLException {
        if (null != parameter) {
            if (parameter instanceof SerializableBlob) {
                parameter = ((SerializableBlob) parameter).getWrappedBlob();
            }
            if (parameter instanceof BlobImpl) {
                final Blob blob = (Blob) parameter;
                final InputStream input = blob.getBinaryStream();
                setter.setBinaryStream(input, (int) blob.length());
            } else {
                setter.setBlob((Blob) (parameter));
            }
        } else {
            setter.setNull(Types.BLOB);
        }
    }

    public Object valueOf(String s) {
        return s;
    }
}
