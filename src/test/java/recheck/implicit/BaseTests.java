package recheck.implicit;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.recheck.RecheckOptions;
import de.retest.web.selenium.RecheckDriver;
import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import pageobject.HomePO;

public class BaseTests {

	public static final DriverManager.Browser BROWSER = DriverManager.Browser.CHROME;

	public static final String APP_HOME_URL = "http://localhost:8080";

	protected RecheckDriver recheckDriver;

	protected HomePO homePO;

	private static WebDriver getDriverInitialized() {
		WebDriver driver = DriverManager.getNewDriverInstance(BROWSER);
		driver.get(APP_HOME_URL);
		return driver;
	}

	@BeforeEach
	void createAndStartService() {
		WebDriver driver = getDriverInitialized();

		RecheckOptions recheckOptions = RecheckOptions.builder()
				//.enableReportUpload()
				.addIgnore("addowner_help_errors.filter.js")
				//.addIgnore("addowner.filter")
				.build();

		recheckDriver = new RecheckDriver((RemoteWebDriver) driver, recheckOptions);

		homePO = new HomePO(recheckDriver);
	}

	@AfterEach
	void closeDriver() {
		homePO.quitDriver();
	}

}
