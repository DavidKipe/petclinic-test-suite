package recheck.implicit;

import de.retest.recheck.junit.jupiter.RecheckExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pageobject.AddVisitPO;

@ExtendWith(RecheckExtension.class)
public class AddVisitTests extends BaseTests {

	private final String lastName = "Rodriquez";
	private final String date = "2020-10-30";

	@Test
	void testAddVisitToPet() {
		String description = "Routine";

		AddVisitPO addVisitPO = homePO.goToFindOwner().searchFor(lastName).addVisitToFirstPet();

		addVisitPO.fillFieldsAndSubmit(date, description);
	}

	@Test
	void testAddVisitToPetWithEmptyDescription() {
		AddVisitPO addVisitPO = homePO.goToFindOwner().searchFor(lastName).addVisitToFirstPet();

		addVisitPO.setDate(date);
		addVisitPO.submit();
	}

}
