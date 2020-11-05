package recheck.explicit;

import org.junit.jupiter.api.Test;

public class AdditionalTests extends BaseTests {

	@Test
	void testWelcomeMessage() {
		startTest("welcomeMessage");
		homePO.goHome();
		endTest("welcomeMessage");
	}

	@Test
	void testAtLeastOneVet() {
		startTest("atLeastOneVet");
		homePO.goToVeterinarians();
		endTest("atLeastOneVet");
	}

}
