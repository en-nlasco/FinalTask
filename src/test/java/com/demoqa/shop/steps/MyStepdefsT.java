package com.demoqa.shop.steps;

import com.demoqa.shop.pages.*;
import com.demoqa.shop.util.Browser;
import com.demoqa.shop.util.Context;
import com.demoqa.shop.util.ScenarioContext;
import com.demoqa.shop.util.ScreenshotUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class MyStepdefsT {
    Logger log = LoggerFactory.getLogger ( MyStepdefsT.class );

    @When("user add item to wishlist from Home page")
    public void userAddItemToWishlistFromHomePage () {
        HomePage homePage = (HomePage) ScenarioContext.getInstance ( ).getContext ( Context.CURRENT_PAGE );
        homePage.clickWishlistIconOnHomePage ( );
        ScreenshotUtil.takeScreenshot ( "HomePageScreen" );
    }


    @When("user clicks on My Wishlist link")
    public void userGoToMyWishlistPage ( ) {
        BasePage page = (BasePage)ScenarioContext.getInstance ().getContext ( Context.CURRENT_PAGE );
        ((AbstractCommonPage)page).goToMyWishListPage ();
    }

    @Then("{int} item is displayed")
    public void addedItemIsDisplayed ( int countOfItem ) {
        List < WebElement > optionCount = Browser.getBrowser ( ).findElements ( By.cssSelector ( "tbody.wishlist-items-wrapper tr" ) );
        log.info ( "actual result is" + optionCount.size ( ) );
        // ScreenshotUtil.takeScreenshot ( "1 items in cart" );
        assertEquals ( countOfItem , optionCount.size ( ) );
    }


    @When("user add item to wishlist")
    public void userAddItemToWishlist () {
        ProductPage productPage = (ProductPage) ScenarioContext.getInstance ( ).getContext ( Context.CURRENT_PAGE );
        productPage.clickOnWishlistIcon ( );
    }

    @Then("icon is checked")
    public void iconIsChecked () {
        ProductPage productPage = (ProductPage) ScenarioContext.getInstance ( ).getContext ( Context.CURRENT_PAGE );
        assertTrue ( productPage.getIconIsChecked ( ) != null , "Item is marked as added to Wishlist" );
    }

    @When("user go back to product page")
    public void userGoBackToProductPage () {
        MyWishlistPage myWishlistPage = (MyWishlistPage) ScenarioContext.getInstance ( ).getContext ( Context.CURRENT_PAGE );
        myWishlistPage.clickProductLink ( );
        ScenarioContext.getInstance ( ).setContext ( Context.CURRENT_PAGE , new ProductPage ( Browser.getBrowser ( ) ) );
    }

    @When("user uncheck wishlist icon")
    public void userUncheckWishlistIcon () {
        ProductPage page = (ProductPage) ScenarioContext.getInstance ( ).getContext ( Context.CURRENT_PAGE );
        page.clickOnWishlistIcon ( );
    }

    @Then("icon is unchecked")
    public void iconIsUnchecked () {
        WebElement webElement = Browser.getBrowser ( ).findElement ( By.cssSelector ( ".single-product-content .yith-wcwl-add-button" ) );
        assertTrue ( webElement != null , "Item is not marked as added to Wishlist" );
    }

    @When("user click on search input field")
    public void userClickOnSearchInputField () {
        HomePage homePage = (HomePage) ScenarioContext.getInstance ( ).getContext ( Context.CURRENT_PAGE );
        homePage.openSearchField ( );
    }

    @When("user searches {string} category")
    public void userEnterTheDressCategory ( String string ) {
        HomePage homePage = (HomePage) ScenarioContext.getInstance ( ).getContext ( Context.CURRENT_PAGE );
        homePage.setSearchField ( string );

        log.info ( "Category name is insert" );
    }

    @When("user add to wishlist two items")
    public void userAddToWishlistTwoItems () {
        SearchResultPage searchResultPage = (SearchResultPage) ScenarioContext.getInstance ( ).getContext ( Context.CURRENT_PAGE );
        searchResultPage.addFirstCategoryItemToWL ( );
        searchResultPage.addSecondCategoryItemToWL ( );
//        ScreenshotUtil.takeScreenshot ( "HomePageScreen" );
    }

    @When("remove first item")
    public void removeOneItem () {
        MyWishlistPage myWishlistPage = (MyWishlistPage) ScenarioContext.getInstance ( ).getContext ( Context.CURRENT_PAGE );
        myWishlistPage.clickRemoveButtonFromWishlistPage ( );

    }

    @Then("the list immediately renewed itself")
    public void theListImmediatelyRenewedItself () {
        Browser.getBrowser ( ).navigate ( ).refresh ( );
    }

    @Then("successfully message is displayed")
    public void successfullyMessageIsDisplayed () {
        MyWishlistPage myWishlistPage = (MyWishlistPage) ScenarioContext.getInstance ( ).getContext ( Context.CURRENT_PAGE );
        assertNotNull ( myWishlistPage.getSuccessfullMessageOfRemoveFromWishlist ( ) , "Success Message is Displayed" );
    }

    @When("Wishlist icon is checked")
    public void wishlistIconIsChecked () {
        ProductPage productPage = (ProductPage) ScenarioContext.getInstance ( ).getContext ( Context.CURRENT_PAGE );
        assertNotNull ( productPage.getIconIsChecked ( ) , "Item is not marked as added to Wishlist" );
    }

    @Then("input form is displayed")
    public void inputFormIsDisplayed () {
        HomePage homePage = (HomePage) ScenarioContext.getInstance ( ).getContext ( Context.CURRENT_PAGE );
        assertTrue ( homePage.searchFieldIsDisplayed ( ) , "Search field is displayed" );
    }
}
