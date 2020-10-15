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

public class EditPetTests extends BaseTests {

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
	void testEditWithValidData() {
		re.startTest("editPet");
		String name = "Arnold Jr.";
		String birthDate = "2019-11-30";

		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).editFirstPet();

		addEditPetPO.fillFieldsAndSubmit(name, birthDate);

		re.check(driver, "editPet");
		re.capTest();
	}

	@Test
	void testEditPetWithEmptyFields() {
		re.startTest("editPetWithEmptyDescription");
		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).editFirstPet();

		addEditPetPO.setName("");
		addEditPetPO.setBirthDate("");
		addEditPetPO.submit();

		re.check(driver, "editPetWithEmptyDescription");
		re.capTest();
	}

	@AfterEach
	void closeDriver() {
		homePO.closeDriver();
		re.cap();
	}

}
