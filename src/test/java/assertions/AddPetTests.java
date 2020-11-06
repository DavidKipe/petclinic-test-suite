package assertions;

import dataclass.Pet;
import org.junit.jupiter.api.Test;
import pageobject.AddEditPetPO;
import pageobject.OwnerPO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddPetTests extends BaseTests {

	private final String lastName = "Black";
	private final String petBirthDate = "2017-11-30";

	@Test
	void testAddPetToOwner() {
		String petName = "Arnold";

		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();

		OwnerPO ownerPO = addEditPetPO.fillFieldsAndSubmit(petName, petBirthDate);

		List<Pet> pets = ownerPO.getPets();

		assertTrue(pets.stream().anyMatch(p -> p.name.equals(petName) && p.birthDate.equals(petBirthDate)));
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

	@Test
	void testAddPetToOwnerDuplicateName() {
		String petName = "Lucky";

		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();

		addEditPetPO.fillFieldsAndSubmit(petName, petBirthDate);

		assertEquals("is already in use", addEditPetPO.getNameError());
	}

}
