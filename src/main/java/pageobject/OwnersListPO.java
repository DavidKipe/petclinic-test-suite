package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OwnersListPO extends NavBarPO {

	OwnersListPO(WebDriver driver) {
		super(driver);
	}

	public OwnerPO clickOn(String fullName) {
		WebElement ownerLink = driver.findElement(By.xpath("//a[text()='" + fullName + "']"));
		ownerLink.click();
		return new OwnerPO(driver);
	}

	public boolean isThereOnlyOneOwner() {
		try {
			WebElement pageH2 = driver.findElement(By.xpath("//h2[contains(text(),'Owner Information')]"));
			return pageH2.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public OwnerPO convertToOwner() {
		return new OwnerPO(driver);
	}

}
