package assertions;

import driver.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobject.AddPetPO;
import pageobject.NavBarPO;
import pageobject.OwnerPO;
import recheck.BaseTests;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddPetTests extends BaseTests {

	private static NavBarPO navBarPO;

	private final String lastName = "Black";

	@BeforeAll
	static void createAndStartService() {
		WebDriver driver = DriverManager.getNewDriverInstance(BROWSER);
		navBarPO = new NavBarPO(driver);
	}

	@Test
	void TestAddPetToOwner() {
		String name = "Arnold";
		String birthDate = "2017-11-30";

		AddPetPO addPetPO = navBarPO.goToFindOwner().searchFor(lastName).addPet();

		OwnerPO ownerPO = addPetPO.fillFieldsAndSubmit(name, birthDate);

		List<String> petNames = ownerPO.getPetNames();

		assertTrue(petNames.stream().anyMatch(n -> n.equals(name)));
		// NOTE fail if it is not a clean run because the name is already in use (these tests don't clean up)
		// NOTE fail on OLD version because the submit button is underneath the date picker
	}

	@Test
	void TestAddPetToOwnerWithEmptyFields() {
		AddPetPO addPetPO = navBarPO.goToFindOwner().searchFor(lastName).addPet();

		addPetPO.clickSubmitOnly();

		assertEquals("is required", addPetPO.getNameError());
		assertEquals("is required", addPetPO.getBirthDateError());
	}

	@AfterAll
	static void closeDriver() {
		navBarPO.closeDriver();
	}

}
