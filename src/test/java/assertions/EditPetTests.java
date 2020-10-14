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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditPetTests extends BaseTests {

	private static NavBarPO navBarPO;

	private final String lastName = "Black";

	@BeforeAll
	static void createAndStartService() {
		WebDriver driver = DriverManager.getNewDriverInstance(BROWSER);
		navBarPO = new NavBarPO(driver);
	}

	@Test
	void testEditWithValidData() {
		String name = "Arnold Jr.";
		String birthDate = "2019-11-30";

		AddEditPetPO addEditPetPO = navBarPO.goToFindOwner().searchFor(lastName).editFirstPet();

		OwnerPO ownerPO = addEditPetPO.fillFieldsAndSubmit(name, birthDate);

		List<Pet> pets = ownerPO.getPets();

		assertTrue(pets.stream().anyMatch(p -> p.name.equals(name) && p.birthDate.equals(birthDate)));
	}

	@Test
	void testEditPetWithEmptyFields() {
		AddEditPetPO addEditPetPO = navBarPO.goToFindOwner().searchFor(lastName).editFirstPet();

		addEditPetPO.setName("");
		addEditPetPO.setBirthDate("");
		addEditPetPO.submit();

		assertEquals("is required", addEditPetPO.getNameError());
		assertEquals("is required", addEditPetPO.getBirthDateError());
	}

	@AfterAll
	static void closeDriver() {
		navBarPO.closeDriver();
	}

}
