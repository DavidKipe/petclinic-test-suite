package assertions;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pageobject.HomePO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionalTests extends BaseTests {

	private static HomePO homePO;

	@BeforeAll
	static void createAndStartService() {
        homePO = new HomePO(getDriverInitialized());
	}

	@Test
	void testWelcomeMessage() {
		assertEquals("Welcome", homePO.getWelcomeMessage());
	}

	@AfterAll
	static void closeDriver() {
		homePO.closeDriver();
	}

}
