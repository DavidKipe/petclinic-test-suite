package recheck.implicit;

import de.retest.recheck.junit.jupiter.RecheckExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pageobject.AddEditPetPO;

@ExtendWith(RecheckExtension.class)
public class EditPetTests extends BaseTests {

	private final String lastName = "Schroeder";

	@Test
	void testEditPetWithValidData() {
		String petName = "Arnold Jr.";
		String petBirthDate = "2019-11-30";

		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).editFirstPet();

		addEditPetPO.fillFieldsAndSubmit(petName, petBirthDate);
	}

	@Test
	void testEditPetToOwnerWithEmptyName() {
		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).editFirstPet();

		addEditPetPO.setName("");
		addEditPetPO.submit();
	}

	@Test
	void testEditPetToOwnerWithEmptyBirthDate() {
		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).editFirstPet();

		addEditPetPO.setBirthDate("");
		addEditPetPO.submit();
	}

}
