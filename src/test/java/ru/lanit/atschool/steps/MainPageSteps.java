package ru.lanit.atschool.steps;

import io.cucumber.java.*;
import io.cucumber.java.ru.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.lanit.atschool.pages.MainPage;
import ru.lanit.atschool.webdriver.WebDriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class MainPageSteps {
    private WebDriver driver = WebDriverManager.getDriver();
    private String current_url = driver.getCurrentUrl();
    private MainPage page = new MainPage();
    private WebDriverWait wait;

    @Before
    public void beforeScenario() {
        wait = new WebDriverWait(driver, 7);
    }

    @After
    public void afterScenario() {
        WebDriverManager.quit();
    }

    @Пусть("открыт браузер и введен адрес")
    public void openedBrowserAndEnteredUrl() {
        page.openPage();
    }

    @И("пользователь кликнул на {string}")
    public void openCategories(String categories) {
        try {
            wait.until(elementToBeClickable(page.get(categories)));
            page.get(categories).click();
        } catch (Exception e) {
            Assert.fail("Вкладка Категории недоступна! " + e.getMessage());
        }
    }

    @И("пользоватль перешел на вкладку Пользователь")
    public void openUsers() {
        try {
            wait.until(elementToBeClickable(page.goToUsers));
            page.goToUsers.click();
        } catch (Exception e) {
            Assert.fail("Вкладка Пользователи недоступна! " + e.getMessage());
        }
    }


    @И("кликнет на поиск пользователей")
    public void searchUserButton() {
        try {
            wait.until(elementToBeClickable(page.searchButton));
            page.searchButton.click();
        } catch (Exception e) {
            Assert.fail("Не найден элемент для поиска пользователей" + e.getMessage());
        }
    }

    @Тогда("введет в форму поиска пользователей (.*)$")
    public void searchUserForm(String name) {
        try {
            wait.until(elementToBeClickable(page.searchArea));
            page.searchArea.click();
            page.searchArea.sendKeys(name);
        } catch (Exception e) {
            Assert.fail("Не найдена форма для заполнения" + e.getMessage());
        }
    }

    @И("кликнет на \"Показать полные результаты \"Пользователи\"\"")
    public void openAllUsers() {
        try {
            wait.until(elementToBeClickable(page.allUsersHaving));
            page.allUsersHaving.click();
        } catch (Exception e) {
            Assert.fail("Не найдена кнопка для открытия всех пользователей" + e.getMessage());
        }
    }

    @То("кликнет на пользователя под ником Eduard")
    public void openUserProfile() {
        try {
            wait.until(elementToBeClickable(page.userNameSeach));
            page.userNameSeach.click();
        } catch (Exception e) {
            Assert.fail("Не найдет пользователь с ником Eduard" + e.getMessage());
        }
    }


//
//
//
//
//

    @И("пользователь открыл форму авторизации")
    public void userEnters() {
        try {
            wait.until(elementToBeClickable(page.authetificationButton));
            page.authetificationButton.click();
        } catch (Exception e) {
            Assert.fail("Не обнаружена форма авторизации" + e.getMessage());
        }
    }

    @Тогда("пользователь вводит логин (.*)$")
    public void userEnteredCredentialLogin(String username) {
        try {
            wait.until(elementToBeClickable(page.loginFormName));
            page.loginFormName.click();
            page.loginFormName.sendKeys(username);
        } catch (Exception e) {
            Assert.fail("Не обнаружено поле ввода логина" + e.getMessage());
        }
    }

    @И("пользователь вводит пароль (.*)$")
    public void userEnteredCredentialPassword(String password) {
        try {
            wait.until(elementToBeClickable(page.loginFormPassword));
            page.loginFormPassword.click();
            page.loginFormPassword.sendKeys(password);
        } catch (Exception e) {
            Assert.fail("Не обнаружено поле ввода пароль" + e.getMessage());
        }
    }

    @И("пользователь подтвержает авторизацию")
    public void userSubmitAuthorization() {
        try {
            wait.until(elementToBeClickable(page.submitButton));
            page.submitButton.click();
        } catch (Exception e) {
            Assert.fail("Не обнарежуна кнопка подтвержения авторизации" + e.getMessage());
        }
    }

    @Если("пользователь авторизован")
    public void userSucces() {
        if (current_url.equals("https://dev.n7lanit.ru/?ref=login")) {
            Assert.assertTrue(driver.findElement(By.xpath("//a[@class=\"dropdown-toggle\"][@href=\"/u/eduard/7/\"]")).isDisplayed(), "Авторизация прошла успешно");
            page.userMenu.click();
            wait.until(elementToBeClickable(page.userExit));
            page.userExit.click();
            driver.switchTo().alert().accept();
        }
    }

    @Если("пользователь ввел неверные данные")
    public void userFailure() {
        if (current_url.equals("https://dev.n7lanit.ru")) {
            Assert.assertTrue(driver.findElement(By.xpath("//button[@type=\"button\"][text()=\"Войти\"]")).isDisplayed(), "Данные введены не верно");
            page.exitAuthorization.click();
        }
    }

    /**
     * Метод для скриншотов после каждого шага
     */
    @AfterStep
    public void takeScreenshot() throws IOException {
        File screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        InputStream targetStream = new FileInputStream(screenshotBase64);
        Allure.addAttachment("Screenshot", "image/png", targetStream, "png");
    }
}