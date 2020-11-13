package assertions;

import dataclass.Pet;
import org.junit.jupiter.api.Test;
import pageobject.AddEditPetPO;
import pageobject.OwnerPO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditPetTests extends BaseTests {

	private final String lastName = "Schroeder";

	@Test
	void testEditPetWithValidData() {
		String petName = "Arnold Jr.";
		String petBirthDate = "2019-11-30";

		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).editFirstPet();

		OwnerPO ownerPO = addEditPetPO.fillFieldsAndSubmit(petName, petBirthDate);

		List<Pet> pets = ownerPO.getPets();

		assertTrue(pets.stream().anyMatch(p -> p.name.equals(petName) && p.birthDate.equals(petBirthDate)));
	}

	@Test
	void testEditPetToOwnerWithEmptyName() {
		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).editFirstPet();

		addEditPetPO.setName("");
		addEditPetPO.submit();

		assertEquals("is required", addEditPetPO.getNameError());
	}

	@Test
	void testEditPetToOwnerWithEmptyBirthDate() {
		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).editFirstPet();

		addEditPetPO.setBirthDate("");
		addEditPetPO.submit();

		assertEquals("is required", addEditPetPO.getBirthDateError());
	}

}
