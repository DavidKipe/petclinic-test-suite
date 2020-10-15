package recheck.explicit;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.recheck.RecheckOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobject.FindOwnerPO;
import pageobject.HomePO;
import pageobject.OwnersListPO;
import recheck.BaseTests;

class FindOwnerTests extends BaseTests {

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
	void testFindExistingOwner() {
		re.startTest("findExistingOwner");

		String lastName = "Black";
		homePO.goToFindOwner().searchFor(lastName);

		re.check(driver, "findExistingOwner");
		re.capTest();
	}

	@Test
	void testFindNotExistingOwner() {
		re.startTest("findNotExistingOwner");

		FindOwnerPO findOwnerPO = homePO.goToFindOwner();
		findOwnerPO.searchFor("nobody");

		re.check(driver, "findNotExistingOwner");
		re.capTest();
	}

	@Test
	void testFindAllOwners() {
		re.startTest("findAllOwners");

		homePO.goToFindOwner().searchAll();

		re.check(driver, "findAllOwners");
		re.capTest();
	}

	@Test
	void testFindExistingOwnerFromAll() {
		re.startTest("findExistingOwnerFromAll");

		String fullName = "George Franklin";
		OwnersListPO ownersListPO = homePO.goToFindOwner().searchAll();
		ownersListPO.clickOn(fullName);

		re.check(driver, "findExistingOwnerFromAll");
		re.capTest();
	}

	@AfterEach
	void closeDriver() {
		homePO.closeDriver();
		re.cap();
	}

}
