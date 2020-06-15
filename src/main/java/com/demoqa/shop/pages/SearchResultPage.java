package com.demoqa.shop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultPage extends AbstractCommonPage {


    @FindBy(css = ".noo-container-shop.noo-shop-wrap")
    private List < WebElement > listOfProducts;

    @FindBy(css = "a[data-product-id='1473']")
    private WebElement firstCategoryItem;

    @FindBy(css = "a[data-product-id='1467']")
    private WebElement secondCategoryItem;


    public SearchResultPage ( WebDriver driver ) {
        super ( driver );
        PageFactory.initElements ( driver , this );
//        driver.get ( searchResultPageURL );
//        driver.manage ( ).window ( );
    }

    public final String searchResultPageURL = "http://shop.demoqa.com/?s=";

    @Override
    public String getUrl () {
        return searchResultPageURL;
    }

    @Override
    public String getTitle () {
        return null;
    }


    public void addFirstCategoryItemToWL () {
        firstCategoryItem.click ( );
    }

    public void addSecondCategoryItemToWL () {
        secondCategoryItem.click ( );
    }

    public List < WebElement > getListOfProducts () {
        return listOfProducts;
    }
}
