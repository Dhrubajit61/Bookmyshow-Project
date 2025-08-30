package hooks;

import base.DriverSetup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.cucumber.java.Scenario;
import Utils.ScreenshotUtil;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Hooks {

    @Before
    public void setUp() {
        DriverSetup.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        String timeStamp = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        
        // Replace all invalid filename characters with "_"
        String safeScenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9._-]", "_");

        String folder = scenario.isFailed() ? "screenshots/failed/" : "screenshots/passed/";
        String path = folder + safeScenarioName + "_" + timeStamp + ".png";
        
        ScreenshotUtil.capture(DriverSetup.getDriver(), path);
        DriverSetup.quit();
    }

}
