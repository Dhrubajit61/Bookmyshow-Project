package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;


import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.spi.CurrencyNameProvider;

import base.DriverSetup;
import pages.CitySearchPage;

public class MovieSearchPage {
    WebDriver driver;
    CitySearchPage citySearchPage;
    WebDriverWait wait;
    WebElement targetMovieResult;

    // Locators - update based on actual page
    private By clickmoviesearchinput1=By.xpath("//span[@class=\'sc-1or3vea-15 bMjnfo\'][text()='Search for Movies, Events, Plays, Sports and Activities']");
    private By clickmoviesearchinput2=By.xpath("//input[@class=\'sc-vuznvr-5 extnng\']");
    private By moviecategory=By.xpath("//span[@class=\'sc-vuznvr-11 AJdUy\'][text()='Movies']");
    private By BookTickets = By.xpath("(//div[@class='sc-1vmod7e-2 ixpVNC']//span[text()='Book tickets'])[1]");
    private By bookticketpopup=By.xpath("//div[@class='sc-1k6uqqy-0 icKFjI']");
    private By movietheatre=By.xpath("(//span[@class='sc-1qdowf4-0 kVfEkA'])[1]");
    
    private By cityDropdown = By.id("cityDropdown");
    private By cityOptions = By.cssSelector("#cityDropdownOptions li");
    private By movieInput = By.id("movieSearchInput");
    private By searchButton = By.id("searchButton");
    private By searchResults = By.cssSelector(".search-results .movie-title");
    private By moviesTab = By.xpath("//a[@class='sc-1or3vea-20 fsiaZi'][text()='Movies']");
    private By exploreUpcomingLink = By.xpath("//div[@class='sc-133848s-2 sc-1t5vwh0-1 ccqrhI dnWEBR']/img");
    private By cinemasNearYouLink = By.linkText("Cinemas near you");
   // private By uiElements = By.cssSelector(".search-bar, #moviesTab, #cityDropdown");
    
    private By el1 = By.cssSelector(".sc-1or3vea-13.kbbBtJ");
    private By el2 = By.cssSelector(".sc-7o7nez-0.KnxOF");
    private By el3 = By.cssSelector(".sc-lnhrs7-0.jMvQQo");
    private By el4 = By.cssSelector(".sc-1or3vea-9.iFYeNM");
    
    public MovieSearchPage(WebDriver driver) {
    	this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        this.citySearchPage = new CitySearchPage(driver);
    }

    public void selectCity(String city) {
    	citySearchPage.enterCityName(city);
    }
    

//    public void enterMovieName(String movie) {
//    	wait.until(ExpectedConditions.elementToBeClickable(clickmoviesearchinput1)).click();
//    	WebElement input= wait.until(ExpectedConditions.elementToBeClickable(clickmoviesearchinput2));
//    	input.click();
//    	input.clear();
//        input.sendKeys(movie);
//    }
//    public void clickmoviecategory() {
//    	wait.until(ExpectedConditions.elementToBeClickable(moviecategory)).click();
//    	
//    }
    public boolean find_movie_from_recommend_section(String movie) {
        String xpathExpression = "//div[contains(@class, 'sc-133848s-2') and contains(@class, 'sc-1t5vwh0-1') and contains(@class, 'ccqrhI') and contains(@class, 'dnWEBR')]//img[@alt='" + movie + "']";

        try {
            targetMovieResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
            String altText = targetMovieResult.getAttribute("alt");
            System.out.println("Found movie alt: " + altText);
            return altText.equalsIgnoreCase(movie);
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    

    public boolean check_movie_present_in_the_given_city(String movie, String city) {
    	targetMovieResult.click();
    	String currentUrl = driver.getCurrentUrl();
    	return currentUrl.toLowerCase().contains(city.toLowerCase()); 
    	
    }
   
    
//    public boolean check_movie_present_in_the_given_city(String movie, String city) {
//    	targetMovieResult.click();
//    	wait.until(ExpectedConditions.elementToBeClickable(BookTickets)).click();
//    	WebElement ticketPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(bookticketpopup));
//    	if (ticketPopup != null) {
//    		wait.until(ExpectedConditions.elementToBeClickable(ticketPopup)).click();
//    	}
//    	WebElement firstmovietheatre=wait.until(ExpectedConditions.visibilityOfElementLocated(movietheatre));
//    	String spanText = firstmovietheatre.getText();
//    	if (spanText.contains(city)) {
//    	    System.out.println(spanText);
//    	    return true;
//    	} else {
//    	    System.out.println(spanText+"not found");
//    	    return false;
//    	}
//    	
//    	
//    }


//    public WebElement waitForMovieOrNull(String movie) {
//        try {
//            return wait.until(ExpectedConditions.visibilityOfElementLocated(find_movie_from_recommend_section(movie)));
//        } catch (org.openqa.selenium.TimeoutException e) {
//            return null; // Movie not found within the timeout
//        }
//    }
//    
//
//    public void submitSearch(String movie) {
//    	
//    	WebElement foundMovie = waitForMovieOrNull(movie);
//    	if (foundMovie != null) {
//    	    // Movie was found, proceed with actions
//    	    foundMovie.click();
//    	} else {
//    	    // Handle "movie not found": log, assert, or alternate path
//    	    System.out.println("Movie not found: " + movie);
//    	}
//
//        
//    }
//    public By getmovieresultexpression(String movie) {
//        String xpathExpression = "//h1[@class='sc-qswwm9-6 ea-drWB' and text()='" + movie + "']";
//        return By.xpath(xpathExpression);
//    }

    public boolean areResultsCorrect(String movie, String city) {
    	return false;
    	
       
    }
    

    public void goToMovieSearchScreen() {
        driver.get("https://yourapp.com/moviesearch"); // Update URL accordingly
    }

    public boolean areUIElementsDisplayed() {
    	List<By> locators = Arrays.asList(el1, el2, el3, el4);

        try {
            for (By locator : locators) {
                // Wait for each element individually
                wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            }
            return true; // ✅ All elements found
        } catch (Exception e) {
            return false; // ❌ One or more elements missing
        }
    }
    

    public void clickMoviesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(moviesTab)).click();
        
    }

    public void clickExploreUpcomingMovies() {
    	String url="https://assets-in.bmscdn.com/discovery-catalog/collections/tr:w-1440,h-120/coming-soon-web-collection-202012090733.png";
    	try {
            WebElement upcomingmovieslink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='" + url + "']"))
            );
            upcomingmovieslink.click();
        } catch (TimeoutException e) {
            System.out.println("Upcoming movies link not found within wait time.");
        }
    }

    public boolean isCinemasNearYouLinkVisible() {
    	String url="https://assets-in.bmscdn.com/discovery-catalog/collections/tr:w-1440,h-120/now-showing-web-collection-202012090733.png";
    	try {
            WebElement cinemasNearYou = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='" + url + "']"))
            );
            return cinemasNearYou.isDisplayed(); // safe check
        } catch (TimeoutException e) {
            return false; // element not found within wait time
        }
    }
}
