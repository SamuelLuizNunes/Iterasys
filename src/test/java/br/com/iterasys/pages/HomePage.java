package br.com.iterasys.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public By byLocal(String local){
        return By.xpath("//option[. = '" + local + "']");
    }

    public By byBtnFindFlights = By.cssSelector("input[value]");

    public void selecionarOrigemDestino(String byOrigem, String byDestino){
        this.driver.findElement(byLocal(byOrigem)).click();
        this.driver.findElement(byLocal(byDestino)).click();
    }

    public void clicarBtnProcurarVoo(){
        this.driver.findElement(byBtnFindFlights).click();
    }

}


