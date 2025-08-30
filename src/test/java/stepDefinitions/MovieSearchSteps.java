package stepDefinitions;

import base.DriverSetup;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pages.MovieSearchPage;

public class MovieSearchSteps {
    WebDriver driver = DriverSetup.getDriver();
    MovieSearchPage moviePage = new MovieSearchPage(driver);

    @Given("user is on the Home page")
    public void user_is_on_home_page() {
        driver.get("https://in.bookmyshow.com/"); 
    }

    @When("user type and enter city {string}")
    public void user_selects_city(String city) throws InterruptedException {
        moviePage.selectCity(city);
        Thread.sleep(2000);
    }
    @Given("user is on the Home page and click a city")
    public void user_is_on_the_Home_page_and_click_a_city(){
    	driver.get("https://in.bookmyshow.com/");
    	moviePage.selectCity("Kolkata");
    }
    
    @Given("user is on the Movie Search screen")
    public void user_is_on_the_Movie_Search_screen() {
    	driver.get("https://in.bookmyshow.com/");
    	moviePage.selectCity("Kolkata");
    }
    

    @Then("user searches for running movie {string} from recommended section")
    public void user_searches_running_movie(String movie) {
    	Assert.assertTrue(moviePage.find_movie_from_recommend_section(movie));
    
    }
    
    @Then("the {string} should be present in recommended movie section playing in {string}")
    public void movie_present_in_recommended_section_in_the_given_city(String movie, String city) {
    	Assert.assertTrue(moviePage.check_movie_present_in_the_given_city(movie,city));
    }

//    @Then("search results should display {string} playing in {string}")
//    public void search_results_should_display_movie_playing_in_city(String movie, String city) {
//        Assert.assertTrue(moviePage.areResultsCorrect(movie, city));
//    }
//
//    @Given("user is on the Movie Search screen")
//    public void user_is_on_movie_search_screen() {
//        moviePage.goToMovieSearchScreen();
//    }

    @Then("all required UI elements should be displayed correctly")
    public void all_ui_elements_should_be_displayed_correctly() {
        Assert.assertTrue(moviePage.areUIElementsDisplayed());
    }

    @When("user clicks on the Movies tab")
    public void user_clicks_on_movies_tab() {
        moviePage.clickMoviesTab();
    }

    @When("user clicks the Explore Upcoming Movies link")
    public void user_clicks_explore_upcoming_movies_link() {
        moviePage.clickExploreUpcomingMovies();
    }

    @Then("the {string} link should be visible")
    public void link_should_be_visible(String linkText) {
        Assert.assertTrue(moviePage.isCinemasNearYouLinkVisible());
    }
    
}
