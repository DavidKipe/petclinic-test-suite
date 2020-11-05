package recheck.explicit;

import org.junit.jupiter.api.Test;
import pageobject.AddEditOwnerPO;

class EditOwnerTests extends BaseTests {

	private final String lastName = "Coleman"; // lastname to find and test

	@Test
	void testEditOwnerWithValidData() {
		startTest("editOwnerWithValidData");

		String newFirstName = "Bob"; // new first name
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setFirstName(newFirstName);
		addEditOwnerPO.submit();

		endTest("editOwnerWithValidData");
	}

	@Test
	void testEditOwnerLongTelephone() {
		startTest("editOwnerLongTelephone");

		String newTelephone = "010123456789";
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setTelephone(newTelephone);
		addEditOwnerPO.submit();

		endTest("editOwnerLongTelephone");
	}

	@Test
	void testEditOwnerNotNumericTelephone() {
		startTest("editOwnerNotNumericTelephone");

		String newTelephone = "abcdef";
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setTelephone(newTelephone);
		addEditOwnerPO.submit();

		endTest("editOwnerNotNumericTelephone");
	}

	@Test
	void testEditOwnerWithEmptyFields() {
		startTest("editOwnerWithEmptyField");

		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.clearFields();
		addEditOwnerPO.submit();

		endTest("editOwnerWithEmptyField");
	}

}
