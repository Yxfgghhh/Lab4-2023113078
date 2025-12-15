package net.geekhour;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Solution 类的单元测试（基于 JUnit 4.13.2）
 * 等价类划分原则：覆盖有效等价类、边界等价类
 */
public class SolutionTest {

    // 创建 Solution 实例（所有测试方法复用）
    private final Solution solution = new Solution();

    /**
     * 测试1：前导零不同但数值相同（有效等价类）
     */
    @Test
    public void testCompareVersion_LeadingZeroEqual() {
        String version1 = "1.01";
        String version2 = "1.001";
        int result = solution.compareVersion(version1, version2);
        assertEquals("前导零不同但数值相同，应返回0", 0, result);
    }

    /**
     * 测试2：版本号长度不同，短的补0后相等（有效等价类）
     */
    @Test
    public void testCompareVersion_DifferentLengthEqual() {
        String version1 = "1.0";
        String version2 = "1.0.0";
        int result = solution.compareVersion(version1, version2);
        assertEquals("短版本补0后相等，应返回0", 0, result);
    }

    /**
     * 测试3：version1 大于 version2（有效等价类）
     */
    @Test
    public void testCompareVersion_Version1Greater() {
        // 场景1：后位更大
        assertEquals(1, solution.compareVersion("1.2.3", "1.2.1"));
        // 场景2：长度不同，长的后位非0
        assertEquals(1, solution.compareVersion("1.0.1", "1.0"));
        // 场景3：无小数点，纯数字更大
        assertEquals(1, solution.compareVersion("100", "99"));
    }

    /**
     * 测试4：version1 小于 version2（有效等价类）
     */
    @Test
    public void testCompareVersion_Version1Less() {
        // 场景1：首位更小
        assertEquals(-1, solution.compareVersion("0.1", "1.1"));
        // 场景2：中间位更小
        assertEquals(-1, solution.compareVersion("2.3.4", "2.4.0"));
    }

    /**
     * 测试5：边界值 - 全0版本号（边界等价类）
     */
    @Test
    public void testCompareVersion_AllZero() {
        assertEquals(0, solution.compareVersion("0.0.0", "0"));
        assertEquals(0, solution.compareVersion("0", "0.0"));
    }

    /**
     * 测试6：边界值 - 超大前导零（边界等价类）
     */
    @Test
    public void testCompareVersion_LargeLeadingZero() {
        assertEquals(0, solution.compareVersion("2.0000000", "2.0"));
        assertEquals(1, solution.compareVersion("3.0001", "3.00009"));
    }
}
