package recheck.explicit;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.recheck.RecheckOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobject.AddEditPetPO;
import pageobject.HomePO;
import recheck.BaseTests;

class AddPetTests extends BaseTests {

	private static HomePO homePO;

	private final String lastName = "Black";

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
	void testAddPetToOwner() {
		re.startTest("addPetToOwner");

		String name = "Arnold";
		String birthDate = "2017-11-30";

		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();
		addEditPetPO.fillFieldsAndSubmit(name, birthDate);

		// NOTE fail if it is not a clean run because the name is already in use (these tests don't clean up)
		// NOTE fail on OLD version because the submit button is underneath the date picker

		re.check(driver, "addPetToOwner");
		re.capTest();
	}

	@Test
	void testAddPetToOwnerWithEmptyFields() {
		re.startTest("addPetToOwnerWithEmptyFields");
		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();

		addEditPetPO.submit();

		re.check(driver, "addPetToOwnerWithEmptyFields");
		re.capTest();
	}

	@AfterEach
	void closeDriver() {
		homePO.closeDriver();
		re.cap();
	}

}
