package cn.xyz.chaos.common.utils;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * cn.xyz.chaos.common.utils
 *
 * 大部分方法 Copy from focustech
 */
public class IPUtils {

    /**
     * ipv4正则
     */
    private static final Pattern IPV4_PATTERN = Pattern.compile(
            "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");

    /**
     * 从请求中获取真实的IP地址<br>
     * X-Forwarded-For > Proxy-Client-IP > WL-Proxy-Client-IP > HTTP_CLIENT_IP > HTTP_X_FORWARDED_FOR > RemoteAddr
     *
     * @param request - http请求对象
     *
     * @return 取得真实的IP地址。
     */
    public static String getIP(HttpServletRequest request) {
        // 也可以先取一次X-Real-Ip
        String ip = request.getHeader("X-Forwarded-For");
        if (!isInValidIP(ip)) {
            ip = getIPFromXFF(ip);
        }
        if (isInValidIP(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isInValidIP(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isInValidIP(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (isInValidIP(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (isInValidIP(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 判断ip信息的有效性
     *
     * @param ip
     * @return
     */
    private static boolean isInValidIP(String ip) {
        return ip == null || ip.trim().equals("") || "unknown".equalsIgnoreCase(ip);
    }

    /**
     * 处理X-Forwarded-For信息,取第一个非unknown的有效的非内网ipIP地址<br/>
     *
     * @param xxf
     * @return
     */
    private static String getIPFromXFF(String xxf) {
        xxf = xxf.trim();
        if (xxf.indexOf(".") == -1) {
            return null;
        }
        String result = null;
        if (xxf.indexOf(",") != -1) {
            xxf = xxf.replace("'", "");
            String[] tempArr = xxf.split(",");
            String tempStr = null;
            for (int i = 0, len = tempArr.length; i < len; i++) {
                tempStr = tempArr[i].trim();
                if (!isIPV4(tempStr)) {
                    continue;
                }
                // 取非内网ip地址
                if (!isIANA(tempStr)) {
                    return tempStr;
                }
                // 如果都是内网IP那就取第一个
                if (i == 0) {
                    result = tempStr;
                }
            }
        } else if (isIPV4(xxf)) {
            result = xxf;
        }
        return result;
    }

    /**
     * 是否是IPV4地址。
     *
     * @param ip 形如192.168.0.1的ip字符串
     * @return 是v4ip地址返回 true ，否则返回 false
     */
    public static boolean isIPV4(String ip) {
        return isNotBlank(ip) && IPV4_PATTERN.matcher(ip).matches();
    }

    /**
     * 是否是保留地址。
     *
     * <p>
     * 10.0.0.0(184549375l)－10.255.255.255(184549375l)
     * </p>
     * <p>
     * 172.16.0.0(2886729728l)－172.31.255.255(2887778303l)
     * </p>
     * <p>
     * 192.168.0.0(3232235520l)－192.168.255.255(3232301055l)
     * </p>
     *
     * @param ip
     * @return
     */
    public static boolean isIANA(String ip) {
        if (isBlank(ip)) {
            return false;
        }
        long temp = convertIpToDecimal(ip);
        if ((temp >= 167772160l && temp <= 184549375l) || (temp >= 2886729728l && temp <= 2887778303l)
                || (temp >= 3232235520l && temp <= 3232301055l)) {
            return true;
        }
        return false;
    }

    /**
     * 将ip值转为数值,如果ip不合法，则返回-1。
     *
     * @param ip - 形如192.168.0.1的ip字符串
     * @return - 转换后的整形IP值
     */
    public static long convertIpToDecimal(final String ip) {
        long iIp = 0;
        if (isIPV4(ip)) {
            int index = 0;
            String[] strArray = ip.split("\\.");
            for (int i = 0; i < strArray.length; i++) {
                iIp = iIp | Integer.parseInt(strArray[index++]);
                if (index != 4) {
                    iIp = iIp << 8;
                }
            }
        } else {
            iIp = -1;
        }
        return iIp;
    }

    private static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

}
