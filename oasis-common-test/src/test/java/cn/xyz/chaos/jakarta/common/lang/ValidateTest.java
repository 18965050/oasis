package cn.xyz.chaos.jakarta.common.lang;

import org.apache.commons.lang3.Validate;
import org.junit.Test;

/**
 * {@link org.apache.commons.lang3.Validate}单元测试
 *
 * @author lvchenggang
 *
 */
public class ValidateTest {

    @Test(expected = Exception.class)
    public void testValidate() {
        String s1 = "aaa";
        String s2 = null;

        Validate.notNull(s1);
        Validate.notNull(s2, "参数不能为空");
        Validate.isTrue(3 < 7);
        Validate.notEmpty(new Integer[] { 1, 2 }, "数组不能为空");

    }
}
