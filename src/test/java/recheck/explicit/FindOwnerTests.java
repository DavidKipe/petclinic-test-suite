package recheck.explicit;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.recheck.RecheckOptions;
import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobject.FindOwnerPO;
import pageobject.NavBarPO;
import pageobject.OwnerPO;
import pageobject.OwnersListPO;
import recheck.BaseTests;

class FindOwnerTests extends BaseTests {

	private static NavBarPO navBarPO;

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
	void TestFindExistingOwner() {
		re.startTest("findExistingOwner");

		String lastName = "Black";
		OwnerPO ownerPO = navBarPO.goToFindOwner().searchFor(lastName);

		re.check(driver, "findExistingOwner");
		re.capTest();
	}

	@Test
	void TestFindNotExistingOwner() {
		re.startTest("findNotExistingOwner");

		FindOwnerPO findOwnerPO = navBarPO.goToFindOwner();
		findOwnerPO.searchFor("nobody");

		re.check(driver, "findNotExistingOwner");
		re.capTest();
	}

	@Test
	void TestFindAllOwners() {
		re.startTest("findAllOwners");

		OwnersListPO ownersListPO = navBarPO.goToFindOwner().searchAll();

		re.check(driver, "findAllOwners");
		re.capTest();
	}

	@Test
	void TestFindExistingOwnerFromAll() {
		re.startTest("findExistingOwnerFromAll");

		String fullName = "George Franklin";
		OwnersListPO ownersListPO = navBarPO.goToFindOwner().searchAll();
		OwnerPO ownerPO = ownersListPO.clickOn(fullName);

		re.check(driver, "findExistingOwnerFromAll");
		re.capTest();
	}

	@AfterEach
	void closeDriver() {
		navBarPO.closeDriver();
		re.cap();
	}

}
