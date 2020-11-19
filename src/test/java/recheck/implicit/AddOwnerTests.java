package recheck.implicit;

import de.retest.recheck.junit.jupiter.RecheckExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pageobject.AddEditOwnerPO;

@ExtendWith(RecheckExtension.class)
class AddOwnerTests extends BaseTests {

	private final String firstName = "Mario";
	private final String lastName = "Rossi";
	private final String address = "Via Dei Matti";
	private final String city = "Genova";
	private final String telephone = "010123456";

	@Test
	void testAddOwnerWithValidData() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, telephone);
	}

	@Test
	void testAddOwnerWithEmptyFirstName() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit("", lastName, address, city, telephone);
	}

	@Test
	void testAddOwnerWithEmptyLastName() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, "", address, city, telephone);
	}

	@Test
	void testAddOwnerWithEmptyAddress() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, "", city, telephone);
	}

	@Test
	void testAddOwnerWithEmptyCity() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, "", telephone);
	}

	@Test
	void testAddOwnerWithEmptyTelephone() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, "");
	}

	@Test
	void testAddOwnerWithLongTelephone() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, "010123456789");
	}

	@Test
	void testAddOwnerWithNotNumericTelephone() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, "abcdef");
	}

}
