package com.demoqa.shop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart extends BasePage {

    @FindBy(css = "a.empty-cart")
    private WebElement clearCartButton;

    @FindBy(css = "input.button[name=\"update_cart\"]")
    private WebElement updateShoppingCartButton;

    @FindBy(css = "button.qty-increase")
    private WebElement qtyIncrease;

    @FindBy(css = "a[property = \"item\"]")
    private WebElement logoButton;

    @FindBy(css = "a[class$='forward']")
    private WebElement proceedCheckoutButton;

    public Cart(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getUrl() {
        return "http://shop.demoqa.com/cart/";
    }

    @Override
    public String getTitle() {
        return "Cart Page";
    }

    public void clearCart() {
        clearCartButton.click();
    }

    public void updateCart() {
        updateShoppingCartButton.click();
    }

    public void increaseQty() {
        qtyIncrease.click();
    }

    public void clickLogo() {
        logoButton.click();
    }

    public void clickProceedCheckout() {
        proceedCheckoutButton.click();
    }
}