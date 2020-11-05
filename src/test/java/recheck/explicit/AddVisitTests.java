package recheck.explicit;

import org.junit.jupiter.api.Test;
import pageobject.AddVisitPO;

public class AddVisitTests extends BaseTests {

	private final String lastName = "Rodriquez";
	private final String date = "2020-10-30";

	@Test
	void testAddVisitToPet() {
		startTest("addVisitToPet");
		String description = "Routine";

		AddVisitPO addVisitPO = homePO.goToFindOwner().searchFor(lastName).addVisitToFirstPet();

		addVisitPO.fillFieldsAndSubmit(date, description);

		endTest("addVisitToPet");
	}

	@Test
	void testAddVisitToPetWithEmptyDescription() {
		startTest("addVisitToPetWithEmptyDescription");
		AddVisitPO addVisitPO = homePO.goToFindOwner().searchFor(lastName).addVisitToFirstPet();

		addVisitPO.setDate(date);
		addVisitPO.submit();

		endTest("addVisitToPetWithEmptyDescription");
	}

}
