package assertions;

import dataclass.Pet;
import driver.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobject.AddEditPetPO;
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
	void testAddPetToOwner() {
		String name = "Arnold";
		String birthDate = "2017-11-30";

		AddEditPetPO addEditPetPO = navBarPO.goToFindOwner().searchFor(lastName).addPet();

		OwnerPO ownerPO = addEditPetPO.fillFieldsAndSubmit(name, birthDate);

		List<Pet> pets = ownerPO.getPets();

		assertTrue(pets.stream().anyMatch(p -> p.name.equals(name) && p.birthDate.equals(birthDate)));
		// NOTE fail if it is not a clean run because the name is already in use (these tests don't clean up)
		// NOTE fail on OLD version because the submit button is underneath the date picker
	}

	@Test
	void testAddPetToOwnerWithEmptyFields() {
		AddEditPetPO addEditPetPO = navBarPO.goToFindOwner().searchFor(lastName).addPet();

		addEditPetPO.submit();

		assertEquals("is required", addEditPetPO.getNameError());
		assertEquals("is required", addEditPetPO.getBirthDateError());
	}

	@AfterAll
	static void closeDriver() {
		navBarPO.closeDriver();
	}

}
