package airquality.selenium;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumJupiter.class)
public class WebpageTest {
    WebDriver browser = new FirefoxDriver();

    @BeforeEach
    void setUp(){
        WebDriver browser;
    }

    @Test
    void testWithOneFirefox() {
        browser.get("http://127.0.0.1:8000/templates/");
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        browser.manage().window().setSize(new Dimension(1116, 697));
        browser.findElement(By.id("cityToBeSearched")).click();
        browser.findElement(By.id("cityToBeSearched")).sendKeys("Aveiro");
        browser.findElement(By.id("searchBtn")).click();
    }

    @AfterEach
    void tearDown() {
        browser.close();
    }
}
