package com.demoqa.shop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage {

    @FindBy(how = How.CLASS_NAME, using = "woocommerce-store-notice__dismiss-link")
    private WebElement lnk_Dismiss;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'My Account')]")
    private WebElement lnk_MyAccount;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'My Wishlist')]")
    private WebElement lnk_MyWishlist;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Checkout')]")
    private WebElement lnk_Checkout;
    @FindBy(how = How.XPATH, using = "//span[contains(@class,'cart-name-and-total')]")
    private WebElement lnk_Cart;
    @FindBy(how = How.XPATH, using = "//a[contains(@class,'noo-search')]")
    private WebElement lnk_Search;


    @FindBy(css = "div.first.product_cat-t-shirt")
    private WebElement firstItem;

    @FindBy(css = "div.last.product_cat-t-shirt")
    private WebElement lastItem;

    public WebElement getLnk_Dismiss() {
        return lnk_Dismiss;
    }

    public WebElement getLnk_MyAccount() {
        return lnk_MyAccount;
    }

    public WebElement getLnk_MyWishlist() {
        return lnk_MyWishlist;
    }

    public WebElement getLnk_Checkout() {
        return lnk_Checkout;
    }

    public WebElement getLnk_Cart() {
        return lnk_Cart;
    }

    public WebElement getLnk_Search() {
        return lnk_Search;
    }


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickDismiss() {
        getLnk_Dismiss().click();
    }

    public void clickFirstItem() {
        firstItem.click();
    }

    public void clickSecondItem() { lastItem.click(); }

    @Override
    public String getUrl() {
        return "http://shop.demoqa.com";
    }

    @Override
    public String getTitle() {
        return "ToolsQA Demo Site – ToolsQA – Demo E-Commerce Site";
    }
}
