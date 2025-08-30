package base;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
public class DriverSetup {
    private static WebDriver driver;
    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigLoader.get("browser");
            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }
            driver.manage().window().maximize();
            int timeout = Integer.parseInt(ConfigLoader.get("timeout"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        }
        return driver;
    }
    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}