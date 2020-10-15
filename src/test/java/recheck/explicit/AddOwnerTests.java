package recheck.explicit;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.recheck.RecheckOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobject.AddEditOwnerPO;
import pageobject.HomePO;
import recheck.BaseTests;


class AddOwnerTests extends BaseTests {

	private static HomePO homePO;

	private final String firstName = "Mario";
	private final String lastName = "Rossi";
	private final String address = "Via Dei Matti";
	private final String city = "Genova";
	private final String telephone = "010123456";

	Recheck re;
	WebDriver driver;

	@BeforeEach
	void createAndStartService() {
		driver = getDriverInitialized();

		RecheckOptions recheckOptions = RecheckOptions.builder()
				//.enableReportUpload()
				.addIgnore("addowner_help_errors.filter.js")
				//.addIgnore("addowner.filter")
				.build();
		re = new RecheckImpl(recheckOptions);

		homePO = new HomePO(driver);
	}

	@Test
	void testAddOwnerWithValidData() {
		re.startTest("addOwnerWithValidData");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, telephone);
		re.check(driver, "addOwnerWithValidData");
		re.capTest();
	}

	@Test
	void testAddOwnerWithEmptyFirstName() {
		re.startTest("addOwnerWithEmptyFirstName");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit("", lastName, address, city, telephone);
		re.check(driver, "addOwnerWithEmptyFirstName");
		re.capTest();
	}

	@Test
	void testAddOwnerWithEmptyLastName() {
		re.startTest("addOwnerWithEmptyLastName");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, "", address, city, telephone);
		re.check(driver, "addOwnerWithEmptyLastName");
		re.capTest();
	}

	@Test
	void testAddOwnerWithEmptyAddress() {
		re.startTest("addOwnerWithEmptyAddress");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, "", city, telephone);
		re.check(driver, "addOwnerWithEmptyAddress");
		re.capTest();
	}

	@Test
	void testAddOwnerWithEmptyCity() {
		re.startTest("addOwnerWithEmptyCity");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, "", telephone);
		re.check(driver, "addOwnerWithEmptyCity");
		re.capTest();
	}

	@Test
	void testAddOwnerWithEmptyTelephone() {
		re.startTest("addOwnerWithEmptyTelephone");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, "");
		re.check(driver, "addOwnerWithEmptyTelephone");
		re.capTest();
	}

	@Test
	void testAddOwnerWithLongTelephone() {
		re.startTest("addOwnerWithLongTelephone");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, "010123456789");
		re.check(driver, "addOwnerWithLongTelephone");
		re.capTest();
	}

	@Test
	void testAddOwnerWithNotNumericTelephone() {
		re.startTest("addOwnerWithNotNumericTelephone");
		AddEditOwnerPO addEditOwnerPO = homePO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, "abcdef");
		re.check(driver, "addOwnerWithNotNumericTelephone");
		re.capTest();
	}

	@AfterEach
	void closeDriver() {
		homePO.closeDriver();
		re.cap();
	}

}
