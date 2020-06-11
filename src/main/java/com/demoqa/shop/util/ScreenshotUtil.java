package com.demoqa.shop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class ScreenshotUtil {
    static Logger log = LoggerFactory.getLogger( ScreenshotUtil.class );
    private static Scenario scenario;

    public static void setScenario ( Scenario scenario ) {
        ScreenshotUtil.scenario = scenario;

    }

    public static void takeScreenshot ( String name ) throws WebDriverException {
        try {
            TakesScreenshot ts = (TakesScreenshot) Browser.getBrowser ( );
            byte[] source = ts.getScreenshotAs ( OutputType.BYTES );
            scenario.embed ( source , "image/png" );
            Path scenarioPath = Paths.get ( new File ( "target/screenshot/" + scenario.getName ( ) + "/" ).getPath ( ) );
            Files.createDirectories ( scenarioPath );
            Files.write ( scenarioPath.resolve ( name + LocalDate.now ( ).toString ( ) + ".png" ) , source );
        } catch (Exception e) {
            System.out.println ( "Exception while taking screenshots" + e.getMessage ( ) );

        }
    }

    public static void scrollToElement ( WebDriver driver , WebElement myItem ) throws WebDriverException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript ( "arguments[0].scrollIntoView();" , myItem );

    }

    public static void highLight ( WebElement myItem ) {
        JavascriptExecutor js = (JavascriptExecutor) Browser.getBrowser ( );
        js.executeScript ( "arguments[0].setAttribute('style', 'border: 3px solid orange;');" , myItem );
    }
}