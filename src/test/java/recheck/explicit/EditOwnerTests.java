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
	void testEditOwnerWithEmptyFirstName() {
		startTest("editOwnerWithEmptyFirstName");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setFirstName("");
		addEditOwnerPO.submit();
		endTest("editOwnerWithEmptyFirstName");
	}

	@Test
	void testEditOwnerWithEmptyLastName() {
		startTest("editOwnerWithEmptyLastName");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setLastName("");
		addEditOwnerPO.submit();
		endTest("editOwnerWithEmptyLastName");
	}

	@Test
	void testEditOwnerWithEmptyAddress() {
		startTest("editOwnerWithEmptyAddress");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setAddress("");
		addEditOwnerPO.submit();
		endTest("editOwnerWithEmptyAddress");
	}

	@Test
	void testEditOwnerWithEmptyCity() {
		startTest("editOwnerWithEmptyCity");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setCity("");
		addEditOwnerPO.submit();
		endTest("editOwnerWithEmptyCity");
	}

	@Test
	void testEditOwnerWithEmptyTelephone() {
		startTest("editOwnerWithEmptyTelephone");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setTelephone("");
		addEditOwnerPO.submit();
		endTest("editOwnerWithEmptyTelephone");
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

}
