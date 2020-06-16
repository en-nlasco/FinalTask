package com.demoqa.shop.steps;

import com.demoqa.shop.pages.HomePage;
import com.demoqa.shop.pages.ProductPage;
import com.demoqa.shop.pages.ProductsPage;
import com.demoqa.shop.util.ATFAssert;
import com.demoqa.shop.util.Browser;
import com.demoqa.shop.util.Context;
import com.demoqa.shop.util.ScenarioContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.junit.jupiter.api.Assertions.*;

public class ProdPageStepDef {

    static Logger log = LoggerFactory.getLogger(ProdPageStepDef.class);
    WebDriverWait wait = new WebDriverWait(Browser.getBrowser(), 15);

    @Given("the user is on the {string} page")
    public void userOnProductPage(String value) {
        ProductsPage page = new ProductsPage(Browser.getBrowser());
        HomePage home = new HomePage(Browser.getBrowser());
        home.clickFirstItem();
        ProductPage product = new ProductPage(Browser.getBrowser());
        product.clickProductsButton();
        assertTrue(Browser.getBrowser().getCurrentUrl().contains(page.getUrl()));
        log.info("User is on Products page");
        ScenarioContext.getInstance().setContext(Context.CURRENT_PAGE, page);
    }

    @And("the filter is on the default values")
    public void filterOnDefault() {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        ATFAssert.assertTrue("A color is selected", page.getDefaultColor().isSelected(), "Filter color on default");
        ATFAssert.assertTrue("A size is selected", page.getDefaultSize().isSelected(), "Filter size on default");
        ATFAssert.assertTrue("An order criteria is selected", page.getDefaultOrder().isSelected(), "Default sorting selected");
    }

    @Then("all the items are displayed")
    public void allItemsDisplayed() {
        ProductsPage myPage = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        ATFAssert.assertTrue("Not all items displayed", myPage.getAllElements().isDisplayed(), "All elements displayed");
        log.info("All items are displayed");
    }

    @When("user selects the following filter:")
    public void userSelectsColor(Map<String, String> filterMap) {
        ProductsPage myPage = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        if (filterMap.containsKey("color")) {
            myPage.selectColor(filterMap.get("color"));
            log.info("Color black is selected");
        } else if (filterMap.containsKey("size")) {
            myPage.selectSize(filterMap.get("size"));
            log.info("Size 32 is selected");
        }
    }

    @Then("only the items with that {string} are displayed")
    public void itemsWithSelectedColorDisplayed(String filter) {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);

