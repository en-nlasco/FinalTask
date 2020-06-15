package com.demoqa.shop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//select[@id='pa_size']")
    private WebElement sizeDropdown;

    @FindBy(xpath = "//select[@id='pa_color']")
    private WebElement colorDropdown;

    @FindBy(xpath = "//button[@class='single_add_to_cart_button button alt']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//li[@id='nav-menu-item-cart']//a//span//i")
    private WebElement viewCart;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getUrl() {
        return "http://shop.demoqa.com/product/";
    }

    @Override
    public String getTitle() {
        return "Product Page";
    }

    public void selectColor(String color) {
        new Select(colorDropdown).selectByValue(color);
    }

    public void selectSize(String size) {
        new Select(sizeDropdown).selectByValue(size);
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public void clickViewCart() {
        viewCart.click();
    }

}