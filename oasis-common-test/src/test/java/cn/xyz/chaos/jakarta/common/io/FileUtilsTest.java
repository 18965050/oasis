package cn.xyz.chaos.jakarta.common.io;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 * {@link org.apache.commons.io.FileUtils}单元测试
 *
 * @author lvchenggang
 *
 */
public class FileUtilsTest {

    /**
     * {@link org.apache.commons.io.FileUtils#copyFile(File, File)}, <br />
     * {@link org.apache.commons.io.FileUtils#copyFileToDirectory(File, File)}测试
     *
     * @throws Exception
     */
    @Test
    public void testCopy() throws Exception {
        String srcFileName = this.getClass().getResource("/jakarta/user.properties").getPath();
        File src = new File(srcFileName);
        File dest = new File("user.properties");
        File destDir = new File("/temp");
        dest.deleteOnExit();
        destDir.deleteOnExit();
        FileUtils.copyFile(src, dest);
        FileUtils.copyFileToDirectory(src, destDir);
    }

    /**
     * {@link org.apache.commons.io.FileUtils#cleanDirectory(File)},<br />
     * {@link org.apache.commons.io.FileUtils#deleteDirectory(File)}测试
     *
     * @throws Exception
     */
    @Test
    public void testDeleteRecursive() throws Exception {
        File destDir = new File("/temp");
        FileUtils.cleanDirectory(destDir);
        FileUtils.deleteDirectory(destDir);
    }

    /**
     * {@link org.apache.commons.io.FileUtils#touch(File)}测试
     *
     * @throws Exception
     */
    @Test
    public void testTouch() throws Exception {
        File f = new File("aaa.txt");
        f.deleteOnExit();
        FileUtils.touch(f);
    }

}
