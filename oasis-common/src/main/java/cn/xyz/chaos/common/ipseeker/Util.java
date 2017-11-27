/*
 * LumaQQ - Java QQ Client
 *
 * Copyright (C) 2004 luma <stubma@163.com>
 *                    notXX
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package cn.xyz.chaos.common.ipseeker;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 工具类，提供一些方便的方法，有些主要是用于调试用途，有些不是
 *
 * @author luma
 * @author notXX
 */
public class Util {
    // Log
    private static Logger log = LoggerFactory.getLogger(Util.class);

    /**
     * @param ip ip的字节数组形式
     * @return 字符串形式的ip
     */
    public static String getIpStringFromBytes(byte[] ip) {
        StringBuilder sb = new StringBuilder();
        sb.delete(0, sb.length());
        sb.append(ip[0] & 0xFF);
        sb.append('.');
        sb.append(ip[1] & 0xFF);
        sb.append('.');
        sb.append(ip[2] & 0xFF);
        sb.append('.');
        sb.append(ip[3] & 0xFF);
        return sb.toString();
    }

    /**
     * 从ip的字符串形式得到字节数组形式
     *
     * @param ip 字符串形式的ip
     * @return 字节数组形式的ip
     */
    public static byte[] getIpByteArrayFromString(String ip) {
        byte[] ret = new byte[4];
        StringTokenizer st = new StringTokenizer(ip, ".");
        try {
            ret[0] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
            ret[1] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
            ret[2] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
            ret[3] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ret;
    }

    /**
     * 判断IP是否相等
     *
     * @param ip1 IP的字节数组形式
     * @param ip2 IP的字节数组形式
     * @return true如果两个IP相等
     */
    public static boolean isIpEquals(byte[] ip1, byte[] ip2) {
        return (ip1[0] == ip2[0] && ip1[1] == ip2[1] && ip1[2] == ip2[2] && ip1[3] == ip2[3]);
    }

    /**
     * 根据某种编码方式将字节数组转换成字符串
     *
     * @param b 字节数组
     * @param offset 要转换的起始位置
     * @param len 要转换的长度
     * @param encoding 编码方式
     * @return 如果encoding不支持，返回一个缺省编码的字符串
     */
    public static String getString(byte[] b, int offset, int len, String encoding) {
        try {
            return new String(b, offset, len, encoding);
        } catch (UnsupportedEncodingException e) {
            return new String(b, offset, len);
        }
    }

}
