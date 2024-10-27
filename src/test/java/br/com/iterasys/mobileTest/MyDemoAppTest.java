package br.com.iterasys.mobileTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.interactions.Sequence;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MyDemoAppTest {

    private AndroidDriver driver;
    Config config = new Config();

    @BeforeEach
    public void setUp() throws MalformedURLException {
        var options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setPlatformVersion("9.0")
                .setDeviceName("Samsung Galaxy S9 FHD GoogleAPI Emulator")
                .setOrientation(ScreenOrientation.PORTRAIT)
                .setApp("storage:filename=mda-2.1.0-24.apk")
                .setAppPackage("com.saucelabs.mydemoapp.android")
                .setAppActivity("com.saucelabs.mydemoapp.android.view.activities.SplashActivity")
                .setNewCommandTimeout(Duration.ofSeconds(3600))
                .setAutoGrantPermissions(true)
                .amend("sauce:options", Map.of("name", "FTS137 - MyDemoApp"));

        driver = new AndroidDriver(getUrl(), options);

    }

    private URL getUrl() throws MalformedURLException {
        String user = config.getSauceLabsUser();
        String key = config.getSauceLabsKey();
        String baseUrl = config.getSauceLabsURL();

        // Concatena o usuário e a chave na URL base
        return new URL("https://" + user + ":" + key + "@" + baseUrl);
    }

    @Test
    public void selecionarProdutoTest() {
        WebElement produtoSelecionado = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView[@content-desc=\"Product Image\"])[1]"));
        produtoSelecionado.click();

        WebElement nomeProduto = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV"));

        WebElement precoProduto = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/priceTV"));

        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(509, 1702);
        var end = new Point (529, 847);
        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(swipe));

        var btnAdicionarNoCarrinho = driver.findElement(AppiumBy.accessibilityId("Tap to add product to cart"));
        btnAdicionarNoCarrinho.click();



        assertEquals("Sauce Labs Backpack", nomeProduto.getText());
        assertEquals("$ 29.99", precoProduto.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}