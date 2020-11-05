package assertions;

import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pageobject.HomePO;

class BaseTests {

	private static final DriverManager.Browser BROWSER = DriverManager.Browser.CHROME;

	private static final String APP_HOME_URL = "http://localhost:8080";

	private WebDriver webDriver;

	protected HomePO homePO;

	public static WebDriver getDriverInitialized() {
		WebDriver driver = DriverManager.getNewDriverInstance(BROWSER);
		driver.get(APP_HOME_URL);
		return driver;
	}

//	public void callGarbageCollectorForChrome() {
//		Double usedJSHeapSize = (Double) ((JavascriptExecutor) webDriver).executeScript("return window.performance.memory.usedJSHeapSize/1024/1024");
//		System.out.println("Calling gc. Memory Usage: " + usedJSHeapSize + " MiB");
//		((JavascriptExecutor) webDriver).executeScript("window.gc()");
////		try {
////			TimeUnit.SECONDS.sleep(2);
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
//		usedJSHeapSize = (Double) ((JavascriptExecutor) webDriver).executeScript("return window.performance.memory.usedJSHeapSize/1024/1024");
//		System.out.println("Memory Usage after gc: " + usedJSHeapSize + " MiB");
//	}

	@BeforeEach
	void createAndStartService() {
		webDriver = getDriverInitialized();
        homePO = new HomePO(webDriver);
	}

	@AfterEach
	void closeDriver() {
		//callGarbageCollectorForChrome();
		homePO.closeDriver();
	}


}
