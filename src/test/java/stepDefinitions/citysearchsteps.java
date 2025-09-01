package stepDefinitions;


import base.DriverSetup;
import io.cucumber.java.en.*;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CitySearchPage;

public class citysearchsteps {
    WebDriver driver = DriverSetup.getDriver();
    CitySearchPage citySearchPage = new CitySearchPage(driver);

    @Given("user is on the City Search page")
    public void user_is_on_the_city_search_page() {
        driver.get("https://in.bookmyshow.com/"); // update URL accordingly
        Assert.assertTrue(citySearchPage.isPageLoaded());
    }

    @When("user enters a valid city name {string}")
    public void user_enters_a_valid_city_name(String city) {
        citySearchPage.typeCityName(city);
       
    }

    @Then("search results should display cities matching {string}")
    public void search_results_should_display_cities_matching(String city) {
    	Assert.assertTrue(citySearchPage.is_city_found(city));
    }

    @When("user enters an invalid city name {string}")
    public void user_enters_an_invalid_city_name(String invalidCity) {
    	citySearchPage.typeCityName(invalidCity);
    	
    }

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String expectedErrorMsg) {
        String actualErrorMsg = citySearchPage.getErrorMessage();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }

    @When("user selects the city icon for {string}")
    public void user_selects_the_city_icon_for(String cityIcon) {
        citySearchPage.clickCityIcon(cityIcon);
    }

    @Then("search results should display cities related to {string}")
    public void search_results_should_display_cities_related_to(String cityIcon) {
        Assert.assertTrue(citySearchPage.areResultsCorrect(cityIcon));
    }

    @When("user clicks on city name dropdown and selects \"View All Cities\"")
    public void user_clicks_on_city_name_dropdown_and_selects_view_all_cities() {
        if(citySearchPage.openCityDropdown()) {
        Assert.assertTrue(citySearchPage.selectViewAllCities());
        }else {
        	Assert.assertTrue(false);
        }
    }

    @Then("the list should include few {string}")
    public void the_list_should_include_few_city(String city) {
        Assert.assertTrue(citySearchPage.isTargetCityDisplayed(city));
    }
}
