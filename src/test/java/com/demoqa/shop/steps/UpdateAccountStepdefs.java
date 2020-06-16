package com.demoqa.shop.steps;

import com.demoqa.shop.pages.*;
import com.demoqa.shop.util.*;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateAccountStepdefs {

    Logger log = LoggerFactory.getLogger(UpdateAccountStepdefs.class);
    public WebDriver driver = Browser.getBrowser();

    @Given("Demoqa shop homepage is opened")
    public void demoqaShopHomepageIsOpened() {
        HomePage homePage = (HomePage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        ATFAssert.assertEquals(homePage.getTitle(), driver.getTitle(), "New page is opened", "Page" + homePage.getUrl() + " is opened");

        // assertEquals(homePage.getTitle(), driver.getTitle());
        log.info("Home page with url {}  is loaded",
                driver.getCurrentUrl());
    }

    @And("he clicks account link menu")
    public void heClicksAccountLinkMenu() {
        HomePage homePage = (HomePage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        homePage.getLnk_MyAccount().click();
        log.info("{} was clicked.",
                homePage.getLnk_MyAccount().getText());
        ScenarioContext.getInstance().setContext(Context.CURRENT_PAGE, new AccountPage(driver));
    }

    @And("my-account page is opened")
    public void myAccountPageIsOpened() {
        AccountPage accountPage = (AccountPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        ATFAssert.assertEquals(accountPage.getTitle(), driver.getTitle(), "New page is opened", "Page" + accountPage.getUrl() + " is opened");

        //   assertEquals(accountPage.getTitle(), driver.getTitle());
        log.info("Page {} is opened.",
                accountPage.getUrl());
    }

    @Given("customer enters his new username, email and password in registry form")
    public void customerEntersHisNewUsernameEmailAndPasswordInRegistryForm() {
        AccountPage accountPage = (AccountPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        ScenarioContext.getInstance().setContext(Context.NEW_LOGINNAME, RandomString.getAlphaNumericString(8));
        String name = ScenarioContext.getInstance().getContext(Context.NEW_LOGINNAME).toString();
        ScenarioContext.getInstance().setContext(Context.NEW_EMAIL, RandomString.getAlphaNumericString(5) + "@mail.ru");
        String email = ScenarioContext.getInstance().getContext(Context.NEW_EMAIL).toString();
        ScenarioContext.getInstance().setContext(Context.NEW_PASSWORD, RandomString.getAlphaNumericString(12) + "@#$12");
        String password = ScenarioContext.getInstance().getContext(Context.NEW_PASSWORD).toString();
        accountPage.getField_username().sendKeys(name);
        accountPage.getField_email().sendKeys(email);
        accountPage.getField_password().sendKeys(password);
        log.info(" Into field account name value ' {} ' is entered.",
                name);
        log.info(" Into field account email value ' {} ' is entered.",
                email);
        log.info(" Into field account password value ' {} ' is entered.",
                password);
    }

    @And("submits his request in registry form")
    public void submitsHisRequestInRegistryForm() {
        AccountPage accountPage = (AccountPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        accountPage.getButton_register().isSelected();
        accountPage.getButton_register().sendKeys(Keys.ENTER);
        log.info("Button register is clicked.");
        ScenarioContext.getInstance().setContext(Context.CURRENT_PAGE, new LoginPage(driver));


    }

    @Given("Demoqa shop  login page is opened")
    public void demoqaShopLoginPageIsOpened() {
        LoginPage loginPage = (LoginPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        ATFAssert.assertEquals(loginPage.getUrl(), driver.getCurrentUrl(), "New page is opened", "Page" + loginPage.getUrl() + " is opened");
        log.info("Page {} is opened.",
                loginPage.getUrl());
    }

    @When("he clicks back to home")
    public void heClicksBackToHome() {
        LoginPage loginPage = (LoginPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        loginPage.getButton_back_to_account().isSelected();
        loginPage.getButton_back_to_account().sendKeys(Keys.ENTER);
        loginPage.getButton_back_to_account().click();
        log.info("Button back_to_account_page is clicked.");
        ScenarioContext.getInstance().setContext(Context.CURRENT_PAGE, new HomePage(driver));
    }

    @When("he enters his username and password in login form")
    public void heEntersHisUsernameAndPasswordInLoginForm() {
        AccountPage accountPage = (AccountPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        String name = ScenarioContext.getInstance().getContext(Context.NEW_LOGINNAME).toString();
        String password = ScenarioContext.getInstance().getContext(Context.NEW_PASSWORD).toString();
        accountPage.getField_username_login().sendKeys(name);
        accountPage.getField_password_login().sendKeys(password);
        log.info(" Into field account name value ' {} ' is entered.",
                name);
        log.info(" Into field account email value ' {} ' is entered.",
                password);
    }

    @And("submits Login request")
    public void submitsLoginRequest() {
        AccountPage accountPage = (AccountPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        accountPage.getButton_login_account_page().click();
        log.info("Login Done with button login account  clicks.");
    }

    @Then("account name is set as entered username")
    public void accountNameIsSetAsEnteredUsername() {
        AccountPage accountPage = (AccountPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        String name = ScenarioContext.getInstance().getContext(Context.NEW_LOGINNAME).toString();
        ATFAssert.assertEquals(accountPage.getMessage_username().getText(), "Hello " + name + " (not " + name + "? Log out)", "user is  logged in ", "user with acount " + name + "  is logedin");
        log.info("Page with url {}  is loaded",
                driver.getCurrentUrl());
        log.info("user with acount name{}  is logedin",
                name);
    }

    @When("he click Account settings")
    public void heClickAccountSettings() {
        AccountPage accountPage = (AccountPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        accountPage.getNavigation_link_account_details().click();
        ScenarioContext.getInstance().setContext(Context.CURRENT_PAGE, new AccountEditPage(driver));
    }

    @And("He enters his First Name")
    public void heEntersHisFirstName() {
        AccountEditPage accountEditPage = (AccountEditPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        String firstName = "Mike";
        accountEditPage.getField_first_name().sendKeys(firstName);
        log.info(" Into field First name value ' {} ' is entered.",
                firstName);
    }

    @And("He enters his Second Name")
    public void heEntersHisSecondName() {
        AccountEditPage accountEditPage = (AccountEditPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        String lastName = "Mike";
        accountEditPage.getField_last_name().sendKeys(lastName);
        log.info(" Into field First name value ' {} ' is entered.",
                lastName);
    }

    @And("He enters his new account name")
    public void heEntersHisNewAccountName() {
        AccountEditPage accountEditPage = (AccountEditPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        ScenarioContext.getInstance().setContext(Context.NEW_LOGINNAME, RandomString.getAlphaNumericString(8));
        String newName = ScenarioContext.getInstance().getContext(Context.NEW_LOGINNAME).toString();
        accountEditPage.getField_display_name().clear();
        accountEditPage.getField_display_name().sendKeys(newName);
        log.info(" Into field First name value ' {} ' is entered.",
                newName);
    }

    @And("submits save request")
    public void submitsSaveRequest() {
        AccountEditPage accountEditPage = (AccountEditPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        accountEditPage.getButton_save_account().click();
        log.info(" Save request is submitted on registry form. ");
        ScenarioContext.getInstance().setContext(Context.CURRENT_PAGE, new AccountPage(driver));

    }

    @And("He enters his new email")
    public void heEntersHisNewEmail() {
        AccountEditPage accountEditPage = (AccountEditPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        ScenarioContext.getInstance().setContext(Context.NEW_EMAIL, RandomString.getAlphaNumericString(5) + "@mail.ru");
        String newEmail = ScenarioContext.getInstance().getContext(Context.NEW_EMAIL).toString();
        accountEditPage.getField_account_email().clear();
        accountEditPage.getField_account_email().sendKeys(newEmail);
        log.info(" Into field email value ' {} ' is entered.",
                newEmail);
    }

    @Then("message email was changed is displayed")
    public void messageEmailWasChangedIsDisplayed() {
        AccountPage accountPage = (AccountPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        ATFAssert.assertTrue("email is changed", accountPage.getMessage_account_updated().isDisplayed(),
                accountPage.getMessage_account_updated().getText());
        log.info(" Message for email changing ' {} ' is displayed.",
                accountPage.getMessage_account_updated().getText());
    }


    @Given("Customer enters his username {string} and password {string} in login form")
    public void customerEntersHisUsernameTesterAndPasswordFDSACdeInLoginForm(String name, String password) {
        AccountPage accountPage = (AccountPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        accountPage.getField_username_login().sendKeys(name);
        accountPage.getField_password_login().sendKeys(password);
        log.info(" Into field account name value ' {} ' is entered.",
                name);
        log.info(" Into field account email value ' {} ' is entered.",
                password);
    }

    @Then("account name is set as entered new username")
    public void accountNameIsSetAsEnteredNewUsername() {
        AccountPage accountPage = (AccountPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        String name = ScenarioContext.getInstance().getContext(Context.NEW_LOGINNAME).toString();
        assertEquals(accountPage.getMessage_username().getText(), "Hello " + name + " (not " + name + "? Log out)");
        log.info("Page with url {}  is loaded",
                driver.getCurrentUrl());
        log.info("user with acount name{}  is logedin",
                name);
    }

    @And("He enters not valid current password")
    public void heEntersNotValidCurrentPassword() {
        AccountEditPage accountEditPage = (AccountEditPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        String wrongPassword = " sdfdfgs@SFDSF324$";
        accountEditPage.getField_password_current().sendKeys(wrongPassword);
        log.info(" Into field current password value ' {} ' is entered.",
                wrongPassword);
    }

    @And("He enters new password")
    public void heEntersNewPassword() {
        AccountEditPage accountEditPage = (AccountEditPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        ScenarioContext.getInstance().setContext(Context.NEW_PASSWORD, RandomString.getAlphaNumericString(12) + "@#$12");
        String passwordNew = ScenarioContext.getInstance().getContext(Context.NEW_PASSWORD).toString();
        accountEditPage.getField_password_new1().sendKeys(passwordNew);
        log.info(" Into field current password value ' {} ' is entered.",
                passwordNew);
    }

    @And("He reenters new password")
    public void heReentersNewPassword() {
        AccountEditPage accountEditPage = (AccountEditPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        String passwordNew = ScenarioContext.getInstance().getContext(Context.NEW_PASSWORD).toString();
        accountEditPage.getField_password_new2().sendKeys(passwordNew);
        log.info(" Into field current password value ' {} ' is entered.",
                passwordNew);
    }

    @And("submits save request with wrong data")
    public void submitsSaveRequestWithWrongData() {
        AccountEditPage accountEditPage = (AccountEditPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        accountEditPage.getButton_save_account().click();
        log.info(" Save request is submitted on registry form. ");
    }

    @Then("message not valid current password is displayed")
    public void messageNotValidCurrentPasswordIsDisplayed() {
        AccountEditPage accountEditPage = (AccountEditPage) ScenarioContext.getInstance().getContext(Context.CURRENT_PAGE);
        accountEditPage.getMessage_current_passwords_is_incorrect().getText();
        ATFAssert.assertTrue("email is changed", accountEditPage.getMessage_current_passwords_is_incorrect().isDisplayed(),
                accountEditPage.getMessage_current_passwords_is_incorrect().getText());
        log.info(" Message {} is displayed.",
                accountEditPage.getMessage_current_passwords_is_incorrect().getText());
    }

}
