package ru.lanit.atschool.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.lanit.atschool.Intefaces.NameOfElement;
import ru.lanit.atschool.helpers.ConfigReader;
import ru.lanit.atschool.webdriver.WebDriverManager;


import java.io.IOException;
import java.util.List;
public class MainPage extends BasePage {

    /**
     * Метод открывает браузер на заданной странице
     */
    public void openPage() {
        String url = null;
        try {
            url = ConfigReader.getStringSystemProperty("url");
        } catch (IOException e) {
            logger.error("Невозможно получить адрес сайта");
        }
        driver.get(url);
        logger.info("Выполнен вход на страницу: " + url);
    }

    /**
     * Вкладка "Категории".
     */
    @NameOfElement("вкладку_Категории")
    @FindBy(linkText = "Категории")
    public WebElement goToCategories;


    /**
     * Вкладка "Пользователи".
     */
    @FindBy(linkText = "Пользователи")
    public WebElement goToUsers;

    /**
     *  Кнопка поиска пользователей
     */
    @FindBy(xpath = "//a[@class = \"navbar-icon\"]/i[text() = \"search\"]")
    public WebElement searchButton;

    /**
     * Строка для поиска пользователей
     */
    @FindBy(xpath = "//input[@class = \"form-control\"]")
    public WebElement searchArea;

    /**
     *  Кнопка для просмотра всех пользователей похожих по запросу
     */
    @FindBy(xpath = "//li[@class=\"dropdown-search-footer\"]")
    public WebElement allUsersHaving;

    /**
     *  Ник пользователя, которого ищем
     */
    @FindBy(xpath = "//a[text()=\"Eduard\"]")
    public WebElement userNameSeach;

    /**
     *  Кнопка "Войти"
     */
    @FindBy(xpath = "//button[text() = \"Войти\"]")
    public WebElement authetificationButton;

    /**
     *  Название формы авторизации
     */
    @FindBy(xpath = "//h4[text() = \"Войти\"]")
    public WebElement loginForm;

    /**
     * Логин пользователя
     */
    @FindBy(id = "id_username")
    public WebElement loginFormName;

    /**
     *  Пароль пользователя
     */
    @FindBy(id = "id_password")
    public WebElement loginFormPassword;

    /**
     * Подтверждение авторизации
     */
    @FindBy(xpath = "//button[@type = \"submit\"][text() = \"Войти\"]")
    public WebElement submitButton;

    /**
     * Кнопка открывает меню пользователя
     */
    @FindBy(xpath = "//a/img[@class=\"user-avatar\"]")
    public WebElement userMenu;

    /**
     * Кнопка разлогинивает пользователя
     */
    @FindBy(xpath = "//button[text()=\"Выход\"]")
    public WebElement userExit;

    /**
     * Кнопка выхода из формы авторизации
     */
    @FindBy(xpath = "//*[@id=\"modal-mount\"]/div/div/div/button")
    public WebElement exitAuthorization;


}

