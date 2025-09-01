package pages;

import java.time.Duration;
import java.time.chrono.IsoChronology;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;

    By mobileField = By.id("userMobileNumber");
    By nextButton = By.xpath("//div[@class=\"sc-zgl7vj-8 hpVUcY\"][text()='Continue']");
    By otpInputs = By.cssSelector("div.sc-2vmyj1-4.gSRumm > input.sc-2vmyj1-5.boXfAA");
    By loginButton = By.xpath("//div[@class=\"sc-zgl7vj-8 hpVUcY\"][contains(text(),'Continue')]");
    By verify_mob_number_msg = By.xpath("//div[@class=\"sc-2vmyj1-17 hcvbMz\"][text()='Verify your Mobile Number']");
    By invalid_mob_num_msg=By.xpath("//div[@class=\"sc-z1ldnh-12 Qsrzn\"][text()='Invalid mobile number']");
    By loginmodal=By.xpath("//div[@class=\"sc-z1ldnh-2 hpLowx\"]");
    By loginmodaltext=By.xpath("//h1[@class=\"sc-1ydq0aj-4 fjijyY\"][text()='Get Started']");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterMobile(String mobile) {
        driver.findElement(mobileField).sendKeys(mobile);
    }

    public void clickNext() {
        driver.findElement(nextButton).click();
    }
    public void waitForUserToEnterOTP() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(d -> {
            List<WebElement> inputs = d.findElements(otpInputs);
            if (inputs.size() < 6) return false;
            for (WebElement input : inputs) {
                String val = input.getAttribute("value");
                if (val == null || val.trim().isEmpty()) return false;
            }
            return true;
        });
    }

    public void enterOTP(String otp) throws InterruptedException {
        waitForUserToEnterOTP();
        Thread.sleep(30000);
        //driver.findElement(loginButton).click();
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getverifyMsg() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.visibilityOfElementLocated(verify_mob_number_msg));
        return driver.findElement(verify_mob_number_msg).getText();
    }

    public boolean isLoginDisplayed() {
        return driver.findElement(mobileField).isDisplayed();
    }
    public String geterrorMsg() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.visibilityOfElementLocated(invalid_mob_num_msg));
        return driver.findElement(invalid_mob_num_msg).getText();
    }
    public boolean isModalDisplayed() {
    	return driver.findElement(loginmodal).isDisplayed();
    }
    public String login_modal_text() {
    	return driver.findElement(loginmodaltext).getText();
    }
}
