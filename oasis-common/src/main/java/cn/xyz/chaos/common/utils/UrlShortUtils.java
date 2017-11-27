package cn.xyz.chaos.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by lcg on 2015/6/29.
 */
public class UrlShortUtils {
    /** 加密字符串前的混合KEY */
    static String MIXED_WORD = "urlshort.xyz.cn";

    /* 要使用生成URL的字符 */
    static String[] CHARS_TABLE = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
            "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z" };

    /**
     * 短网址生成
     *
     * @param url
     * @return 短网址编码（6位字符）
     */
    public static String shorter(String url) {
        return shorters(url)[0];
    }

    public static String[] shorters(String url) {
        String hex = DigestUtils.md5Hex(MIXED_WORD + url);
        String[] shorts = new String[4];
        for (int i = 0; i < 4; i++) {
            StringBuilder out = new StringBuilder(6);
            String subHex = hex.substring(i * 8, (i + 1) * 8);
            long idx = Long.valueOf("3FFFFFFF", 16) & Long.valueOf(subHex, 16);
            for (int k = 0; k < 6; k++) {
                int index = (int) (Long.valueOf("0000003D", 16) & idx);
                out.append(CHARS_TABLE[index]);
                idx = idx >> 5;
            }
            shorts[i] = out.toString();
        }
        return shorts;
    }
}
