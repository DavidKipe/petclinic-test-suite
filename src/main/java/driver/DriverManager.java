package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {

	public enum Browser {
		CHROME, FIREFOX
	}

	public static final int TIMEOUT_SECS = 0;                                   // parameter for waiting timeout in seconds
	public static final String DRIVER_FOLDER_NAME = "/home/david/.webdriver/";  // parameter for the folder of the web drivers

	public static final String CHROME_DRIVER_UNIX = "chromedriver";
	public static final String FIREFOX_DRIVER_UNIX = "geckodriver";

	public static final String CHROME_DRIVER_WIN = "chromedriver.exe";
	public static final String FIREFOX_DRIVER_WIN = "geckodriver.exe";

	static final boolean WINDOWS_OS = System.getProperty("os.name").toLowerCase().contains("windows");

	static void setChromeWebDriver() { // CHROME / CHROMIUM
		if (WINDOWS_OS)
			System.setProperty("webdriver.chrome.driver", DRIVER_FOLDER_NAME + CHROME_DRIVER_WIN);
		else
			System.setProperty("webdriver.chrome.driver", DRIVER_FOLDER_NAME + CHROME_DRIVER_UNIX);
	}

	static void setFirefoxWebDriver() { // FIREFOX
		if (WINDOWS_OS)
			System.setProperty("webdriver.gecko.driver", DRIVER_FOLDER_NAME + FIREFOX_DRIVER_WIN);
		else
			System.setProperty("webdriver.gecko.driver", DRIVER_FOLDER_NAME + FIREFOX_DRIVER_UNIX);
	}

	public static WebDriver getNewDriverInstance(Browser browser) {
		WebDriver driver;

		switch (browser) {
			case CHROME:
				setChromeWebDriver();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless", "--window-size=1280,720", "-js-flags=--expose-gc");
				driver = new ChromeDriver(options);
				break;
			case FIREFOX:
				setFirefoxWebDriver();
				driver = new FirefoxDriver();
				break;
			default:
				throw new IllegalArgumentException();
		}

		driver.manage().timeouts().implicitlyWait(TIMEOUT_SECS, TimeUnit.SECONDS);
		return driver;
	}

}
