package recheck.explicit;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.recheck.RecheckOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobject.AddEditOwnerPO;
import pageobject.HomePO;
import recheck.BaseTests;

class EditOwnerTests extends BaseTests {

	private static HomePO homePO;

	private final String lastName = "Black"; // lastname to find and test

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
	void testEditOwnerWithValidData() {
		re.startTest("editOwnerWithValidData");

		String newFirstName = "Bob"; // new first name
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setFirstName(newFirstName);
		addEditOwnerPO.submit();

		re.check(driver, "editOwnerWithValidData");
		re.capTest();
	}

	@Test
	void testEditOwnerLongTelephone() {
		re.startTest("editOwnerLongTelephone");

		String newTelephone = "010123456789";
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setTelephone(newTelephone);
		addEditOwnerPO.submit();

		re.check(driver, "editOwnerLongTelephone");
		re.capTest();
	}

	@Test
	void testEditOwnerNotNumericTelephone() {
		re.startTest("editOwnerNotNumericTelephone");

		String newTelephone = "abcdef";
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setTelephone(newTelephone);
		addEditOwnerPO.submit();

		re.check(driver, "editOwnerNotNumericTelephone");
		re.capTest();
	}

	@Test
	void testEditOwnerWithEmptyField() {
		re.startTest("editOwnerWithEmptyField");

		String newTelephone = "";
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setTelephone(newTelephone);
		addEditOwnerPO.submit();

		re.check(driver, "editOwnerWithEmptyField");
		re.capTest();
	}

	@AfterEach
	void closeDriver() {
		homePO.closeDriver();
		re.cap();
	}

}
