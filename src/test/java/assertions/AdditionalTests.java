package assertions;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pageobject.HomePO;
import pageobject.VetsPO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionalTests extends BaseTests {

	private static HomePO homePO;

	@BeforeAll
	static void createAndStartService() {
        homePO = new HomePO(getDriverInitialized());
	}

	@Test
	void testWelcomeMessage() {
		homePO.goHome();
		assertEquals("Welcome", homePO.getWelcomeMessage());
	}

	@Test
	void testAtLeastOneVet() {
		VetsPO vetsPO = homePO.goToVeterinarians();
		Assertions.assertFalse(vetsPO.getFirstVetName().isEmpty());
	}

	@AfterAll
	static void closeDriver() {
		homePO.closeDriver();
	}

}
