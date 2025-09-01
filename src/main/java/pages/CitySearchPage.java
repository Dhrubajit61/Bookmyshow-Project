package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class CitySearchPage {
    WebDriver driver;
    WebDriverWait wait;
    // Locators
    private By cityInput = By.xpath("//input[@placeholder='Search for your city']"); // Update with actual locator
    private By citySearchModal=By.xpath("//div[@class=\"sc-hyqai2-0 hswna-D\"]");
    private By searchButton = By.id("searchBtn"); // Update with actual locator
   
    private By resultsList = By.cssSelector(".search-results .city-name"); // Update selector
    private By errorMsg = By.xpath("//div[@class=\"sc-fv93km-1 fZhJNQ\"][text()='No results found.']"); // Update with actual locator
   
    private By selectedcity=By.xpath("//span[@class='sc-1or3vea-16 gPcyDI']");
    private By cityDropdown = By.id("cityDropdown"); // Dropdown button
    private By viewAllCitiesOption = By.xpath("//p[@class=\'sc-p6ayv6-0 iwwDFF\'][text()='View All Cities']"); // Link in dropdown
    private By allCitiesList = By.cssSelector(".all-cities-list .city-name"); // List of all cities in "View All Cities"

    // Constructor
    public CitySearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    // Verify if city modal is loaded (e.g., city input visible)
    public boolean isPageLoaded() {
        try {
           // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        	wait.until(ExpectedConditions.visibilityOfElementLocated(cityInput));
            wait.until(ExpectedConditions.visibilityOfElementLocated(citySearchModal));
            return true;
        } catch (Exception e) {
        	System.err.println(e);
            return false;
        }
    }




    // Enter city name in search input
    public boolean typeCityName(String city) {
        //WebElement input = driver.findElement(cityInput);
        WebElement input=wait.until(ExpectedConditions.visibilityOfElementLocated(cityInput));
        input.clear();
        input.sendKeys(city);
       // input.sendKeys(Keys.ENTER); 
        return true;
    }
    public boolean enterCityName(String city) {
        //WebElement input = driver.findElement(cityInput);
        WebElement input=wait.until(ExpectedConditions.visibilityOfElementLocated(cityInput));
        input.clear();
        input.sendKeys(city);
        input.sendKeys(Keys.ENTER); 
        return true;
    }
    public boolean cityNameEntered(String city) {
       
    	WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedcity));
    	
    	
        String cityValue = inputElement.getText();
        System.out.println(cityValue);
        return cityValue != null && cityValue.equals(city);
    }
    public By getCityResult(String city) {
        String xpathExpression = "//span[@class='sc-mrh8fw-0 ghlPza' and text()='" + city + "']";
        return By.xpath(xpathExpression);
    }
    
    
    public boolean is_city_found(String city) {
    	try {
            By cityResult = getCityResult(city);
            return !driver.findElements(cityResult).isEmpty();
        } catch (Exception e) {
            return false;
        }
    	
    }

    // Click search button
    public void submitSearch() {
        driver.findElement(searchButton).click();
    }

    // Check if search results contain expected city name (case insensitive)
    public boolean areResultsCorrect(String cityName) {
    	 System.out.println(driver.findElement(selectedcity).getText());
        return driver.findElement(selectedcity).getText().equals(cityName);
    }

    // Get displayed error message text
    public String getErrorMessage() {
        return driver.findElement(errorMsg).getText();
    }
    public By getCityicon(String cityicon) {
    	String xpathExpression = "//img[@class='sc-eykeme-1 iVbwwW' and @alt='" + cityicon + "']";
    	return By.xpath(xpathExpression);


    }

    // Click on city icon matching cityIcon name (tooltip or alt attribute assumed)
    public void clickCityIcon(String cityIconName) {
        WebElement icon = driver.findElement(getCityicon(cityIconName));
        try {
        	icon.click();
        }catch(Exception e) {
        	System.out.println(e);
        }
//        for (WebElement icon : icons) {
//            String altText = icon.getAttribute("alt"); // or use tooltip attribute
//            if (altText != null && altText.equalsIgnoreCase(cityIconName)) {
//                icon.click();
//                break;
//            }
//        }
    }

    // Open city dropdown list
    public boolean openCityDropdown() {
        //driver.findElement(cityDropdown).click();
        
        if(isPageLoaded()) {
        	try {
        		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                wait.until(ExpectedConditions.visibilityOfElementLocated(viewAllCitiesOption));
                return true;
        	}catch (Exception e) {
				return false;
			}
        }
        return false;
    }

    // Select "View All Cities" option from dropdown
    public boolean selectViewAllCities() {
    	try {
    		driver.findElement(viewAllCitiesOption).click();
    		return true;
    	}catch(Exception e){
    	return false;
    	}	
        
    }
    public By targetCity(String city) {
    	String xpathExpression = "//li[@class='sc-1a0jimq-0 hhOIxv' and text()='" + city + "']";
    	return By.xpath(xpathExpression);
    } 

    // Validate some cities not in popular cities list are present in all cities list
    public boolean isTargetCityDisplayed(String city) {
    	System.out.println(driver.findElement(targetCity(city)).getText());
    	return driver.findElement(targetCity(city)).isDisplayed();

    }
}
