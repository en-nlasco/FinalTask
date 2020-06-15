package com.demoqa.shop.steps;

import com.demoqa.shop.pages.*;
import com.demoqa.shop.util.Browser;
import com.demoqa.shop.util.Context;
import com.demoqa.shop.util.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NLuchianStepsDefinition {
    Logger log = LoggerFactory.getLogger(NLuchianStepsDefinition.class);

    @Given("{string} page is displayed")
    public void validatePage(String string) {
        String currentURL = Browser.getBrowser().getCurrentUrl();
        BasePage page;
        switch (string.toLowerCase()) {
            case "home": {
                page = new HomePage(Browser.getBrowser());
                break;
            }
            case "cart": {
                page = new Cart(Browser.getBrowser());
                break;
            }
            case "product": {
                page = new ProductPage(Browser.getBrowser());
                break;
            }
//            case "checkout": {
//                page = new CheckoutPage(Browser.getBrowser());
//                break;
//            }
//            case "order received": {
//                page = new OrderReceivedPage(Browser.getBrowser());
//                break;
//            }                                                TO BE UNCOMMENTED BY N. LASCO
            default:
                throw new IllegalStateException("Unexpected value for page name: " + string.toLowerCase());
        }
        assertTrue(currentURL.contains(page.getUrl()), "wrong page is displayed");
        ScenarioContext.getInstance().setContext(Context.CURRENT_PAGE, page);
    }

    @When("User select {string} item from main page")
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

    @When("User select {string}")
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

    @When("User click on {string} button")
    public void userClickButton(String string) {
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
//            case "place order": {
//                ((CheckoutPage) page).clickPlaceOrderButton();
//                break;
//            }
//            case "login": {
//                ((CheckoutPage) page).clickLoginButton();
//                break;
//            }                                                         TO BE UNCOMMENTED BY N. LASCO
            case "proceed checkout": {
                ((Cart) page).clickProceedCheckout();
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value for button name: " + string.toLowerCase());
        }
    }

    @Then("Cart page is updated with right quantity of product")
    public void cartPageIsUpdatedWithRightQuantityOfProduct() {
    }

    @Then("Items are removed from Cart")
    public void itemsAreRemovedFromCart() {
    }

    @Then("Both items are added")
    public void bothItemsAreAdded() {
        List<WebElement> optionCount = Browser.getBrowser().findElements(By.xpath("//tr[@class='cart_item']"));
        log.info("actual result is " + optionCount.size());
        //ScreenshotUtil.takeScreenshot("2 items in cart ");
        assertEquals(2, optionCount.size());
    }
}