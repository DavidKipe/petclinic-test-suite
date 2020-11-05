package recheck.explicit;

import org.junit.jupiter.api.Test;
import pageobject.AddEditOwnerPO;


class AddOwnerTests extends BaseTests {

	private final String firstName = "Mario";
	private final String lastName = "Rossi";
	private final String address = "Via Dei Matti";
	private final String city = "Genova";
	private final String telephone = "010123456";

	@Test
	void testAddOwnerWithValidData() {
		startTest("addOwnerWithValidData");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, telephone);
		endTest("addOwnerWithValidData");
	}

	@Test
	void testAddOwnerWithEmptyFirstName() {
		startTest("addOwnerWithEmptyFirstName");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit("", lastName, address, city, telephone);
		endTest("addOwnerWithEmptyFirstName");
	}

	@Test
	void testAddOwnerWithEmptyLastName() {
		startTest("addOwnerWithEmptyLastName");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, "", address, city, telephone);
		endTest("addOwnerWithEmptyLastName");
	}

	@Test
	void testAddOwnerWithEmptyAddress() {
		startTest("addOwnerWithEmptyAddress");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, "", city, telephone);
		endTest("addOwnerWithEmptyAddress");
	}

	@Test
	void testAddOwnerWithEmptyCity() {
		startTest("addOwnerWithEmptyCity");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, "", telephone);
		endTest("addOwnerWithEmptyCity");
	}

	@Test
	void testAddOwnerWithEmptyTelephone() {
		startTest("addOwnerWithEmptyTelephone");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, "");
		endTest("addOwnerWithEmptyTelephone");
	}

	@Test
	void testAddOwnerWithLongTelephone() {
		startTest("addOwnerWithLongTelephone");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, "010123456789");
		endTest("addOwnerWithLongTelephone");
	}

	@Test
	void testAddOwnerWithNotNumericTelephone() {
		startTest("addOwnerWithNotNumericTelephone");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, "abcdef");
		endTest("addOwnerWithNotNumericTelephone");
	}

}
