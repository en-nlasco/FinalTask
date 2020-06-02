package com.demoqa.shop.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith( Cucumber.class )
@CucumberOptions(
        plugin = {"pretty", "html:target/report", "json:target/cucumber.json"},
        features = {"src/test/resources/com/demoqa/shop/features"},
        glue = {"src/test/java/com/demoqa/shop/hooks",
                "src/test/java/com/demoqa/shop/steps"},
        tags = "@Run",
        strict = true,
        monochrome = true,
        stepNotifications = true
)
public class RunCukeTests {
}
