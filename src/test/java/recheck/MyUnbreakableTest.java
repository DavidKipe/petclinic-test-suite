package recheck;

import de.retest.web.selenium.RecheckDriver;
import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.nio.file.Paths;

@Disabled
public class MyUnbreakableTest {

    RecheckDriver driver;

    @BeforeEach
    public void setup() {
        driver = new RecheckDriver((RemoteWebDriver) DriverManager.getNewDriverInstance(DriverManager.Browser.CHROME));
    }

    @Test
    public void check_order() throws Exception {
        driver.startTest();
        String url = Paths.get( "src/test/resources/formPage.html" ).toUri().toURL().toString();
        driver.get(url);

        driver.findElement(By.id("email")).sendKeys("Max");
        driver.findElement(By.id("age")).sendKeys("16");
        driver.findElement(By.name("login")).submit();

        driver.capTest();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
