package pageobject;

import dataclass.Visit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AddVisitPO extends PageObject {

	@FindBy(how = How.XPATH, xpath = "//input[@id='date']")
	private WebElement dateInput;

	@FindBy(how = How.XPATH, xpath = "//input[@id='description']")
	private WebElement descriptionInput;

	@FindBy(how = How.XPATH, xpath = "//button[@type='submit']")
	private WebElement submitButton;

	AddVisitPO(WebDriver driver) {
		super(driver);
	}

	public void setDate(String date) {
		dateInput.clear();
		dateInput.sendKeys(date);
	}

	public void setDescription(String description) {
		descriptionInput.clear();
		descriptionInput.sendKeys(description);
	}

	public void fillFields(String date, String description) {
		setDate(date);
		setDescription(description);
	}

	public void fillFields(Visit visit) {
		setDate(visit.date);
		setDescription(visit.description);
	}

	public OwnerPO fillFieldsAndSubmit(String date, String description) {
		fillFields(date, description);
		return submit();
	}

	public OwnerPO fillFieldsAndSubmit(Visit visit) {
		fillFields(visit);
		return submit();
	}

	public OwnerPO submit() {
		submitButton.click();
		return new OwnerPO(driver);
	}

	public String getDescriptionError() {
		return Utils.getTextIfExistsAndDisplayed(driver, "//div[@class='form-group has-error' and label[contains(text(),'Description')]]//span[@class='help-inline']");
	}

}
