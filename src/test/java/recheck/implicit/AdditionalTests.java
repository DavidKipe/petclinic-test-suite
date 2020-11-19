package recheck.implicit;

import de.retest.recheck.junit.jupiter.RecheckExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(RecheckExtension.class)
public class AdditionalTests extends BaseTests {

	@Test
	void testWelcomeMessage() {
		homePO.goHome();
	}

	@Test
	void testAtLeastOneVet() {
		homePO.goToVeterinarians();
	}

}
