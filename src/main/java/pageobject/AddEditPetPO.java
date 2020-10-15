package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AddEditPetPO extends NavBarPO {

	@FindBy(how = How.XPATH, xpath = "//input[@id='name']")
	private WebElement nameInput;

	@FindBy(how = How.XPATH, xpath = "//input[@id='birthDate']")
	private WebElement birthDateInput;

	@FindBy(how = How.XPATH, xpath = "//button[@type='submit']")
	private WebElement submitButton;

	AddEditPetPO(WebDriver driver) {
		super(driver);
	}

	public void setName(String name) {
		nameInput.clear();
		nameInput.sendKeys(name);
	}

	public void setBirthDate(String birthDate) {
		birthDateInput.clear();
		birthDateInput.sendKeys(birthDate);
	}

	public void fillFields(String name, String birthDate) {
		setName(name);
		setBirthDate(birthDate);
	}

	public OwnerPO fillFieldsAndSubmit(String name, String birthDate) {
		fillFields(name, birthDate);
		return submit();
	}

	public void clearFields() {
		fillFields("", "");
	}

	public OwnerPO submit() {
		submitButton.click();
		return new OwnerPO(driver);
	}

	public String getNameError() {
		return Utils.getTextIfExistsAndDisplayed(driver, "//div[@class='form-group has-error' and label[contains(text(),'Name')]]//span[@class='help-inline']");
	}

	public String getBirthDateError() {
		return Utils.getTextIfExistsAndDisplayed(driver, "//div[@class='form-group has-error' and label[contains(text(),'Birth Date')]]//span[@class='help-inline']");
	}

}
