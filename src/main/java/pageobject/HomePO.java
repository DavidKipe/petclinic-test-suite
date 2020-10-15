package pageobject;

import org.openqa.selenium.WebDriver;

public class HomePO extends NavBarPO {

	public HomePO(WebDriver driver) {
		super(driver);
	}

	public String getWelcomeMessage() {
		return Utils.getTextIfExistsAndDisplayed(driver, "//h2");
	}

}
