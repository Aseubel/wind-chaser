package com.aseubel.dynamictp.service;

/**
 * @author Aseubel
 * @date 2025/9/13 下午11:56
 */
public interface TestService {

    /**
     * Test juc tp.
     */
    void testJucTp();

    /**
     * Test spring tp.
     */
    void testSpringTp();

    /**
     * Test common dtp.
     */
    void testCommonDtp();

    /**
     * Test eager dtp.
     */
    void testEagerDtp();

    /**
     * Test scheduled dtp.
     */
    void testScheduledDtp();

    /**
     * Test ordered dtp.
     */
    void testOrderedDtp();
}
