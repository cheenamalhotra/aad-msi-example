package com.test.aadmsi.msiexample;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Unit test for simple Application
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName
     *        name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigorous Test :-)
     * 
     * @throws Exception
     */
    public void testApp() throws Exception {
        App.main(null);
    }

    /**
     * Rigorous Test :-)
     * 
     * @throws Exception
     */
    public void testMSIAuth() throws Exception {
        TestMSIAuth.main(null);
    }
}
