package com.demoqa.shop.hooks;

import com.demoqa.shop.pages.HomePage;
import com.demoqa.shop.util.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {


    Logger log = LoggerFactory.getLogger(Hooks.class);

    @Before(order =1)
    public void beforeScenario(){

        WebDriver driver = Browser.getBrowser();

        HomePage home = new HomePage(driver);
        home.lnk_Dismiss.click();
    }

    @After(order =1)
    public void afterScenario(){

    }
    @BeforeEach
    public void beforeEachTest(TestInfo testInfo) {
        log.info( "Started executing [%s]" +
                testInfo.getDisplayName());
    }

    @AfterEach
    public void afterEachTest(TestInfo testInfo) {
        log.info("Finished executing [%s]" +
                testInfo.getDisplayName());
    }
}
