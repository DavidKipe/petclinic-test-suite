package recheck.explicit;

import org.junit.jupiter.api.Test;
import pageobject.FindOwnerPO;
import pageobject.OwnersListPO;

class FindOwnerTests extends BaseTests {

	@Test
	void testFindExistingOwner() {
		startTest("findExistingOwner");

		String lastName = "McTavish";
		homePO.goToFindOwner().searchFor(lastName);

		endTest("findExistingOwner");
	}

	@Test
	void testFindNotExistingOwner() {
		startTest("findNotExistingOwner");

		FindOwnerPO findOwnerPO = homePO.goToFindOwner();
		findOwnerPO.searchFor("nobody");

		endTest("findNotExistingOwner");
	}

	@Test
	void testFindAllOwners() {
		startTest("findAllOwners");

		homePO.goToFindOwner().searchAll();

		endTest("findAllOwners");
	}

	@Test
	void testFindExistingOwnerFromAll() {
		startTest("findExistingOwnerFromAll");

		String fullName = "George Franklin";
		OwnersListPO ownersListPO = homePO.goToFindOwner().searchAll();
		ownersListPO.clickOn(fullName);

		endTest("findExistingOwnerFromAll");
	}

}
