package assertions;

import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pageobject.HomePO;

class BaseTests {

	private static final DriverManager.Browser BROWSER = DriverManager.Browser.CHROME;

	private static final String APP_HOME_URL = "http://localhost:8080";

	protected HomePO homePO;

	public static WebDriver getDriverInitialized() {
		WebDriver driver = DriverManager.getNewDriverInstance(BROWSER);
		driver.get(APP_HOME_URL);
		return driver;
	}

	@BeforeEach
	void createAndStartService() {
        homePO = new HomePO(getDriverInitialized());
	}

	@AfterEach
	void closeDriver() {
		homePO.quitDriver();
	}


}
