package cn.xyz.chaos.common.ipseeker;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 由于IPSeeker使用RandomAccessFile读取文件，而jar包中只能使用inputStream读取, 故先将jar保中dat文件保存到磁盘，在读取为file便于构造RandomAccessFile
 *
 * @see http://www.coderli.com/jar-classloader
 * @see http://hxraid.iteye.com/blog/483115
 */
public class IPDataUtil {
    private static Logger log = LoggerFactory.getLogger(IPDataUtil.class);
    private final static String IP_DATA_PATH_SRC = "/ipdata/qqwry.dat";
    private final static String IP_DATA_PATH_DES = System.getProperty("user.home") + "/ipdata/"
            + System.currentTimeMillis() + "/qqwry.dat";
    private final static String IP_DATA_PATH_CONFIG = "config.ipdata.path";

    static {
        if (System.getProperty(IP_DATA_PATH_CONFIG) == null) {
            unzip();
        }
    }

    public static String getIPDataPath() {
        String configPath = System.getProperty(IP_DATA_PATH_CONFIG);
        if (configPath != null) {
            return configPath;
        } else {
            return IP_DATA_PATH_DES;
        }
    }

    private static void unzip() {
        InputStream in = IPDataUtil.class.getClass().getResourceAsStream(IP_DATA_PATH_SRC);
        OutputStream out = null;
        try {
            File file = new File(IP_DATA_PATH_DES);
            if (file.exists()) {
                file.delete();
            }
            if (file.getParentFile() == null || !file.getParentFile().exists()) {
                boolean mkSuccess = file.getParentFile().mkdirs();
                if (!mkSuccess) {
                    log.error("Failed to create directory");
                    throw new FileNotFoundException(IP_DATA_PATH_DES);
                }
            }
            out = new FileOutputStream(file);
            copy(in, out);
            log.info("Copy qqwry.dat to :" + IP_DATA_PATH_DES);
        } catch (IOException e) {
            log.error(IP_DATA_PATH_DES, e);
        } finally {
            closeQuietly(in);
            closeQuietly(out);
        }
    }

    /**
     * @see org.apache.commons.io.IOUtils
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
    private static final int EOF = -1;

    private static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }

    private static int copy(InputStream input, OutputStream output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    private static long copyLarge(InputStream input, OutputStream output) throws IOException {
        return copyLarge(input, output, new byte[DEFAULT_BUFFER_SIZE]);
    }

    private static long copyLarge(InputStream input, OutputStream output, byte[] buffer) throws IOException {
        long count = 0;
        int n = 0;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    public static void main(String[] args) {
        System.setProperty(IP_DATA_PATH_CONFIG, "x:/qqwry.dat");
        System.out.println(IPSeeker.getInstance().getArea("58.240.26.203"));
        System.out.println(IP_DATA_PATH_DES);
    }
}
