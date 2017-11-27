package cn.xyz.chaos.mvc.shiro.session;

import org.springframework.util.SerializationUtils;

import java.io.*;
import java.util.Arrays;

/**
 * 序列化：对String类型数据直接调用getBytes()，其它情况使用jdk序列化 Created by lcg on 14/11/18 018.
 */
public abstract class SerialUtils {
    // 序列化固定头
    private static final byte[] STREAM_MAGIC_BYTES = new byte[] { (byte) (ObjectStreamConstants.STREAM_MAGIC >>> 8),
            (byte) (ObjectStreamConstants.STREAM_MAGIC) };

    public static byte[] serialize(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof String) {
            return ((String) object).getBytes();
        } else {
            return SerializationUtils.serialize(object);
        }
    }

    public static Object deserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        byte[] header = Arrays.copyOf(bytes, Math.min(bytes.length, 2));
        if (Arrays.equals(header, STREAM_MAGIC_BYTES)) {
            return SerializationUtils.deserialize(bytes);
        } else {
            return new String(bytes);
        }
    }

}
