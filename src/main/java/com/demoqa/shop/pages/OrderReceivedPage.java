package com.demoqa.shop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class OrderReceivedPage extends BasePage {

    public OrderReceivedPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getUrl() {
        return "http://shop.demoqa.com/checkout/order-received/";
    }

    @Override
    public String getTitle() {
        return null;
    }
}