package assertions;

import org.junit.jupiter.api.Test;
import pageobject.AddEditOwnerPO;
import pageobject.OwnerPO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EditOwnerTests extends BaseTests {

	private final String lastName = "Coleman"; // lastname to find and test

	@Test
	void testEditOwnerWithValidData() {
		String newFirstName = "Bob"; // new first name

		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();

		addEditOwnerPO.setFirstName(newFirstName);
		OwnerPO ownerPO = addEditOwnerPO.submit();

		assertEquals(newFirstName + " " + lastName, ownerPO.getName());
	}

	@Test
	void testEditOwnerWithEmptyFirstName() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();

		addEditOwnerPO.setFirstName("");
		addEditOwnerPO.submit();

		assertTrue(addEditOwnerPO.getFirstNameError().contains("must not be empty"));
	}

	@Test
	void testEditOwnerWithEmptyLastName() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();

		addEditOwnerPO.setLastName("");
		addEditOwnerPO.submit();

		assertTrue(addEditOwnerPO.getLastNameError().contains("must not be empty"));
	}

	@Test
	void testEditOwnerWithEmptyAddress() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();

		addEditOwnerPO.setAddress("");
		addEditOwnerPO.submit();

		assertTrue(addEditOwnerPO.getAddressError().contains("must not be empty"));
	}

	@Test
	void testEditOwnerWithEmptyCity() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();

		addEditOwnerPO.setCity("");
		addEditOwnerPO.submit();

		assertTrue(addEditOwnerPO.getCityError().contains("must not be empty"));
	}

	@Test
	void testEditOwnerWithEmptyTelephone() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();

		addEditOwnerPO.setTelephone("");
		addEditOwnerPO.submit();

		assertTrue(addEditOwnerPO.getTelephoneError().contains("must not be empty"));
	}

	@Test
	void testEditOwnerLongTelephone() {
		String newTelephone = "010123456789";
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();

		addEditOwnerPO.setTelephone(newTelephone);
		addEditOwnerPO.submit();

		assertTrue(addEditOwnerPO.getTelephoneError().contains("numeric value out of bounds (<10 digits>.<0 digits> expected)"));
	}

	@Test
	void testEditOwnerNotNumericTelephone() {
		String newTelephone = "abcdef";
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();

		addEditOwnerPO.setTelephone(newTelephone);
		addEditOwnerPO.submit();

		assertTrue(addEditOwnerPO.getTelephoneError().contains("numeric value out of bounds (<10 digits>.<0 digits> expected)"));
	}

}
