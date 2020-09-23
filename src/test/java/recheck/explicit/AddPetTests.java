package recheck.explicit;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.recheck.RecheckOptions;
import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobject.AddPetPO;
import pageobject.NavBarPO;
import pageobject.OwnerPO;
import recheck.BaseTests;

class AddPetTests extends BaseTests {

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
				.addIgnore("addowner.filter")
				.build();

		re = new RecheckImpl(recheckOptions);
		navBarPO = new NavBarPO(driver);
	}

	@Test
	void TestAddPetToOwner() {
		re.startTest("addPetToOwner");

		String name = "Arnold";
		String birthDate = "2017-11-30";

		AddPetPO addPetPO = navBarPO.goToFindOwner().searchFor(lastName).addPet();
		OwnerPO ownerPO = addPetPO.fillFieldsAndSubmit(name, birthDate);

		// NOTE fail if it is not a clean run because the name is already in use (these tests don't clean up)
		// NOTE fail on OLD version because the submit button is underneath the date picker

		re.check(driver, "addPetToOwner");
		re.capTest();
	}

	@Test
	void TestAddPetToOwnerWithEmptyFields() {
		re.startTest("addPetToOwnerWithEmptyFields");
		AddPetPO addPetPO = navBarPO.goToFindOwner().searchFor(lastName).addPet();

		addPetPO.clickSubmitOnly();

		re.check(driver, "addPetToOwnerWithEmptyFields");
		re.capTest();
	}

	@AfterEach
	void closeDriver() {
		navBarPO.closeDriver();
		re.cap();
	}

}
