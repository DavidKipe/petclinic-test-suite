package recheck.implicit;

import de.retest.recheck.junit.jupiter.RecheckExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pageobject.FindOwnerPO;
import pageobject.OwnersListPO;

@ExtendWith(RecheckExtension.class)
class FindOwnerTests extends BaseTests {

	@Test
	void testFindExistingOwner() {
		String lastName = "McTavish";
		homePO.goToFindOwner().searchFor(lastName);
	}

	@Test
	void testFindNotExistingOwner() {
		FindOwnerPO findOwnerPO = homePO.goToFindOwner();
		findOwnerPO.searchFor("nobody");
	}

	@Test
	void testFindAllOwners() {
		homePO.goToFindOwner().searchAll();
	}

	@Test
	void testFindExistingOwnerFromAll() {
		String fullName = "George Franklin";
		OwnersListPO ownersListPO = homePO.goToFindOwner().searchAll();
		ownersListPO.clickOn(fullName);
	}

}
