package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class Utils {

	static String getTextIfExistsAndDisplayed(WebDriver driver, String xpath) {
		try {
			WebElement elem = driver.findElement(By.xpath(xpath));
			if (elem.isDisplayed())
				return elem.getText();
			return "";
		} catch (NoSuchElementException e) {
			return "";
		}
	}

}
