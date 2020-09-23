package assertions;

import driver.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.AddEditOwnerPO;
import pageobject.NavBarPO;
import pageobject.OwnerPO;
import recheck.BaseTests;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddOwnerTests extends BaseTests {

	private static NavBarPO navBarPO;

	private String firstName = "Mario";
    private String lastName = "Rossi";
    private String address = "Via Dei Matti";
    private String city = "Genova";
    private String telephone = "010123456";

	@BeforeAll
    static void createAndStartService() {
        WebDriver driver = DriverManager.getNewDriverInstance(BROWSER);
        navBarPO = new NavBarPO(driver);
    }

    @Test
    void TestAddOwnerWithValidData() {
        AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();

        OwnerPO ownerPO = addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, telephone);

        assertEquals(ownerPO.getName(), firstName + " " + lastName);
        assertEquals(ownerPO.getAddress(), address);
        assertEquals(ownerPO.getCity(), city);
        assertEquals(ownerPO.getTelephone(), telephone);
    }

    @Test
    void TestAddOwnerWithEmptyFirstName() {
        AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();

	    addEditOwnerPO.fillFieldsAndClick("", lastName, address, city, telephone);

        assertTrue(addEditOwnerPO.getFirstNameError().contains("must not be empty"));
    }

    @Test
    void TestAddOwnerWithEmptyLastName() {
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();

		addEditOwnerPO.fillFieldsAndClick(firstName, "", address, city, telephone);

	    assertTrue(addEditOwnerPO.getLastNameError().contains("must not be empty"));
    }

    @Test
    void TestAddOwnerWithEmptyAddress() {
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();

	    addEditOwnerPO.fillFieldsAndClick(firstName, lastName, "", city, telephone);

		assertTrue(addEditOwnerPO.getAddressError().contains("must not be empty"));
    }

    @Test
    void TestAddOwnerWithEmptyCity() {
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();

	    addEditOwnerPO.fillFieldsAndClick(firstName, lastName, address, "", telephone);

		assertTrue(addEditOwnerPO.getCityError().contains("must not be empty"));
    }

    @Test
    void TestAddOwnerWithEmptyTelephone() {
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();

	    addEditOwnerPO.fillFieldsAndClick(firstName, lastName, address, city, "");

		assertTrue(addEditOwnerPO.getTelephoneError().contains("must not be empty"));
    }

    @Test
    void TestAddOwnerWithLongTelephone() {
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();

	    addEditOwnerPO.fillFieldsAndClick(firstName, lastName, address, city, "010123456789");

        assertTrue(addEditOwnerPO.getTelephoneError().contains("numeric value out of bounds (<10 digits>.<0 digits> expected)"));
    }

    @Test
    void TestAddOwnerWithNotNumericTelephone() {
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();

	    addEditOwnerPO.fillFieldsAndClick(firstName, lastName, address, city, "abcdef");

        assertTrue(addEditOwnerPO.getTelephoneError().contains("numeric value out of bounds (<10 digits>.<0 digits> expected)"));
    }

    @AfterAll
    static void closeDriver() {
        navBarPO.closeDriver();
    }

}
