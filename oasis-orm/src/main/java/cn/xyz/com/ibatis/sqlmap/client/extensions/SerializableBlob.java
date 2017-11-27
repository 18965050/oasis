package cn.xyz.com.ibatis.sqlmap.client.extensions;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * @author sunwei TODO To change the template for this generated type comment go to Window - Preferences - Java - Code
 *         Style - Code Templates
 */
public class SerializableBlob implements Serializable, Blob {
    /**
     *
     */
    private static final long serialVersionUID = -6266335326853623441L;
    /**
     *
     */
    private transient final Blob blob;

    /**
     * @param blob
     */
    public SerializableBlob(Blob blob) {
        this.blob = blob;
    }

    /**
     * �շ����ˣ�Ϊ�˼���1.6
     *
     * @throws SQLException
     */
    public void free() throws SQLException {
        getWrappedBlob().free();
    }

    public InputStream getBinaryStream() throws SQLException {
        return getWrappedBlob().getBinaryStream();
    }

    /**
     * �շ����ˣ�Ϊ�˼���1.6
     *
     * @throws SQLException
     */
    public InputStream getBinaryStream(long pos, long length) throws SQLException {
        return getWrappedBlob().getBinaryStream(pos, length);
    }

    public byte[] getBytes(long pos, int length) throws SQLException {
        return getWrappedBlob().getBytes(pos, length);
    }

    public Blob getWrappedBlob() {
        if (blob == null) {
            throw new IllegalStateException("Blobs may not be accessed after serialization");
        } else {
            return blob;
        }
    }

    public long length() throws SQLException {
        return getWrappedBlob().length();
    }

    public long position(Blob pattern, long start) throws SQLException {
        return getWrappedBlob().position(pattern, start);
    }

    public long position(byte[] pattern, long start) throws SQLException {
        return getWrappedBlob().position(pattern, start);
    }

    public OutputStream setBinaryStream(long pos) throws SQLException {
        return getWrappedBlob().setBinaryStream(pos);
    }

    public int setBytes(long pos, byte[] bytes) throws SQLException {
        return getWrappedBlob().setBytes(pos, bytes);
    }

    public int setBytes(long pos, byte[] bytes, int offset, int len) throws SQLException {
        return getWrappedBlob().setBytes(pos, bytes, offset, len);
    }

    public void truncate(long len) throws SQLException {
        getWrappedBlob().truncate(len);
    }
}
