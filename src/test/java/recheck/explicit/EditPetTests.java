package recheck.explicit;

import org.junit.jupiter.api.Test;
import pageobject.AddEditPetPO;

public class EditPetTests extends BaseTests {

	private final String lastName = "Schroeder";

	@Test
	void testEditPetWithValidData() {
		startTest("editPet");

		String petName = "Arnold Jr.";
		String petBirthDate = "2019-11-30";

		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).editFirstPet();

		addEditPetPO.fillFieldsAndSubmit(petName, petBirthDate);

		endTest("editPet");
	}

	@Test
	void testEditPetToOwnerWithEmptyName() {
		startTest("editPetToOwnerWithEmptyName");
		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).editFirstPet();

		addEditPetPO.setName("");
		addEditPetPO.submit();

		endTest("editPetToOwnerWithEmptyName");
	}

	@Test
	void testEditPetToOwnerWithEmptyBirthDate() {
		startTest("editPetToOwnerWithEmptyBirthDate");
		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).editFirstPet();

		addEditPetPO.setBirthDate("");
		addEditPetPO.submit();

		endTest("editPetToOwnerWithEmptyBirthDate");
	}

}
