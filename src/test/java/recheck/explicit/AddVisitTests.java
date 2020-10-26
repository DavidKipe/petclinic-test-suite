package recheck.explicit;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.recheck.RecheckOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobject.AddVisitPO;
import pageobject.HomePO;
import recheck.BaseTests;

public class AddVisitTests extends BaseTests {

	private static HomePO homePO;

	private final String lastName = "Rodriquez";
	private final String date = "2020-10-30";

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
	void testAddVisitToPet() {
		re.startTest("addVisitToPet");
		String description = "Routine";

		AddVisitPO addVisitPO = homePO.goToFindOwner().searchFor(lastName).addVisitToFirstPet();

		addVisitPO.fillFieldsAndSubmit(date, description);

		re.check(driver, "addVisitToPet");
		re.capTest();
	}

	@Test
	void testAddVisitToPetWithEmptyDescription() {
		re.startTest("addVisitToPetWithEmptyDescription");
		AddVisitPO addVisitPO = homePO.goToFindOwner().searchFor(lastName).addVisitToFirstPet();

		addVisitPO.setDate(date);
		addVisitPO.submit();

		re.check(driver, "addVisitToPetWithEmptyDescription");
		re.capTest();

	}

	@AfterEach
	void closeDriver() {
		homePO.closeDriver();
		re.cap();
	}

}
