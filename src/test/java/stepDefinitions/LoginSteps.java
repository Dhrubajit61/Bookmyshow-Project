package stepDefinitions;

import base.DriverSetup;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import org.testng.Assert;

public class LoginSteps {
    WebDriver driver = DriverSetup.getDriver();
    LoginPage loginPage = new LoginPage(driver);

    @Given("user is on the Login page")
    public void user_is_on_the_login_page() {
        driver.get("https://bookmyshow.com/login");
        Assert.assertTrue(loginPage.isLoginDisplayed());
    }

    @When("user enters mobile {string} and clicks Next")
    public void user_enters_mobile_and_clicks_next(String mobile) {
        loginPage.enterMobile(mobile);
        loginPage.clickNext();
    }

    @When("user waits to enter OTP manually and clicks Login")
    public void user_waits_to_enter_otp_manually_and_clicks_login() {
        //loginPage.waitForUserToEnterOTP();   Implement wait logic for user input here
        loginPage.clickLogin();
    }



    @Then("{string} message should be displayed")
    public void message_should_be_displayed(String result) {
        Assert.assertTrue(loginPage.getverifyMsg().contains(result));
    }
    @When("user enters mobile {string}")
    public void user_enters_mobile(String mobile) {
        loginPage.enterMobile(mobile);
        
    }
    @Then("{string} message should be shown")
    public void message_should_be_shown(String result) {
        Assert.assertTrue(loginPage.geterrorMsg().contains(result));
    }
    @Then("Login modal should be displayed")
    public void login_modal_should_be_displayed() {
    	Assert.assertTrue(loginPage.isModalDisplayed());
    }
    @Then("{string} should be displayed")
    public void custom_text_should_be_displayed(String text) {
    	Assert.assertTrue(loginPage.login_modal_text().contains(text));
    }
}
