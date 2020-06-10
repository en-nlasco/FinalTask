package com.demoqa.shop.pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    final WebDriver driver ;
    BasePage(WebDriver driver)
    {
        this.driver = driver;
    }
    abstract String getUrl ();
    abstract String getTitle();


}
