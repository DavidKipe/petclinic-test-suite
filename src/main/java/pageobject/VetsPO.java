package pageobject;

import org.openqa.selenium.WebDriver;

public class VetsPO extends NavBarPO {

	public VetsPO(WebDriver driver) {
		super(driver);
	}

	public String getFirstVetName() {
		return Utils.getTextIfExistsAndDisplayed(driver, "//tbody/tr[1]/td[1]");
	}

}
