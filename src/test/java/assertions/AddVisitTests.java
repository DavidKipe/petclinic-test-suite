package assertions;

import dataclass.Visit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pageobject.AddVisitPO;
import pageobject.HomePO;
import pageobject.OwnerPO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddVisitTests extends BaseTests {

	private static HomePO homePO;

	private final String lastName = "Black";

	@BeforeAll
	static void createAndStartService() {
        homePO = new HomePO(getDriverInitialized());
	}

	@Test
	void testAddVisitToPet() {
		String date = "2020-10-30";
		String description = "Routine";

		AddVisitPO addVisitPO = homePO.goToFindOwner().searchFor(lastName).addVisitToFirstPet();

		OwnerPO ownerPO = addVisitPO.fillFieldsAndSubmit(date, description);

		List<Visit> visits = ownerPO.getVisitFirstPet();

		assertTrue(visits.stream().anyMatch(v -> v.date.equals(date) && v.description.equals(description)));
	}

	@Test
	void testAddVisitToPetWithEmptyDescription() {
		AddVisitPO addVisitPO = homePO.goToFindOwner().searchFor(lastName).addVisitToFirstPet();

		addVisitPO.submit();

		assertEquals("must not be empty", addVisitPO.getDescriptionError());
	}

	@AfterAll
	static void closeDriver() {
		homePO.closeDriver();
	}

}
