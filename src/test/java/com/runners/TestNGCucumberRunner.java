package com.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/Login.feature", // Path to your feature files
        glue = "com.stepdefinitions" // Make sure this matches the correct package for step definitions
)
public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {
}

