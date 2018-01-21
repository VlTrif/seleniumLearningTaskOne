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

import java.util.ArrayList;
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

        WebElement title2 = driver.findElement(By.xpath("//div[@class='sbrf-rich-outer']/h1"));
        wait.until(ExpectedConditions.visibilityOf(title2));
        assertEquals("Страхование путешественников", title2.getText());

        driver.findElement(By.xpath("//*[contains(@class, 'sbrf-rich-outer')]/p/a[contains(@target, '_blank')]/img")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        ArrayList tabs2 = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window((String) tabs2.get(1));

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//*[contains(@ng-class,'b-form-prog-box')]//*[contains(text(),'Минимальная')]/..")))).click();

        driver.findElement(By.xpath("//*[contains(@class,'b-button-block-center')]//*[contains(@class,'b-continue-btn')]")).click();

        fillField(By.name("insured0_surname"),"Ivanov");
        fillField(By.name("insured0_name"),"Ivan");
        fillField(By.name("insured0_birthDate"),"21.01.1990");
        fillField(By.name("surname"),"Иванов");
        fillField(By.name("name"),"Иван");
        fillField(By.name("middlename"),"Иванович");
        driver.findElement(By.xpath("//*[@name='birthDate']")).click();
        fillField(By.name("birthDate"),"21.01.1990");
        fillField(By.name("passport_series"),"1122");
        fillField(By.name("passport_number"),"222333");
        fillField(By.name("issueDate"),"21.01.1999");
        fillField(By.name("issuePlace"),"Отделением УФМС по г.Москва");

        driver.findElement(By.xpath("//*[contains(text(),'Продолжить')]")).click();

        assertEquals("Заполнены не все обязательные поля",
                driver.findElement(By.xpath("//div[contains(@ng-show,'tryNext && myForm.$invalid')]")).getText());

        assertEquals("Ivanov",
                driver.findElement(By.name("insured0_surname")).getAttribute("value"));

        assertEquals("Ivan",
                driver.findElement(By.name("insured0_name")).getAttribute("value"));

        assertEquals("21.01.1990",
                driver.findElement(By.name("insured0_birthDate")).getAttribute("value"));

        assertEquals("Иванов",
                driver.findElement(By.name("surname")).getAttribute("value"));

        assertEquals("Иван",
                driver.findElement(By.name("name")).getAttribute("value"));

        assertEquals("Иванович",
                driver.findElement(By.name("middlename")).getAttribute("value"));

        assertEquals("21.01.1990",
                driver.findElement(By.name("birthDate")).getAttribute("value"));

        assertEquals("1122",
                driver.findElement(By.name("passport_series")).getAttribute("value"));

        assertEquals("222333",
                driver.findElement(By.name("passport_number")).getAttribute("value"));

        assertEquals("21.01.1999",
                driver.findElement(By.name("issueDate")).getAttribute("value"));

        assertEquals("Отделением УФМС по г.Москва",
                driver.findElement(By.name("issuePlace")).getAttribute("value"));
    }

    private void fillField(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    @After
    public void AfterTask(){
        driver.quit();

    }
}
