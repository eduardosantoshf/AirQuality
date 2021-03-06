package airquality.selenium;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumJupiter.class)
class WebpageTest {
    WebDriver browser = new FirefoxDriver();

    @BeforeEach
    void setUp(){
        WebDriver browser;
    }

    @Test
    void searchCityByName() {
        browser.get("http://127.0.0.1:8000/");
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("window.scrollBy(0,750)");
        browser.manage().window().setSize(new Dimension(1116, 697));
        browser.findElement(By.id("cityToBeSearched")).click();
        browser.findElement(By.id("cityToBeSearched")).sendKeys("Aveiro");
        browser.findElement(By.id("searchBtn")).click();
        assertEquals("Name: \"Aveiro\"", browser.findElement(By.id("name1")).getText());
    }


    @Test
    void searchCityByLatAndLon() {
        browser.get("http://127.0.0.1:8000/");

        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("window.scrollBy(0,1000)");

        browser.manage().window().setSize(new Dimension(1116, 698));
        try {
            if (browser.findElements(By.id("searchBtn2")).size() <= 0 && !browser.findElement(By.id("searchBtn2")).isDisplayed()) {
                WebDriverWait wait2 = new WebDriverWait(browser, 120);
                wait2.until(ExpectedConditions.visibilityOf(browser.findElement(By.id("searchBtn2"))));
                browser.findElement(By.id("latitude")).click();
                browser.findElement(By.id("latitude")).sendKeys("40.64427");
                browser.findElement(By.id("longitude")).click();
                browser.findElement(By.id("longitude")).sendKeys("-8.64554");
                browser.findElement(By.id("searchBtn2")).click();
                assertEquals("Name: \"Aveiro\"", browser.findElement(By.id("name2")).getText());
            }
        } catch (StaleElementReferenceException e) {
            return;
        }
    }

    @Test
    void searchCacheDetails() {
        browser.get("http://127.0.0.1:8000/");
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("window.scrollBy(0,1500)");

        try {
            if (browser.findElements(By.id("searchBtn3")).size() <= 0 && !browser.findElement(By.id("searchBtn3")).isDisplayed()) {
                WebDriverWait wait2 = new WebDriverWait(browser, 120);
                wait2.until(ExpectedConditions.visibilityOf(browser.findElement(By.id("searchBtn3"))));
                browser.findElement(By.id("searchBtn3")).click();
                assertEquals("hits: 0", browser.findElement(By.id("hits")).getText());
            }
        } catch (StaleElementReferenceException e) {
            return;
        }
    }

    @AfterEach
    void tearDown() {
        browser.close();
    }
}
