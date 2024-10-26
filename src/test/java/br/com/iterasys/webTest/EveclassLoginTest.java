package br.com.iterasys.webTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EveclassLoginTest {
    WebDriver driver;
    WebDriverWait wait;


    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void LoginTest(){
        driver.get("https://testando.eveclass.com");
        driver.findElement(By.id("support-action")).click();
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".auth-header h1")));

        driver.findElement(By.cssSelector("input[type=\"email\"]")).sendKeys("samuel.nunes@gmail.com");
        driver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys("Teste123");

        driver.findElement(By.cssSelector(".button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".user-avatar")));
        driver.findElement(By.cssSelector(".user-avatar")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div[2]/div/div[2]//p[1]")));
        String name = driver.findElement(By.xpath("//div[2]/div/div[2]/div/div[2]//p[1]")).getText();

        assertEquals("Samuel Nunes", name);

    }
}
