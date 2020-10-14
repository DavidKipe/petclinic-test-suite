package assertions;

import driver.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobject.FindOwnerPO;
import pageobject.NavBarPO;
import pageobject.OwnerPO;
import pageobject.OwnersListPO;
import recheck.BaseTests;

import static org.junit.jupiter.api.Assertions.*;

class FindOwnerTests extends BaseTests {

	private static NavBarPO navBarPO;

	@BeforeAll
	static void createAndStartService() {
		WebDriver driver = DriverManager.getNewDriverInstance(BROWSER);
		navBarPO = new NavBarPO(driver);
	}


	@Test
	void testFindExistingOwner() {
		String lastName = "Black";
		OwnerPO ownerPO = navBarPO.goToFindOwner().searchFor(lastName);

		assertTrue(ownerPO.getName().contains(lastName)); // FIXME can be fail if there are more than one result
	}

	@Test
	void testFindNotExistingOwner() {
		FindOwnerPO findOwnerPO = navBarPO.goToFindOwner();
		findOwnerPO.searchFor("nobody");

		assertEquals("has not been found", findOwnerPO.getNotFoundMessage());
	}

	@Test
	void testFindAllOwners() {
		OwnersListPO ownersListPO = navBarPO.goToFindOwner().searchAll();

		assertFalse(ownersListPO.isThereOnlyOneOwner());
	}

	@Test
	void testFindExistingOwnerFromAll() {
		String fullName = "George Franklin";
		OwnersListPO ownersListPO = navBarPO.goToFindOwner().searchAll();
		OwnerPO ownerPO = ownersListPO.clickOn(fullName);

		assertEquals(ownerPO.getName(), fullName);
	}

	@AfterAll
	static void closeDriver() {
		navBarPO.closeDriver();
	}

}
