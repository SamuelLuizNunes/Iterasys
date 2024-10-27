package br.com.iterasys.mobileTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private Properties properties = new Properties();

    public Config() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("saucelabs.properties")) {
            if (input == null) {
                throw new IOException("Unable to find saucelabs.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getSauceLabsURL() {
        // Monta a URL completa com o usuário e a chave
        return properties.getProperty("saucelabs.url");
    }

    public String getSauceLabsUser() {
        return properties.getProperty("saucelabs.user");
    }

    public String getSauceLabsKey() {
        return properties.getProperty("saucelabs.key");
    }
}
