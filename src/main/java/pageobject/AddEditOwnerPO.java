package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AddEditOwnerPO extends PageObject implements WrapsElement {

	@FindBy(how = How.XPATH, xpath = "//input[@id='firstName']")
	private WebElement firstNameInput;

	@FindBy(how = How.XPATH, xpath = "//input[@id='lastName']")
	private WebElement lastNameInput;

	@FindBy(how = How.XPATH, xpath = "//input[@id='address']")
	private WebElement addressInput;

	@FindBy(how = How.XPATH, xpath = "//input[@id='city']")
	private WebElement cityInput;

	@FindBy(how = How.XPATH, xpath = "//input[@id='telephone']")
	private WebElement telephoneInput;

	@FindBy(how = How.XPATH, xpath = "//button[@type='submit']")
	private WebElement submitButton;

	AddEditOwnerPO(WebDriver driver) {
		super(driver);
	}

	public void setFirstName(String firstName) {
		firstNameInput.clear();
		firstNameInput.sendKeys(firstName);
	}

	public void setLastName(String lastName) {
		lastNameInput.clear();
		lastNameInput.sendKeys(lastName);
	}

	public void setAddress(String address) {
		addressInput.clear();
		addressInput.sendKeys(address);
	}

	public void setCity(String city) {
		cityInput.clear();
		cityInput.sendKeys(city);
	}

	public void setTelephone(String telephone) {
		telephoneInput.clear();
		telephoneInput.sendKeys(telephone);
	}

	public void fillFields(String firstName, String lastName, String address, String city, String telephone) {
		setFirstName(firstName);
		setLastName(lastName);
		setAddress(address);
		setCity(city);
		setTelephone(telephone);
	}

	public OwnerPO submit() {
		submitButton.click();
		return new OwnerPO(driver);
	}

	public OwnerPO fillFieldsAndSubmit(String firstName, String lastName, String address, String city, String telephone) {
		fillFields(firstName, lastName, address, city, telephone);
		return submit();
	}

	public String getFirstNameError() {
		return Utils.getTextIfExistsAndDisplayed(driver, "//div[@class='form-group has-error' and label[contains(text(),'First Name')]]//span[@class='help-inline']");
	}

	public String getLastNameError() {
		return Utils.getTextIfExistsAndDisplayed(driver, "//div[@class='form-group has-error' and label[contains(text(),'Last Name')]]//span[@class='help-inline']");
	}

	public String getAddressError() {
		return Utils.getTextIfExistsAndDisplayed(driver, "//div[@class='form-group has-error' and label[contains(text(),'Address')]]//span[@class='help-inline']");
	}

	public String getCityError() {
		return Utils.getTextIfExistsAndDisplayed(driver, "//div[@class='form-group has-error' and label[contains(text(),'City')]]//span[@class='help-inline']");
	}

	public String getTelephoneError() {
		return Utils.getTextIfExistsAndDisplayed(driver, "//div[@class='form-group has-error' and label[contains(text(),'Telephone')]]//span[@class='help-inline']");
	}

	@Override
	public WebElement getWrappedElement() {
		return driver.findElement(By.id("add-owner-form"));
	}
}
