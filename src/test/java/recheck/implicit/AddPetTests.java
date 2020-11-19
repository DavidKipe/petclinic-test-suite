package recheck.implicit;

import de.retest.recheck.junit.jupiter.RecheckExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pageobject.AddEditPetPO;

@ExtendWith(RecheckExtension.class)
class AddPetTests extends BaseTests {

	private final String lastName = "Black";

	private final String petBirthDate = "2017-11-30";
	private final String petName = "Arnold";

	@Test
	void testAddPetToOwner() {
		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();
		addEditPetPO.fillFieldsAndSubmit(petName, petBirthDate);
		// NOTE fail if it is not a clean run because the name is already in use (these tests don't clean up)
		// NOTE fail on OLD version because the submit button is underneath the date picker
	}

	@Test
	void testAddPetToOwnerWithEmptyName() {
		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();

		addEditPetPO.setBirthDate(petBirthDate);
		addEditPetPO.submit();
	}

	@Test
	void testAddPetToOwnerWithEmptyBirthDate() {
		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();

		addEditPetPO.setName(petName);
		addEditPetPO.submit();
	}

	@Test
	void testAddPetToOwnerDuplicateName() {
		String duplicatePetName = "Lucky";

		AddEditPetPO addEditPetPO = homePO.goToFindOwner().searchFor(lastName).addPet();

		addEditPetPO.fillFieldsAndSubmit(duplicatePetName, petBirthDate);
	}

}
