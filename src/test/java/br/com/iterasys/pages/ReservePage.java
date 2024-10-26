package br.com.iterasys.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReservePage {
    WebDriver driver;

    public ReservePage(WebDriver driver){
        this.driver = driver;
    }

    public By byCabecalhoListaDeVoos = By.cssSelector("div.container h3");


    public String lerTituloGuia(){
        return driver.getTitle();
    }

    public String getCabecalhoListaDeVoos(){
            return driver.findElement(byCabecalhoListaDeVoos).getText();
    }
}
