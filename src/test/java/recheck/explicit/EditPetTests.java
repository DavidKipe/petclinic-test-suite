package recheck.explicit;

import org.junit.jupiter.api.Test;
import pageobject.AddEditPetPO;

public class EditPetTests extends BaseTests {

	private final String lastName = "Schroeder";

	@Test
	void testEditPetWithValidData() {
		startTest("editPet");
		String name = "Arnold Jr.";
		String birthDate = "2019-11-30";

		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).editFirstPet();

		addEditPetPO.fillFieldsAndSubmit(name, birthDate);

		endTest("editPet");
	}

	@Test
	void testEditPetWithEmptyFields() {
		startTest("editPetWithEmptyDescription");
		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).editFirstPet();

		addEditPetPO.clearFields();
		addEditPetPO.submit();

		endTest("editPetWithEmptyDescription");
	}

}
