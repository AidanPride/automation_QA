import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import testData.DataProviderTrello;

import java.util.concurrent.TimeUnit;

/**
 * Created by azvonov on 016 16.12.15.
 */
public class TrelloTests {

    WebDriver driver;

    @BeforeSuite
    public void setUpSuite() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test(groups = "windows",
            dataProviderClass = DataProviderTrello.class,
            dataProvider = "firstTestProvider")
    public void firstTests(String login, String password) {
        String expectedResult;

        driver.get("https://trello.com/login");
        driver.findElement(By.id("user")).sendKeys(login);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login")).click();
        driver.findElement(By.className("board-list-item-name")).click();
        WebElement source = driver.findElement(By.xpath(
                "//*[@class='list-card-title js-card-name' and contains(text(),'... any kind of hyperlink ...')]"));
        expectedResult = source.getText();
        WebElement target = driver.findElement(By.xpath("//*[@id='board']/div[2]/div/div[2]/div[1]/div[3]"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();

        WebElement result = driver.findElement(By.xpath("//*[@id='board']/div[2]/div/div[2]/div[1]/div[3]"));
        String actualResult = result.getText();

        Assert.assertEquals(actualResult, expectedResult, "Drag and Drop failed");
        actions.dragAndDrop(result, driver.findElement(
                By.xpath("//*[@id='board']/div[1]/div/div[2]/div[1]/div[3]/a"))).perform();

        driver.findElement(By.xpath(".//*[@id='header']/div[4]/a[2]/span[1]/span[1]")).click();
        driver.findElement(By.cssSelector(".js-logout")).click();
    }

    @Test(groups = "Linux")
    public void secondTest(){
    }

    @AfterTest
    public void tearDownTest() throws Exception {

    }
}
