package recheck.explicit;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.recheck.RecheckOptions;
import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobject.AddEditOwnerPO;
import pageobject.NavBarPO;
import pageobject.OwnerPO;
import recheck.BaseTests;


class AddOwnerTests extends BaseTests {

	Recheck re;
	WebDriver driver;

	private static NavBarPO navBarPO;

	private final String firstName = "Mario";
	private final String lastName = "Rossi";
	private final String address = "Via Dei Matti";
	private final String city = "Genova";
	private final String telephone = "010123456";

	@BeforeEach
	void createAndStartService() {
		driver = DriverManager.getNewDriverInstance(BaseTests.BROWSER);

		RecheckOptions recheckOptions = RecheckOptions.builder()
				//.enableReportUpload()
				.addIgnore("addowner_help_errors.filter.js")
				//.addIgnore("addowner.filter")
				.build();
		re = new RecheckImpl(recheckOptions);

		navBarPO = new NavBarPO(driver);
	}

	@Test
	void TestAddOwnerWithValidData() {
		re.startTest("addOwnerWithValidData");
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();
		OwnerPO ownerPO = addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, telephone);
		re.check(driver, "addOwnerWithValidData");
		re.capTest();
	}

	@Test
	void TestAddOwnerWithEmptyFirstName() {
		re.startTest("addOwnerWithEmptyFirstName");
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndClick("", lastName, address, city, telephone);
		re.check(driver, "addOwnerWithEmptyFirstName");
		re.capTest();
	}

	@Test
	void TestAddOwnerWithEmptyLastName() {
		re.startTest("addOwnerWithEmptyLastName");
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndClick(firstName, "", address, city, telephone);
		re.check(driver, "addOwnerWithEmptyLastName");
		re.capTest();
	}

	@Test
	void TestAddOwnerWithEmptyAddress() {
		re.startTest("addOwnerWithEmptyAddress");
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndClick(firstName, lastName, "", city, telephone);
		re.check(driver, "addOwnerWithEmptyAddress");
		re.capTest();
	}

	@Test
	void TestAddOwnerWithEmptyCity() {
		re.startTest("addOwnerWithEmptyCity");
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndClick(firstName, lastName, address, "", telephone);
		re.check(driver, "addOwnerWithEmptyCity");
		re.capTest();
	}

	@Test
	void TestAddOwnerWithEmptyTelephone() {
		re.startTest("addOwnerWithEmptyTelephone");
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndClick(firstName, lastName, address, city, "");
		re.check(driver, "addOwnerWithEmptyTelephone");
		re.capTest();
	}

	@Test
	void TestAddOwnerWithLongTelephone() {
		re.startTest("addOwnerWithLongTelephone");
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndClick(firstName, lastName, address, city, "010123456789");
		re.check(driver, "addOwnerWithLongTelephone");
		re.capTest();
	}

	@Test
	void TestAddOwnerWithNotNumericTelephone() {
		re.startTest("addOwnerWithNotNumericTelephone");
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndClick(firstName, lastName, address, city, "abcdef");
		re.check(driver, "addOwnerWithNotNumericTelephone");
		re.capTest();
	}

	@AfterEach
	void closeDriver() {
		navBarPO.closeDriver();
		re.cap();
	}

}
