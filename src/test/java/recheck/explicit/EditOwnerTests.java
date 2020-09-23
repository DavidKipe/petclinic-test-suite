package recheck.explicit;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.recheck.RecheckOptions;
import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobject.AddEditOwnerPO;
import pageobject.NavBarPO;
import pageobject.OwnerPO;
import recheck.BaseTests;

class EditOwnerTests extends BaseTests {

	private static NavBarPO navBarPO;

	private final String lastName = "Black"; // lastname to find and test

	Recheck re;
	WebDriver driver;

	@BeforeEach
	void createAndStartService() {
		driver = DriverManager.getNewDriverInstance(BaseTests.BROWSER);

		RecheckOptions recheckOptions = RecheckOptions.builder()
				//.enableReportUpload()
				.addIgnore("addowner_help_errors.filter.js")
				.addIgnore("addowner.filter")
				.build();

		re = new RecheckImpl(recheckOptions);
		navBarPO = new NavBarPO(driver);
	}

	@Test
	void TestEditOwnerWithValidData() {
		re.startTest("editOwnerWithValidData");

		String newFirstName = "Bob"; // new first name
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setFirstName(newFirstName);
		OwnerPO ownerPO = addEditOwnerPO.submit();

		re.check(driver, "editOwnerWithValidData");
		re.capTest();
	}

	@Test
	void TestEditOwnerLongTelephone() {
		re.startTest("editOwnerLongTelephone");

		String newTelephone = "010123456789";
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setTelephone(newTelephone);
		addEditOwnerPO.clickSubmitOnly();

		re.check(driver, "editOwnerLongTelephone");
		re.capTest();
	}

	@Test
	void TestEditOwnerNotNumericTelephone() {
		re.startTest("editOwnerNotNumericTelephone");

		String newTelephone = "abcdef";
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setTelephone(newTelephone);
		addEditOwnerPO.clickSubmitOnly();

		re.check(driver, "editOwnerNotNumericTelephone");
		re.capTest();
	}

	@Test
	void TestEditOwnerWithEmptyField() {
		re.startTest("editOwnerWithEmptyField");

		String newTelephone = "";
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setTelephone(newTelephone);
		addEditOwnerPO.clickSubmitOnly();

		re.check(driver, "editOwnerWithEmptyField");
		re.capTest();
	}

	@AfterEach
	void closeDriver() {
		navBarPO.closeDriver();
		re.cap();
	}

}
