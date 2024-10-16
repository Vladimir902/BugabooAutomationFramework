package com.bugaboo.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;
    private static final int maxRetryCount = 3; // Specify the maximum number of retries

    @Override
    public boolean retry(ITestResult result) {
        if (count < maxRetryCount) {
            count++;
            return true; // Retry the test
        }
        return false; // Do not retry
    }
}
