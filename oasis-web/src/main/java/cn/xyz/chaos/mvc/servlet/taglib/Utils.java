package cn.xyz.chaos.mvc.servlet.taglib;

/**
 * @author badqiu
 */
public class Utils {

    public static String BLOCK = "__jsp_override__";

    static String getOverrideVariableName(String name) {
        return BLOCK + name;
    }

}
