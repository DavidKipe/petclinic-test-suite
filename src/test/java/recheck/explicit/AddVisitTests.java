package recheck.explicit;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.recheck.RecheckOptions;
import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobject.AddVisitPO;
import pageobject.NavBarPO;
import recheck.BaseTests;

public class AddVisitTests extends BaseTests {

	private static NavBarPO navBarPO;

	private final String lastName = "Black";

	Recheck re;
	WebDriver driver;

	@BeforeEach
	void createAndStartService() {
		driver = DriverManager.getNewDriverInstance(BaseTests.BROWSER);

		RecheckOptions recheckOptions = RecheckOptions.builder()
				//.enableReportUpload()
				.addIgnore("addowner_help_errors.filter.js")
				//.addIgnore("addowner.filter")
				.build();

		re = new RecheckImpl(recheckOptions);
		navBarPO = new NavBarPO(driver);
	}

	@Test
	void testAddVisitToPet() {
		re.startTest("addVisitToPet");
		String date = "2020-10-30";
		String description = "Routine";

		AddVisitPO addVisitPO = navBarPO.goToFindOwner().searchFor(lastName).addVisitToFirstPet();

		addVisitPO.fillFieldsAndSubmit(date, description);

		re.check(driver, "addVisitToPet");
		re.capTest();
	}

	@Test
	void testAddVisitToPetWithEmptyDescription() {
		re.startTest("addVisitToPetWithEmptyDescription");
		AddVisitPO addVisitPO = navBarPO.goToFindOwner().searchFor(lastName).addVisitToFirstPet();

		addVisitPO.submit();

		re.check(driver, "addVisitToPetWithEmptyDescription");
		re.capTest();

	}

	@AfterEach
	void closeDriver() {
		navBarPO.closeDriver();
		re.cap();
	}

}
