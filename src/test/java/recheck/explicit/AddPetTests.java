package recheck.explicit;

import org.junit.jupiter.api.Test;
import pageobject.AddEditPetPO;

class AddPetTests extends BaseTests {

	private final String lastName = "Black";

	private final String petBirthDate = "2017-11-30";
	private final String petName = "Arnold";

	@Test
	void testAddPetToOwner() {
		startTest("addPetToOwner");

		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();
		addEditPetPO.fillFieldsAndSubmit(petName, petBirthDate);

		// NOTE fail if it is not a clean run because the name is already in use (these tests don't clean up)
		// NOTE fail on OLD version because the submit button is underneath the date picker

		endTest("addPetToOwner");
	}

	@Test
	void testAddPetToOwnerWithEmptyName() {
		startTest("addPetToOwnerWithEmptyName");
		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();

		addEditPetPO.setBirthDate(petBirthDate);
		addEditPetPO.submit();

		endTest("addPetToOwnerWithEmptyName");
	}

	@Test
	void testAddPetToOwnerWithEmptyBirthDate() {
		startTest("addPetToOwnerWithEmptyBirthDate");
		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();

		addEditPetPO.setName(petName);
		addEditPetPO.submit();

		endTest("addPetToOwnerWithEmptyBirthDate");
	}

	@Test
	void testAddPetToOwnerDuplicateName() {
		startTest("addPetToOwnerDuplicateName");

		String duplicatePetName = "Lucky";

		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();

		addEditPetPO.fillFieldsAndSubmit(duplicatePetName, petBirthDate);

		endTest("addPetToOwnerDuplicateName");
	}

}
