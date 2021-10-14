package com.cruidvat.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = { "pretty", "com.epam.reportportal.cucumber.ScenarioReporter" },
    monochrome = true,
    tags = "@Regression",
    glue = "com.cruidvat",
    features = "src/test/resources/com.cucumber.junit.features"
)
public class CucumberTest {

}
