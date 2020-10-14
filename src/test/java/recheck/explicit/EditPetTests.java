package recheck.explicit;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.recheck.RecheckOptions;
import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobject.AddEditPetPO;
import pageobject.NavBarPO;
import recheck.BaseTests;

public class EditPetTests {

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
	void testEditWithValidData() {
		re.startTest("editPet");
		String name = "Arnold Jr.";
		String birthDate = "2019-11-30";

		AddEditPetPO addEditPetPO = navBarPO.goToFindOwner().searchFor(lastName).editFirstPet();

		addEditPetPO.fillFieldsAndSubmit(name, birthDate);

		re.check(driver, "editPet");
		re.capTest();
	}

	@Test
	void testEditPetWithEmptyFields() {
		re.startTest("editPetWithEmptyDescription");
		AddEditPetPO addEditPetPO = navBarPO.goToFindOwner().searchFor(lastName).editFirstPet();

		addEditPetPO.setName("");
		addEditPetPO.setBirthDate("");
		addEditPetPO.submit();

		re.check(driver, "editPetWithEmptyDescription");
		re.capTest();
	}

	@AfterEach
	void closeDriver() {
		navBarPO.closeDriver();
		re.cap();
	}

}
