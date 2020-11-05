package assertions;

import dataclass.Visit;
import org.junit.jupiter.api.Test;
import pageobject.AddVisitPO;
import pageobject.OwnerPO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddVisitTests extends BaseTests {

	private final String lastName = "Rodriquez";
	private final String date = "2020-10-30";

	@Test
	void testAddVisitToPet() {
		String description = "Routine";

		AddVisitPO addVisitPO = homePO.goToFindOwner().searchFor(lastName).addVisitToFirstPet();

		OwnerPO ownerPO = addVisitPO.fillFieldsAndSubmit(date, description);

		List<Visit> visits = ownerPO.getVisitFirstPet();

		assertTrue(visits.stream().anyMatch(v -> v.date.equals(date) && v.description.equals(description)));
	}

	@Test
	void testAddVisitToPetWithEmptyDescription() {
		AddVisitPO addVisitPO = homePO.goToFindOwner().searchFor(lastName).addVisitToFirstPet();

		addVisitPO.setDate(date);
		addVisitPO.submit();

		assertEquals("must not be empty", addVisitPO.getDescriptionError());
	}

}
