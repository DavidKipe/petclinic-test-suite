package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NavBarPO extends PageObject {

	@FindBy(how = How.XPATH, xpath = "//a[@title='find owners']")
	private WebElement findOwnerBtn;

	public NavBarPO(WebDriver driver) {
		super(driver);
		driver.get("http://localhost:8080");
	}

	public FindOwnerPO goToFindOwner() {
		findOwnerBtn.click();
		return new FindOwnerPO(driver);
	}

}
