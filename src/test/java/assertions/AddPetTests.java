package assertions;

import dataclass.Pet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobject.AddEditPetPO;
import pageobject.HomePO;
import pageobject.OwnerPO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddPetTests extends BaseTests {

	private static HomePO homePO;

	private final String lastName = "Black";

	@BeforeEach
	void createAndStartService() {
        homePO = new HomePO(getDriverInitialized());
	}

	@Test
	void testAddPetToOwner() {
		String name = "Arnold";
		String birthDate = "2017-11-30";

		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();

		OwnerPO ownerPO = addEditPetPO.fillFieldsAndSubmit(name, birthDate);

		List<Pet> pets = ownerPO.getPets();

		assertTrue(pets.stream().anyMatch(p -> p.name.equals(name) && p.birthDate.equals(birthDate)));
		// NOTE fail if it is not a clean run because the name is already in use (these tests don't clean up)
		// NOTE fail on OLD version because the submit button is underneath the date picker
	}

	@Test
	void testAddPetToOwnerWithEmptyFields() {
		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();

		addEditPetPO.submit();

		assertEquals("is required", addEditPetPO.getNameError());
		assertEquals("is required", addEditPetPO.getBirthDateError());
	}

	@AfterEach
	void closeDriver() {
		homePO.closeDriver();
	}

}
