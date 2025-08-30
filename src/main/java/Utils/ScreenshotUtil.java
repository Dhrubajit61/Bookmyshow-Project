package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static void capture(WebDriver driver, String filePath) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
