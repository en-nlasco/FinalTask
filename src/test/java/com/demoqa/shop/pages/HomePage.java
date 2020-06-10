package com.demoqa.shop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage {


    private static WebElement element = null;
    @FindBy(how = How.CLASS_NAME, using = "woocommerce-store-notice__dismiss-link")
    public WebElement lnk_Dismiss;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'My Account')]")
    public WebElement lnk_MyAccount;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    String getUrl() {
        return "http://shop.demoqa.com";
    }

    @Override
    String getTitle() {
        return "ToolsQA Demo Site";
    }
}
