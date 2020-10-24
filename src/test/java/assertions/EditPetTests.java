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

public class EditPetTests extends BaseTests {

	private static HomePO homePO;

	private final String lastName = "Black";

	@BeforeEach
	void createAndStartService() {
        homePO = new HomePO(getDriverInitialized());
	}

	@Test
	void testEditWithValidData() {
		String name = "Arnold Jr.";
		String birthDate = "2019-11-30";

		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).editFirstPet();

		OwnerPO ownerPO = addEditPetPO.fillFieldsAndSubmit(name, birthDate);

		List<Pet> pets = ownerPO.getPets();

		assertTrue(pets.stream().anyMatch(p -> p.name.equals(name) && p.birthDate.equals(birthDate)));
	}

	@Test
	void testEditPetWithEmptyFields() {
		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).editFirstPet();

		addEditPetPO.clearFields();
		addEditPetPO.submit();

		assertEquals("is required", addEditPetPO.getNameError());
		assertEquals("is required", addEditPetPO.getBirthDateError());
	}

	@AfterEach
	void closeDriver() {
		homePO.closeDriver();
	}

}
