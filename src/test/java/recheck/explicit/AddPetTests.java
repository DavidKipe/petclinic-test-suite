package recheck.explicit;

import org.junit.jupiter.api.Test;
import pageobject.AddEditPetPO;

class AddPetTests extends BaseTests {

	private final String lastName = "Black";

	@Test
	void testAddPetToOwner() {
		startTest("addPetToOwner");

		String name = "Arnold";
		String birthDate = "2017-11-30";

		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();
		addEditPetPO.fillFieldsAndSubmit(name, birthDate);

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

}
