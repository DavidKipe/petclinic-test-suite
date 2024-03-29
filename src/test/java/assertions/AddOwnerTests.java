package assertions;

import org.junit.jupiter.api.Test;
import pageobject.AddEditOwnerPO;
import pageobject.OwnerPO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddOwnerTests extends BaseTests {

	private final String firstName = "Mario";
	private final String lastName = "Rossi";
	private final String address = "Via Dei Matti";
	private final String city = "Genova";
	private final String telephone = "010123456";

	@Test
	void testAddOwnerWithValidData() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();

		OwnerPO ownerPO = addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, telephone);

		assertEquals(firstName + " " + lastName, ownerPO.getName());
		assertEquals(address, ownerPO.getAddress());
		assertEquals(city, ownerPO.getCity());
		assertEquals(telephone, ownerPO.getTelephone());
	}

	@Test
	void testAddOwnerWithEmptyFirstName() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();

		addEditOwnerPO.fillFieldsAndSubmit("", lastName, address, city, telephone);

		assertTrue(addEditOwnerPO.getFirstNameError().contains("must not be empty"));
	}

	@Test
	void testAddOwnerWithEmptyLastName() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();

		addEditOwnerPO.fillFieldsAndSubmit(firstName, "", address, city, telephone);

		assertTrue(addEditOwnerPO.getLastNameError().contains("must not be empty"));
	}

	@Test
	void testAddOwnerWithEmptyAddress() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();

		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, "", city, telephone);

		assertTrue(addEditOwnerPO.getAddressError().contains("must not be empty"));
	}

	@Test
	void testAddOwnerWithEmptyCity() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();

		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, "", telephone);

		assertTrue(addEditOwnerPO.getCityError().contains("must not be empty"));
	}

	@Test
	void testAddOwnerWithEmptyTelephone() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();

		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, "");

		assertTrue(addEditOwnerPO.getTelephoneError().contains("must not be empty"));
	}

	@Test
	void testAddOwnerWithLongTelephone() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();

		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, "010123456789");

		assertTrue(addEditOwnerPO.getTelephoneError().contains("numeric value out of bounds (<10 digits>.<0 digits> expected)"));
	}

	@Test
	void testAddOwnerWithNotNumericTelephone() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();

		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, "abcdef");

		assertTrue(addEditOwnerPO.getTelephoneError().contains("numeric value out of bounds (<10 digits>.<0 digits> expected)"));
	}

}
