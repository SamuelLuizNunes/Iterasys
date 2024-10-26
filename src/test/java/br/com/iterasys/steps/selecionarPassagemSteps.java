package br.com.iterasys.steps;

import br.com.iterasys.pages.HomePage;
import br.com.iterasys.pages.ReservePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class selecionarPassagemSteps {

    WebDriver driver;
    HomePage homePage;
    ReservePage reservePage;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(60000));
        homePage = new HomePage(driver);
        reservePage = new ReservePage(driver);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Given("que acesso o site Blazedemo")
    public void que_acesso_o_site_blazedemo() {
        driver.get("https://blazedemo.com/");
    }

    @When("soleciono a origem como {string} e destino {string}")
    public void soleciono_a_origem_como_e_destino(String origem, String destino) {
        homePage.selecionarOrigemDestino(origem, destino);
    }

    @And("clico em Procurar Voo")
    public void clico_em_procurar_voo() {
        homePage.clicarBtnProcurarVoo();
    }

    @Then("exibe a frase indicando voo entre {string} e {string}")
    public void exibe_a_frase_indicando_voo_entre_e(String origem, String destino) {
        String cabecalhoListaDeVoosEsperado = "Flights from "+ origem + " to " + destino + ":";

        Assertions.assertEquals(cabecalhoListaDeVoosEsperado, reservePage.getCabecalhoListaDeVoos());
        Assertions.assertEquals("BlazeDemo - reserve", reservePage.lerTituloGuia());
    }
}