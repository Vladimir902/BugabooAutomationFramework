package com.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/Login.feature",
        glue = "com.stepdefinitions"
)
public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {
}

