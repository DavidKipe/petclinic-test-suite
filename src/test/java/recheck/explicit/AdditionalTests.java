package recheck.explicit;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.recheck.RecheckOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobject.HomePO;
import recheck.BaseTests;

public class AdditionalTests extends BaseTests {

	private static HomePO homePO;

	Recheck re;
	WebDriver driver;

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

	@Test
	void testWelcomeMessage() {
		re.startTest("welcomeMessage");
		re.check(driver, "welcomeMessage");
		re.capTest();
	}

	@AfterEach
	void closeDriver() {
		homePO.closeDriver();
		re.cap();
	}

}
