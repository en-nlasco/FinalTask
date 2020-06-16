package com.demoqa.shop.steps;

import com.demoqa.shop.pages.*;
import com.demoqa.shop.util.Browser;
import com.demoqa.shop.util.Context;
import com.demoqa.shop.util.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NLuchianStepsDefinition {
    Logger log = LoggerFactory.getLogger(NLuchianStepsDefinition.class);

    @When( "User select {string} item from main page" )
    public void userSelectAnItemFromMainPage(String string) {
        HomePage homePage = (HomePage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        if (string.toLowerCase().equals("first")) {
            homePage.clickFirstItem();
        } else if (string.toLowerCase().equals("last")) {
            homePage.clickSecondItem();
        } else {
            throw new IllegalStateException("Unexpected value for page name: " + string.toLowerCase());
        }
    }

    @When( "User select {string}" )
    public void userSelectOption(String string, String value) {
        ProductPage productpage = (ProductPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        switch (string.toLowerCase()) {
            case "color": {
                productpage.selectColor(value);
                break;
            }
            case "size": {
                productpage.selectSize(value);
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value for dropdown: " + string.toLowerCase());
        }
    }

    @When( "User click on {string} button" )
    public void userClickButton(String string) throws InterruptedException {
        BasePage page = (BasePage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        switch (string.toLowerCase()) {
            case "add to cart": {
                ((ProductPage) page).addToCart();
                break;
            }
            case "view cart": {
                ((ProductPage) page).clickViewCart();
                break;
            }
            case "logo": {
                ((Cart) page).clickLogo();
                break;
            }
            case "+": {
                ((Cart) page).increaseQty();
                break;
            }
            case "update shopping cart": {
                ((Cart) page).updateCart();
                break;
            }
            case "clear cart": {
                ((Cart) page).clearCart();
                break;
            }
            case "place order": {
                ((CheckoutPage) page).clickPlaceOrderButton();
                Thread.sleep(1000);
                break;
            }
            case "login": {
                ((CheckoutPage) page).clickLoginButton();
                break;
            }
            case "proceed checkout": {
                ((Cart) page).clickProceedCheckout();
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value for button name: " + string.toLowerCase());
        }
        Thread.sleep(2000);
    }

    @Then( "Cart page is updated with right quantity of product" )
    public void cartPageIsUpdatedWithRightQuantityOfProduct() {
    }

    @Then( "Items are removed from Cart" )
    public void itemsAreRemovedFromCart() {
    }

    @Then( "Both items are added" )
    public void bothItemsAreAdded() {
        List<WebElement> optionCount = Browser.getBrowser().findElements(By.xpath("//tr[@class='cart_item']"));
        log.info("actual result is " + optionCount.size());
        //ScreenshotUtil.takeScreenshot("2 items in cart ");
        assertEquals(2, optionCount.size());
    }
}