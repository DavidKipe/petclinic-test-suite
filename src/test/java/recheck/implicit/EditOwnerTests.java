package recheck.implicit;

import de.retest.recheck.junit.jupiter.RecheckExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pageobject.AddEditOwnerPO;

@ExtendWith(RecheckExtension.class)
class EditOwnerTests extends BaseTests {

	private final String lastName = "Coleman"; // lastname to find and test

	@Test
	void testEditOwnerWithValidData() {
		String newFirstName = "Bob"; // new first name
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setFirstName(newFirstName);
		addEditOwnerPO.submit();
	}

	@Test
	void testEditOwnerWithEmptyFirstName() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setFirstName("");
		addEditOwnerPO.submit();
	}

	@Test
	void testEditOwnerWithEmptyLastName() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setLastName("");
		addEditOwnerPO.submit();
	}

	@Test
	void testEditOwnerWithEmptyAddress() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setAddress("");
		addEditOwnerPO.submit();
	}

	@Test
	void testEditOwnerWithEmptyCity() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setCity("");
		addEditOwnerPO.submit();
	}

	@Test
	void testEditOwnerWithEmptyTelephone() {
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setTelephone("");
		addEditOwnerPO.submit();
	}

	@Test
	void testEditOwnerLongTelephone() {
		String newTelephone = "010123456789";
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setTelephone(newTelephone);
		addEditOwnerPO.submit();
	}

	@Test
	void testEditOwnerNotNumericTelephone() {
		String newTelephone = "abcdef";
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().searchFor(lastName).edit();
		addEditOwnerPO.setTelephone(newTelephone);
		addEditOwnerPO.submit();
	}

}
