package pageobject;

import dataclass.Pet;
import dataclass.Visit;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class OwnerPO extends NavBarPO implements WrapsElement {

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

	public AddEditPetPO addPet() {
		driver.findElement(By.xpath("//a[@class='btn btn-default'][contains(text(),'Add')]")).click();
		return new AddEditPetPO(driver);
	}

	public AddEditPetPO editFirstPet() {
		driver.findElement(By.xpath("//tbody/tr/td[1]/a[1]")).click();
		return new AddEditPetPO(driver);
	}

	public AddVisitPO addVisitToFirstPet() {
		driver.findElement(By.xpath("//tbody/tr/td[2]/a[1]")).click();
		return new AddVisitPO(driver);
	}

	public List<Pet> getPets() {
		final String petValueXPath = "//dt[contains(text(),'%s')]/following::dd[1]";
		List<String> petNames = driver.findElements(By.xpath(String.format(petValueXPath, "Name"))).stream().map(WebElement::getText).collect(Collectors.toList());
		List<String> petBirthDates = driver.findElements(By.xpath(String.format(petValueXPath, "Birth Date"))).stream().map(WebElement::getText).collect(Collectors.toList());
		List<String> petTypes = driver.findElements(By.xpath(String.format(petValueXPath, "Type"))).stream().map(WebElement::getText).collect(Collectors.toList());

		List<Pet> pets = new ArrayList<>(petNames.size());
		for (int i = 0; i < petNames.size(); i++)
			pets.add(new Pet(petNames.get(i), petBirthDates.get(i), petTypes.get(i)));
		return pets;
	}

	public List<Visit> getVisitFirstPet() {
		final String dates = "//body[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/table[1]/tbody[1]/tr[position() < last()]/td[1]";
		final String descriptions = "//body[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/table[1]/tbody[1]/tr[position() < last()]/td[2]";

		List<String> visitDates = driver.findElements(By.xpath(dates)).stream().map(WebElement::getText).collect(Collectors.toList());
		List<String> visitDescriptions = driver.findElements(By.xpath(descriptions)).stream().map(WebElement::getText).collect(Collectors.toList());

		List<Visit> visits = new ArrayList<>(visitDates.size());
		for (int i = 0; i < visitDates.size(); i++)
			visits.add(new Visit(visitDates.get(i), visitDescriptions.get(i)));
		return visits;
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
