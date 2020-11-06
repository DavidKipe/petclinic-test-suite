package recheck.explicit;

import org.junit.jupiter.api.Test;
import pageobject.AddEditPetPO;

class AddPetTests extends BaseTests {

	private final String lastName = "Black";
	private final String petBirthDate = "2017-11-30";

	@Test
	void testAddPetToOwner() {
		startTest("addPetToOwner");

		String petName = "Arnold";

		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();
		addEditPetPO.fillFieldsAndSubmit(petName, petBirthDate);

		// NOTE fail if it is not a clean run because the name is already in use (these tests don't clean up)
		// NOTE fail on OLD version because the submit button is underneath the date picker

		endTest("addPetToOwner");
	}

	@Test
	void testAddPetToOwnerWithEmptyFields() {
		startTest("addPetToOwnerWithEmptyFields");
		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();

		addEditPetPO.submit();

		endTest("addPetToOwnerWithEmptyFields");
	}

	@Test
	void testAddPetToOwnerDuplicateName() {
		startTest("addPetToOwnerDuplicateName");

		String petName = "Lucky";

		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();

		addEditPetPO.fillFieldsAndSubmit(petName, petBirthDate);

		endTest("addPetToOwnerDuplicateName");
	}

}