        switch (filter) {
            case "color":
                page.getFisrstItem().click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id = 'pa_color']//option[@value='black']")));
                ATFAssert.assertTrue("No such color for that item", page.getItemColorBlack().isDisplayed(), "Color black available for this item");
                break;
            case "size":
                page.getFisrstItem().click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id = 'pa_size']//option[@value='32']")));
                ATFAssert.assertTrue("No such size for that item", page.getItemSize32().isDisplayed(), "Size 32 available for this item");
                break;
        }
        Browser.getBrowser().get(page.getUrl());
    }

    @When("the user selects a option for both filters")
    public void userSelectsOptionForBothFilters() {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        Browser.getBrowser().get(page.getUrl());
        page.selectColor("60");
        page.selectSize("66");
        log.info("Color white and size large are selected");
    }

    @Then("only the items matching both parameters are displayed")
    public void itemsWithBothParametersDisplayed() {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        page.getFisrstItem().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='pa_color']")));
        ATFAssert.assertTrue("No such color for that item", page.getItemColorWhite().isDisplayed(), "Color white available for this item");
        ATFAssert.assertTrue("No such size for that item", page.getItemSizeLarge().isDisplayed(), "Size large available for this item");
    }

    @Then("the items on the page are displayed by default in a grid format")
    public void itemsDisplayedByDefaultInGridFormat() {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        ATFAssert.assertTrue("List view selected", page.getGridView().isDisplayed(), "Grid view selected");
    }

    @When("the user changes the default sorting to {string}")
    public void userChangesDefaultSortingToList(String view) {
        view = "list";
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        page.selectStyle(view);
        log.info("List view is selected");
    }

    @Then("all the elements are displayed in List style")
    public void elementsDisplayedInListStyle() {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        ATFAssert.assertTrue("Grid view selected", page.getListView().isDisplayed(), "List view is selected");
    }

    @And("the following information is displayed for all the items:")
    public void followingInformationDisplayed(List<String> elementsList) {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);

        for (String element : elementsList) {
            switch (element) {
                case "Item title":
                    ATFAssert.assertTrue("No title displayed", page.getItemTitle().isDisplayed(), "Item title is displayed");
                    break;
                case "Category":
                    ATFAssert.assertTrue("No category displayed",page.getCategory().isDisplayed(), "Item category is displayed");
                    break;
                case "Price":
                    ATFAssert.assertTrue("No price displayed", page.getPrice().isDisplayed(), "Item price is displayed");
                    break;
                case "Description":
                    ATFAssert.assertTrue("No description", page.getDescription().isDisplayed(), "Item description is displayed");
                    break;
                case "Action options":
                    ATFAssert.assertTrue("No action options displayed", page.getActionOptions().isDisplayed(), "Item action options displayed");
                    break;
            }
        }
    }

    @When("the users changes the style back to {string}")
    public void usersChangesStyleToGrid(String view) {
        view = "grid";
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        page.selectStyle(view);
        log.info("Grid view is selected");
    }

    @Then("all the items are displayed in Grid style")
    public void allItemsDisplayedInGridStyle() {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        ATFAssert.assertTrue("List view selected", page.getGridView().isDisplayed(), "Grid view selected");
    }

    @Then("the products are ordered in a default order")
    public void productsOrderedInDefaultOrder() {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        ATFAssert.assertTrue("An order criteria is selected", page.getDefaultOrder().isSelected(), "Default sorting selected");
    }

    @When("the user selects a specific sorting criteria")
    public void userSelectsSortingCriteria() {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        page.selectOrder("price");
        log.info("Items ordered by price: low to high");
    }

    @Then("all the items are ordered according to the selected criterion")
    public void itemsOrderedAccordingToSelectedCriterion() {
        int a = 3;
        double d;
        List<Double> actualPriceList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String price = Browser.getBrowser().findElement(By.xpath("(//span[@class='woocommerce-Price-amount amount'])[" + a + "]")).getText();
            String[] parts = price.split("₹");
            String part = parts[1];
            d = Double.parseDouble(part);
            actualPriceList.add(d);
            a++;
        }
        List<Double> expectedPriceList = new ArrayList<>(actualPriceList);
        Collections.sort(expectedPriceList);
        assertEquals(expectedPriceList, actualPriceList, "Results displayed in wrong order");
    }

    @When("the user selects a filter value for {string}, {string} and {string}")
    public void userSelectsValueForColorSizeSorting(String color, String size, String sorting) {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        color = "60";
        size = "65";
        sorting = "price-desc";
        page.selectColor(color);
        page.selectSize(size);
        page.selectOrder(sorting);
        log.info("Color white, size medium and sort by price: high to low selected");
    }

    @Then("only the items with that color and size will be displayed")
    public void itemsMatchingColorSize() {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        page.getFisrstItem().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='pa_color']")));
        ATFAssert.assertTrue("No such color for that item", page.getItemColorWhite().isDisplayed(), "Color white available for this item");
        ATFAssert.assertTrue("No such size for that item", page.getItemSizeMedium().isDisplayed(), "Medium size available for this item");
        Browser.getBrowser().navigate().back();
    }

    @And("it will be sorted according to the selected criteria")
    public void sortedAccordingToTheSelectedCriteria() {
        int a = 2;
        double d;
        List<Double> actualPriceList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String price = Browser.getBrowser().findElement(By.xpath("(//span[@class='woocommerce-Price-amount amount'])[" + a + "]")).getText();
            String[] parts = price.split("₹");
            String part = parts[1];
            d = Double.parseDouble(part);
            actualPriceList.add(d);
            a++;
        }
        List<Double> expectedPriceList = new ArrayList<>(actualPriceList);
        Collections.sort(expectedPriceList, Collections.reverseOrder());
        ATFAssert.assertEquals(expectedPriceList, actualPriceList, "Results displayed in wrong order", "Results displayed in right order");
    }
}
