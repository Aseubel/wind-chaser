package com.aseubel.dynamictp.controller;

import com.aseubel.dynamictp.service.TestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aseubel
 * @date 2025/9/13 下午11:57
 */
@Slf4j
@RestController
@AllArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/dtp-nacos-example/testJucTp")
    public String testJuc() {
        testService.testJucTp();
        return "testJucTp success";
    }

    @GetMapping("/dtp-nacos-example/testSpringTp")
    public String testSpring() {
        testService.testSpringTp();
        return "testSpringTp success";
    }

    @GetMapping("/dtp-nacos-example/testCommonDtp")
    public String testCommon() {
        testService.testCommonDtp();
        return "testCommonDtp success";
    }

    @GetMapping("/dtp-nacos-example/testEagerDtp")
    public String testEager() {
        testService.testEagerDtp();
        return "testEagerDtp success";
    }

    @GetMapping("/dtp-nacos-example/testScheduledDtp")
    public String testScheduled() {
        testService.testScheduledDtp();
        return "testScheduledDtp success";
    }

    @GetMapping("/dtp-nacos-example/testOrderedDtp")
    public String testOrdered() {
        testService.testOrderedDtp();
        return "testOrderedDtp success";
    }
}
