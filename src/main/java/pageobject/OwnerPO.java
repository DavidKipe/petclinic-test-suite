package pageobject;

import org.openqa.selenium.*;

import java.util.List;
import java.util.stream.Collectors;


public class OwnerPO extends PageObject implements WrapsElement {

	OwnerPO(WebDriver driver) {
		super(driver);
	}

	public String getName() {
		return Utils.getTextIfExistsAndDisplayed(driver, "//th[contains(text(),'Name')]/following::td[1]");
	}

	public String getAddress() {
		return Utils.getTextIfExistsAndDisplayed(driver, "//th[contains(text(),'Address')]/following::td[1]");
	}

	public String getCity() {
		return Utils.getTextIfExistsAndDisplayed(driver, "//th[contains(text(),'City')]/following::td[1]");
	}

	public String getTelephone() {
		return Utils.getTextIfExistsAndDisplayed(driver, "//th[contains(text(),'Telephone')]/following::td[1]");
	}

	public AddEditOwnerPO edit() {
		driver.findElement(By.xpath("//a[@class='btn btn-default'][contains(text(),'Edit')]")).click();
		return new AddEditOwnerPO(driver);
	}

	public AddPetPO addPet() {
		driver.findElement(By.xpath("//a[@class='btn btn-default'][contains(text(),'Add')]")).click();
		return new AddPetPO(driver);
	}

	public List<String> getPetNames() {
		List<WebElement> petNames = driver.findElements(By.xpath("//dt[contains(text(),'Name')]/following::dd[1]"));
		return petNames.stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public boolean areThereMoreOwners() {
		try {
			WebElement tableHeaderAddress = driver.findElement(By.xpath("//table[@id='vets']//th[contains(text(),'Address')]"));
			return tableHeaderAddress.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public OwnersListPO convertToOwnersList() {
		return new OwnersListPO(driver);
	}

	@Override
	public WebElement getWrappedElement() {
		return driver.findElement(By.xpath("//div[@class='container xd-container']"));
	}
}
