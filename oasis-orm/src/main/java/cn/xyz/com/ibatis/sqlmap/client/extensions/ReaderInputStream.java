// $Id: ReaderInputStream.java,v 1.1 2008-04-28 03:16:29 sunw Exp $
package cn.xyz.com.ibatis.sqlmap.client.extensions;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Exposes a <tt>Reader</tt> as an <tt>InputStream</tt>
 *
 * @author sunwei
 */
public class ReaderInputStream extends InputStream {
    private Reader reader;

    public ReaderInputStream(Reader reader) {
        this.reader = reader;
    }

    @Override
    public int read() throws IOException {
        return reader.read();
    }
}
