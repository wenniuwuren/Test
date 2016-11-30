package test.java;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 单元测试基类
 * <li>单元测试需要的配置文件包含到该基础测试类中{@code ContextConfiguration}
 * <li>启用不同环境的配置{@code ActiveProfiles}
 * <p>
 * <strong>使用：</strong>实际的测试类只需继承该基础类并在测试方法上加@Test注解，即可进行单元测试
 *
 * @author hzyurui
 */
@ActiveProfiles(profiles = {"config/dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test.xml"})
public class BaseTest {

    @Test
    public void baseTest() {
        System.out.println("如果看到这句话，说明单元测试环境搭建成功");
    }
}