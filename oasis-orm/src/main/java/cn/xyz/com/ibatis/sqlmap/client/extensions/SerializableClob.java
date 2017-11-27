// $Id: SerializableClob.java,v 1.1 2008-04-28 03:16:29 sunw Exp $
package cn.xyz.com.ibatis.sqlmap.client.extensions;

import java.io.*;
import java.sql.Clob;
import java.sql.SQLException;

/**
 * @author sunwei
 */
public class SerializableClob implements Serializable, Clob {
    /**
     *
     */
    private static final long serialVersionUID = -2420506265614875967L;
    private transient final Clob clob;

    public SerializableClob(Clob blob) {
        clob = blob;
    }

    /**
     * �շ����ˣ�Ϊ�˼���1.6
     *
     * @throws SQLException
     */
    @Override
    public void free() throws SQLException {
    }

    @Override
    public InputStream getAsciiStream() throws SQLException {
        return getWrappedClob().getAsciiStream();
    }

    @Override
    public Reader getCharacterStream() throws SQLException {
        return getWrappedClob().getCharacterStream();
    }

    /**
     * �շ����ˣ�Ϊ�˼���1.6
     *
     * @throws SQLException
     */
    @Override
    public Reader getCharacterStream(long pos, long length) throws SQLException {
        return null;
    }

    @Override
    public String getSubString(long pos, int length) throws SQLException {
        return getWrappedClob().getSubString(pos, length);
    }

    public Clob getWrappedClob() {
        if (clob == null) {
            throw new IllegalStateException("Clobs may not be accessed after serialization");
        } else {
            return clob;
        }
    }

    @Override
    public long length() throws SQLException {
        return getWrappedClob().length();
    }

    @Override
    public long position(Clob searchstr, long start) throws SQLException {
        return getWrappedClob().position(searchstr, start);
    }

    @Override
    public long position(String searchstr, long start) throws SQLException {
        return getWrappedClob().position(searchstr, start);
    }

    @Override
    public OutputStream setAsciiStream(long pos) throws SQLException {
        return getWrappedClob().setAsciiStream(pos);
    }

    @Override
    public Writer setCharacterStream(long pos) throws SQLException {
        return getWrappedClob().setCharacterStream(pos);
    }

    @Override
    public int setString(long pos, String str) throws SQLException {
        return getWrappedClob().setString(pos, str);
    }

    @Override
    public int setString(long pos, String str, int offset, int len) throws SQLException {
        return getWrappedClob().setString(pos, str, offset, len);
    }

    @Override
    public void truncate(long len) throws SQLException {
        getWrappedClob().truncate(len);
    }
}
