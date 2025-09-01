package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.DriverSetup;
import pages.CitySearchPage;

public class GiftCardPage {
    WebDriver driver;
    CitySearchPage citySearchPage;
    WebDriverWait wait;
    private By giftCardSection = By.xpath("//a[@class=\"sc-1or3vea-21 hxCjFQ\"][text()='Gift Cards']");
    private By checkgiftcardbalancetext = By.xpath("//div[@class=\"sc-12r1n02-1 ehSquB\"][text()='Check Gift Card Balance']");
    private By cardNumberInput = By.xpath("//input[@class=\'sc-ifipx4-9 izZQKd\'][@placeholder='Enter your gift card voucher code']");
    private By checkbalancesubmitButton = By.xpath("//div[contains(@class, 'sc-zgl7vj-7') and contains(@class, 'kdBUB')]");
    private By errorMessage = By.xpath("//p[@class=\"sc-12r1n02-9 cKoKSF\"]");
    
    public GiftCardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        this.citySearchPage = new CitySearchPage(driver);
    }
    
    

    

    public void goToGiftCardSection() {
    	
    	citySearchPage.clickCityIcon("Kolkata");
    	try {
    	    
    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sc-1j4wkcy-0.dDhfvt")));
    	    wait.until(ExpectedConditions.elementToBeClickable(giftCardSection)).click();
    	    
    	} catch (Exception e) {
    	    System.err.println("Timed out waiting for elements in Gift Card section: " + e.getMessage());
    	    throw e;
    	}


    }
    public By getCheckBalanceIcon(String iconname) {
    	String xpathExpression = "//div[@class='sc-12r1n02-1 ehSquB' and text()='" + iconname + "']";
        return By.xpath(xpathExpression);
    	
    }

    public boolean isCheckBalanceIconVisible(String iconName) {
    	try {
    		
        	return wait.until(ExpectedConditions.visibilityOfElementLocated(getCheckBalanceIcon(iconName))).isDisplayed();
    	}catch(Exception e) {
    		System.err.println(e);
    		return false;
    	}
    	
    }

    public void clickCheckBalanceIcon(String iconName) {
    	try {
    		
        	wait.until(ExpectedConditions.visibilityOfElementLocated(getCheckBalanceIcon(iconName))).click();
    		
    	}catch (Exception e) {
			System.err.println(e);
		}    	
        
    }
    

    public void enterGiftCardNumber(String cardNumber) {
    	try {
    		
        	WebElement cardinput=wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberInput));
            cardinput.clear();
            if(cardNumber != null && cardNumber.length() >= 10) {
            	cardinput.sendKeys(cardNumber);
            }else {
                System.err.println("Invalid card number: must be at least 10 characters");          
            }
            
    	}catch(Exception e) {
    		System.err.println(e);
    	}
    	
    }

    public void submitCheckBalance() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(checkbalancesubmitButton)).click();
        } catch (Exception e) {
            System.err.println("Error clicking submit button: " + e.getMessage());
            // Optionally: throw e; to propagate the failure
        }
    }


    public boolean getErrorMessage(String expectedMsg) {
        try {
            WebElement errormsg = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            String actualMsg = errormsg.getText().trim();
            System.out.println("Error msg got: " + actualMsg);

            // Compare expected and actual
            return actualMsg.equals(expectedMsg);

        } catch (Exception e) {
            System.err.println("Error message not found: " + e);
            return false;  // return false if element not found or exception occurs
        }
    }

}
