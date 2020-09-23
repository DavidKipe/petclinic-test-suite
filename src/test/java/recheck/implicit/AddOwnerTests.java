package recheck.implicit;

import de.retest.recheck.RecheckOptions;
import de.retest.recheck.junit.jupiter.RecheckExtension;
import de.retest.web.selenium.RecheckDriver;
import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import pageobject.AddEditOwnerPO;
import pageobject.NavBarPO;
import recheck.BaseTests;


@Disabled
@ExtendWith(RecheckExtension.class)
class AddOwnerTests extends BaseTests {

	RecheckDriver recheckDriver;

	private static NavBarPO navBarPO;

	private final String firstName = "Mario";
	private final String lastName = "Rossi";
	private final String address = "Via Dei Matti";
	private final String city = "Genova";
	private final String telephone = "010123456";

	@BeforeEach
	void createAndStartService() {
		WebDriver webDriver = DriverManager.getNewDriverInstance(DriverManager.Browser.CHROME);

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless", "--window-size=1280,720");

		RecheckOptions recheckOptions = RecheckOptions.builder()
				//.enableReportUpload()
				.addIgnore("addowner_help_errors.filter.js")
				.addIgnore("addowner.filter")
				.addIgnore("class.filter.js")
				.build();

		recheckDriver = new RecheckDriver((RemoteWebDriver) webDriver, recheckOptions);

		navBarPO = new NavBarPO(recheckDriver);
	}

	@Test
	void TestAddOwnerWithValidData() {
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndSubmit(firstName, lastName, address, city, telephone);
	}

	@Test
	void TestAddOwnerWithEmptyFirstName() {
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndClick("", lastName, address, city, telephone);
	}

	@Test
	void TestAddOwnerWithEmptyLastName() {
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndClick(firstName, "", address, city, telephone);
	}

	@Test
	void TestAddOwnerWithEmptyAddress() {
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndClick(firstName, lastName, "", city, telephone);
	}

	@Test
	void TestAddOwnerWithEmptyCity() {
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndClick(firstName, lastName, address, "", telephone);
	}

	@Test
	void TestAddOwnerWithEmptyTelephone() {
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndClick(firstName, lastName, address, city, "");
	}

	@Test
	void TestAddOwnerWithLongTelephone() {
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndClick(firstName, lastName, address, city, "010123456789");
	}

	@Test
	void TestAddOwnerWithNotNumericTelephone() {
		AddEditOwnerPO addEditOwnerPO = navBarPO.goToFindOwner().goToAddOwner();
		addEditOwnerPO.fillFieldsAndClick(firstName, lastName, address, city, "abcdef");
	}

	@AfterEach
	void closeDriver() {
		recheckDriver.quit();
	}

}
