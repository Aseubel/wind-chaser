package com.aseubel.designpattern;

import com.aseubel.designpattern.company.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aseubel
 * @date 2025/6/19 上午11:21
 */
@SpringBootTest
public class CompanyTest {

    private CompanyRecruit recruit;
    private Javaer javaer;
    private PythonGuy pythonGuy;
    private Consumer<AbstractDeveloper> mockInterviewer;
    private AtomicInteger interviewCount;

    @BeforeEach
    void setUp() {
        recruit = new CompanyRecruit();
        javaer = new Javaer("张三", Major.BACK_END_DEVELOPMENT, 25);
        pythonGuy = new PythonGuy("李四", Major.BACK_END_DEVELOPMENT, 30);
        interviewCount = new AtomicInteger(0);
        mockInterviewer = developer -> {
            interviewCount.incrementAndGet();
            developer.showTime();
        };
    }

    @Test
    void testRecruitInitialStatus() {
        // 测试初始状态
        String overview = recruit.recruitOverView();
        assertTrue(overview.contains("NO_START"));
        assertTrue(overview.contains("收到简历数量：0"));
        assertTrue(overview.contains("进入面试者数量：0"));
    }

    @Test
    void testStartRecruitWithoutConsumer() {
        // 测试不带面试官的招聘启动
        recruit.start("Java开发招聘", Major.BACK_END_DEVELOPMENT);
        String overview = recruit.recruitOverView();
        assertTrue(overview.contains("NEW_START"));
        assertTrue(overview.contains("Java开发招聘"));
        assertTrue(overview.contains("后端开发"));
    }

    @Test
    void testStartRecruitWithConsumer() {
        // 测试带面试官的招聘启动
        recruit.start(mockInterviewer, "Python开发招聘", Major.BACK_END_DEVELOPMENT);
        String overview = recruit.recruitOverView();
        assertTrue(overview.contains("WAITING_FOR_RESUME"));
        assertTrue(overview.contains("Python开发招聘"));
    }

    @Test
    void testDuplicateStart() {
        // 测试重复启动招聘
        recruit.start("Java开发招聘", Major.BACK_END_DEVELOPMENT);
        assertThrows(IllegalStateException.class, () -> {
            recruit.start("重复招聘", Major.FRONT_END_DEVELOPMENT);
        });
    }

    @Test
    void testSetConsumerAfterStart() {
        // 测试启动后设置面试官
        recruit.start("Java开发招聘", Major.BACK_END_DEVELOPMENT);
        recruit.setConsumer(mockInterviewer);
        String overview = recruit.recruitOverView();
        assertTrue(overview.contains("WAITING_FOR_RESUME"));
    }

    @Test
    void testSetConsumerBeforeStart() {
        // 测试启动前设置面试官
        assertThrows(IllegalStateException.class, () -> {
            recruit.setConsumer(mockInterviewer);
        });
    }

    @Test
    void testReceiveQualifiedResume() {
        // 测试接收符合条件的简历
        recruit.start(mockInterviewer, "Java开发招聘", Major.BACK_END_DEVELOPMENT);
        boolean result = recruit.receiveResume(javaer);
        assertTrue(result);
        
        String overview = recruit.recruitOverView();
        assertTrue(overview.contains("收到简历数量：1"));
        assertTrue(overview.contains("进入面试者数量：1"));
    }

    @Test
    void testReceiveUnqualifiedResume() {
        // 测试接收不符合条件的简历（专业不匹配）
        recruit.start(mockInterviewer, "前端开发招聘", Major.FRONT_END_DEVELOPMENT);
        boolean result = recruit.receiveResume(javaer); // javaer是后端开发
        assertFalse(result);
        
        String overview = recruit.recruitOverView();
        assertTrue(overview.contains("收到简历数量：1"));
        assertTrue(overview.contains("进入面试者数量：0"));
    }

    @Test
    void testReceiveResumeWithWrongAge() {
        // 测试接收年龄不符合的简历
        recruit.start(mockInterviewer, "Java开发招聘", Major.BACK_END_DEVELOPMENT);
        Javaer youngDeveloper = new Javaer("小明", Major.BACK_END_DEVELOPMENT, 17);
        Javaer oldDeveloper = new Javaer("老王", Major.BACK_END_DEVELOPMENT, 36);
        
        assertFalse(recruit.receiveResume(youngDeveloper));
        assertFalse(recruit.receiveResume(oldDeveloper));
        
        String overview = recruit.recruitOverView();
        assertTrue(overview.contains("收到简历数量：2"));
        assertTrue(overview.contains("进入面试者数量：0"));
    }

    @Test
    void testReceiveNullResume() {
        // 测试接收空简历
        recruit.start(mockInterviewer, "Java开发招聘", Major.BACK_END_DEVELOPMENT);
        boolean result = recruit.receiveResume(null);
        assertFalse(result);
    }

    @Test
    void testReceiveResumeBeforeStart() {
        // 测试启动前接收简历
        boolean result = recruit.receiveResume(javaer);
        assertFalse(result);
    }

    @Test
    void testConsumeInterview() throws InterruptedException {
        // 测试面试流程
        recruit.start(mockInterviewer, "Java开发招聘", Major.BACK_END_DEVELOPMENT);
        recruit.receiveResume(javaer);
        recruit.receiveResume(pythonGuy);
        
        recruit.consume();
        
        // 等待面试完成
        Thread.sleep(6000);
        
        String overview = recruit.recruitOverView();
        assertTrue(overview.contains("CONSUMING"));
        assertEquals(2, interviewCount.get());
    }

