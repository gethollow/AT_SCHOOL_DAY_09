package ru.lanit.atschool.webdriver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;
import ru.lanit.atschool.helpers.ConfigReader;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {

    public static WebDriver driver;
    protected static final Logger logger = Logger.getLogger(WebDriverManager.class);
    private WebDriverManager() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            try {
                System.setProperty("webdriver.chrome.driver", ConfigReader.getStringSystemProperty("chrome.driver.path"));
                ChromeOptions option = new ChromeOptions();
                option.addArguments(ConfigReader.getStringSystemProperty("size-resolution"));
                driver = new ChromeDriver(option);
            } catch(UnreachableBrowserException e) {
                logger.error("Невозможно инциализировать драйвер!", e);
            } catch (IOException e) {
                logger.error("Не найден путь до драйвера", e);
            }
            int count = 0;
            try {
                count = Integer.parseInt(ConfigReader.getStringSystemProperty("implicit.wait"));
            } catch (IOException e) {
                logger.error("Не найдено неявное ожидание", e);
            }
            driver.manage().timeouts().implicitlyWait(count, TimeUnit.SECONDS);
        }
        return driver;
    }


    public static void quit() {
        try {
            driver.quit();
            driver = null;
        } catch (UnreachableBrowserException e) {
            logger.error("Невозможно закрыть браузер!");
        }
    }
}
