package assertions;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pageobject.FindOwnerPO;
import pageobject.HomePO;
import pageobject.OwnerPO;
import pageobject.OwnersListPO;

import static org.junit.jupiter.api.Assertions.*;

class FindOwnerTests extends BaseTests {

	private static HomePO homePO;

	@BeforeAll
	static void createAndStartService() {
        homePO = new HomePO(getDriverInitialized());
	}


	@Test
	void testFindExistingOwner() {
		String lastName = "Black";
		OwnerPO ownerPO = homePO.goToFindOwner().searchFor(lastName);

		assertTrue(ownerPO.getName().contains(lastName)); // FIXME can be fail if there are more than one result
	}

	@Test
	void testFindNotExistingOwner() {
		FindOwnerPO findOwnerPO = homePO.goToFindOwner();
		findOwnerPO.searchFor("nobody");

		assertEquals("has not been found", findOwnerPO.getNotFoundMessage());
	}

	@Test
	void testFindAllOwners() {
		OwnersListPO ownersListPO = homePO.goToFindOwner().searchAll();

		assertFalse(ownersListPO.isThereOnlyOneOwner());
	}

	@Test
	void testFindExistingOwnerFromAll() {
		String fullName = "George Franklin";
		OwnersListPO ownersListPO = homePO.goToFindOwner().searchAll();
		OwnerPO ownerPO = ownersListPO.clickOn(fullName);

		assertEquals(ownerPO.getName(), fullName);
	}

	@AfterAll
	static void closeDriver() {
		homePO.closeDriver();
	}

}
