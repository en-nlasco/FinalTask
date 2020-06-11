package com.demoqa.shop.hooks;

import com.demoqa.shop.pages.HomePage;
import com.demoqa.shop.util.Browser;
import com.demoqa.shop.util.Context;
import com.demoqa.shop.util.ScenarioContext;
//import com.demoqa.shop.util.ScreenshotUtil;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Hooks {
    Logger log = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void before(Scenario scenario) {
        log.info("Execution of scenario '{}' has been started", scenario.getName());
        //ScreenshotUtil.setScenario(scenario); //TO BE UNCOMMENTED BY T.BALMUS WITH SCREENSHOT IMPLEMENTATION
        WebDriver browser = Browser.getBrowser();
        HomePage homePage = new HomePage(browser);
        PageFactory.initElements(browser, homePage);// TO BE DELETED BY A.DIOGOTI AFTER ADDING IT TO BASEPAGE
        homePage.lnk_Dismiss.click(); //WOULD BE BETTER TO CHANGE WITH A PUBLIC METHOD IN HOMEPAGE
        ScenarioContext.getInstance().setContext(Context.CURRENT_PAGE, homePage);
        log.info("'Before' block has been executed");
    }

    @After
    public void after() {
        Browser.closeBrowser();
        ScenarioContext.getInstance().clearScenarioContext();
    }

}
