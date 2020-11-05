package recheck.explicit;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.recheck.RecheckOptions;
import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pageobject.HomePO;

public class BaseTests {

	public static final DriverManager.Browser BROWSER = DriverManager.Browser.CHROME;

	public static final String APP_HOME_URL = "http://localhost:8080";

	private Recheck re;
	private WebDriver driver;

	protected static HomePO homePO;

	private static WebDriver getDriverInitialized() {
		WebDriver driver = DriverManager.getNewDriverInstance(BROWSER);
		driver.get(APP_HOME_URL);
		return driver;
	}

	protected void startTest(String testName) {
		re.startTest(testName);
	}

	protected void endTest(String testName) {
		re.check(driver, testName);
		re.capTest();
	}

	@BeforeEach
	void createAndStartService() {
		driver = getDriverInitialized();

		RecheckOptions recheckOptions = RecheckOptions.builder()
				//.enableReportUpload()
				.addIgnore("addowner_help_errors.filter.js")
				//.addIgnore("addowner.filter")
				.build();
		re = new RecheckImpl(recheckOptions);

		homePO = new HomePO(driver);
	}

	@AfterEach
	void closeDriver() {
		homePO.closeDriver();
		re.cap();
	}

}
