import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

/**
 * Created by azvonov on 016 16.12.15.
 */
public class FinanceTests {

    WebDriver driver;

    @BeforeSuite
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://finance.i.ua/");
    }

    @Test
    public void sellCurrency() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://finance.i.ua/");
        String expectedResult = "4549,59";
        driver.findElement(By.id("fn_s1")).sendKeys("5000");
        new Select(driver.findElement(By.id("fn_bank"))).selectByValue("1");
        new Select(driver.findElement(By.id("fn_c1"))).selectByValue("840");
        String actualResult = driver.findElement(By.id("fn_o1_978")).getAttribute("value");
        assertEquals(actualResult, expectedResult);
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}
