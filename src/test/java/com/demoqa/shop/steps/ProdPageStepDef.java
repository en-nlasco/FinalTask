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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.junit.jupiter.api.Assertions.*;

public class ProdPageStepDef {
    private WebDriver driver = Browser.getBrowser();
    WebDriverWait wait = new WebDriverWait(driver, 15);

    @Given("the user is on the {string} page")
    public void userOnProductPage(String value) {
        ProductsPage page = new ProductsPage(Browser.getBrowser());
        HomePage home = new HomePage(Browser.getBrowser());
        home.clickFirstItem();
        ProductPage product = new ProductPage(Browser.getBrowser());
        product.clickProductsButton();
        assertTrue(Browser.getBrowser().getCurrentUrl().contains(page.getUrl()));
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

    }

    @When("the user selects a {string} color")
    public void userSelectsColor(String color) {
        color = "59";

        ProductsPage myPage = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        myPage = new ProductsPage(Browser.getBrowser());
        myPage.selectColor(color);
    }

    @Then("only the items with that color are displayed")
    public void itemsWithSelectedColorDisplayed() {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        page.getFisrstItem().click();
        ATFAssert.assertTrue("No such color for that item", page.getItemColorBlack().isDisplayed(), "Color black available for this item");
    }

    @When("the user selects a {string} size")
    public void userSelectsSize(String size) {
        size = "108";
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        page = new ProductsPage(Browser.getBrowser());
        driver.get(page.getUrl());
        page.selectSize(size);
    }

    @Then("only the items with that size are displayed")
    public void itemsWithSelectedSizeDisplayed() {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        page.getFisrstItem().click();
        ATFAssert.assertTrue("No such size for that item", page.getItemSize32().isDisplayed(), "Size 32 available for this item");
    }

    @When("the user selects a option for both filters")
    public void userSelectsOptionForBothFilters() {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        page = new ProductsPage(Browser.getBrowser());
        driver.get(page.getUrl());
        page.selectColor("60");
        page.selectSize("66");
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
        page = new ProductsPage(Browser.getBrowser());
        page.selectStyle(view);
    }

    @Then("all the elements are displayed in List style")
    public void elementsDisplayedInListStyle() {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        ATFAssert.assertTrue("Grid view selected", page.getListView().isDisplayed(), "List view is selected");
    }

    @And("the following information is displayed for all the items:")
    public void followingInformationDisplayed(List<String> elementsList) {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);

        elementsList = new ArrayList<String>();
        elementsList.add("Item title");
        elementsList.add("Category");
        elementsList.add("Price");
        elementsList.add("Description");
        elementsList.add("Action options");

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
        page = new ProductsPage(Browser.getBrowser());
        page.selectStyle(view);
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
        page = new ProductsPage(Browser.getBrowser());
        page.selectOrder("price");
    }

    @Then("all the items are ordered according to the selected criterion")
    public void itemsOrderedAccordingToSelectedCriterion() {
        int a = 3;
        double d;
        List<Double> actualPriceList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String price = driver.findElement(By.xpath("(//span[@class='woocommerce-Price-amount amount'])[" + a + "]")).getText();
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
        page = new ProductsPage(Browser.getBrowser());
        page.selectColor(color);
        page.selectSize(size);
        page.selectOrder(sorting);
    }

    @Then("only the items with that color and size will be displayed")
    public void itemsMatchingColorSize() {
        ProductsPage page = (ProductsPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        page.getFisrstItem().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='pa_color']")));
        ATFAssert.assertTrue("No such color for that item", page.getItemColorWhite().isDisplayed(), "Color white available for this item");
        ATFAssert.assertTrue("No such size for that item", page.getItemSizeMedium().isDisplayed(), "Medium size available for this item");
        page = new ProductsPage(Browser.getBrowser());
        driver.navigate().back();
    }

    @And("it will be sorted according to the selected criteria")
    public void sortedAccordingToTheSelectedCriteria() {
        int a = 2;
        double d;
        List<Double> actualPriceList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String price = driver.findElement(By.xpath("(//span[@class='woocommerce-Price-amount amount'])[" + a + "]")).getText();
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
