package assertions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobject.AddEditOwnerPO;
import pageobject.HomePO;
import pageobject.OwnerPO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EditOwnerTests extends BaseTests {

	private static HomePO homePO;

	private final String lastName = "Coleman"; // lastname to find and test

	@BeforeEach
	void createAndStartService() {
        homePO = new HomePO(getDriverInitialized());
	}

	@Test
	void testEditOwnerWithValidData() {
		String newFirstName = "Bob"; // new first name

		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();

		addEditOwnerPO.setFirstName(newFirstName);
		OwnerPO ownerPO = addEditOwnerPO.submit();

		assertEquals(newFirstName + " " + lastName, ownerPO.getName());
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

		assertEquals("numeric value out of bounds (<10 digits>.<0 digits> expected)", addEditOwnerPO.getTelephoneError());
	}

	@Test
	void testEditOwnerWithEmptyFields() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();

		addEditOwnerPO.clearFields();
		addEditOwnerPO.submit();

		assertEquals("must not be empty", addEditOwnerPO.getFirstNameError());
		assertEquals("must not be empty", addEditOwnerPO.getLastNameError());
		assertEquals("must not be empty", addEditOwnerPO.getAddressError());
		assertEquals("must not be empty", addEditOwnerPO.getCityError());
		assertTrue(addEditOwnerPO.getTelephoneError().contains("must not be empty"));
	}

	@AfterEach
	void closeDriver() {
		homePO.closeDriver();
	}

}