    @Test
    void testConsumeWithoutConsumer() {
        // 测试没有面试官时开始面试
        recruit.start("Java开发招聘", Major.BACK_END_DEVELOPMENT);
        assertThrows(IllegalStateException.class, () -> {
            recruit.consume();
        });
    }

    @Test
    void testConsumeInWrongStatus() {
        // 测试错误状态下开始面试
        assertThrows(IllegalStateException.class, () -> {
            recruit.consume();
        });
    }

    @Test
    void testOverRecruit() throws InterruptedException {
        // 测试结束招聘
        recruit.start(mockInterviewer, "Java开发招聘", Major.BACK_END_DEVELOPMENT);
        recruit.receiveResume(javaer);
        recruit.consume();
        
        recruit.over();
        
        String overview = recruit.recruitOverView();
        assertTrue(overview.contains("ONLY_CONSUMING"));
        
        // 结束招聘后不能再接收简历
        boolean result = recruit.receiveResume(pythonGuy);
        assertFalse(result);
    }

    @Test
    void testStopRecruit() throws InterruptedException {
        // 测试强制停止招聘
        recruit.start(mockInterviewer, "Java开发招聘", Major.BACK_END_DEVELOPMENT);
        recruit.receiveResume(javaer);
        recruit.consume();
        
        Thread.sleep(1000); // 让面试开始
        recruit.stop();
        
        Thread.sleep(1000); // 等待停止完成
        
        String overview = recruit.recruitOverView();
        assertTrue(overview.contains("END"));
    }

    @Test
    void testMultipleResumes() {
        // 测试多个简历处理
        recruit.start(mockInterviewer, "开发招聘", Major.BACK_END_DEVELOPMENT);
        
        Javaer dev1 = new Javaer("开发者1", Major.BACK_END_DEVELOPMENT, 25);
        Javaer dev2 = new Javaer("开发者2", Major.BACK_END_DEVELOPMENT, 28);
        PythonGuy dev3 = new PythonGuy("开发者3", Major.BACK_END_DEVELOPMENT, 30);
        Javaer dev4 = new Javaer("开发者4", Major.FRONT_END_DEVELOPMENT, 26); // 专业不匹配
        
        assertTrue(recruit.receiveResume(dev1));
        assertTrue(recruit.receiveResume(dev2));
        assertTrue(recruit.receiveResume(dev3));
        assertFalse(recruit.receiveResume(dev4)); // 专业不匹配
        
        String overview = recruit.recruitOverView();
        assertTrue(overview.contains("收到简历数量：4"));
        assertTrue(overview.contains("进入面试者数量：3"));
    }

    @Test
    void testRecruitOverview() {
        // 测试招聘概览信息
        recruit.start(mockInterviewer, "全栈开发招聘", Major.FULL_STACK_DEVELOPMENT);
        
        String overview = recruit.recruitOverView();
        assertTrue(overview.contains("招聘名称：全栈开发招聘"));
        assertTrue(overview.contains("招聘职位：全栈开发"));
        assertTrue(overview.contains("收到简历数量：0"));
        assertTrue(overview.contains("进入面试者数量：0"));
        assertTrue(overview.contains("招聘状态：WAITING_FOR_RESUME"));
    }

    @Test
    void testCandidateShowTime() {
        // 测试候选人展示功能
        assertDoesNotThrow(() -> {
            javaer.showTime();
            pythonGuy.showTime();
        });
    }

    @Test
    void testMajorEnum() {
        // 测试专业枚举
        assertEquals("后端开发", Major.BACK_END_DEVELOPMENT.getDisplayName());
        assertEquals("前端开发", Major.FRONT_END_DEVELOPMENT.getDisplayName());
        assertEquals("全栈开发", Major.FULL_STACK_DEVELOPMENT.getDisplayName());
        assertEquals("产品设计", Major.PRODUCT_DESIGN.getDisplayName());
        assertEquals("数据分析", Major.DATA_ANALYSIS.getDisplayName());
        assertEquals("测试工程师", Major.QUALITY_ASSURANCE.getDisplayName());
        assertEquals("运维工程师", Major.OPERATIONS_SUPPORT.getDisplayName());
    }

    @Test
    void testStatusEnum() {
        // 测试状态枚举
        assertEquals(0, CompanyRecruit.Status.NO_START.getStatusCode());
        assertEquals(1, CompanyRecruit.Status.NEW_START.getStatusCode());
        assertEquals(2, CompanyRecruit.Status.WAITING_FOR_RESUME.getStatusCode());
        assertEquals(3, CompanyRecruit.Status.CONSUMING.getStatusCode());
        assertEquals(4, CompanyRecruit.Status.ONLY_CONSUMING.getStatusCode());
        assertEquals(5, CompanyRecruit.Status.END.getStatusCode());
    }

    @Test
    void testCompanySingleton() {
        // 测试公司单例模式
        CompanyRecruit recruit1 = Company.getRecruit();
        CompanyRecruit recruit2 = Company.getRecruit();
        assertSame(recruit1, recruit2);
    }
}
