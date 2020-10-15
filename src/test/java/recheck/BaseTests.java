package recheck;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;

public class BaseTests {

	public static final DriverManager.Browser BROWSER = DriverManager.Browser.CHROME;

	public static final String APP_HOME_URL = "http://localhost:8080";

	public static WebDriver getDriverInitialized() {
		WebDriver driver = DriverManager.getNewDriverInstance(BROWSER);
        driver.get(APP_HOME_URL);
        return driver;
	}

}
