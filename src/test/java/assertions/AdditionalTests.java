package assertions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobject.VetsPO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionalTests extends BaseTests {

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

}
