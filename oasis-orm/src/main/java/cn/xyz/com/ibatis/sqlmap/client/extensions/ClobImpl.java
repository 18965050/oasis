// $Id: ClobImpl.java,v 1.1 2008-04-28 03:16:29 sunw Exp $
package cn.xyz.com.ibatis.sqlmap.client.extensions;

import java.io.*;
import java.sql.Clob;
import java.sql.SQLException;

/**
 * A dummy implementation of <tt>java.sql.Clob</tt> that may be used to insert new data into a CLOB.
 *
 * @author Gavin King
 */
public class ClobImpl implements Clob {
    private static void excep() {
        throw new UnsupportedOperationException("Blob may not be manipulated from creating session");
    }

    private Reader reader;
    private int length;

    private boolean needsReset = false;

    public ClobImpl(Reader reader, int length) {
        this.reader = reader;
        this.length = length;
    }

    public ClobImpl(String string) {
        reader = new StringReader(string);
        length = string.length();
    }

    /**
     * �շ����ˣ�Ϊ�˼���1.6
     *
     * @throws SQLException
     */
    public void free() throws SQLException {
    }

    /**
     * @see java.sql.Clob#getAsciiStream()
     */
    public InputStream getAsciiStream() throws SQLException {
        try {
            if (needsReset) {
                reader.reset();
            }
        } catch (IOException ioe) {
            throw new SQLException("could not reset reader");
        }
        needsReset = true;
        return new ReaderInputStream(reader);
    }

    /**
     * @see java.sql.Clob#getCharacterStream()
     */
    public Reader getCharacterStream() throws SQLException {
        try {
            if (needsReset) {
                reader.reset();
            }
        } catch (IOException ioe) {
            throw new SQLException("could not reset reader");
        }
        needsReset = true;
        return reader;
    }

    /**
     * �շ����ˣ�Ϊ�˼���1.6
     *
     * @throws SQLException
     */
    public Reader getCharacterStream(long pos, long length) throws SQLException {
        return null;
    }

    /**
     * @see java.sql.Clob#getSubString(long, int)
     */
    public String getSubString(long pos, int len) throws SQLException {
        excep();
        return null;
    }

    /**
     * @see java.sql.Clob#length()
     */
    public long length() throws SQLException {
        return length;
    }

    /**
     * @see java.sql.Clob#position(Clob, long)
     */
    public long position(Clob colb, long pos) throws SQLException {
        excep();
        return 0;
    }

    /**
     * @see java.sql.Clob#position(String, long)
     */
    public long position(String string, long pos) throws SQLException {
        excep();
        return 0;
    }

    /**
     * @see java.sql.Clob#setAsciiStream(long)
     */
    public OutputStream setAsciiStream(long pos) throws SQLException {
        excep();
        return null;
    }

    /**
     * @see java.sql.Clob#setCharacterStream(long)
     */
    public Writer setCharacterStream(long pos) throws SQLException {
        excep();
        return null;
    }

    /**
     * @see java.sql.Clob#setString(long, String)
     */
    public int setString(long pos, String string) throws SQLException {
        excep();
        return 0;
    }

    /**
     * @see java.sql.Clob#setString(long, String, int, int)
     */
    public int setString(long pos, String string, int i, int j) throws SQLException {
        excep();
        return 0;
    }

    /**
     * @see java.sql.Clob#truncate(long)
     */
    public void truncate(long pos) throws SQLException {
        excep();
    }
}
