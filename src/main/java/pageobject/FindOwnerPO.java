package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FindOwnerPO extends NavBarPO {

	@FindBy(how = How.XPATH, xpath = "//input[@id='lastName']")
	private WebElement lastNameInput;

	@FindBy(how = How.XPATH, xpath = "//button[@type='submit']")
	private WebElement submitButton;

	@FindBy(how = How.XPATH, xpath = "//a[contains(text(), 'Add')]")
	private WebElement addOwnerButton;

	FindOwnerPO(WebDriver driver) {
		super(driver);
	}

	public OwnerPO searchFor(String lastName) {
		lastNameInput.clear();
		lastNameInput.sendKeys(lastName);
		submitButton.submit();
		return new OwnerPO(driver);
	}

	public OwnersListPO searchForMultiple(String lastName) {
		lastNameInput.clear();
		lastNameInput.sendKeys(lastName);
		submitButton.submit();
		return new OwnersListPO(driver);
	}

	public OwnersListPO searchAll() {
		return searchForMultiple("");
	}

	public String getNotFoundMessage() {
		try {
			WebElement errorLabel = driver.findElement(By.xpath("//span[@class='help-inline']/div/p"));
			if (errorLabel.isDisplayed())
				return errorLabel.getText();
			return "";
		} catch (NoSuchElementException e) {
			return "";
		}
	}

	public AddEditOwnerPO goToAddOwner() {
		addOwnerButton.click();
		return new AddEditOwnerPO(driver);
	}

}
