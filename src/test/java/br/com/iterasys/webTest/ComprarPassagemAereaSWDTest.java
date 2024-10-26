package br.com.iterasys.webTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ComprarPassagemAereaSWDTest {
    WebDriver driver;

    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void comprarPassagemAerea(){
        driver.get("https://blazedemo.com/");
        driver.findElement(By.name("fromPort")).click();
        {
            WebElement list = driver.findElement(By.name("fromPort"));
            list.findElement(By.xpath("//option[@value='San Diego']")).click();
        }

        driver.findElement(By.name("toPort")).click();
        {
            WebElement list = driver.findElement(By.name("fromPort"));
            list.findElement(By.xpath("//option[@value='Dublin']")).click();
        }

        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
        String tituloEsperado = "Flights from San Diego to Dublin:";
        String tituloAtual = driver.findElement(By.cssSelector("h3")).getText();

        Assertions.assertEquals(tituloEsperado, tituloAtual);
    }

}
