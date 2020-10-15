package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NavBarPO extends PageObject {

	@FindBy(how = How.XPATH, xpath = "//a[@title='home page']")
	private WebElement homeBtn;

	@FindBy(how = How.XPATH, xpath = "//a[@title='find owners']")
	private WebElement findOwnerBtn;

	@FindBy(how = How.XPATH, xpath = "//a[@title='veterinarians']")
	private WebElement veterinariansBtn;

	public NavBarPO(WebDriver driver) {
		super(driver);
	}

	public HomePO goHome() {
		homeBtn.click();
		return new HomePO(driver);
	}

	public FindOwnerPO goToFindOwner() {
		findOwnerBtn.click();
		return new FindOwnerPO(driver);
	}

	public VetsPO goToVeterinarians() {
		veterinariansBtn.click();
		return new VetsPO(driver);
	}

}
