package task1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TaskOneClass {

    WebDriver driver;
    String baseUrl;

    @Before
    public void BeforeTask(){
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://www.sberbank.ru/ru/person";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @Test
    public void TaskOne() {
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
        driver.findElement(By.xpath("//ul[contains(@aria-labelledby,'alt-menu-mid__header4')]//a[contains(@aria-label,'Застраховать себя ')]")).click();
        WebElement title = driver.findElement(By.xpath("//ul[contains(@aria-labelledby,'alt-menu-mid__header4')]//*[contains(text(),'Страхование путешественников')]"));
        wait.until(ExpectedConditions.visibilityOf(title));
        driver.findElement(By.xpath("//ul[contains(@aria-labelledby,'alt-menu-mid__header4')]//*[contains(text(),'Страхование путешественников')]")).click();
        //Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
        //assertEquals("Страхование путешественников",
        //driver.findElement(By.xpath("//div[@class='sbrf-rich-outer']/h1")).getText());

        /*WebElement title = driver.findElement(By.xpath("//div[@class='sbrf-rich-outer']/h1"));
        wait.until(ExpectedConditions.visibilityOf(title));
        assertEquals("Страхование путешественников", title.getText());*/

        driver.findElement(By.xpath("//*[contains(@class, 'sbrf-rich-outer')]/p/a[contains(@target, '_blank')]/img")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.navigate().forward();

        //driver.findElement(By.xpath("//*[contains(@class,'b-form-prog-box')]//*[contains(text(),'Минимальная')]")).click();
        //driver.findElement(By.xpath("//div[contains(@class,'b-form-prog-box')]//*[contains(text(),'Необходимый минимум для оплаты медицинской помощи за границей')]")).click();
        WebElement selectElem = driver.findElement(By.xpath("//div[contains(@class,'b-form-prog-box')]"));
                //tagName("select"));
        Select select = new Select(selectElem);
        //select.selectByVisibleText("Необходимый минимум для оплаты медицинской помощи за границей");
        select.selectByValue("Минимальная");

    }
    @After
    public void AfterTask(){
        //driver.quit();

    }
}
