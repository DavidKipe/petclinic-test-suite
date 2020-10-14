package assertions;

import dataclass.Visit;
import driver.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobject.AddVisitPO;
import pageobject.NavBarPO;
import pageobject.OwnerPO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddVisitTests extends BaseTests {

	private static NavBarPO navBarPO;

	private final String lastName = "Black";

	@BeforeAll
	static void createAndStartService() {
		WebDriver driver = DriverManager.getNewDriverInstance(BROWSER);
		navBarPO = new NavBarPO(driver);
	}

	@Test
	void testAddVisitToPet() {
		String date = "2020-10-30";
		String description = "Routine";

		AddVisitPO addVisitPO = navBarPO.goToFindOwner().searchFor(lastName).addVisitToFirstPet();

		OwnerPO ownerPO = addVisitPO.fillFieldsAndSubmit(date, description);

		List<Visit> visits = ownerPO.getVisitFirstPet();

		assertTrue(visits.stream().anyMatch(v -> v.date.equals(date) && v.description.equals(description)));
	}

	@Test
	void testAddVisitToPetWithEmptyDescription() {
		AddVisitPO addVisitPO = navBarPO.goToFindOwner().searchFor(lastName).addVisitToFirstPet();

		addVisitPO.submit();

		assertEquals("must not be empty", addVisitPO.getDescriptionError());
	}

	@AfterAll
	static void closeDriver() {
		navBarPO.closeDriver();
	}

}
