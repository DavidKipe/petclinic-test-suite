package assertions;

import driver.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobject.AddEditOwnerPO;
import pageobject.NavBarPO;
import pageobject.OwnerPO;
import recheck.BaseTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EditOwnerTests extends BaseTests {

	private static NavBarPO navBarPO;

	private final String lastName = "Black"; // lastname to find and test

	@BeforeAll
	static void createAndStartService() {
		WebDriver driver = DriverManager.getNewDriverInstance(BROWSER);
		navBarPO = new NavBarPO(driver);
	}

	@Test
	void TestEditOwnerWithValidData() {
		String newFirstName = "Bob"; // new first name

		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().searchFor(lastName).edit();

		addEditOwnerPO.setFirstName(newFirstName);
		OwnerPO ownerPO = addEditOwnerPO.submit();

		assertEquals(ownerPO.getName(), newFirstName + " " + lastName);
	}

	@Test
	void TestEditOwnerLongTelephone() {
		String newTelephone = "010123456789";
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().searchFor(lastName).edit();

		addEditOwnerPO.setTelephone(newTelephone);
		addEditOwnerPO.clickSubmitOnly();

		assertTrue(addEditOwnerPO.getTelephoneError().contains("numeric value out of bounds (<10 digits>.<0 digits> expected)"));
	}

	@Test
	void TestEditOwnerNotNumericTelephone() {
		String newTelephone = "abcdef";
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().searchFor(lastName).edit();

		addEditOwnerPO.setTelephone(newTelephone);
		addEditOwnerPO.clickSubmitOnly();

		assertEquals("numeric value out of bounds (<10 digits>.<0 digits> expected)", addEditOwnerPO.getTelephoneError());
	}

	@Test
	void TestEditOwnerWithEmptyField() {
		String newTelephone = "";
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().searchFor(lastName).edit();

		addEditOwnerPO.setTelephone(newTelephone);
		addEditOwnerPO.clickSubmitOnly();

		assertTrue(addEditOwnerPO.getTelephoneError().contains("must not be empty"));
		//assertEquals("must not be empty\nnumeric value out of bounds (<10 digits>.<0 digits> expected)", addEditOwnerPO.getTelephoneError());
	}

	@AfterAll
	static void closeDriver() {
		navBarPO.closeDriver();
	}

}
